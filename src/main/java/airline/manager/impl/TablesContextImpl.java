package airline.manager.impl;

import airline.dao.AirlineDAO;
import airline.manager.ContextManager;
import airline.model.Table;
import com.google.inject.Inject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Feb 8, 2009
 * Time: 4:45:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class TablesContextImpl implements ContextManager{
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
        Map<String, Table> tables = airlineDAO.getTables();
        request.setAttribute("tables", tables);
        return context.getRequestDispatcher("/admin/TablesShow.jsp");
    }

    public RequestDispatcher add(HttpServletRequest request, HttpServletResponse response) {
        return null; // non implémenté
    }

    public RequestDispatcher edit(HttpServletRequest request, HttpServletResponse response) {
        return null; // non implémenté
    }

    public RequestDispatcher delete(HttpServletRequest request, HttpServletResponse response) {
        return null; // non implémenté
    }
}
