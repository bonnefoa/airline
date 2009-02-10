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
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page import="airline.servlet.enumeration.Context" %>
<%@ page import="airline.servlet.enumeration.Action" %>
<%@ page import="java.sql.Types" %>
<%--
    Affiche le contenu d'une table.
    @param columns : les colonnes de la table à afficher.
    @param url.action
    @param url.table
    @param url.row
    @param url.rowNb
--%>
<%
    Collection<TableColumn> columns = (Collection<TableColumn>) request.getAttribute("columns");
    Action action = (Action) request.getAttribute("url.action");
    Table table = (Table) request.getAttribute("url.table");
    TableRow row = (TableRow) request.getAttribute("url.row");
    int rowNb = (Integer) request.getAttribute("url.rowNb");
    if (row == null) {
        row = new TableRow();
    }


    StringBuilder formAction = new StringBuilder((String) request.getAttribute("baseURL"));
    formAction.append("/admin/table");
    formAction.append(table.getName());
    if (action == Action.ADD) {
        formAction.append("/field/add");
    } else if (action == Action.EDIT) {
        formAction.append('/');
        formAction.append(table.getName());
        formAction.append("/field/");
        formAction.append(rowNb);
        formAction.append("/edit");
    }
%>
<script type="text/javascript" src="<%= request.getAttribute("baseURL") %>/script/TableRowsAddOrEdit.js"></script>
<form action="<%= formAction.toString() %>" method="post">
    <div>
        <table>
            <thead>
            <tr>
                <%
                    for (TableColumn column : columns) {
                %>
                <th><%= column.getName() %>
                </th>
                <%
                    }
                %>
            </tr>
            </thead>
            <tbody>
            <tr>
                <%
                    for (TableColumn column : columns) {
                %>
                <td>
                    <%
                        switch (column.getDataType()) {
                            case Types.INTEGER:
                    %><input class="INTEGER" value="<%=row.get(column)%>"/><%
                        break;
                    case Types.DATE:
                %><input class="DATE" value="<%=row.get(column)%>"/><%
                        break;
                    default:
                %><input class="VARCHAR" value="<%=row.get(column)%>"/><%
                            break;
                    }
                %>
                </td>
                <%
                    }
                %>
            </tr>
            </tbody>
        </table>
        <% if (action == Action.ADD) {%>
        <input type="submit" id="addRow" value="Ajouter une ligne"/>
        <% } %>
    </div>
</form>