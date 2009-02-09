package airline.tables.context;

import airline.model.Table;
import airline.servlet.enumeration.Action;
import airline.servlet.enumeration.Context;
import airline.servlet.enumeration.MessageError;

import javax.servlet.ServletRequest;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Feb 9, 2009
 * Time: 1:47:29 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class TableContextHandler extends ContextHandler {

    protected void init(Action action) {
        init(Context.FIELD, action);
    }

    /**
     * Vérifie le contexte Table.
     * Ce contexte correspond aux cas suivants :
     * /admin/table/add
     * /admin/table/---/
     * /admin/table/---/delete
     * On vérifie donc juste l'existence de la table.
     */
    public MessageError checkContext(ServletRequest req) {
        Action action = (Action) req.getAttribute("url.action");
        if (action != Action.ADD) {
            Table table = getTable(req);
            if (table != null) {
                req.setAttribute("url.table", table);
                return null;
            } else {
                return MessageError.INEXISTANT_TABLE;
            }
        } else {
            return null;
        }
    }

    private Table getTable(ServletRequest req) {
        String tableName = (String) req.getAttribute("url.table");
        Map<String, Table> tables = airlineDAO.getTables();
        return tables.get(tableName);
    }

}
