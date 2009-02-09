package airline.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Feb 7, 2009
 * Time: 12:37:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class APropos extends AbstractInjectableServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/apropos.jsp");
        dispatcher.forward(request, response);
    }
}
