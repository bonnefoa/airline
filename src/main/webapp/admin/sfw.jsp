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
  <jsp:include page="header.jsp" />
  <div>
      SELECT
      <select id="select">
      </select><br />
      FROM
      <select id="from">
      </select><br />
      WHERE
      <select id="whereField"></select>
      <select id="whereCond"></select>
      <input id="whereVal" />
  </div>
  <div>

  </div>
  </body>
</html>