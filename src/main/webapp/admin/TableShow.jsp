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

<%@ page import="airline.model.Table" %>
<%@ page contentType="application/xhtml+xml; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%
    Table table = (Table) request.getAttribute("url.table");
    request.setAttribute("title", "Table " + table.getName());
%>
<jsp:include page="/admin/header.jsp"/>
<h2>champs de la table</h2>
<jsp:include page="/fragments/TableFields.jsp"/>
<a href="<%= request.getAttribute("baseURL") %>/admin/table/<%=table.getName()%>/field/add">ajouter un champ</a><br/>
<br/>
<a href="<%= request.getAttribute("baseURL") %>/admin/table/<%=table.getName()%>/delete">supprimer cette table</a>

<h2>contenu de la table</h2>
<a href="<%= request.getAttribute("baseURL") %>/admin/table/<%=table.getName()%>/row/add">ajouter une ligne</a><br/>
<jsp:include page="/fragments/TableRowsList.jsp"/>
<jsp:include page="/admin/footer.jsp"/>