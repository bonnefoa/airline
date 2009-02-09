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
<jsp:include page="/fragments/TableRows.jsp"/>
<jsp:include page="/admin/footer.jsp"/>