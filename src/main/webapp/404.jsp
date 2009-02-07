<%
    request.setAttribute("title", "Page introuvable !");

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

<div class="centered">
    <img src="<%= request.getAttribute("baseURL") %>/img/404.jpg" alt=""/>
</div>

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