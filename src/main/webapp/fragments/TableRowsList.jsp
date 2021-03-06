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
<%@ page import="airline.criteria.enumeration.SqlConstraints" %>
<%@ page import="airline.model.Table" %>
<%@ page import="airline.model.TableRow" %>
<%@ page import="airline.model.TableColumn" %>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.net.URLEncoder" %>
<%--
    Affiche le contenu d'une table.
    @param columns : les colonnes de la table à afficher.
    @param rows : ses lignes.
    @param url.table
--%>
<%
    Collection<TableColumn> columns = (Collection<TableColumn>) request.getAttribute("columns");
    Collection<TableRow> rows = (Collection<TableRow>) request.getAttribute("rows");
    Table table = (Table) request.getAttribute("url.table");
    boolean logged = session.getAttribute("user") != null;

    if (table == null) {
%>
Aucune table à afficher !<br/>
<%
} else if (rows != null && rows.size() == 0) {
%>
Aucune ligne à afficher !<br/>
<%
} else if (columns != null && rows != null) {
%>
<table>
<thead>
<tr>
    <%
        for (TableColumn column : columns) {
    %>
    <th><%= StringEscapeUtils.escapeHtml(column.getName()) %>
    </th>
    <%
        }
    %>
    <%
        if (logged) {
    %>
    <th>Action</th>
    <%
        }
    %>
</tr>
</thead>
<tbody>
<%
    int nb = 0;
    for (TableRow row : rows) {
%>
<tr>
    <%
        for (TableColumn column : columns) {
    %>
    <td><%= StringEscapeUtils.escapeHtml(row.get(column)) %>
    </td>
    <%
        }
    %>
    <%
        if (logged) {
    %>
    <td>
        <a href="<%= request.getContextPath() %>/table/<%= URLEncoder.encode(table.getName(), "UTF-8")%>/row/<%=nb%>/edit">
            <img src="<%= request.getContextPath() %>/img/edit.png" alt="modifier" title="modifier"/>
        </a>&nbsp;
        <a href="<%= request.getContextPath() %>/table/<%= URLEncoder.encode(table.getName(), "UTF-8")%>/row/<%=nb%>/delete">
            <img src="<%= request.getContextPath() %>/img/delete.png" alt="supprimer" title="supprimer"/>
        </a>
    </td>
    <%
        }
    %>
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