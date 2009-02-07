package airline.filter;

import airline.dao.AirlineDAO;
import airline.model.Table;
import airline.model.TableRow;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import javax.servlet.*;
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

public class ExistingTablesFilter implements Filter {
    private AirlineDAO airlineDAO;

    public ExistingTablesFilter() {
        Injector injector = Guice.createInjector(new airline.guiceBindings.Servlet());
        injector.injectMembers(this);
    }

    @Inject
    public void setAirlineDAO(AirlineDAO airlineDAO) {
        this.airlineDAO = airlineDAO;
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        System.out.println("checking infos accuracy : " +
                " table=" + req.getAttribute("url.table") +
                " row=" + req.getAttribute("url.row") +
                " action=" + req.getAttribute("url.action")
        );

        Table table = getTable(req);
        if (table != null) {
            req.setAttribute("url.table", table);

            TableRow row = getRow(table, req);
            if (row != null) {
                req.setAttribute("url.row", row);
            } else {
                req.removeAttribute("url.row");
            }

        } else {
            req.removeAttribute("url.table");
            req.removeAttribute("url.row");
        }

        chain.doFilter(req, resp);
    }


    public void init(FilterConfig config) throws ServletException {

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
        if (rows.size() < row) {
            return rows.get(row);
        } else {
            return null;
        }
    }
}
