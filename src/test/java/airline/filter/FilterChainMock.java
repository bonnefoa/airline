package airline.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Feb 7, 2009
 * Time: 11:31:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class FilterChainMock implements FilterChain {
    private boolean hasChained;

    public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        hasChained = true;
    }

    public boolean hasChained() {
        return hasChained;
    }

    public void setHasChained(boolean hasChained) {
        this.hasChained = hasChained;
    }

    public void reset() {
        hasChained = false;
    }
}
