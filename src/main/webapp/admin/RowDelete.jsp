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
<%@ page import="airline.model.TableRow" %>
<%@ page import="java.util.Map" %>
<%@ page import="airline.model.TableColumn" %>
<%@ page contentType="application/xhtml+xml; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%
    TableRow row = (TableRow) request.getAttribute("url.row");
    int rowNb = (Integer) request.getAttribute("url.rowNb");
    Table table = (Table) request.getAttribute("url.table");
    request.setAttribute("title", "Suppression d'une ligne");
%>
<jsp:include page="/admin/header.jsp"/>

<form action="<%= request.getContextPath() %>/admin/table/<%=table.getName()%>/row/<%= rowNb %>/delete" method="post">
    <div>
        Voulez-vous vraiment supprimer cette ligne ?<br/>
        <ul>
            <%
                for (Map.Entry<TableColumn, String> entry : row.entrySet()) {
            %>
            <li>
                <%=entry.getKey().getName() %> : <%= entry.getValue() %>
            </li>
            <%}%>
        </ul>
        <input type="submit" value="supprimer"/>&nbsp;
        <a class="button" href="<%= request.getContextPath() %>/admin/table/<%=table.getName()%>">retour</a>
    </div>
</form>

<jsp:include page="/admin/footer.jsp"/>
