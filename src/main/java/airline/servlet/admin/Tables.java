package airline.servlet.admin;

import airline.manager.ContextManager;
import airline.servlet.AbstractInjectableServlet;
import airline.servlet.enumeration.Action;
import airline.servlet.enumeration.Context;
import airline.servlet.enumeration.MessageError;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: dev
 * Date: Feb 4, 2009
 * Time: 9:28:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class Tables extends AbstractInjectableServlet {

    private ContextManager tableContext;
    private ContextManager rowContext;
    private ContextManager fieldContext;
    private ContextManager tablesContext;

    @Inject
    public void setTablesContext(@Named("Tables") ContextManager tablesContext) {
        this.tablesContext = tablesContext;
    }

    @Inject
    public void setTableContext(@Named("Table") ContextManager tableContext) {
        this.tableContext = tableContext;
    }

    @Inject
    public void setRowContext(@Named("Row") ContextManager rowContext) {
        this.rowContext = rowContext;
    }

    @Inject
    public void setFieldContext(@Named("Field") ContextManager fieldContext) {
        this.fieldContext = fieldContext;
    }


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = this.getServletContext();
        rowContext.init(servletContext);
        tableContext.init(servletContext);
        fieldContext.init(servletContext);
        tablesContext.init(servletContext);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Context context = (Context) request.getAttribute("url.context");
        Action action = (Action) request.getAttribute("url.action");

        RequestDispatcher dispatcher = null;

        switch (context) {
            case FIELD:
                switch (action) {
                    case ADD: // /admin/table/---/field/add
                        dispatcher = fieldContext.add(request, response);
                        break;
                    case DELETE: // /admin/table/---/field/---/delete
                        dispatcher = fieldContext.delete(request, response);
                        break;
                    case EDIT: // /admin/table/---/field/---/edit
                        dispatcher = fieldContext.edit(request, response);
                        break;
                }
                break;
            case ROW:
                switch (action) {
                    case ADD: // /admin/table/---/row/add
                        dispatcher = rowContext.add(request, response);
                        break;
                    case DELETE: // /admin/table/---/row/---/delete
                        dispatcher = rowContext.delete(request, response);
                        break;
                    case EDIT: // /admin/table/---/row/---/edit
                        dispatcher = rowContext.edit(request, response);
                        break;
                }
                break;
            case TABLE:
                switch (action) {
                    case ADD: // /admin/table/add
                        dispatcher = tableContext.add(request, response);
                        break;
                    case DELETE: // /admin/table/---/delete
                        dispatcher = tableContext.delete(request, response);
                        break;
                    case SHOW: // /admin/table/---/
                        dispatcher = tableContext.show(request, response);
                        break;
                    case EDIT: // /admin/table/---/edit
                        dispatcher = tableContext.edit(request, response);
                        break;
                }
                break;

            case TABLES: // /admin/table
                dispatcher = tablesContext.show(request, response);
                break;
        }

        if (dispatcher == null) {
            request.setAttribute("error.type", MessageError.UNIMPLEMENTED_METHOD);
            dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
        }
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
