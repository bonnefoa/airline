package airline.servlet;

import org.aspectj.lang.annotation.Before;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * Servlet accueil
 */
public class Accueil extends HttpServlet {

    private List<String> list;

    public Accueil() {
        list = new ArrayList<String>();
        list.add("hello");
        list.add("world");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("Response.jsp");
        //dispatcher.forward(request,response);
        response.getWriter().println("hello world");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("hello world");
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("Accueil.jsp");
        dispatcher.forward(request,response);

        
    }
    
    public List<String> getList() {
        return list;
    }
}
