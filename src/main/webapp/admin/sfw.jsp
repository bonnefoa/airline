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
<%@ page import="airline.criteria.enumeration.SqlConstraints" %>
<%@ page import="airline.model.Table" %>
<%@ page import="airline.model.TableRow" %>
<%@ page import="airline.model.TableColumn" %>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page import="airline.servlet.enumeration.MessageError" %>
<%@ page import="java.sql.SQLException" %>
<%--
  Created by IntelliJ IDEA.
  User: dev
  Date: Jan 31, 2009
  Time: 4:59:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%
    request.setAttribute("title", "SELECT FROM WHERE");
%>
<jsp:include page="header.jsp"/>
<script type="text/javascript" src="<%= request.getContextPath() %>/script/sfw.js"></script>
<form action="<%= request.getContextPath() %>/admin/sfw" method="get">
<div>
    <%
        List<TableColumn> columns = (List<TableColumn>) request.getAttribute("columns");
        Map<String, Table> tables = (Map<String, Table>) request.getAttribute("tables");
        Table selectedTable = (Table) request.getAttribute("selectedTable");
        List<TableColumn> selectedFields = (List<TableColumn>) request.getAttribute("selectedFields");
        TableColumn whereField = (TableColumn) request.getAttribute("whereField");
        SqlConstraints whereCond = (SqlConstraints) request.getAttribute("whereCond");
        String whereVal = (String) request.getAttribute("whereVal");
        Set<TableRow> rows = (Set<TableRow>) request.getAttribute("rows");
    %>
    <%-----------------------------------------------------%>
    <%------------------- PARTIE SELECT -------------------%>
    <%-----------------------------------------------------%>
    SELECT
    <%
        if (columns != null) {
    %>
    <select name="select" id="select" multiple="multiple" size="<%=columns.size()%>">
        <%
            for (TableColumn column : columns) {
                boolean selected = (selectedFields != null && selectedFields.contains(column));
        %>
        <option<% if (selected) { %> selected="selected"<%}%>><%= column.getName() %>
        </option>
        <%
            }
        %>
    </select>
    <%
    } else { /* pas de table choisie */
    %>
    <select name="select" id="select" multiple="multiple" size="1" disabled="disabled">
        <option>choisissez d'abord une table</option>
    </select>
    <%
        }
    %>
    <br/>
    <%-----------------------------------------------------%>
    <%-------------------- PARTIE FROM --------------------%>
    <%-----------------------------------------------------%>
    FROM
    <%
        if (tables != null && tables.size() != 0) {
    %>
    <select name="from" id="from">
        <option value="">choisissez une table ici</option>
        <%
            for (Table table : tables.values()) {
                boolean selected = (selectedTable == table);
        %>
        <option<% if (selected) { %> selected="selected"<%}%>><%= table.getName()%>
        </option>
        <%
            }
        %>
    </select>
    <%
    } else {
    %>
    <select name="from" id="from" disabled="disabled">
        <option>aucune table n'est disponible !</option>
    </select>
    <%
        }
    %>
    <br/>
    <%-----------------------------------------------------%>
    <%-------------------- PARTIE WHERE -------------------%>
    <%-----------------------------------------------------%>
    WHERE
    <%
        if (columns != null) {
    %>
    <select name="whereField" id="whereField">
        <%
            for (TableColumn column : columns) {
                boolean selected = (column == whereField);
        %>
        <option<% if (selected) { %> selected="selected"<%}%>><%= column.getName() %>
        </option>
        <%
            }
        %>
    </select>
    <%
    } else {
    %>
    <select name="whereField" id="whereField" disabled="disabled">
        <option>choisissez d'abord une table</option>
    </select>
    <%
        }
    %>
    <select name="whereCond" id="whereCond">
        <%
            for (SqlConstraints constraint : SqlConstraints.values()) {
                boolean selected = (constraint == whereCond);

        %>
        <option<% if (selected) { %>
                selected="selected"<%}%>><%= StringEscapeUtils.escapeHtml(constraint.getSqlValue()) %>
        </option>
        <%
            }
        %>
    </select>
    <input name="whereVal" id="whereVal" value="<%= (whereVal == null) ? "" : whereVal %>"/><br/>
    <br/>
    <input type="submit"/>
</div>
</form>

<%-----------------------------------------------------%>
<%------------------ PARTIE RESULTATS -----------------%>
<%-----------------------------------------------------%>
<%
    MessageError error = (MessageError) request.getAttribute("error.type");
    Exception e = (Exception) request.getAttribute("error.exception");

    if (error != null) {
%>
La requete SQL n'a pas pu aboutir ! Une condition erronee ? <br/>
Erreur SQL : <%= (e == null)?"erreur inconnue !":e.getMessage()%>
<%
} else if (rows != null) {
%>
<div>
résultat :<br/>
    <%
    // TableRows prend columns en paramètre, mais il doit afficher selectedFields
    request.setAttribute("columns", selectedFields);
    request.setAttribute("url.table", selectedTable);

%>
<jsp:include page="/fragments/TableRowsList.jsp"/>
</div>
    <%
}
%>
<jsp:include page="footer.jsp"/>
