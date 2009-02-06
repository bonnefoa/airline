<%@ page import="java.util.List" %>
<%@ page import="airline.model.Table" %>
<%@ page import="java.util.Map" %>

<%--
  Created by IntelliJ IDEA.
  User: sora
  Date: 25 janv. 2009
  Time: 19:36:44
  To change this template use File | Settings | File Templates.
--%>
<%
    request.setAttribute("title", "Bienvenue");
%>
<jsp:include page="header.jsp"/>
<form action="<%= request.getAttribute("baseURL") %>/accueil" method="post">
    <div>
        Rechercher un avion : <br/>
        <input name="q"/><br/>
        <input type="submit"/>
    </div>
</form>
<%
    try {
        Map<String, Table> list = (Map<String, Table>) request.getAttribute("graou");
        if (list != null && list.size() != 0) {
            out.println("<ul>");
            for (Table table : list.values()) {
                out.println(table.getName());
            }
            out.println("</ul>");
        } else {
            out.println("rien a afficher");
        }
    } catch (NullPointerException e) {
        e.printStackTrace();
    }
%>
<jsp:include page="footer.jsp"/>