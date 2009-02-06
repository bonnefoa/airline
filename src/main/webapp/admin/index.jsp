<%@ page import="airline.model.User" %>
<%--
  Created by IntelliJ IDEA.
  User: dev
  Date: Jan 31, 2009
  Time: 4:57:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head><title>Simple jsp page</title></head>
  <body>
  <div id="menu">
      <ul>
          <li><a href="<%= request.getAttribute("baseURL") %>/">retour à l'accueil</a></li>
          <li><a href="<%= request.getAttribute("baseURL") %>/admin/sfw">requête SFW</a></li>
          <li><a href="<%= request.getAttribute("baseURL") %>/admin/tables">aficher les tables</a></li>
      </ul>
  </div>
  <% User user = (User) request.getSession().getAttribute("user");%>
  <div>
      bienvenue, <%= user.getLogin() %><br />
      voici la partie admin. Ne casse pas tout !
  </div>
  </body>
</html>