package airline.tables.context.action;

import airline.servlet.enumeration.Action;
import airline.servlet.enumeration.MessageAction;
import airline.servlet.enumeration.MessageError;
import airline.tables.context.FieldContextHandler;
import airline.criteria.model.DeleteFieldRequest;
import airline.model.Table;
import airline.model.TablesColumns;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Feb 9, 2009
 * Time: 1:55:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class DeleteField extends FieldContextHandler {
    public DeleteField() {
        init(Action.DELETE);
    }

    public RequestDispatcher get(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
        return servletContext.getRequestDispatcher("/admin/FieldDelete.jsp");
    }

    public RequestDispatcher post(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
        Table table = (Table) request.getAttribute("url.table");
        TablesColumns column = (TablesColumns) request.getAttribute("url.field");
        List<TablesColumns> columns  = new ArrayList<TablesColumns>();
        columns.add(column);
        DeleteFieldRequest deleteField = new DeleteFieldRequest(table, columns);
        try {
            airlineDAO.executeRequest(deleteField);
            request.setAttribute("action.done", MessageAction.FIELD_DELETED);
            return servletContext.getRequestDispatcher("/message.jsp");
        } catch (SQLException e) {
            request.setAttribute("error.type", MessageError.SQL_ERROR);
            request.setAttribute("error.exception", e);
            return servletContext.getRequestDispatcher("/error.jsp");
        }
    }
}
