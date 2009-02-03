package airline.servlet;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Guice;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import java.io.IOException;

import airline.dao.AirlineDAO;
import airline.guiceBindings.Servlet;

/**
 * Servlet accueil
 */
public class Accueil extends HttpServlet {
    private AirlineDAO airlineDAO;

    public Accueil() {
        Injector injector = Guice.createInjector(new Servlet());
        injector.injectMembers(this);
    }

    @Inject
    public void setAirlineDAO(AirlineDAO airlineDAO) {
        this.airlineDAO = airlineDAO;
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("graou", airlineDAO.getTables());
        
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/accueil.jsp");
        dispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/accueil.jsp");
        dispatcher.forward(request,response);
    }
}
