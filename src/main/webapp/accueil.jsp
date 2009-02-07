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
        <input name="q" value="<%= request.getParameter("q") %>"/>
        <%
            String type = request.getParameter("type");
            if(type == null) {
                type = "";
            }
        %>
        <select name="type">
            <option value="flight"<% if ("flight".equals(type)) { %> selected="selected"<%}%>>n° de vol</option>
            <option value="pilot"<% if ("pilot".equals(type)) { %> selected="selected"<%}%>>n° de pilote</option>
            <option value="plane"<% if ("plane".equals(type)) { %> selected="selected"<%}%>>n° d'avion</option>
        </select><br/>
        <input type="submit"/>
    </div>
</form>

<jsp:include page="fragments/TableRows.jsp"/>

<jsp:include page="footer.jsp"/>