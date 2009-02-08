package airline.filter;

import airline.dao.AirlineDAO;
import airline.model.Table;
import airline.model.TableRow;
import airline.model.TablesColumns;
import airline.servlet.enumeration.Action;
import airline.servlet.enumeration.Context;
import airline.servlet.enumeration.MessageError;
import com.google.inject.Inject;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Feb 7, 2009
 * Time: 1:39:52 PM
 * To change this template use File | Settings | File Templates.
 */

public class ExistingTablesFilter extends AbstractInjectableFilter {
    private AirlineDAO airlineDAO;

    @Inject
    public void setAirlineDAO(AirlineDAO airlineDAO) {
        this.airlineDAO = airlineDAO;
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        System.out.println("ExistingTablesFilter:: checking infos accuracy : " +
                " table=" + req.getAttribute("url.table") +
                " row=" + req.getAttribute("url.row") +
                " field=" + req.getAttribute("url.field") +
                " action=" + req.getAttribute("url.action") +
                " context=" + req.getAttribute("url.context")
        );

        Context context = (Context) req.getAttribute("url.context");
        MessageError error = null;
        switch (context) {

            case FIELD:
                error = checkFieldContext(req, resp);
                break;
            case ROW:
                error = checkRowContext(req, resp);
                break;
            case TABLE:
                error = checkTableContext(req, resp);
                break;
            case TABLES:
                // toujours bon
                break;
        }

        if (error == null) {
            chain.doFilter(req, resp);
        } else {
            req.setAttribute("error.type", error);
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }

    /**
     * Vérifie le contexte Table.
     * Ce contexte correspond aux cas suivants :
     * /admin/table/add
     * /admin/table/---/
     * /admin/table/---/delete
     * On vérifie donc juste l'existence de la table.
     */
    private MessageError checkTableContext(ServletRequest req, ServletResponse resp) {
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

    /**
     * Vérifie le contexte Row.
     * Ce contexte correspond aux cas suivants :
     * /admin/table/---/row/add
     * /admin/table/---/row/---/edit
     * /admin/table/---/row/---/delete
     * On vérifie donc l'existence de la table et la validité du tuple.
     */
    private MessageError checkRowContext(ServletRequest req, ServletResponse resp) {
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
            int row = Integer.parseInt((String) req.getAttribute("url.row"));
            List<TableRow> rows = airlineDAO.getTablesRows(table);
            if (row < rows.size()) {
                req.setAttribute("url.row", rows.get(row));
                return null;
            } else {
                return MessageError.INEXISTANT_ROW;
            }
        }
    }

    /**
     * Vérifie le contexte Row.
     * Ce contexte correspond aux cas suivants :
     * /admin/table/---/field/add
     * /admin/table/---/field/---/edit
     * /admin/table/---/field/---/delete
     * On vérifie donc l'existence de la table et la validité de la colonne.
     */
    private MessageError checkFieldContext(ServletRequest req, ServletResponse resp) {
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

    private TableRow getRow(Table table, ServletRequest req) {
        String rowNb = (String) req.getAttribute("url.row");
        int row;

        try {
            row = Integer.parseInt(rowNb);
        } catch (NumberFormatException e) {
            return null;
        }

        List<TableRow> rows = airlineDAO.getTablesRows(table);
        if (rows.size() > row) {
            return rows.get(row);
        } else {
            return null;
        }
    }
}
