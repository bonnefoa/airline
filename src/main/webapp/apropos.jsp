<%--
  Created by IntelliJ IDEA.
  User: tetradavid
  Date: Feb 7, 2009
  Time: 12:38:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%
    request.setAttribute("title", "à propos");
%>
<jsp:include page="header.jsp"/>
<h2>AirLine</h2>

<p>
    Cette application web a été réalisée dans le cardre d'un TP de troisième année à
    l'<acronym title="Institut Supérieur d'Informatique, de Modélisation et de leurs Application">ISIMA</acronym> par
    Anthonin Bonnefoy et David Duponchel pour Mamadou K. Traore.
</p>

<p>
    La réalisation de cette application a mise en &oelig;uvre plusieurs technologies :
<ul>
    <li>Java comme langage principal de programmation</li>
    <li>JSP et Servlets pour gérer les pages</li>
    <li>Glassfish comme serveur applicatif</li>
    <li>maven pour gérer efficacement les dépendances</li>
    <li>git comme gestionnaire de version</li>
    <li>jdbc comme couche d'accès aux données</li>
    <li>xhtml et css pour la présentation et le rendu dans le navigateur</li>
    <li>jQuery comme framework simplifiant l'utilisation du javascript</li>
</ul>
</p>

<h2>images</h2>

<p>
    Les images utilisées dans cette application sont sous licence Creative Commons. Voici les images d'origine :<br/>
<ul>
    <li><a href="<%= request.getAttribute("baseURL") %>/img/banniere.jpg">la bannière</a> provient de <a
            href="http://flickr.com/photos/smartjunco/2592304374/">flickr</a> (licence <a
            href="http://creativecommons.org/licenses/by-nc/2.0/deed.fr">by-nc 2.0</a>)
    </li>
    <li><a href="<%= request.getAttribute("baseURL") %>/img/banniere_admin.jpg">la bannière admin</a> provient de <a
            href="http://flickr.com/photos/andrei_dimofte/2540951813/">flickr</a> (licence <a
            href="http://creativecommons.org/licenses/by/2.0/deed.fr">by 2.0</a>)
    </li>
    <li><a href="<%= request.getAttribute("baseURL") %>/img/404.jpg">l'image sur la page 404</a> provient de <a
            href="http://flickr.com/photos/lifeontheedge/293346468/">flickr</a> (licence <a
            href="http://creativecommons.org/licenses/by-sa/2.0/deed.fr">by-sa 2.0</a>)
    </li>
    <li><a href="<%= request.getAttribute("baseURL") %>/img/login.jpg">l'image au login</a> provient de <a
            href="http://flickr.com/photos/lifeontheedge/293345792/">flickr</a> (licence <a
            href="http://creativecommons.org/licenses/by-sa/2.0/deed.fr">by-sa 2.0</a>)
    </li>
</ul>
</p>
<jsp:include page="footer.jsp"/>