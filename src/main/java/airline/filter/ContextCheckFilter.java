package airline.filter;

import airline.dao.AirlineDAO;
import airline.servlet.enumeration.MessageError;
import airline.tables.ActionHandler;
import com.google.inject.Inject;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Feb 7, 2009
 * Time: 1:39:52 PM
 * To change this template use File | Settings | File Templates.
 */

public class ContextCheckFilter extends AbstractInjectableFilter {
    private AirlineDAO airlineDAO;

    @Inject
    public void setAirlineDAO(AirlineDAO airlineDAO) {
        this.airlineDAO = airlineDAO;
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        System.out.println("ContextCheckFilter:: checking infos accuracy : " +
                " table=" + req.getAttribute("url.table") +
                " row=" + req.getAttribute("url.row") +
                " field=" + req.getAttribute("url.field") +
                " action=" + req.getAttribute("url.action") +
                " context=" + req.getAttribute("url.context")
        );

        ActionHandler handler = (ActionHandler) req.getAttribute("url.handler");

        MessageError error = handler.checkContext(req);

        if (error == null) {
            chain.doFilter(req, resp);
        } else {
            req.setAttribute("error.type", error);
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
