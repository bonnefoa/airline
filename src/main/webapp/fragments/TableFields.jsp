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
<%@ page import="airline.model.Table" %>
<%@ page import="airline.model.TableColumn" %>
<%@ page import="airline.servlet.enumeration.Action" %>
<%@ page import="airline.servlet.enumeration.Context" %>
<%@ page import="java.sql.Types" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="airline.util.SQLConversion" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%--
    Affiche ou modifie les colonnes d'une table.
    @param columns : les colonnes de la table.
    @param url.table : la table à afficher.
    @param url.action : l'action à effectuer.
    @param url.field : le champ modifiable si applicable.
    @param url.context : le contexte actuel.
--%>
<%
    Action action = (Action) request.getAttribute("url.action");
    Table table = (Table) request.getAttribute("url.table");
    Context context = (Context) request.getAttribute("url.context");
    List<TableColumn> columns = (List<TableColumn>) request.getAttribute("columns");
    TableColumn editableField = (TableColumn) request.getAttribute("url.field");
    boolean logged = session.getAttribute("user") != null;

    if (columns == null) {
        columns = new ArrayList<TableColumn>();
    }

    if (action == Action.ADD && context == Context.TABLE) {
        columns.add(new TableColumn()); // affiche une vide
    }
    if (action == Action.ADD && context == Context.FIELD) {
        editableField = new TableColumn();
        columns.add(editableField);
    }
%>
<script type="text/javascript" src="<%= request.getContextPath() %>/script/TableFields.js"></script>

<%
    if (action != Action.SHOW) {

        StringBuilder formAction = new StringBuilder(request.getContextPath());
        formAction.append("/table");
        if (action == Action.ADD && context == Context.TABLE) { // /table/add
            formAction.append("/add");
        } else if (action == Action.ADD && context == Context.FIELD) { // EDIT : /table/tablename/field/add
            formAction.append('/');
            formAction.append(URLEncoder.encode(table.getName(), "UTF-8"));
            formAction.append("/field/add");
        } else if (action == Action.EDIT && context == Context.FIELD) { // EDIT : /table/tablename/field/fieldname/edit
            formAction.append('/');
            formAction.append(URLEncoder.encode(table.getName(), "UTF-8"));
            formAction.append("/field/");
            formAction.append(URLEncoder.encode(editableField.getName(), "UTF-8"));
            formAction.append("/edit");
        } else if (action == Action.EDIT && context == Context.TABLE) { // EDIT : /table/tablename/edit
            formAction.append('/');
            formAction.append(URLEncoder.encode(table.getName(), "UTF-8"));
            formAction.append("/edit");
        }
%>
<form action="<%= formAction.toString() %>" method="post">
<%
    }
%>
<div>
    Nom de la table :
    <%
        if (context == Context.TABLE && action != Action.SHOW) {
    %>
    <input name="tableName"
           value="<%=(table != null && table.getName() != null)?table.getName():""%>"/>
    <%
    } else {
    %>
    <%=(table != null && table.getName() != null) ? StringEscapeUtils.escapeHtml(table.getName()) : ""%>
    <%
        }
    %>
    <br/>
    <br/>
    <%
        if (columns != null && columns.size() != 0) {
    %>
    Champs : <br/>
    <table>
        <thead>
        <tr>
            <th>nom</th>
            <th>type</th>
            <th>clef primaire</th>
            <% if (action == Action.ADD && logged) {%>
            <th>supprimer le champ</th>
            <% } else if (action == Action.SHOW && logged) {%>
            <th>action</th>
            <% } %>
        </tr>
        </thead>
        <tbody>
        <%
            for (TableColumn column : columns) {
                boolean editable = ((action == Action.ADD && context == Context.TABLE) || column.equals(editableField));
        %>
        <tr>
            <td>
                <% if (editable) { %>
                <input name="name" value="<%= (column.getName() == null) ? "" : column.getName() %>"/>
                <% } else { %>
                <%= (column.getName() == null) ? "" : StringEscapeUtils.escapeHtml(column.getName()) %>
                <% } %>
            </td>
            <td>
                <%
                    int type = column.getDataType();
                    if (editable) {
                %>
                <select name="type">
                    <%
                        Map<Integer, String> lists = SQLConversion.getTypeList();
                        for(Map.Entry<Integer, String> entry : SQLConversion.getTypeList().entrySet()) {
                            %>
                    <option value="<%=entry.getKey()%>" <%if(entry.getKey().equals(type)){%>selected="selected"<%}%>>
                        <%=StringEscapeUtils.escapeHtml(entry.getValue())%>
                    </option>
                    <%
                        }
                    %>
                </select>
                <% } else {
                    out.print(SQLConversion.sqlTypeToString(type));
                } %>
            </td>
            <td>
                <% if (editable) { %>
                <select name="primary">
                    <option value="off" <%if(!column.isPrimaryKey()){%>selected="selected"<%}%>>non</option>
                    <option value="on" <%if(column.isPrimaryKey()){%>selected="selected"<%}%>>OUI</option>
                </select>
                <% } else { %>
                <%= (column.isPrimaryKey()) ? "oui" : "" %>
                <% } %>
            </td>
            <% if (logged && ((action == Action.ADD && context == Context.TABLE) || (action == Action.ADD && context == Context.FIELD && editable))) {%>
            <td>
                <img class="deleteImg" src="<%= request.getContextPath() %>/img/delete.png"
                     alt="supprimer le champ" title="supprimer le champ"/>
            </td>
            <% } else if (action == Action.SHOW && logged) { %>
            <td>
                <a href="<%= request.getContextPath() %>/table/<%= table.getName()%>/field/<%=column.getName()%>/edit">
                    <img src="<%= request.getContextPath() %>/img/edit.png" alt="modifier" title="modifier"/>
                </a>&nbsp;
                <a href="<%= request.getContextPath() %>/table/<%= table.getName()%>/field/<%=column.getName()%>/delete">
                    <img src="<%= request.getContextPath() %>/img/delete.png" alt="supprimer" title="supprimer"/>
                </a>
            </td>
            <% } %>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <%
    } else {
    %>
    Aucun champ a afficher !
    <%
        }
    %>

    <% if (action == Action.ADD) {%>
    <a id="addField" href="#" class="button"><img src="<%= request.getContextPath() %>/img/add.png" alt=""/>Ajouter un champ</a>
    <br/>
    <br/>
    <% } %>
    <%if (action != Action.SHOW) {%>
    <input type="submit"/>
    <%}%>
</div>
<%
    if (action != Action.SHOW) {
%>
</form>
<%
    }
%>