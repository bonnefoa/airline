<%--
  Created by IntelliJ IDEA.
  User: dev
  Date: 3 févr. 2009
  Time: 21:40:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Simple jsp page</title></head>
<body>
<div>
    <%
        Boolean failed = (Boolean) request.getAttribute("loginFailed");
        if (failed != null && failed) {
    %>
    <div>LOGIN FAILED !</div>
    <%
        }
    %>
    <form action="<%= request.getAttribute("baseURL") %>/login" method="post">
        login : <input name="login"/><br/>
        password : <input type="password" name="passwd"/><br/>
        <br/>
        <input type="submit"/>
    </form>
</div>
</body>
</html>