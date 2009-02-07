package airline.servlet;

import airline.criteria.Restriction;
import airline.criteria.enumeration.SqlConstraints;
import airline.criteria.impl.SelectRequest;
import airline.dao.AirlineDAO;
import airline.guiceBindings.Servlet;
import airline.model.Table;
import airline.model.TableRow;
import airline.model.TablesColumns;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Servlet accueil
 */
public class Accueil extends HttpServlet {
    private AirlineDAO airlineDAO;
    private static final String TABLE_NAME = "AIRLINEDATA";
    private static final String FLIGHT_FIELD = "FLIGHT NUMBER";
    private static final String PILOT_FIELD = "PILOT NUMBER";
    private static final String PLANE_FIELD = "PLANE NUMBER";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        Injector injector = Guice.createInjector(new Servlet());
        injector.injectMembers(this);
    }

    @Inject
    public void setAirlineDAO(AirlineDAO airlineDAO) {
        this.airlineDAO = airlineDAO;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String query = request.getParameter("q");
        String type = request.getParameter("type");

        if (query != null && type != null && query.length() != 0 && type.length() != 0) {

            Map<String, Table> tables = airlineDAO.getTables();
            List<TablesColumns> columns = airlineDAO.getTablesColumns(tables.get(TABLE_NAME));

            TablesColumns column = getCorrespondingCol(type, columns);

            if (column != null) {

                SelectRequest selectRequest = new SelectRequest();
                Restriction restriction = new Restriction();
                restriction.constraint(column, query, SqlConstraints.EQ);
                selectRequest.addRestriction(restriction);
                Set<TableRow> result = airlineDAO.executeRequest(selectRequest);

                request.setAttribute("columns", columns);
                request.setAttribute("rows", result);
            }
        }
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/accueil.jsp");
        dispatcher.forward(request, response);
    }

    private TablesColumns getCorrespondingCol(String type, List<TablesColumns> columns) {
        String field = null;

        if ("flight".equals(type)) {
            field = FLIGHT_FIELD;
        } else if ("pilot".equals(type)) {
            field = PILOT_FIELD;
        } else if ("plane".equals(type)) {
            field = PLANE_FIELD;
        }

        for (TablesColumns column : columns) {
            if (column.getName().equals(field)) {
                return column;
            }
        }
        return null;
    }
}
