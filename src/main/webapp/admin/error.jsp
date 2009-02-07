<%@ page import="airline.servlet.enumeration.MessageAction" %>
<%@ page import="airline.servlet.enumeration.MessageError" %>
<%@ page contentType="application/xhtml+xml; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8"%>
<%--
  Created by IntelliJ IDEA.
  User: dev
  Date: Jan 31, 2009
  Time: 4:57:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%
    MessageError errorType = (MessageError) request.getAttribute("error.type");
    String title = "Une erreur inconnue s'est produite.";
    switch (errorType) {

        case INTERNAL_ERROR:
            title = "Une erreur interne s'est produite.";
            break;
    }
    request.setAttribute("title",title);
%>
<jsp:include page="header.jsp"/>

<jsp:include page="footer.jsp"/>