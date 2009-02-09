package airline.tables.context;

import airline.servlet.enumeration.Action;
import airline.servlet.enumeration.Context;
import airline.servlet.enumeration.MessageError;

import javax.servlet.ServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Feb 9, 2009
 * Time: 1:47:29 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class TablesContextHandler extends ContextHandler {

    protected void init(Action action) {
        init(Context.FIELD, action);
    }

    public MessageError checkContext(ServletRequest request) {
        return null;
    }
}
