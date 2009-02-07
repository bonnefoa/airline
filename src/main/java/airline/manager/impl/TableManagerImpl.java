package airline.manager.impl;

import airline.criteria.model.CreateTableRequest;
import airline.dao.AirlineDAO;
import airline.guiceBindings.Servlet;
import airline.manager.RequestManager;
import airline.model.Table;
import airline.model.TablesColumns;
import airline.servlet.enumeration.MessageAction;
import airline.servlet.enumeration.MessageError;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Inject
    public void setAirlineDAO(AirlineDAO airlineDAO) {
        this.airlineDAO = airlineDAO;
    }

    public void init(ServletContext servletContext) {
        context = servletContext;
        Injector injector = Guice.createInjector(new Servlet());
        injector.injectMembers(this);
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
            String tableName = request.getParameter("tableName");
            String[] names = request.getParameterValues("name");
            String[] types = request.getParameterValues("type");
            String[] primarys = request.getParameterValues("primary");

            // conditions nécessaires
            if (tableName != null && tableName.length() != 0 &&
                    names != null && names.length != 0 &&
                    types != null && types.length != 0 &&
                    primarys != null && primarys.length != 0 &&
                    names.length == types.length && types.length == primarys.length
                    ) {
                Table table = new Table(tableName);
                List<TablesColumns> columns = new ArrayList<TablesColumns>();
                for (int i = 0; i < primarys.length; i++) {
                    TablesColumns column = new TablesColumns();
                    column.setName(names[i]);
                    column.setType(types[i]);
                    if ("on".equals(primarys[i])) {
                        column.setPrimaryKey(true);
                    }

                    columns.add(column);
                }
                airlineDAO.executeRequest(new CreateTableRequest(table, columns));

                request.setAttribute("action.done", MessageAction.TABLE_CREATED);
                return context.getRequestDispatcher("/admin/message.jsp");
            } else {

                request.setAttribute("error.type", MessageError.INTERNAL_ERROR);
                return context.getRequestDispatcher("/admin/error.jsp");
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

            return null;
        }
    }

    public RequestDispatcher delete(HttpServletRequest request, HttpServletResponse response) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
