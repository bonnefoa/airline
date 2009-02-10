<%--

    Copyright (C) 2009 Anthonin Bonnefoy and David Duponchel
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
            http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

--%>

<%@ page contentType="application/xhtml+xml; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="airline.model.Table" %>
<%@ page import="airline.model.TableColumn" %>
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
    List<TableColumn> columns = (List<TableColumn>) request.getAttribute("columns");
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
    for (TableColumn column : columns) {
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
        <a href="<%= request.getContextPath() %>/admin/table/<%= table.getName()%>/edit?field=<%=column.getName()%>&amp;fieldAction=edit">
            <img src="<%= request.getContextPath() %>/img/edit.png" alt="modifier" title="modifier"/>
        </a>&nbsp;
        <a href="<%= request.getContextPath() %>/admin/table/<%= table.getName()%>/edit?field=<%=column.getName()%>&amp;fieldAction=delete">
            <img src="<%= request.getContextPath() %>/img/delete.png" alt="supprimer" title="supprimer"/>
        </a>
    </td>
</tr>
<%
    }
%>
</tbody>
</table>
<a href="<%= request.getContextPath() %>/admin/table/<%=table.getName()%>/edit">modifier la
table</a>

<h2>contenu de la table</h2>
<a href="<%= request.getContextPath() %>/admin/table/<%=table.getName()%>/row/add">
<img src="<%= request.getContextPath() %>/img/add.png" alt=""/>
Ajouter une ligne dans la table
</a>

<div>
<jsp:include page="/fragments/TableRowsList.jsp"/>
</div>
<jsp:include page="/admin/footer.jsp"/>