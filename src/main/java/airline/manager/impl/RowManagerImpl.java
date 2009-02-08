package airline.manager.impl;

import airline.dao.AirlineDAO;
import airline.manager.RequestManager;
import airline.model.Table;
import airline.model.TablesColumns;
import airline.model.TableRow;
import com.google.inject.Inject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Feb 7, 2009
 * Time: 4:18:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class RowManagerImpl implements RequestManager {
    private ServletContext context;
    private AirlineDAO airlineDAO;

    @Inject
    public void setAirlineDAO(AirlineDAO airlineDAO) {
        this.airlineDAO = airlineDAO;
    }

    public void init(ServletContext servletContext) {
        context = servletContext;
    }

    public RequestDispatcher show(HttpServletRequest request, HttpServletResponse response) {
        Table table = (Table) request.getAttribute("url.table");
        List<TablesColumns> columns = airlineDAO.getTablesColumns(table);
        List<TableRow> rows = airlineDAO.getTablesRows(table);
        request.setAttribute("columns", columns);
        request.setAttribute("rows", rows);
        return context.getRequestDispatcher("/admin/rowsList.jsp");
    }

    public RequestDispatcher add(HttpServletRequest request, HttpServletResponse response) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public RequestDispatcher edit(HttpServletRequest request, HttpServletResponse response) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public RequestDispatcher delete(HttpServletRequest request, HttpServletResponse response) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
