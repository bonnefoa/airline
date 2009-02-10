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

package airline.tables;

import airline.servlet.enumeration.MessageError;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Feb 9, 2009
 * Time: 1:44:57 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ActionHandler {
    void init(ServletContext servletContext);

    MessageError checkContext(ServletRequest request);

    RequestDispatcher get(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response);

    RequestDispatcher post(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response);
}
