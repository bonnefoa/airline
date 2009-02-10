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
import airline.servlet.enumeration.MessageAction;
import airline.servlet.enumeration.MessageError;
import airline.tables.context.FieldContextHandler;
import airline.model.Table;
import airline.model.TableColumn;
import airline.criteria.model.AlterFieldRequest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Feb 9, 2009
 * Time: 1:55:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class EditField extends FieldContextHandler {
    public EditField() {
        init(Action.EDIT);
    }

    public RequestDispatcher get(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
        Table table = (Table) request.getAttribute("url.table");
        List<TableColumn> columns = airlineDAO.getTableColumns(table);
        request.setAttribute("columns", columns);
        return servletContext.getRequestDispatcher("/admin/FieldEdit.jsp");
    }

    public RequestDispatcher post(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
        Table table = (Table) request.getAttribute("url.table");
        Map<TableColumn, TableColumn> columnsMap = new HashMap<TableColumn, TableColumn>();
        TableColumn oldField = (TableColumn) request.getAttribute("url.field");
        TableColumn newField = new TableColumn();

        try {
            int dataType = Integer.parseInt(request.getParameter("type"));
            String fieldName = request.getParameter("name");
            boolean primary = "on".equals(request.getParameter("primary"));

            newField.setDataType(dataType);
            newField.setName(fieldName);
            newField.setPrimaryKey(primary);

            System.out.println("dataType" + dataType +
                    "fieldName" + fieldName +
                    "primary" + primary);

            columnsMap.put(oldField, newField);

            airlineDAO.executeRequest(new AlterFieldRequest(table, columnsMap));
            request.setAttribute("action.done", MessageAction.FIELD_EDITED);
            return servletContext.getRequestDispatcher("/message.jsp");
        } catch (SQLException e) {
            request.setAttribute("error.type", MessageError.SQL_ERROR);
            request.setAttribute("error.exception", e);
            return servletContext.getRequestDispatcher("/error.jsp");
        } catch (NumberFormatException e) {
            request.setAttribute("error.type", MessageError.WRONG_PARAMETER);
            request.setAttribute("error.exception", e);
            return servletContext.getRequestDispatcher("/error.jsp");
        }

    }
}
