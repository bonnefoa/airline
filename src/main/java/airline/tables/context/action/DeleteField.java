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
import airline.criteria.model.DeleteFieldRequest;
import airline.model.Table;
import airline.model.TableColumn;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Feb 9, 2009
 * Time: 1:55:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class DeleteField extends FieldContextHandler {
    public DeleteField() {
        init(Action.DELETE);
    }

    public RequestDispatcher get(HttpServletRequest request, HttpServletResponse response) {
        return servletContext.getRequestDispatcher("/tables/FieldDelete.jsp");
    }

    public RequestDispatcher post(HttpServletRequest request, HttpServletResponse response) {
        Table table = (Table) request.getAttribute("url.table");
        TableColumn column = (TableColumn) request.getAttribute("url.field");
        List<TableColumn> columns  = new ArrayList<TableColumn>();
        columns.add(column);
        DeleteFieldRequest deleteField = new DeleteFieldRequest(table, columns);
        try {
            airlineDAO.executeRequest(deleteField);
            request.setAttribute("action.done", MessageAction.FIELD_DELETED);
            return servletContext.getRequestDispatcher("/message.jsp");
        } catch (SQLException e) {
            request.setAttribute("error.type", MessageError.SQL_ERROR);
            request.setAttribute("error.exception", e);
            return servletContext.getRequestDispatcher("/error.jsp");
        }
    }
}
