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
  User: dev
  Date: 3 févr. 2009
  Time: 21:40:22
  To change this template use File | Settings | File Templates.
--%>

<%
    request.setAttribute("title", "login");
%>
<jsp:include page="header.jsp"/>
<%
    Boolean failed = (Boolean) request.getAttribute("loginFailed");
    if (failed != null && failed) {
%>
<div class="errorMessage">la connexion a échoué !</div>
<%
    }
%>
<form class="centered" action="<%= request.getAttribute("baseURL") %>/login" method="post">
<div>
    login : <input name="login"/><br/>
    password : <input type="password" name="passwd"/><br/>
    <br/>
    <input type="submit"/>
</div>
</form>
<div class="centered">
<img src="<%= request.getAttribute("baseURL") %>/img/login.jpg" alt=""/>
</div>
<jsp:include page="footer.jsp"/>