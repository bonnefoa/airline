package airline.tables;

import airline.servlet.enumeration.MessageError;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Feb 9, 2009
 * Time: 1:44:57 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ActionHandler {
    void init(ServletContext servletContext);

    MessageError checkContext(ServletRequest request);

    RequestDispatcher get(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response);

    RequestDispatcher post(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response);
}
