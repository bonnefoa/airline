package airline.servlet.admin;

import airline.criteria.Restriction;
import airline.criteria.enumeration.SqlConstraints;
import airline.criteria.model.SelectRequest;
import airline.dao.AirlineDAO;
import airline.model.Table;
import airline.model.TableRow;
import airline.model.TablesColumns;
import airline.servlet.AbstractInjectableServlet;
import airline.servlet.enumeration.MessageError;
import com.google.inject.Inject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.net.URLDecoder;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: dev
 * Date: Feb 4, 2009
 * Time: 9:21:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class SFW extends AbstractInjectableServlet {
    private Map<String, Table> tables;
    private List<TablesColumns> columns;
    private AirlineDAO airlineDAO;

    @Inject
    public void setAirlineDAO(AirlineDAO airlineDAO) {
        this.airlineDAO = airlineDAO;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/admin/sfw.jsp");
        if (canDoRequest) {
            try {
                doRequest(selectRequest, request);
            } catch (SQLException e) {
                request.setAttribute("error.type", MessageError.SQL_ERROR);
                request.setAttribute("error.exception", e);
                dispatcher = this.getServletContext().getRequestDispatcher("/error.jsp");
            }
        }

        dispatcher.forward(request, response);
    }

    private void doRequest(SelectRequest selectRequest, HttpServletRequest request) throws SQLException {
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
            if (paramList.contains(col.getName())) {
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

        String whereCond = null;
        try {
            whereCond = URLDecoder.decode(request.getParameter("whereCond"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        SqlConstraints cond = null;

        System.out.println("checking whereCond");
        if (whereCond != null) {
            System.out.println("whereCond : " + whereCond);
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
            System.out.println("result : " + cond);
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
