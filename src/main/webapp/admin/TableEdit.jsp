<%@ page import="airline.model.Table" %>
<%@ page contentType="application/xhtml+xml; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%
    Table table = (Table) request.getAttribute("url.table");
    request.setAttribute("title", "Modification de la table " + table.getName());
%>
<jsp:include page="/admin/header.jsp"/>

<jsp:include page="/fragments/TableFields.jsp"/>

<jsp:include page="/admin/footer.jsp"/>