package airline.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import java.io.IOException;

/**
 * Servlet accueil
 */
public class Accueil extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("GRA");
//        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("Response.jsp");
//        try {
//            dispatcher.forward(request, response);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("GRA");
    }
}
