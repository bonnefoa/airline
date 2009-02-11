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
        case WRONG_PARAMETER:
            title = "Un des parametres a ete mal saisi !";
            break;
    }
    request.setAttribute("title", title);
%>
<jsp:include page="/header.jsp"/>

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
<jsp:include page="/footer.jsp"/>