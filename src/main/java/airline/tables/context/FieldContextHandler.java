package airline.tables.context;

import airline.model.Table;
import airline.model.TablesColumns;
import airline.servlet.enumeration.Action;
import airline.servlet.enumeration.Context;
import airline.servlet.enumeration.MessageError;

import javax.servlet.ServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Feb 9, 2009
 * Time: 1:47:29 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class FieldContextHandler extends ContextHandler {

    protected void init(Action action) {
        init(Context.FIELD, action);
    }

    /**
     * Vérifie le contexte Row.
     * Ce contexte correspond aux cas suivants :
     * /admin/table/---/field/add
     * /admin/table/---/field/---/edit
     * /admin/table/---/field/---/delete
     * On vérifie donc l'existence de la table et la validité de la colonne.
     */
    public MessageError checkContext(ServletRequest req) {
        Table table = getTable(req);
        if (table != null) {
            req.setAttribute("url.table", table);
        } else {
            return MessageError.INEXISTANT_TABLE;
        }

        Action action = (Action) req.getAttribute("url.action");
        if (action == Action.ADD) {
            return null;
        } else {
            String fieldName = (String) req.getAttribute("url.field");
            List<TablesColumns> fields = airlineDAO.getTablesColumns(table);
            for (TablesColumns field : fields) {
                if (fieldName.equals(field.getName())) {
                    req.setAttribute("url.field", field);
                    return null;
                }
            }
            return MessageError.INEXISTANT_FIELD;
        }
    }

    private Table getTable(ServletRequest req) {
        String tableName = (String) req.getAttribute("url.table");
        Map<String, Table> tables = airlineDAO.getTables();
        return tables.get(tableName);
    }
}
