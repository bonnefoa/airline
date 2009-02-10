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
<%@ page import="airline.model.User" %>
<%@ page import="java.util.Map" %>
<%--
  Created by IntelliJ IDEA.
  User: dev
  Date: Jan 31, 2009
  Time: 4:57:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%
    request.setAttribute("title", "Liste des tables");
%>
<jsp:include page="header.jsp"/>

<%
    Map<String, Table> tables = (Map<String, Table>) request.getAttribute("tables");
%>

<a href="<%= request.getAttribute("baseURL") %>/admin/table/add">
    <img src="<%= request.getAttribute("baseURL") %>/img/add.png" alt=""/>
    ajouter une table
</a><br/>

<%
    if (tables != null && tables.size() != 0) {
%>
<table>
<thead>
<tr>
    <th>nom</th>
    <th>schema</th>
    <th>type</th>
    <th>actions</th>
</tr>
</thead>
<tbody>
<%
    for (Table table : tables.values()) {
%>
<tr>
    <%
    %>
    <td>
        <a href="<%= request.getAttribute("baseURL") %>/admin/table/<%= table.getName()%>/">
            <%= table.getName() %>
        </a>
    </td>
    <td><%=table.getSchema() %>
    </td>
    <td><%=table.getType() %>
    </td>
    <td>
        <!-- renommage : pas encore ;) -->
        <!--
        <a href="<%= request.getAttribute("baseURL") %>/admin/table/<%= table.getName()%>/edit">
            <img src="<%= request.getAttribute("baseURL") %>/img/edit.png" alt="modifier" title="modifier"/>
        </a>&nbsp;
        -->
        <a href="<%= request.getAttribute("baseURL") %>/admin/table/<%= table.getName()%>/delete">
            <img src="<%= request.getAttribute("baseURL") %>/img/delete.png" alt="supprimer" title="supprimer"/>
        </a>
    </td>
</tr>
<%
    }
%>
</tbody>
</table>
total : <%= tables.size() %> table(s).
<%
} else {
%>
Aucune table n'est disponible !
<%
    }
%>
<jsp:include page="footer.jsp"/>