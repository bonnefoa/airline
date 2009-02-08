<%@ page import="airline.model.Table" %>
<%@ page contentType="application/xhtml+xml; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%
    Table table = (Table) request.getAttribute("url.table");
    request.setAttribute("title", "Suppression de la table " + table.getName());
%>
<jsp:include page="/admin/header.jsp"/>

<form action="<%= request.getAttribute("baseURL") %>/admin/table/<%=table.getName()%>/delete" method="post">
    <div>
        Voulez-vous vraiment supprimer cette table ?<br/>
        <input type="submit" value="supprimer"/>&nbsp;
        <a class="button" href="<%= request.getAttribute("baseURL") %>/admin/table">retour</a>
    </div>
</form>

<jsp:include page="/admin/footer.jsp"/>