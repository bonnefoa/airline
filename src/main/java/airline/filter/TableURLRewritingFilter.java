package airline.filter;

import airline.servlet.enumeration.Action;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Agit comme une réécriture d'url dans la partie /admin/table
 * en ajoutant les attributs suivant dans la requête :
 * url.action : l'action demandée par l'utilisateur
 * url.table : la table demandée par l'utilisateur
 * url.row : le tuple demandé par l'utilisateur
 * <p/>
 * Afin d'éviter toute ambiguité, les noms de table/tuple seront suivis d'un slash ('/')
 * tandis que les éventuels verbes d'action ne le seront pas.
 */
public class TableURLRewritingFilter implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String pathInfo = ((HttpServletRequest) req).getPathInfo();
        String url;
        if (pathInfo == null) {
            url = ((HttpServletRequest) req).getServletPath();
        } else {
            url = ((HttpServletRequest) req).getServletPath() + pathInfo;
        }

        System.out.println("Rewriting url " + url);

        Pattern tableAdd = Pattern.compile("^/admin/table(/(add)?)?$");
        Pattern tableEdit = Pattern.compile("^/admin/table/([^/]+)/(edit|delete)$");
        Pattern rowAdd = Pattern.compile("^/admin/table/([^/]+)/(row(/add)?)?$");
        Pattern rowEdit = Pattern.compile("^/admin/table/([^/]+)/row/([0-9]+)/(edit|delete)$");

        Matcher tableAddMatcher = tableAdd.matcher(url);
        Matcher tableEditMatcher = tableEdit.matcher(url);
        Matcher rowAddMatcher = rowAdd.matcher(url);
        Matcher rowEditMatcher = rowEdit.matcher(url);

        if (tableAddMatcher.matches()) { // ajoute ou liste une table
            if (tableAddMatcher.group(2) != null) { // add est présent
                req.setAttribute("url.action", Action.ADD);
            } else {
                req.setAttribute("url.action", Action.SHOW);
            }
        } else if (tableEditMatcher.matches()) { // modifie ou supprime une table
            req.setAttribute("url.table", tableEditMatcher.group(1));
            if ("edit".equals(tableEditMatcher.group(2))) {
                req.setAttribute("url.action", Action.EDIT);
            } else {
                req.setAttribute("url.action", Action.DELETE);
            }
        } else if (rowAddMatcher.matches()) { // ajoute ou affiche un tuple
            req.setAttribute("url.table", rowAddMatcher.group(1));
            if (rowAddMatcher.group(3) != null) { // add présent
                req.setAttribute("url.action", Action.ADD);
            } else {
                req.setAttribute("url.action", Action.SHOW);
            }
        } else if (rowEditMatcher.matches()) { // modifie ou supprime un tuple
            if ("edit".equals(rowEditMatcher.group(3))) {
                req.setAttribute("url.action", Action.EDIT);
            } else {
                req.setAttribute("url.action", Action.DELETE);
            }

            req.setAttribute("url.table", rowEditMatcher.group(1));
            req.setAttribute("url.row", rowEditMatcher.group(2));
        } else { // 404 not found
            ((HttpServletResponse) resp).sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        System.out.println("rewriting : " +
                " table=" + req.getAttribute("url.table") +
                " row=" + req.getAttribute("url.row") +
                " action=" + req.getAttribute("url.action")
        );

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
