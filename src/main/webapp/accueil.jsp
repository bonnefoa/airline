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