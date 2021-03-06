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

import airline.dao.AuthDAO;
import airline.model.User;
import airline.servlet.enumeration.Context;
import airline.servlet.enumeration.Action;
import com.google.inject.Inject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Verifie si l'utilisateur a bien les droits necessaires pour faire l'action demandee.
 */
public class AdminFilter extends AbstractInjectableFilter {
    private AuthDAO auth;
    private FilterConfig filterConfig;

    @Inject
    public void setAuth(AuthDAO auth) {
        this.auth = auth;
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        super.init(filterConfig);
        this.filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // Set standard HTTP/1.1 no-cache headers.
        ((HttpServletResponse) response).setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

        // Set standard HTTP/1.0 no-cache header.
        ((HttpServletResponse) response).setHeader("Pragma", "no-cache");

        HttpSession session = ((HttpServletRequest) request).getSession();

        Action action = (Action) request.getAttribute("url.action");
        User user = (User) session.getAttribute("user");
        boolean isLogged = auth.isLoggedIn(user);

        if (action == null || isLogged || action == Action.SHOW) {
            chain.doFilter(request, response);
        } else {
            RequestDispatcher dispatcher = filterConfig.getServletContext().getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    public void destroy() {
    }
}
