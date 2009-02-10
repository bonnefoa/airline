package airline.tables.context.action;

import airline.servlet.enumeration.Action;
import airline.servlet.enumeration.MessageAction;
import airline.servlet.enumeration.MessageError;
import airline.tables.context.RowContextHandler;
import airline.model.TableRow;
import airline.model.Table;
import airline.model.TablesColumns;
import airline.criteria.model.DeleteRequest;
import airline.criteria.Restriction;
import airline.criteria.enumeration.SqlConstraints;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Feb 9, 2009
 * Time: 1:55:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class DeleteRow extends RowContextHandler {
    public DeleteRow() {
        init(Action.DELETE);
    }

    public RequestDispatcher get(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
        return servletContext.getRequestDispatcher("/admin/RowDelete.jsp");
    }

    public RequestDispatcher post(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
        TableRow row = (TableRow) request.getAttribute("url.row");
        Table table = (Table) request.getAttribute("url.table");

        DeleteRequest deleteRequest = new DeleteRequest(table);
        for (Map.Entry<TablesColumns, String> entry : row.entrySet()) {
            Restriction restriction = new Restriction();
            restriction.constraint(entry.getKey(), entry.getValue(), SqlConstraints.EQ);
            deleteRequest.addRestriction(restriction);
        }
        try {
            airlineDAO.executeRequest(deleteRequest);
            request.setAttribute("action.done", MessageAction.ROW_DELETED);
            return servletContext.getRequestDispatcher("/message.jsp");
        } catch (SQLException e) {
            request.setAttribute("error.type", MessageError.SQL_ERROR);
            request.setAttribute("error.exception", e);
            return servletContext.getRequestDispatcher("/error.jsp");
        }
    }
}
