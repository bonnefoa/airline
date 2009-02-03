<%--
  Created by IntelliJ IDEA.
  User: dev
  Date: Jan 31, 2009
  Time: 4:33:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head><title>Vous n'êtes pas connectés !</title></head>
  <body>
  <h1>Vous n'êtes pas connectés !</h1>
  <div>
      <form method="post" action="/admin/">
          login : <input name="login" /><br />
          password : <input name="passwd" /><br />
          <br />
          <input type="submit" />
      </form>
  </div>
  </body>
</html>