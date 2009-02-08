package airline.filter;

import com.google.inject.Injector;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Feb 8, 2009
 * Time: 3:26:50 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractInjectableFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext context = filterConfig.getServletContext();
        Injector injector = (Injector) context.getAttribute(Injector.class.getName());
        if (injector == null) {
            throw new ServletException("Guice Injector not found");
        }
        injector.injectMembers(this);
    }
}
