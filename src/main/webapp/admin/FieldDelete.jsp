<%@ page import="airline.model.Table" %>
<%@ page import="airline.model.TablesColumns" %>
<%@ page contentType="application/xhtml+xml; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%
    Table table = (Table) request.getAttribute("url.table");
    TablesColumns column = (TablesColumns) request.getAttribute("url.field");
    request.setAttribute("title", "Suppression de la colonne " + column.getName() +  " de la table " + table.getName());
%>
<jsp:include page="/admin/header.jsp"/>

<form action="<%= request.getAttribute("baseURL") %>/admin/table/<%=table.getName()%>/field/<%=column.getName()%>/delete" method="post">
    <div>
        Voulez-vous vraiment supprimer ce champ ?<br/>
        <input type="submit" value="supprimer"/>&nbsp;
        <a class="button" href="<%= request.getAttribute("baseURL") %>/admin/table/<%=table.getName()%>/">retour</a>
    </div>
</form>

<jsp:include page="/admin/footer.jsp"/>
