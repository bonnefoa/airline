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
import airline.tables.context.TableContextHandler;
import airline.model.Table;
import airline.model.TableColumn;
import airline.model.TableRow;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Feb 9, 2009
 * Time: 1:55:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class ShowTable extends TableContextHandler {
    public ShowTable() {
        init(Action.SHOW);
    }

    public RequestDispatcher get(HttpServletRequest request, HttpServletResponse response) {
        Table table = (Table) request.getAttribute("url.table");
        List<TableColumn> columns = airlineDAO.getTableColumns(table);
        List<TableRow> rows = airlineDAO.getTablesRows(table);
        request.setAttribute("columns", columns);
        request.setAttribute("rows", rows);
        return servletContext.getRequestDispatcher("/tables/TableShow.jsp");
    }

    public RequestDispatcher post(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
