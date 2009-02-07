package airline.servlet.admin;

import airline.model.Table;
import airline.model.TablesColumns;
import airline.model.TableRow;
import airline.dao.AirlineDAO;
import airline.guiceBindings.Servlet;
import airline.criteria.model.SelectRequest;
import airline.criteria.enumeration.SqlConstraints;
import airline.criteria.Restriction;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.*;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Guice;

/**
 * Created by IntelliJ IDEA.
 * User: dev
 * Date: Feb 4, 2009
 * Time: 9:21:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class SFW extends HttpServlet {
    private Map<String, Table> tables;
    private List<TablesColumns> columns;
    private AirlineDAO airlineDAO;
    private HttpServletRequest ddReq;

    public SFW() {
        Injector injector = Guice.createInjector(new Servlet());
        injector.injectMembers(this);
    }

    @Inject
    public void setAirlineDAO(AirlineDAO airlineDAO) {
        this.airlineDAO = airlineDAO;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ddReq = request;
        tables = airlineDAO.getTables();
        SelectRequest selectRequest = new SelectRequest();
        boolean canDoRequest = true;

        Table from = handleFrom(request);

        if (from != null) {
            columns = airlineDAO.getTablesColumns(from);
            request.setAttribute("columns", columns);
            canDoRequest = handleSelect(selectRequest, request) && canDoRequest;
            canDoRequest = handleWhere(selectRequest, request) && canDoRequest;
        } else {
            canDoRequest = false;
        }

        if (canDoRequest) {
            doRequest(selectRequest, request);
        }

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/admin/sfw.jsp");
        dispatcher.forward(request, response);
    }

    private void doRequest(SelectRequest selectRequest, HttpServletRequest request) {
        Set<TableRow> result = airlineDAO.executeRequest(selectRequest);
        request.setAttribute("rows", result);
    }

    private Table handleFrom(HttpServletRequest request) {
        request.setAttribute("tables", tables);

        String from = request.getParameter("from");

        if (from != null && tables.containsKey(from)) {
            request.setAttribute("selectedTable", tables.get(from));
            return tables.get(from);
        } else {
            return null;
        }
    }

    private boolean handleSelect(SelectRequest selectRequest, HttpServletRequest request) {

        String[] params = request.getParameterValues("select");

        if (params == null || params.length == 0) {
            return false;
        }

        List<TablesColumns> selected = new ArrayList<TablesColumns>();
        List<String> paramList = Arrays.asList(params);

        for (TablesColumns col : columns) {
            if(paramList.contains(col.getName())) {
                selected.add(col);
                selectRequest.addColumn(col);
            }
        }

        request.setAttribute("selectedFields", selected);

        return !selected.isEmpty();
    }

    private boolean handleWhere(SelectRequest selectRequest, HttpServletRequest request) {

        String whereField = request.getParameter("whereField");
        TablesColumns column = null;

        if (whereField != null) {
            Iterator<TablesColumns> iterator = columns.iterator();
            while (column == null && iterator.hasNext()) {
                TablesColumns current = iterator.next();
                if (current.getName().equals(whereField)) {
                    column = current;
                }
            }
        }

        String whereCond = request.getParameter("whereCond");
        SqlConstraints cond = null;

        if (whereCond != null) {
            if (">".equals(whereCond)) {
                cond = SqlConstraints.GT;
            } else if (">=".equals(whereCond)) {
                cond = SqlConstraints.GE;
            } else if ("<".equals(whereCond)) {
                cond = SqlConstraints.LT;
            } else if ("<=".equals(whereCond)) {
                cond = SqlConstraints.LE;
            } else if ("=".equals(whereCond)) {
                cond = SqlConstraints.EQ;
            } else if ("!=".equals(whereCond)) {
                cond = SqlConstraints.NEQ;
            } else if ("like".equals(whereCond)) {
                cond = SqlConstraints.LIKE;
            } else if ("ilike".equals(whereCond)) {
                cond = SqlConstraints.ILIKE;
            }
        }

        String whereVal = request.getParameter("whereVal");

        request.setAttribute("whereField", column);
        request.setAttribute("whereCond", cond);
        request.setAttribute("whereVal", whereVal);

        if (column != null && cond != null && whereVal != null) {
            Restriction restriction = new Restriction();
            restriction.constraint(column, whereVal, cond);
            selectRequest.addRestriction(restriction);

            return true;
        } else {
            return false;
        }
    }
}
