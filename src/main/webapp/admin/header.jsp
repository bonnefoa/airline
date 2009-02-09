<%@ page contentType="application/xhtml+xml; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr">
<head>
    <meta content="application/xhtml+xml; charset=UTF-8" http-equiv="content-type"/>
    <title><%= request.getAttribute("title") %>
    </title>
    <script type="text/javascript" src="<%= request.getAttribute("baseURL") %>/script/jquery-1.3.1.min.js"></script>
    <link rel="stylesheet" media="screen" type="text/css" title="style par défaut"
          href="<%= request.getAttribute("baseURL") %>/css/default.css"/>
    <link rel="stylesheet" media="screen" type="text/css" title="style par défaut"
          href="<%= request.getAttribute("baseURL") %>/css/admin.css"/>
</head>
<body>

<div id="banniere">
    <img src="<%= request.getAttribute("baseURL") %>/img/banniere_admin.jpg" alt=""/>

    <h1>AirLine</h1>
</div>

<div id="menu">
    <ul>
        <li><a href="<%= request.getAttribute("baseURL") %>/accueil">retour à l'accueil</a></li>
        <li><a href="<%= request.getAttribute("baseURL") %>/admin/sfw">requête SFW</a></li>
        <li><a href="<%= request.getAttribute("baseURL") %>/admin/table">afficher les tables</a></li>
        <li><a href="<%= request.getAttribute("baseURL") %>/logout">déconnexion</a></li>
    </ul>
</div>
<h1><%= request.getAttribute("title") %>
</h1>

<div id="contenu">