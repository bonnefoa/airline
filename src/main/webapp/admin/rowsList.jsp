<%@ page contentType="application/xhtml+xml; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="airline.model.Table" %>
<%@ page import="airline.model.TablesColumns" %>
<%@ page import="java.sql.Types" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: dev
  Date: Jan 31, 2009
  Time: 4:57:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%
    Table table = (Table) request.getAttribute("url.table");
    List<TablesColumns> columns = (List<TablesColumns>) request.getAttribute("columns");
    request.setAttribute("title", "Détail de la table " + table.getName());
%>
<jsp:include page="/admin/header.jsp"/>

<h2>Description des champs</h2>

<table>
<thead>
<tr>
    <th>nom</th>
    <th>type</th>
    <th>clef primaire</th>
    <th>action</th>
</tr>
</thead>
<tbody>
<%
    for (TablesColumns column : columns) {
%>
<tr>
    <td><%=column.getName()%>
    </td>
    <td><%
        switch (column.getDataType()) {
            case Types.INTEGER:
                out.print("INTEGER");
                break;
            case Types.VARCHAR:
                out.print("VARCHAR");
                break;
            case Types.DATE:
                out.print("DATE");
                break;
            default:
                out.print("type inconnu");
                break;

        }
    %></td>
    <td><%=(column.isPrimaryKey()) ? "oui" : ""%>
    </td>
    <td>
        <a href="<%= request.getAttribute("baseURL") %>/admin/table/<%= table.getName()%>/edit?field=<%=column.getName()%>&amp;fieldAction=edit">
            <img src="<%= request.getAttribute("baseURL") %>/img/edit.png" alt="modifier" title="modifier"/>
        </a>&nbsp;
        <a href="<%= request.getAttribute("baseURL") %>/admin/table/<%= table.getName()%>/edit?field=<%=column.getName()%>&amp;fieldAction=delete">
            <img src="<%= request.getAttribute("baseURL") %>/img/delete.png" alt="supprimer" title="supprimer"/>
        </a>
    </td>
</tr>
<%
    }
%>
</tbody>
</table>
<a href="<%= request.getAttribute("baseURL") %>/admin/table/<%=table.getName()%>/edit">modifier la
table</a>

<h2>contenu de la table</h2>
<a href="<%= request.getAttribute("baseURL") %>/admin/table/<%=table.getName()%>/row/add">
<img src="<%= request.getAttribute("baseURL") %>/img/add.png" alt=""/>
Ajouter une ligne dans la table
</a>

<div>
<jsp:include page="/fragments/TableRows.jsp"/>
</div>
<jsp:include page="/admin/footer.jsp"/>