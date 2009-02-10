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

package airline.servlet.admin;

import airline.servlet.AbstractInjectableServlet;
import airline.servlet.enumeration.MessageError;
import airline.tables.ActionHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: dev
 * Date: Feb 4, 2009
 * Time: 9:28:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class Tables extends AbstractInjectableServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionHandler handler = (ActionHandler) request.getAttribute("url.handler");

        RequestDispatcher dispatcher = handler.post(this.getServletContext(), request, response);

        if (dispatcher == null) {
            request.setAttribute("error.type", MessageError.UNIMPLEMENTED_METHOD);
            dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
        }
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionHandler handler = (ActionHandler) request.getAttribute("url.handler");

        RequestDispatcher dispatcher = handler.get(this.getServletContext(), request, response);

        if (dispatcher == null) {
            request.setAttribute("error.type", MessageError.UNIMPLEMENTED_METHOD);
            dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
        }
        dispatcher.forward(request, response);
    }
}
