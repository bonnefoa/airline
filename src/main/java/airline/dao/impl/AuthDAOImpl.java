package airline.dao.impl;

import airline.dao.AuthDAO;
import airline.model.User;

import javax.servlet.http.HttpSession;

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
        if(user == null || user.getLogin() == null || user.getPasswd() == null) {
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
