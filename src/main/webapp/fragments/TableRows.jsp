<%@ page import="airline.model.TablesColumns" %>
<%@ page import="java.util.List" %>
<%@ page import="airline.model.TableRow" %>
<%@ page import="java.util.Set" %>
<%--
    Affiche le contenu d'une table.
    @param columns : les colonnes de la table à afficher.
    @param rows : ses lignes.
--%>
<%
    List<TablesColumns> columns = (List<TablesColumns>) request.getAttribute("columns");
    Set<TableRow> rows = (Set<TableRow>) request.getAttribute("rows");

    if (columns != null && rows != null) {
%>
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
    <%
        for (TableRow row : rows) {
    %>
    <tr>
        <%
            for (TablesColumns column : columns) {
        %>
        <td><%= row.get(column) %>
        </td>
        <%
            }
        %>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<%
    }
%>