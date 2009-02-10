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

package airline.filter;

import com.google.inject.Injector;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Feb 8, 2009
 * Time: 3:26:50 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractInjectableFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext context = filterConfig.getServletContext();
        Injector injector = (Injector) context.getAttribute(Injector.class.getName());
        if (injector == null) {
            throw new ServletException("Guice Injector not found");
        }
        injector.injectMembers(this);
    }
}
