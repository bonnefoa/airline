<%@ page import="airline.model.Table" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="airline.model.TablesColumns" %>
<%@ page import="java.util.List" %>
<%@ page import="airline.criteria.enumeration.SqlConstraints" %>
<%@ page import="java.util.Set" %>
<%@ page import="airline.model.TablesRow" %>
<%--
  Created by IntelliJ IDEA.
  User: dev
  Date: Jan 31, 2009
  Time: 4:59:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Select From Where</title>
    <script type="text/javascript" src="script/jquery.js"></script>
    <script type="text/javascript" src="script/sfw.js"></script>
</head>
<body>
<jsp:include page="header.jsp"/>
<div>
    <form action="<%= request.getAttribute("baseURL") %>/admin/sfw" method="get">
        <%
            List<TablesColumns> columns = (List<TablesColumns>) request.getAttribute("columns");
            Map<String, Table> tables = (Map<String, Table>) request.getAttribute("tables");
            Table selectedTable = (Table) request.getAttribute("selectedTable");
            List<TablesColumns> selectedFields = (List<TablesColumns>) request.getAttribute("selectedFields");
            TablesColumns whereField = (TablesColumns) request.getAttribute("whereField");
            SqlConstraints whereCond = (SqlConstraints) request.getAttribute("whereCond");
            String whereVal = (String) request.getAttribute("whereVal");
            Set<TablesRow> rows = (Set<TablesRow>) request.getAttribute("rows");
        %>
        SELECT
        <select name="select" id="select" multiple="multiple">
            <%
                if (columns != null) {
                    for (TablesColumns column : columns) {
                        boolean selected = (selectedFields != null && selectedFields.contains(column));
            %>
            <option<% if (selected) { %> selected="selected"<%}%>><%= column.getName() %>
            </option>
            <%
                    }
                }
            %>
        </select><br/>
        FROM
        <select name="from" id="from">
            <%
                if (tables != null) {
                    for (Table table : tables.values()) {
                        boolean selected = (selectedTable == table);
            %>
            <option<% if (selected) { %> selected="selected"<%}%>><%= table.getName()%>
            </option>
            <%
                    }
                }
            %>
        </select><br/>
        WHERE
        <select name="whereField" id="whereField">
            <%
                if (columns != null) {
                    for (TablesColumns column : columns) {
                        boolean selected = (column == whereField);
            %>
            <option<% if (selected) { %> selected="selected"<%}%>><%= column.getName() %>
            </option>
            <%
                    }
                }
            %>
        </select>
        <select name="whereCond" id="whereCond">
            <%
                for (SqlConstraints constraint : SqlConstraints.values()) {
                    boolean selected = (constraint == whereCond);
            %>
            <option<% if (selected) { %> selected="selected"<%}%>><%= constraint.getSqlValue() %>
            </option>
            <%
                }
            %>
        </select>
        <input name="whereVal" id="whereVal" value="<%= (whereVal == null) ? "" : whereVal %>"/><br/>
        <br/>
        <input type="submit"/>
    </form>
</div>

<%
    if (rows != null) {
%>
<div>
    résultat :<br/>
    <table>
        <thead>
        <tr>
            <%
                for (TablesColumns column : columns) {
            %>
            <th><%= column.getName() %>
            </th>
            <%
                }
            %>
        </tr>
        </thead>
        <%
            for (TablesRow row : rows) {
        %>
        <tr>
            <%
                for (TablesColumns column : columns) {
            %>
            <td><%= row.get(column) %>
            </td>
            <%
                }
            %>
        </tr>
        <%
            }
        %>
    </table>
</div>
<%
    }
%>
</div>
</body>
</html>