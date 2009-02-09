package airline.tables.context;

import airline.dao.AirlineDAO;
import airline.servlet.enumeration.Action;
import airline.servlet.enumeration.Context;
import airline.tables.ActionHandler;
import com.google.inject.Inject;
import com.google.inject.Injector;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Feb 9, 2009
 * Time: 1:47:29 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class ContextHandler implements ActionHandler {

    protected ServletContext servletContext;
    protected AirlineDAO airlineDAO;
    private Action action;
    private Context context;

    @Inject
    public void setAirlineDAO(AirlineDAO airlineDAO) {
        this.airlineDAO = airlineDAO;
    }

    public void init(ServletContext servletContext) {
        if (servletContext != null) {
            this.servletContext = servletContext;
            Injector injector = (Injector) servletContext.getAttribute(Injector.class.getName());
            injector.injectMembers(this);
        }
    }

    protected void init(Context context, Action action) {
        this.action = action;
        this.context = context;
    }
}
