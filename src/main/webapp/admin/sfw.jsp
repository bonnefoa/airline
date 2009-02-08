<%@ page contentType="application/xhtml+xml; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="airline.criteria.enumeration.SqlConstraints" %>
<%@ page import="airline.model.Table" %>
<%@ page import="airline.model.TableRow" %>
<%@ page import="airline.model.TablesColumns" %>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
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
<script type="text/javascript" src="<%= request.getAttribute("baseURL") %>/script/sfw.js"></script>
<form action="<%= request.getAttribute("baseURL") %>/admin/sfw" method="get">
<div>
    <%
        List<TablesColumns> columns = (List<TablesColumns>) request.getAttribute("columns");
        Map<String, Table> tables = (Map<String, Table>) request.getAttribute("tables");
        Table selectedTable = (Table) request.getAttribute("selectedTable");
        List<TablesColumns> selectedFields = (List<TablesColumns>) request.getAttribute("selectedFields");
        TablesColumns whereField = (TablesColumns) request.getAttribute("whereField");
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
    <select name="select" id="select" multiple="multiple">
        <%
            for (TablesColumns column : columns) {
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
            for (TablesColumns column : columns) {
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
    if (rows != null) {
%>
<div>
résultat :<br/>
<%
    // TableRows prend columns en paramètre, mais il doit afficher selectedFields
    request.setAttribute("columns", selectedFields);
%>
<jsp:include page="/fragments/TableRows.jsp"/>
</div>
<%
    }
%>
<jsp:include page="footer.jsp"/>