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
        if(user == null) {
            return false;
        }
        return user.isLogged();
    }

    public boolean logIn(User user) {
        user.setLogged(true);
        return true;
    }
}
