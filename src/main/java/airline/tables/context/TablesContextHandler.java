package airline.tables.context;

import airline.dao.AirlineDAO;
import airline.servlet.enumeration.Action;
import airline.servlet.enumeration.MessageError;
import airline.tables.ActionHandler;
import com.google.inject.Inject;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Feb 9, 2009
 * Time: 1:47:29 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class TablesContextHandler implements ActionHandler {

    protected ServletContext servletContext;
    protected AirlineDAO airlineDAO;
    private Action action;

    @Inject
    public void setAirlineDAO(AirlineDAO airlineDAO) {
        this.airlineDAO = airlineDAO;
    }

    public void init(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    protected void setAction(Action action) {
        this.action = action;
    }

    public MessageError checkContext(ServletRequest request) {
        return null;
    }
}
