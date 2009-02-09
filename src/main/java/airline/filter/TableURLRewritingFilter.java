package airline.filter;

import airline.servlet.enumeration.Action;
import airline.servlet.enumeration.Context;
import airline.tables.ActionHandler;
import airline.tables.context.action.*;

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

        String table = null;
        String row = null;
        String field = null;
        Action action = null;
        Context context = null;
        ActionHandler handler = null;

        // /admin/table
        // /admin/table/
        Pattern tablesPattern = Pattern.compile("^/admin/table(/)?$");

        // /admin/table/add
        // /admin/table/---/
        // /admin/table/---/edit
        // /admin/table/---/delete
        Pattern tablePattern = Pattern.compile("^/admin/table/(add|([^/]+)/(delete|edit)?)$");

        // /admin/table/---/row/add
        // /admin/table/---/row/---/edit
        // /admin/table/---/row/---/delete
        Pattern rowPattern = Pattern.compile("^/admin/table/([^/]+)/row/(add|(\\d+)/(edit|delete))$");

        // /admin/table/---/field/add
        // /admin/table/---/field/---/edit
        // /admin/table/---/field/---/delete
        Pattern fieldPattern = Pattern.compile("^/admin/table/([^/]+)/field/(add|([^/]+)/(edit|delete))$");

        Matcher tablesMatcher = tablesPattern.matcher(url);
        Matcher tableMatcher = tablePattern.matcher(url);
        Matcher rowMatcher = rowPattern.matcher(url);
        Matcher fieldMatcher = fieldPattern.matcher(url);

        if (tablesMatcher.matches()) {
            context = Context.TABLES;
            action = Action.SHOW;
            handler = new ShowTables();
        } else if (tableMatcher.matches()) {
            context = Context.TABLE;
            if ("add".equals(tableMatcher.group(1))) {// (add|([^/]+)/(delete|edit)?)
                action = Action.ADD;
                handler = new AddTable();
            } else { // ([^/]+)/(delete|edit)?
                table = tableMatcher.group(2); // ([^/]+)
                if (tableMatcher.group(3) != null) { // (delete|edit)?
                    if ("delete".equals(tableMatcher.group(3))) {
                        action = Action.DELETE;
                        handler = new DeleteTable();
                    } else {
                        //action = Action.EDIT;
                        //handler = new EditTable();
                    }
                } else {
                    action = Action.SHOW;
                    handler = new ShowTable();
                }
            }
        } else if (rowMatcher.matches()) {
            context = Context.ROW;
            table = rowMatcher.group(1); // ([^/]+)
            if ("add".equals(rowMatcher.group(2))) { // (add|(\d+)/(edit|delete))
                        action = Action.ADD;
                        handler = new AddRow();
            } else { // (\d+)/(edit|delete)
                row = rowMatcher.group(3); // (\d+)
                if ("edit".equals(rowMatcher.group(4))) { // (edit|delete)
                        action = Action.EDIT;
                        handler = new EditRow();
                } else {
                        action = Action.DELETE;
                        handler = new DeleteRow();
                }
            }
        } else if (fieldMatcher.matches()) {
            context = Context.FIELD;
            table = fieldMatcher.group(1);  // ([^/]+)
            if ("add".equals(fieldMatcher.group(2))) { // (add|([^/]+)/(edit|delete))
                req.setAttribute("url.action", Action.ADD);
                        action = Action.ADD;
                        handler = new AddField();
            } else { // ([^/]+)/(edit|delete)
                field = fieldMatcher.group(3); // ([^/]+)
                if ("edit".equals(fieldMatcher.group(4))) { // (edit|delete)
                        action = Action.EDIT;
                        handler = new EditField();
                } else {
                        action = Action.DELETE;
                        handler = new DeleteField();
                }
            }
        } else { // 404 not found
            ((HttpServletResponse) resp).sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        System.out.println("TableURLRewritingFilter:: " + url + " => " +
                " table=" + table +
                " row=" + row +
                " field=" + field +
                " action=" + action +
                " context=" + context
        );

        req.setAttribute("url.table", table);
        req.setAttribute("url.row", row);
        req.setAttribute("url.field", field);
        req.setAttribute("url.action", action);
        req.setAttribute("url.context", context);
        req.setAttribute("url.handler", handler);

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
