package airline.servlet;

import airline.model.User;
import airline.dao.AuthDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import java.io.IOException;

import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: dev
 * Date: 3 févr. 2009
 * Time: 21:49:08
 * To change this template use File | Settings | File Templates.
 */
public class Login extends HttpServlet {
    private AuthDAO auth;

    @Inject
    public void setAuth(AuthDAO auth) {
        this.auth = auth;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();

        user.setLogin(request.getParameter("login"));
        user.setPasswd(request.getParameter("passwd"));

        if (auth.logIn(user)) {
            request.getSession().setAttribute("user", user);
            response.sendRedirect("admin/");
        } else {
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
    }
}
