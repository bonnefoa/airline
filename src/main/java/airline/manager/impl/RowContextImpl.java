package airline.manager.impl;

import airline.dao.AirlineDAO;
import airline.manager.ContextManager;
import com.google.inject.Inject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Feb 7, 2009
 * Time: 4:18:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class RowContextImpl implements ContextManager {
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
        return null;
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
