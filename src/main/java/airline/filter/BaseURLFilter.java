package airline.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Created by IntelliJ IDEA.
 * User: dev
 * Date: 4 févr. 2009
 * Time: 14:28:56
 * To change this template use File | Settings | File Templates.
 */

public class BaseURLFilter implements Filter {
    private FilterConfig filterConfig;
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String baseURL = ((HttpServletRequest)req).getContextPath();
        req.setAttribute("baseURL", baseURL);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        this.filterConfig = config;
    }

}
