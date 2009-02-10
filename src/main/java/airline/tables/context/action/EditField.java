package airline.tables.context.action;

import airline.servlet.enumeration.Action;
import airline.servlet.enumeration.MessageAction;
import airline.servlet.enumeration.MessageError;
import airline.tables.context.FieldContextHandler;
import airline.model.Table;
import airline.model.TablesColumns;
import airline.criteria.model.AlterFieldRequest;
import airline.criteria.model.CreateTableRequest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Feb 9, 2009
 * Time: 1:55:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class EditField extends FieldContextHandler {
    public EditField() {
        init(Action.EDIT);
    }

    public RequestDispatcher get(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
        Table table = (Table) request.getAttribute("url.table");
        List<TablesColumns> columns = airlineDAO.getTablesColumns(table);
        request.setAttribute("columns", columns);
        return servletContext.getRequestDispatcher("/admin/FieldEdit.jsp");
    }

    public RequestDispatcher post(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
        Table table = (Table) request.getAttribute("url.table");
        Map<TablesColumns, TablesColumns> columnsMap = new HashMap<TablesColumns, TablesColumns>();
        TablesColumns oldField = (TablesColumns) request.getAttribute("url.field");
        TablesColumns newField = new TablesColumns();

        try {
            int dataType = Integer.parseInt(request.getParameter("type"));
            String fieldName = request.getParameter("name");
            boolean primary = "on".equals(request.getParameter("primary"));

            newField.setDataType(dataType);
            newField.setName(fieldName);
            newField.setPrimaryKey(primary);

            System.out.println("dataType" + dataType +
                    "fieldName" + fieldName +
                    "primary" + primary);

            columnsMap.put(oldField, newField);

            airlineDAO.executeRequest(new AlterFieldRequest(table, columnsMap));
            request.setAttribute("action.done", MessageAction.FIELD_EDITED);
            return servletContext.getRequestDispatcher("/message.jsp");
        } catch (SQLException e) {
            request.setAttribute("error.type", MessageError.SQL_ERROR);
            request.setAttribute("error.exception", e);
            return servletContext.getRequestDispatcher("/error.jsp");
        } catch (NumberFormatException e) {
            request.setAttribute("error.type", MessageError.WRONG_PARAMETER);
            request.setAttribute("error.exception", e);
            return servletContext.getRequestDispatcher("/error.jsp");
        }

    }
}
