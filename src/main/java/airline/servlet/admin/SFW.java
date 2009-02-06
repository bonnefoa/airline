package airline.servlet.admin;

import airline.model.Table;
import airline.dao.AirlineDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: dev
 * Date: Feb 4, 2009
 * Time: 9:21:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class SFW extends HttpServlet {
    private Map<String, Table> tables;
    private AirlineDAO airlineDAO;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/admin/sfw.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        tables = airlineDAO.getTables();
        String from = request.getParameter("from");

        request.setAttribute("graou", from);


        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/admin/sfw.jsp");
        dispatcher.forward(request, response);
    }
}
