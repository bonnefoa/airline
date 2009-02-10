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

package airline.dao;

import airline.model.User;

import javax.servlet.http.HttpSession;

/**
 * Created by IntelliJ IDEA.
 * User: dev
 * Date: 3 févr. 2009
 * Time: 21:08:28
 * To change this template use File | Settings | File Templates.
 */
public interface AuthDAO {
    boolean isLoggedIn(User user);

    boolean logIn(User user);
}
