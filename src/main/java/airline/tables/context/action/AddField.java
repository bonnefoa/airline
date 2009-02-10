package airline.tables.context.action;

import airline.servlet.enumeration.Action;
import airline.servlet.enumeration.MessageError;
import airline.servlet.enumeration.MessageAction;
import airline.tables.context.FieldContextHandler;
import airline.model.Table;
import airline.model.TableColumn;
import airline.criteria.model.CreateFieldRequest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.ArrayList;
import java.sql.Types;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Feb 9, 2009
 * Time: 1:55:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class AddField extends FieldContextHandler {
    private static final int DEFAULT_DATATYPE = Types.VARCHAR;
    public AddField() {
        init(Action.ADD);
    }

    public RequestDispatcher get(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {

        Table table = (Table) request.getAttribute("url.table");
        List<TableColumn> columns = airlineDAO.getTableColumns(table);
        request.setAttribute("columns", columns);
        return servletContext.getRequestDispatcher("/admin/FieldAdd.jsp");
    }

    public RequestDispatcher post(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
        Table table = (Table) request.getAttribute("url.table");
        List<TableColumn> columns = generateColumns(request);
        if (columns == null) {
            request.setAttribute("error.type", MessageError.EMPTY_FIELD);
            return servletContext.getRequestDispatcher("/error.jsp");
        }

        try {
            airlineDAO.executeRequest(new CreateFieldRequest(table, columns));
            request.setAttribute("action.done", MessageAction.FIELD_ADDED);
            return servletContext.getRequestDispatcher("/message.jsp");
        } catch (SQLException e) {
            request.setAttribute("error.type", MessageError.SQL_ERROR);
            request.setAttribute("error.exception", e);
            return servletContext.getRequestDispatcher("/error.jsp");
        }
    }

    private List<TableColumn> generateColumns(HttpServletRequest request) {
        String[] names = request.getParameterValues("name");
        String[] types = request.getParameterValues("type");
        String[] primarys = request.getParameterValues("primary");


        if (names != null && names.length != 0 &&
                types != null && types.length != 0 &&
                primarys != null && primarys.length != 0 &&
                names.length == types.length && types.length == primarys.length
                ) {

            List<TableColumn> columns = new ArrayList<TableColumn>();

            boolean allNotEmpty = true;
            TableColumn primaryKey = null;

            for (int i = 0; i < primarys.length && allNotEmpty; i++) {

                allNotEmpty = allNotEmpty && names[i] != null && names[i].length() != 0;
                allNotEmpty = allNotEmpty && types[i] != null && types[i].length() != 0;
                allNotEmpty = allNotEmpty && primarys[i] != null && primarys[i].length() != 0;

                TableColumn column = new TableColumn();
                column.setName(names[i]);
                try {
                    column.setDataType(Integer.parseInt(types[i]));
                } catch (NumberFormatException e) {
                    column.setDataType(DEFAULT_DATATYPE);
                }
                if ("on".equals(primarys[i])) {
                    primaryKey = column;
                }
                columns.add(column);
            }

            if (primaryKey != null) {
                primaryKey.setPrimaryKey(true);
            }


            if (allNotEmpty) {
                return columns;
            }
        }
        return null;
    }
}
