<%@ page import="airline.servlet.enumeration.MessageError" %>
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
    MessageError errorType = (MessageError) request.getAttribute("error.type");
    String title = "Une erreur inconnue s'est produite lors de l'affichage d'une erreur.";
    switch (errorType) {

        case INTERNAL_ERROR:
            title = "Une erreur interne s'est produite.";
            break;
        case SQL_ERROR:
            title = "Une erreur SQL s'est produite.";
            break;
        case EMPTY_FIELD:
            title = "Au moins un des champs n'a pas été rempli correctement !";
            break;
        case INEXISTANT_FIELD:
            title = "Le champ demandé n'existe pas !";
            break;
        case INEXISTANT_ROW:
            title = "Le tuple spécifié n'existe pas !";
            break;
        case INEXISTANT_TABLE:
            title = "La table demandée n'existe pas !";
            break;
        case UNIMPLEMENTED_METHOD:
            title = "La méthode appelée n'a pas été implémentée !";
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

<%
    Exception e = (Exception) request.getAttribute("error.exception");
    if (e != null && e.getStackTrace() != null) {
%>
Le serveur a renvoyé cette erreur : <%=e.getMessage()%><br/>
<br/>
Le détail de l'erreur suit :
<p>
<%
    for (StackTraceElement elt : e.getStackTrace()) {
        out.println(elt + "<br/>");
    }
%>
</p>
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