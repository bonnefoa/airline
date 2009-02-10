<%--

    Copyright (C) 2009 Anthonin Bonnefoy and David Duponchel
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
            http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

--%>

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
    String title = "Une erreur inconnue s'est produite lors de l'affichage d'un message.";
    switch (actionDone) {

        case TABLE_CREATED:
            title = "Table créée.";
            break;
        case TABLE_DELETED:
            title = "Table supprimée.";
            break;
        case FIELD_DELETED:
            title = "Champ supprimé.";
            break;
        case FIELD_EDITED:
            title = "Champ modifié.";
            break;
        case FIELD_ADDED:
            title = "Champ ajouté.";
            break;
        case ROW_DELETED:
            title = "ligne supprimée.";
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
