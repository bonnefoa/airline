package airline.servlet.admin;

import airline.servlet.AbstractInjectableServlet;
import airline.servlet.enumeration.MessageError;
import airline.tables.ActionHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: dev
 * Date: Feb 4, 2009
 * Time: 9:28:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class Tables extends AbstractInjectableServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionHandler handler = (ActionHandler) request.getAttribute("url.handler");

        RequestDispatcher dispatcher = handler.post(this.getServletContext(), request, response);

        if (dispatcher == null) {
            request.setAttribute("error.type", MessageError.UNIMPLEMENTED_METHOD);
            dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
        }
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionHandler handler = (ActionHandler) request.getAttribute("url.handler");

        RequestDispatcher dispatcher = handler.get(this.getServletContext(), request, response);

        if (dispatcher == null) {
            request.setAttribute("error.type", MessageError.UNIMPLEMENTED_METHOD);
            dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
        }
        dispatcher.forward(request, response);
    }
}
