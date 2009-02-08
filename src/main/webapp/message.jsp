<%@ page import="airline.servlet.enumeration.MessageAction" %>
<%@ page contentType="application/xhtml+xml; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%--
  Created by IntelliJ IDEA.
  User: dev
  Date: Jan 31, 2009
  Time: 4:57:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%
    MessageAction actionDone = (MessageAction) request.getAttribute("action.done");
    String title = "Une erreur inconnue s'est produite.";
    switch (actionDone) {

        case TABLE_CREATED:
            title = "Table créée.";
            break;
        case TABLE_DELETED:
            title = "Table supprimée.";
            break;
    }
    request.setAttribute("title", title);
    if (session.getAttribute("user") != null) {
%>
<jsp:include page="/admin/header.jsp"/>
<%
} else {
%>
<jsp:include page="/header.jsp"/>
<%
    }
%>
<% if (session.getAttribute("user") != null) {
%>
<jsp:include page="/admin/footer.jsp"/>
<%
} else {
%>
<jsp:include page="/footer.jsp"/>
<%
    }
%>
