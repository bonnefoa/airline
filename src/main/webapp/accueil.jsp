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

<%@ page contentType="application/xhtml+xml; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%--
  Created by IntelliJ IDEA.
  User: sora
  Date: 25 janv. 2009
  Time: 19:36:44
  To change this template use File | Settings | File Templates.
--%>
<%
    request.setAttribute("title", "Bienvenue");
%>
<jsp:include page="header.jsp"/>
<form action="<%= request.getAttribute("baseURL") %>/accueil" method="get">
    <div>
        Rechercher un avion : <br/>
        <%
            String type = request.getParameter("type");
            String query = request.getParameter("q");
            if (query == null) {
                query = "";
            }
        %>
        <input name="q" value="<%= query %>"/>
        <select name="type">
            <option value="flight"<% if ("flight".equals(type)) { %> selected="selected"<%}%>>n° de vol</option>
            <option value="pilot"<% if ("pilot".equals(type)) { %> selected="selected"<%}%>>n° de pilote</option>
            <option value="plane"<% if ("plane".equals(type)) { %> selected="selected"<%}%>>n° d'avion</option>
        </select><br/>
        <input type="submit"/>
    </div>
</form>

<jsp:include page="fragments/TableRowsList.jsp"/>

<jsp:include page="footer.jsp"/>