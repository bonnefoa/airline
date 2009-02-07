<%@ page contentType="application/xhtml+xml; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="airline.model.Table" %>
<%@ page import="airline.model.TablesColumns" %>
<%@ page import="airline.servlet.enumeration.Action" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: dev
  Date: Jan 31, 2009
  Time: 4:57:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%
    Action action = (Action) request.getAttribute("url.action");
    Table table = (Table) request.getAttribute("url.table");
    List<TablesColumns> columns = (List<TablesColumns>) request.getAttribute("columns");

    if (action == Action.ADD) {
        request.setAttribute("title", "ajout d'une table");
        columns = new ArrayList<TablesColumns>();
        columns.add(new TablesColumns()); // affiche une vide
    } else { // EDIT
        request.setAttribute("title", "modification de la table " + table.getName());
    }
%>
<jsp:include page="/admin/header.jsp"/>
<script type="text/javascript" src="<%= request.getAttribute("baseURL") %>/script/rowsAddOrEdit.js"></script>
<%
    StringBuilder formAction = new StringBuilder((String) request.getAttribute("baseURL"));
        formAction.append("/admin/table");
    if (action == Action.ADD) { // /admin/table/add
        formAction.append("/add");
    } else { // EDIT : /admin/table/tablename/edit
        formAction.append("/");
        formAction.append(table.getName());
        formAction.append("/edit");
    }
%>
<form action="<%= formAction.toString() %>" method="get">
    <div>
        <table>
            <thead>
            <tr>
                <th>nom</th>
                <th>type</th>
                <th>clef primaire</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (TablesColumns column : columns) {
            %>
            <tr>
                <td><input name="name[]" value="<%= (column.getName() == null) ? "" : column.getName() %>"/></td>
                <td>
                    <%
                        String type = column.getType();
                    %>
                    <select name="type[]">
                        <option <%if("INTEGER".equals(type)){%>selected="selected"<%}%>>INTEGER</option>
                        <option <%if("VARCHAR".equals(type)){%>selected="selected"<%}%>>VARCHAR</option>
                    </select>
                </td>
                <td><input name="primary[]" type="checkbox" <%if(column.isPrimaryKey()){%>checked="checked"<%}%>/></td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
        <input type="submit" id="addField" value="Ajouter un champ"/>
        <input type="submit"/>
    </div>
</form>
<jsp:include page="/admin/footer.jsp"/>