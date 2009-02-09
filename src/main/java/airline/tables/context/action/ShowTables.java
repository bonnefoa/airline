package airline.tables.context.action;

import airline.servlet.enumeration.Action;
import airline.tables.context.TablesContextHandler;
import airline.model.Table;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Feb 9, 2009
 * Time: 1:55:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class ShowTables extends TablesContextHandler {
    public ShowTables() {
        setAction(Action.SHOW);
    }

    public RequestDispatcher get(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Table> tables = airlineDAO.getTables();
        request.setAttribute("tables", tables);
        return servletContext.getRequestDispatcher("/admin/TablesShow.jsp");
    }

    public RequestDispatcher post(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
