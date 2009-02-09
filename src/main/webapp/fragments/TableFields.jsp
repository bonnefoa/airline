<%@ page contentType="application/xhtml+xml; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="airline.model.Table" %>
<%@ page import="airline.model.TablesColumns" %>
<%@ page import="airline.servlet.enumeration.Action" %>
<%@ page import="airline.servlet.enumeration.Context" %>
<%@ page import="java.sql.Types" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
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
    List<TablesColumns> columns = (List<TablesColumns>) request.getAttribute("columns");
    TablesColumns editableField = (TablesColumns) request.getAttribute("url.field");

    if (action == Action.ADD) {
        columns = new ArrayList<TablesColumns>();
        columns.add(new TablesColumns()); // affiche une vide
    }
%>
<script type="text/javascript" src="<%= request.getAttribute("baseURL") %>/script/tableAddOrEdit.js"></script>

<%
    if (action != Action.SHOW) {

        StringBuilder formAction = new StringBuilder((String) request.getAttribute("baseURL"));
        formAction.append("/admin/table");
        if (action == Action.ADD) { // /admin/table/add
            formAction.append("/add");
        } else if (action == Action.EDIT && context == Context.FIELD) { // EDIT : /admin/table/tablename/field/fieldname/edit
            formAction.append('/');
            formAction.append(table.getName());
            formAction.append("/field/");
            formAction.append(editableField.getName());
            formAction.append("/edit");
        } else if (action == Action.EDIT && context == Context.TABLE) { // EDIT : /admin/table/tablename/edit
            formAction.append('/');
            formAction.append(table.getName());
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
    <%=(table != null && table.getName() != null) ? table.getName() : ""%>
    <%
        }
    %>
    <br/>
    <br/>
    
    Champs : <br/>
    <table>
        <thead>
        <tr>
            <th>nom</th>
            <th>type</th>
            <th>clef primaire</th>
            <% if (action == Action.ADD) {%>
            <th>supprimer le champ</th>
            <% } else if (action == Action.SHOW) {%>
            <th>action</th>
            <% } %>
        </tr>
        </thead>
        <tbody>
        <%
            for (TablesColumns column : columns) {
                boolean editable = (action == Action.ADD || column.equals(editableField));
        %>
        <tr>
            <td>
                <% if (editable) { %>
                <input name="name" value="<%= (column.getName() == null) ? "" : column.getName() %>"/>
                <% } else { %>
                <%= (column.getName() == null) ? "" : column.getName() %>
                <% } %>
            </td>
            <td>
                <%
                    int type = column.getDataType();
                    if (editable) {
                %>
                <select name="type">
                    <option value="<%=Types.VARCHAR%>" <%if(type == Types.VARCHAR){%>selected="selected"<%}%>>VARCHAR
                    </option>
                    <option value="<%=Types.INTEGER%>" <%if(type == Types.INTEGER){%>selected="selected"<%}%>>INTEGER
                    </option>
                    <option value="<%=Types.DATE%>" <%if(type == Types.DATE){%>selected="selected"<%}%>>DATE</option>
                </select>
                <% } else {
                    switch (type) {
                        case Types.DATE:
                            out.print("DATE");
                        case Types.INTEGER:
                            out.print("INTEGER");
                        default:
                            out.print("VARCHAR");
                    }
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
            <% if (action == Action.ADD) {%>
            <td>
                <img class="deleteImg" src="<%= request.getAttribute("baseURL") %>/img/delete.png"
                     alt="supprimer le champ" title="supprimer le champ"/>
            </td>
            <% } else if (action == Action.SHOW) { %>
            <td>
                <a href="<%= request.getAttribute("baseURL") %>/admin/table/<%= table.getName()%>/field/<%=column.getName()%>/edit">
                    <img src="<%= request.getAttribute("baseURL") %>/img/edit.png" alt="modifier" title="modifier"/>
                </a>&nbsp;
                <a href="<%= request.getAttribute("baseURL") %>/admin/table/<%= table.getName()%>/field/<%=column.getName()%>/delete">
                    <img src="<%= request.getAttribute("baseURL") %>/img/delete.png" alt="supprimer" title="supprimer"/>
                </a>
            </td>
            <% } %>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <% if (action == Action.ADD) {%>
    <input type="submit" id="addField" value="Ajouter un champ"/>
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