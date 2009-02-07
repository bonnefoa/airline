package airline.manager.impl;

import airline.dao.AirlineDAO;
import airline.guiceBindings.Servlet;
import airline.manager.RequestManager;
import airline.model.Table;
import airline.model.TablesColumns;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Feb 7, 2009
 * Time: 4:18:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class TableManagerImpl implements RequestManager {
    private ServletContext context;
    private AirlineDAO airlineDAO;

    @Inject
    public void setAirlineDAO(AirlineDAO airlineDAO) {
        this.airlineDAO = airlineDAO;
    }

    public void init(ServletContext servletContext) {
        context = servletContext;
        Injector injector = Guice.createInjector(new Servlet());
        injector.injectMembers(this);
    }

    public RequestDispatcher show(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Table> tables = airlineDAO.getTables();
        request.setAttribute("tables", tables);
        return context.getRequestDispatcher("/admin/tablesList.jsp");
    }

    public RequestDispatcher add(HttpServletRequest request, HttpServletResponse response) {
        if ("GET".equals(request.getMethod())) {
            return context.getRequestDispatcher("/admin/tableAddOrEdit.jsp");
        } else { // POST

            return null;
        }
    }

    public RequestDispatcher edit(HttpServletRequest request, HttpServletResponse response) {
        if ("GET".equals(request.getMethod())) {
            Table table = (Table) request.getAttribute("url.table");
            List<TablesColumns> columns = airlineDAO.getTablesColumns(table);
            request.setAttribute("columns", columns);
            return context.getRequestDispatcher("/admin/tableAddOrEdit.jsp");
        } else { // POST

            return null;
        }
    }

    public RequestDispatcher delete(HttpServletRequest request, HttpServletResponse response) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
