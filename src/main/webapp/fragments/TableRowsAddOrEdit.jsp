<%@ page contentType="application/xhtml+xml; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="airline.criteria.enumeration.SqlConstraints" %>
<%@ page import="airline.model.Table" %>
<%@ page import="airline.model.TableRow" %>
<%@ page import="airline.model.TablesColumns" %>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page import="airline.servlet.enumeration.Context" %>
<%@ page import="airline.servlet.enumeration.Action" %>
<%@ page import="java.sql.Types" %>
<%--
    Affiche le contenu d'une table.
    @param columns : les colonnes de la table à afficher.
    @param url.action
    @param url.table
    @param url.row
    @param url.rowNb
--%>
<%
    Collection<TablesColumns> columns = (Collection<TablesColumns>) request.getAttribute("columns");
    Action action = (Action) request.getAttribute("url.action");
    Table table = (Table) request.getAttribute("url.table");
    TableRow row = (TableRow) request.getAttribute("url.row");
    int rowNb = (Integer) request.getAttribute("url.rowNb");
    if (row == null) {
        row = new TableRow();
    }


    StringBuilder formAction = new StringBuilder((String) request.getContextPath());
    formAction.append("/admin/table");
    formAction.append(table.getName());
    if (action == Action.ADD) {
        formAction.append("/field/add");
    } else if (action == Action.EDIT) {
        formAction.append('/');
        formAction.append(table.getName());
        formAction.append("/field/");
        formAction.append(rowNb);
        formAction.append("/edit");
    }
%>
<script type="text/javascript" src="<%= request.getContextPath() %>/script/TableRowsAddOrEdit.js"></script>
<form action="<%= formAction.toString() %>" method="post">
    <div>
        <table>
            <thead>
            <tr>
                <%
                    for (TablesColumns column : columns) {
                %>
                <th><%= column.getName() %>
                </th>
                <%
                    }
                %>
            </tr>
            </thead>
            <tbody>
            <tr>
                <%
                    for (TablesColumns column : columns) {
                %>
                <td>
                    <%
                        switch (column.getDataType()) {
                            case Types.INTEGER:
                    %><input class="INTEGER" value="<%=row.get(column)%>"/><%
                        break;
                    case Types.DATE:
                %><input class="DATE" value="<%=row.get(column)%>"/><%
                        break;
                    default:
                %><input class="VARCHAR" value="<%=row.get(column)%>"/><%
                            break;
                    }
                %>
                </td>
                <%
                    }
                %>
            </tr>
            </tbody>
        </table>
        <% if (action == Action.ADD) {%>
        <input type="submit" id="addRow" value="Ajouter une ligne"/>
        <% } %>
    </div>
</form>