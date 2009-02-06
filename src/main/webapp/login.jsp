<%--
  Created by IntelliJ IDEA.
  User: dev
  Date: 3 févr. 2009
  Time: 21:40:22
  To change this template use File | Settings | File Templates.
--%>

<%
    request.setAttribute("title", "login");
%>
<jsp:include page="header.jsp"/>
<%
    Boolean failed = (Boolean) request.getAttribute("loginFailed");
    if (failed != null && failed) {
%>
<div class="errorMessage">la connexion a échoué !</div>
<%
    }
%>
<form action="<%= request.getAttribute("baseURL") %>/login" method="post">
    <div>
        login : <input name="login"/><br/>
        password : <input type="password" name="passwd"/><br/>
        <br/>
        <input type="submit"/>
    </div>
</form>
<jsp:include page="footer.jsp"/>