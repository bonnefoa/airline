package airline.filter;

import airline.servlet.enumeration.Action;
import airline.servlet.enumeration.Context;

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

        // /admin/table
        // /admin/table/
        Pattern tables = Pattern.compile("^/admin/table(/)?$");

        // /admin/table/add
        // /admin/table/---/
        // /admin/table/---/edit
        // /admin/table/---/delete
        Pattern table = Pattern.compile("^/admin/table/(add|([^/]+)/(delete|edit)?)?$");

        // /admin/table/---/row/add
        // /admin/table/---/row/---/edit
        // /admin/table/---/row/---/delete
        Pattern row = Pattern.compile("^/admin/table/([^/]+)/row/(add|(\\d+)/(edit|delete))$");

        // /admin/table/---/field/add
        // /admin/table/---/field/---/edit
        // /admin/table/---/field/---/delete
        Pattern field = Pattern.compile("^/admin/table/([^/]+)/field/(add|([^/]+)/(edit|delete))$");

        Matcher tablesMatcher = tables.matcher(url);
        Matcher tableMatcher = table.matcher(url);
        Matcher rowMatcher = row.matcher(url);
        Matcher fieldMatcher = field.matcher(url);

        if (tablesMatcher.matches()) {
            req.setAttribute("url.context", Context.TABLES);
            req.setAttribute("url.action", Action.SHOW);
        } else if (tableMatcher.matches()) {
            req.setAttribute("url.context", Context.TABLE);
            if (tableMatcher.group(1) != null) { // (add|([^/]+)/(delete|edit)?)?
                if ("add".equals(tableMatcher.group(1))) {
                    req.setAttribute("url.action", Action.ADD);
                } else { // ([^/]+)/(delete|edit)?
                    req.setAttribute("url.table", tableMatcher.group(2)); // ([^/]+)
                    if (tableMatcher.group(3) != null) { // (delete|edit)?
                        if ("delete".equals(tableMatcher.group(3))) {
                            req.setAttribute("url.action", Action.DELETE);
                        } else {
                            req.setAttribute("url.action", Action.EDIT);
                        }
                    } else {
                        req.setAttribute("url.action", Action.SHOW);
                    }
                }
            } else {
                req.setAttribute("url.action", Action.SHOW);
            }
        } else if (rowMatcher.matches()) {
            req.setAttribute("url.context", Context.ROW);
            req.setAttribute("url.table", rowMatcher.group(1)); // ([^/]+)
            if ("add".equals(rowMatcher.group(2))) { // (add|(\d+)/(edit|delete))
                req.setAttribute("url.action", Action.ADD);
            } else { // (\d+)/(edit|delete)
                req.setAttribute("url.row", rowMatcher.group(3)); // (\d+)
                if ("edit".equals(rowMatcher.group(4))) { // (edit|delete)
                    req.setAttribute("url.action", Action.EDIT);
                } else {
                    req.setAttribute("url.action", Action.DELETE);
                }
            }
        } else if (fieldMatcher.matches()) {
            req.setAttribute("url.context", Context.FIELD);
            req.setAttribute("url.table", fieldMatcher.group(1)); // ([^/]+)
            if ("add".equals(fieldMatcher.group(2))) { // (add|([^/]+)/(edit|delete))
                req.setAttribute("url.action", Action.ADD);
            } else { // ([^/]+)/(edit|delete)
                req.setAttribute("url.field", fieldMatcher.group(3)); // ([^/]+)
                if ("edit".equals(fieldMatcher.group(4))) { // (edit|delete)
                    req.setAttribute("url.action", Action.EDIT);
                } else {
                    req.setAttribute("url.action", Action.DELETE);
                }
            }
        } else { // 404 not found
            ((HttpServletResponse) resp).sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        System.out.println("TableURLRewritingFilter:: " + url + " => " +
                " table=" + req.getAttribute("url.table") +
                " row=" + req.getAttribute("url.row") +
                " field=" + req.getAttribute("url.field") +
                " action=" + req.getAttribute("url.action") +
                " context=" + req.getAttribute("url.context")
        );

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
