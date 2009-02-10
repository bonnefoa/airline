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

<%@ page import="airline.model.Table" %>
<%@ page contentType="application/xhtml+xml; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%
    Table table = (Table) request.getAttribute("url.table");
    request.setAttribute("title", "Modification de la table " + table.getName());
%>
<jsp:include page="/admin/header.jsp"/>

<jsp:include page="/fragments/TableFields.jsp"/>

<jsp:include page="/admin/footer.jsp"/>