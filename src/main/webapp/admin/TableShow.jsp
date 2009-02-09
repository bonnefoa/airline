<%@ page import="airline.model.Table" %>
<%@ page contentType="application/xhtml+xml; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%
    Table table = (Table) request.getAttribute("url.table");
    request.setAttribute("title", "Table " + table.getName());
%>
<jsp:include page="/admin/header.jsp"/>
<jsp:include page="/fragments/TableFields.jsp"/>
<a href="<%= request.getAttribute("baseURL") %>/admin/table/<%=table.getName()%>/delete">supprimer cette table</a>
<jsp:include page="/admin/footer.jsp"/>