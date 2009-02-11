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

package airline.tables.context.action;

import airline.servlet.enumeration.Action;
import airline.servlet.enumeration.MessageError;
import airline.servlet.enumeration.MessageAction;
import airline.tables.context.FieldContextHandler;
import airline.model.Table;
import airline.model.TableColumn;
import airline.criteria.model.CreateFieldRequest;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.ArrayList;
import java.sql.Types;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Feb 9, 2009
 * Time: 1:55:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class AddField extends FieldContextHandler {
    private static final int DEFAULT_DATATYPE = Types.VARCHAR;
    public AddField() {
        init(Action.ADD);
    }

    public RequestDispatcher get(HttpServletRequest request, HttpServletResponse response) {

        Table table = (Table) request.getAttribute("url.table");
        List<TableColumn> columns = airlineDAO.getTableColumns(table);
        request.setAttribute("columns", columns);
        return servletContext.getRequestDispatcher("/tables/FieldAdd.jsp");
    }

    public RequestDispatcher post(HttpServletRequest request, HttpServletResponse response) {
        Table table = (Table) request.getAttribute("url.table");
        List<TableColumn> columns = generateColumns(request);
        if (columns == null) {
            request.setAttribute("error.type", MessageError.EMPTY_FIELD);
            return servletContext.getRequestDispatcher("/error.jsp");
        }

        try {
            airlineDAO.executeRequest(new CreateFieldRequest(table, columns));
            request.setAttribute("action.done", MessageAction.FIELD_ADDED);
            return servletContext.getRequestDispatcher("/message.jsp");
        } catch (SQLException e) {
            request.setAttribute("error.type", MessageError.SQL_ERROR);
            request.setAttribute("error.exception", e);
            return servletContext.getRequestDispatcher("/error.jsp");
        }
    }

    private List<TableColumn> generateColumns(HttpServletRequest request) {
        String[] names = request.getParameterValues("name");
        String[] types = request.getParameterValues("type");
        String[] primarys = request.getParameterValues("primary");


        if (names != null && names.length != 0 &&
                types != null && types.length != 0 &&
                primarys != null && primarys.length != 0 &&
                names.length == types.length && types.length == primarys.length
                ) {

            List<TableColumn> columns = new ArrayList<TableColumn>();

            boolean allNotEmpty = true;
            TableColumn primaryKey = null;

            for (int i = 0; i < primarys.length && allNotEmpty; i++) {

                allNotEmpty = allNotEmpty && names[i] != null && names[i].length() != 0;
                allNotEmpty = allNotEmpty && types[i] != null && types[i].length() != 0;
                allNotEmpty = allNotEmpty && primarys[i] != null && primarys[i].length() != 0;

                TableColumn column = new TableColumn();
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
