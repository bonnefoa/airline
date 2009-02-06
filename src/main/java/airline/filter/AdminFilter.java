package airline.filter;

import airline.dao.AuthDAO;
import airline.guiceBindings.*;
import airline.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Guice;

/**
 * Created by IntelliJ IDEA.
 * User: dev
 * Date: 3 févr. 2009
 * Time: 20:55:08
 * To change this template use File | Settings | File Templates.
 */
public class AdminFilter implements Filter {
    private AuthDAO auth;
    private FilterConfig filterConfig;

    @Inject
    public void setAuth(AuthDAO auth) {
        this.auth = auth;
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        Injector injector = Guice.createInjector(new airline.guiceBindings.Servlet());
        injector.injectMembers(this);
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       HttpSession session = ((HttpServletRequest) request).getSession();
        if (auth.isLoggedIn((User) session.getAttribute("user"))) {
            chain.doFilter(request, response);
        } else {
            RequestDispatcher dispatcher = filterConfig.getServletContext().getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    public void destroy() {
    }
}
