package airline.servlet.admin;

import airline.guiceBindings.Servlet;
import airline.manager.RequestManager;
import airline.model.Table;
import airline.model.TableRow;
import airline.servlet.enumeration.Action;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
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
public class Tables extends HttpServlet {

    RequestManager tableManager;
    RequestManager rowManager;

    @Inject
    public void setTableManager(@Named("Table") RequestManager tableManager) {
        this.tableManager = tableManager;
    }

    @Inject
    public void setRowManager(@Named("Row") RequestManager rowManager) {
        this.rowManager = rowManager;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        Injector injector = Guice.createInjector(new Servlet());
        injector.injectMembers(this);
        rowManager.init(this.getServletContext());
        tableManager.init(this.getServletContext());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Action action = (Action) request.getAttribute("url.action");
        Table table = (Table) request.getAttribute("url.table");
        TableRow row = (TableRow) request.getAttribute("url.row");

        RequestDispatcher dispatcher = null;

        if (table == null && row == null) { // POST /table/(add)
            switch (action) {
                case ADD:
                    dispatcher = tableManager.add(request, response);
                    break;
                case DELETE:
                case EDIT:
                case SHOW:
                    handleErrorCase(request, response);
                    break;
            }
        } else if (table != null && row == null) { // POST table/foobar/(add|edit|delete)
            switch (action) {
                case ADD:
                    dispatcher = rowManager.add(request, response);
                    break;
                case DELETE:
                    dispatcher = tableManager.delete(request, response);
                    break;
                case EDIT:
                    dispatcher = tableManager.edit(request, response);
                    break;
                case SHOW:
                    handleErrorCase(request, response);
                    break;
            }
        } else if (table != null && row != null) { // POST table/foobar/row/42/(edit|delete)
            switch (action) {
                case DELETE:
                    dispatcher = rowManager.delete(request, response);
                    break;
                case EDIT:
                    dispatcher = rowManager.edit(request, response);
                    break;
                case ADD:
                case SHOW:
                    handleErrorCase(request, response);
                    break;
            }
        }

        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Action action = (Action) request.getAttribute("url.action");
        Table table = (Table) request.getAttribute("url.table");
        TableRow row = (TableRow) request.getAttribute("url.row");

        RequestDispatcher dispatcher = null;

        if (table == null && row == null) { // GET /table/(show|add)
            switch (action) {
                case ADD:
                    dispatcher = tableManager.add(request, response);
                    break;
                case SHOW:
                    dispatcher = tableManager.show(request, response);
                    break;
                case DELETE:
                case EDIT:
                    handleErrorCase(request, response);
                    break;
            }
        } else if (table != null && row == null) { // GET table/foobar/(show|add|edit|delete)
            switch (action) {
                case ADD:
                    dispatcher = rowManager.add(request, response);
                    break;
                case DELETE:
                    dispatcher = tableManager.delete(request, response);
                    break;
                case EDIT:
                    dispatcher = tableManager.edit(request, response);
                    break;
                case SHOW:
                    dispatcher = rowManager.show(request, response);
                    break;
            }
        } else if (table != null && row != null) { // GET table/foobar/row/42/(edit|delete)
            switch (action) {
                case DELETE:
                    dispatcher = rowManager.delete(request, response);
                    break;
                case EDIT:
                    dispatcher = rowManager.edit(request, response);
                    break;
                case ADD:
                case SHOW:
                    handleErrorCase(request, response);
                    break;
            }
        }

        dispatcher.forward(request, response);
    }

    private void handleErrorCase(HttpServletRequest request, HttpServletResponse response) {

    }
}
