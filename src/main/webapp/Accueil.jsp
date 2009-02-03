<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: sora
  Date: 25 janv. 2009
  Time: 19:36:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="accueil" class="airline.beans.Accueil"/>
<!--jsp:useBean id="accueil" class="airline.servlet.Accueil" /-->
<html>
<head><title>Simple jsp page</title></head>
<body>
<div id="connectionBar">
    <a href="/admin/">partie admin</a>
</div>

<div>
    <form action="/Accueil" method="post">
        Rechercher un avion : <br/>
        <input name="q"/><br/>
        <input type="submit"/>
    </form>
    <%
        List<String> list = accueil.getList();
        if (list.size() != 0) {
            out.println("<ul>");
            for (String s : list) {
                out.println("<li>" + s + "</li>");
            }
            out.println("</ul>");
        }

    %>
</div>
<p>

<form action="/AccueilServlet" method="Post">
    <input type="submit" name="GRAOU"/>
    <!-- éléments du formulaire et autres éléments dans le formulaire -->
</form>
</p>
</body>
</html>