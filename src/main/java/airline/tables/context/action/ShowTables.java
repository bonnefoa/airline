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

import airline.model.Table;
import airline.servlet.enumeration.Action;
import airline.tables.context.TablesContextHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Feb 9, 2009
 * Time: 1:55:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class ShowTables extends TablesContextHandler {
    public ShowTables() {
        init(Action.SHOW);
    }

    public RequestDispatcher get(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Table> tables = airlineDAO.getTables();
        request.setAttribute("tables", tables);
        return servletContext.getRequestDispatcher("/admin/TablesShow.jsp");
    }

    public RequestDispatcher post(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
