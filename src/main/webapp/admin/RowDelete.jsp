<%@ page import="airline.model.Table" %>
<%@ page contentType="application/xhtml+xml; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%
    TableRow row = (TableRow) request.getAttribute("url.row");
    int rowNb = (Integer) request.getAttribute("rowNb");
    Table table = (Table) request.getAttribute("url.table");
    request.setAttribute("title", "Suppression d'une ligne ");
%>
<jsp:include page="/admin/header.jsp"/>

<form action="<%= request.getAttribute("baseURL") %>/admin/table/<%=table.getName()%>/<%= rowNb %>delete" method="post">
    <div>
        Voulez-vous vraiment supprimer cette table ?<br/>
        <ul>
            <%
                for (Map.Entry<TablesColumns, String> entry : row) {
            %>
            <li>
                <%=entry.getKey().getName() %> : <%= entry.getValue() %>
            </li>
            <%}%>
        </ul>
        <input type="submit" value="supprimer"/>&nbsp;
        <a class="button" href="<%= request.getAttribute("baseURL") %>/admin/table/<%=table.getName()%>"/>retour
    </a>
</div>
</form>

<jsp:include page="/admin/footer.jsp"/>
