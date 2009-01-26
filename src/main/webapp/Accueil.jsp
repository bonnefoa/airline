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
  <p>
      Accueil , welcome , angfd Place your content here
  </p>
  <p>

    <form action="/AccueilServlet" method="Post" >
      <input type="submit" name="GRAOU"/>
    <!-- éléments du formulaire et autres éléments dans le formulaire -->
    </form>
  </p>
  </body>
</html>