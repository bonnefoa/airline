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
<%--
    Affiche le contenu d'une table.
    @param columns : les colonnes de la table à afficher.
    @param rows : ses lignes.
    @param url.table
--%>
<%
    Collection<TablesColumns> columns = (Collection<TablesColumns>) request.getAttribute("columns");
    Collection<TableRow> rows = (Collection<TableRow>) request.getAttribute("rows");
    Table table = (Table) request.getAttribute("url.table");

    if (rows != null && rows.size() == 0) {
%>
Aucune ligne à afficher !<br/>
<%
} else if (columns != null && rows != null) {
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
    <th>Action</th>
</tr>
</thead>
<tbody>
<%
    for (TableRow row : rows) {
        int nb = 0;
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
    <td>
        <a href="<%= request.getContextPath() %>/admin/table/<%= table.getName()%>/row/<%=nb%>/edit">
            <img src="<%= request.getContextPath() %>/img/edit.png" alt="modifier" title="modifier"/>
        </a>&nbsp;
        <a href="<%= request.getContextPath() %>/admin/table/<%= table.getName()%>/row/<%=nb%>/delete">
            <img src="<%= request.getContextPath() %>/img/delete.png" alt="supprimer" title="supprimer"/>
        </a>
    </td>
</tr>
<%
        nb++;
    }
%>
</tbody>
</table>
<%
    }
%>