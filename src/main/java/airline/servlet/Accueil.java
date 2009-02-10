/**
 * Copyright (C) 2009 Anthonin Bonnefoy and David Duponchel
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *         http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package airline.servlet;

import airline.criteria.Restriction;
import airline.criteria.enumeration.SqlConstraints;
import airline.criteria.model.SelectRequest;
import airline.dao.AirlineDAO;
import airline.model.Table;
import airline.model.TableRow;
import airline.model.TableColumn;
import airline.servlet.enumeration.MessageError;
import airline.manager.AirlineManager;
import com.google.inject.Inject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.sql.SQLException;

/**
 * Servlet accueil
 */
public class Accueil extends AbstractInjectableServlet {
    private AirlineManager airlineDAO;
    private static final String TABLE_NAME = "AIRLINEDATA";
    private static final String FLIGHT_FIELD = "FLIGHT NUMBER";
    private static final String PILOT_FIELD = "PILOT NUMBER";
    private static final String PLANE_FIELD = "PLANE NUMBER";

    @Inject
    public void setAirlineDAO(AirlineManager airlineDAO) {
        this.airlineDAO = airlineDAO;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String query = request.getParameter("q");
        String type = request.getParameter("type");

        if (query != null && type != null && query.length() != 0 && type.length() != 0) {

            Map<String, Table> tables = airlineDAO.getTables();
            List<TableColumn> columns = airlineDAO.getTableColumns(tables.get(TABLE_NAME));

            TableColumn column = getCorrespondingCol(type, columns);

            if (column != null) {

                SelectRequest selectRequest = new SelectRequest();
                Restriction restriction = new Restriction();
                restriction.constraint(column, query, SqlConstraints.EQ);
                selectRequest.addRestriction(restriction);
                Set<TableRow> result = null;
                try {
                    result = airlineDAO.executeRequest(selectRequest);
                } catch (SQLException e) {

                    request.setAttribute("error.type", MessageError.SQL_ERROR);
                    request.setAttribute("error.exception", e);
                    this.getServletContext().getRequestDispatcher("/error.jsp");
                    return;
                }

                request.setAttribute("columns", columns);
                request.setAttribute("rows", result);
            }
        }

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/accueil.jsp");
        dispatcher.forward(request, response);
    }

    private TableColumn getCorrespondingCol(String type, List<TableColumn> columns) {
        String field = null;

        if ("flight".equals(type)) {
            field = FLIGHT_FIELD;
        } else if ("pilot".equals(type)) {
            field = PILOT_FIELD;
        } else if ("plane".equals(type)) {
            field = PLANE_FIELD;
        }

        for (TableColumn column : columns) {
            if (column.getName().equals(field)) {
                return column;
            }
        }
        return null;
    }
}
