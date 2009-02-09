<%@ page contentType="application/xhtml+xml; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="airline.model.User" %>
<%--
  Created by IntelliJ IDEA.
  User: dev
  Date: Jan 31, 2009
  Time: 4:57:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%
    request.setAttribute("title", "partie admin");
%>
<jsp:include page="header.jsp"/>

<% User user = (User) request.getSession().getAttribute("user");%>
<div>
    bienvenue, <%= user.getLogin() %>
<br/>
    voici la partie admin. Ne casse pas tout !
</div>
<jsp:include page="footer.jsp"/>