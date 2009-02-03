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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Simple jsp page</title></head>
<body>
<div id="connectionBar">
    <a href="admin/">partie admin</a>
</div>

<div>
    <form action="accueil" method="post">
        Rechercher un avion : <br/>
        <input name="q"/><br/>
        <input type="submit"/>
    </form>
    <%
        try{
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
        } catch(NullPointerException e) {
            e.printStackTrace();
        }
    %>
</div>
</body>
</html>