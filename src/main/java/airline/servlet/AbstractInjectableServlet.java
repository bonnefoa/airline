package airline.servlet;

import com.google.inject.Injector;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public abstract class AbstractInjectableServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        ServletContext context = config.getServletContext();
        Injector injector = (Injector) context.getAttribute(Injector.class.getName());
        if (injector == null) {
            throw new ServletException("Guice Injector not found");
        }
        injector.injectMembers(this);

    }
}