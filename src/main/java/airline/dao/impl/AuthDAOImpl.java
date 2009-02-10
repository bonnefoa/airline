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

package airline.dao.impl;

import airline.dao.AuthDAO;
import airline.model.User;

/**
 * Created by IntelliJ IDEA.
 * User: dev
 * Date: 3 févr. 2009
 * Time: 21:08:41
 * To change this template use File | Settings | File Templates.
 */
public class AuthDAOImpl implements AuthDAO {
    public boolean isLoggedIn(User user) {
        return (user != null);
    }

    public boolean logIn(User user) {
        if (user == null || user.getLogin() == null || user.getPasswd() == null) {
            return false;
        }

        if ("admin".equals(user.getLogin()) && "adminadmin".equals(user.getPasswd())) {
            user.setLogged(true);
            return true;
        } else {
            user.setLogged(false);
            return false;
        }
    }
}
