package airline.manager.impl;

import airline.criteria.model.CreateTableRequest;
import airline.dao.AirlineDAO;
import airline.manager.RequestManager;
import airline.model.Table;
import airline.model.TablesColumns;
import airline.servlet.enumeration.MessageAction;
import airline.servlet.enumeration.MessageError;
import com.google.inject.Inject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Feb 7, 2009
 * Time: 4:18:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class TableManagerImpl implements RequestManager {
    private ServletContext context;
    private AirlineDAO airlineDAO;
    private static final int DEFAULT_DATATYPE = Types.VARCHAR;

    @Inject
    public void setAirlineDAO(AirlineDAO airlineDAO) {
        this.airlineDAO = airlineDAO;
    }

    public void init(ServletContext servletContext) {
        context = servletContext;
        //Injector injector = Guice.createInjector(new Servlet());
        //injector.injectMembers(this);
    }

    public RequestDispatcher show(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Table> tables = airlineDAO.getTables();
        request.setAttribute("tables", tables);
        return context.getRequestDispatcher("/admin/tablesList.jsp");
    }

    public RequestDispatcher add(HttpServletRequest request, HttpServletResponse response) {
        if ("GET".equals(request.getMethod())) {
            return context.getRequestDispatcher("/admin/tableAddOrEdit.jsp");
        } else { // POST

            Table table = generateTable(request);
            List<TablesColumns> columns = generateColumns(request);

            if (table == null || columns == null) {
                request.setAttribute("error.type", MessageError.EMPTY_FIELD);
                return context.getRequestDispatcher("/error.jsp");
            }

            try {
                airlineDAO.executeRequest(new CreateTableRequest(table, columns));
                request.setAttribute("action.done", MessageAction.TABLE_CREATED);
                return context.getRequestDispatcher("/message.jsp");
            } catch (SQLException e) {
                request.setAttribute("error.type", MessageError.SQL_ERROR);
                request.setAttribute("error.exception", e);
                return context.getRequestDispatcher("/error.jsp");
            }
        }
    }

    public RequestDispatcher edit(HttpServletRequest request, HttpServletResponse response) {
        if ("GET".equals(request.getMethod())) {
            Table table = (Table) request.getAttribute("url.table");
            List<TablesColumns> columns = airlineDAO.getTablesColumns(table);
            request.setAttribute("columns", columns);
            return context.getRequestDispatcher("/admin/tableAddOrEdit.jsp");
        } else { // POST

            Table table = generateTable(request);
            List<TablesColumns> columns = generateColumns(request);

            return null;
        }
    }

    public RequestDispatcher delete(HttpServletRequest request, HttpServletResponse response) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private Table generateTable(HttpServletRequest request) {
        String tableName = request.getParameter("tableName");
        if (tableName != null && tableName.length() != 0) {
            return new Table(tableName);
        } else {
            return null;
        }
    }

    private List<TablesColumns> generateColumns(HttpServletRequest request) {
        String[] names = request.getParameterValues("name");
        String[] types = request.getParameterValues("type");
        String[] primarys = request.getParameterValues("primary");


        if (names != null && names.length != 0 &&
                types != null && types.length != 0 &&
                primarys != null && primarys.length != 0 &&
                names.length == types.length && types.length == primarys.length
                ) {

            List<TablesColumns> columns = new ArrayList<TablesColumns>();

            boolean allNotEmpty = true;
            TablesColumns primaryKey = null;

            for (int i = 0; i < primarys.length && allNotEmpty; i++) {

                allNotEmpty = allNotEmpty && names[i] != null && names[i].length() != 0;
                allNotEmpty = allNotEmpty && types[i] != null && types[i].length() != 0;
                allNotEmpty = allNotEmpty && primarys[i] != null && primarys[i].length() != 0;

                TablesColumns column = new TablesColumns();
                column.setName(names[i]);
                try {
                    column.setDataType(Integer.parseInt(types[i]));
                } catch (NumberFormatException e) {
                    column.setDataType(DEFAULT_DATATYPE);
                }
                if ("on".equals(primarys[i])) {
                    primaryKey = column;
                }
                columns.add(column);
            }

            if (primaryKey != null) {
                primaryKey.setPrimaryKey(true);
            }


            if (allNotEmpty) {
                return columns;
            }
        }
        return null;
    }
}
