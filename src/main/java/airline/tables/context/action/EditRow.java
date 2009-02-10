package airline.tables.context.action;

import airline.servlet.enumeration.Action;
import airline.tables.context.RowContextHandler;
import airline.model.Table;
import airline.model.TableColumn;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Feb 9, 2009
 * Time: 1:55:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class EditRow extends RowContextHandler {
    public EditRow() {
        init(Action.EDIT);
    }

    public RequestDispatcher get(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
        Table table = (Table) request.getAttribute("url.table");
        List<TableColumn> columns = airlineDAO.getTableColumns(table);
        request.setAttribute("columns", columns);
        return servletContext.getRequestDispatcher("/admin/RowEdit.jsp");
    }

    public RequestDispatcher post(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
