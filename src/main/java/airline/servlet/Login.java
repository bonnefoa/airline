package airline.servlet;

import airline.dao.AuthDAO;
import airline.model.User;
import com.google.inject.Inject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: dev
 * Date: 3 févr. 2009
 * Time: 21:49:08
 * To change this template use File | Settings | File Templates.
 */
public class Login extends AbstractInjectableServlet {
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
            response.sendRedirect(request.getContextPath() + "/admin/");
        } else {
            request.setAttribute("loginFailed", new Boolean(true));
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
    }
}
