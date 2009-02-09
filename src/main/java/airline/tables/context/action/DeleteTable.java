package airline.tables.context.action;

import airline.servlet.enumeration.Action;
import airline.servlet.enumeration.MessageAction;
import airline.servlet.enumeration.MessageError;
import airline.tables.context.TableContextHandler;
import airline.model.Table;
import airline.criteria.model.DropTableRequest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Feb 9, 2009
 * Time: 1:55:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class DeleteTable extends TableContextHandler {
    public DeleteTable() {
        init(Action.DELETE);
    }

    public RequestDispatcher get(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
        return servletContext.getRequestDispatcher("/admin/TableDelete.jsp");
    }

    public RequestDispatcher post(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
        Table table = (Table) request.getAttribute("url.table");
        try {
            airlineDAO.executeRequest(new DropTableRequest(table));
            request.setAttribute("action.done", MessageAction.TABLE_DELETED);
            return servletContext.getRequestDispatcher("/message.jsp");
        } catch (SQLException e) {
            request.setAttribute("error.type", MessageError.SQL_ERROR);
            request.setAttribute("error.exception", e);
            return servletContext.getRequestDispatcher("/error.jsp");
        }
    }
}
