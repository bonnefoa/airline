package airline.tables.context.action;

import airline.servlet.enumeration.Action;
import airline.tables.context.FieldContextHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Feb 9, 2009
 * Time: 1:55:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class AddField extends FieldContextHandler {
    public AddField() {
        setAction(Action.ADD);
    }

    public RequestDispatcher get(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public RequestDispatcher post(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
