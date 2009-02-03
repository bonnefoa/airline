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
