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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr">
<head>
    <meta content="application/xhtml+xml; charset=UTF-8" http-equiv="content-type"/>
    <title><%= request.getAttribute("title") %>
    </title>
    <script type="text/javascript" src="<%= request.getContextPath() %>/script/jquery.ui-1.6rc6/jquery-1.3.1.min.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath() %>/script/jquery.ui-1.6rc6/ui/minified/ui.core.min.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath() %>/script/jquery.ui-1.6rc6/ui/minified/ui.datepicker.min.js"></script>
    <link rel="stylesheet" media="screen" type="text/css" title="style par défaut"
          href="<%= request.getContextPath() %>/css/default.css"/>
    <link rel="stylesheet" media="screen" type="text/css" title="style par défaut"
          href="<%= request.getContextPath() %>/css/admin.css"/>
    <link rel="stylesheet" media="screen" type="text/css" title="style par défaut"
          href="<%= request.getContextPath() %>/script/jquery.ui-1.6rc6/themes/base/ui.core.css"/>
    <link rel="stylesheet" media="screen" type="text/css" title="style par défaut"
          href="<%= request.getContextPath() %>/script/jquery.ui-1.6rc6/themes/base/ui.datepicker.css"/>
    <link rel="stylesheet" media="screen" type="text/css" title="style par défaut"
          href="<%= request.getContextPath() %>/script/jquery.ui-1.6rc6/themes/base/ui.theme.css"/>
</head>
<body>

<div id="banniere">
    <img src="<%= request.getContextPath() %>/img/banniere_admin.jpg" alt=""/>

    <h1>AirLine</h1>
</div>

<div id="menu">
    <ul>
        <li><a href="<%= request.getContextPath() %>/accueil">retour à l'accueil</a></li>
        <li><a href="<%= request.getContextPath() %>/admin/sfw">requête SFW</a></li>
        <li><a href="<%= request.getContextPath() %>/admin/table">afficher les tables</a></li>
        <li><a href="<%= request.getContextPath() %>/logout">déconnexion</a></li>
    </ul>
</div>
<h1><%= request.getAttribute("title") %>
</h1>

<div id="contenu">