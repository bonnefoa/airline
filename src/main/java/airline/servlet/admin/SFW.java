package airline.servlet.admin;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: dev
 * Date: Feb 4, 2009
 * Time: 9:21:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class SFW extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/admin/sfw.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/admin/sfw.jsp");
        dispatcher.forward(request, response);
    }
}
