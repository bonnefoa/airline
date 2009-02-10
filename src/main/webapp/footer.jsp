<%@ page contentType="application/xhtml+xml; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
</div>
<div id="footer">
    <noscript>
        <div>
            Le javascript est actuellement désactivé ! Le site ne fonctionnera pas correctement sans !<br/>
        </div>
    </noscript>
    Ce site est prévu pour s'afficher dans un navigateur respectueux des standards : Firefox, Opera ou Safari.<br/>

    <img src="<%= request.getContextPath() %>/img/valid-xhtml11.png" alt="Valid XHTML 1.1" height="31" width="88" />&nbsp;
    <img src="<%= request.getContextPath() %>/img/vcss.gif" alt="Valid XHTML 1.1" height="31" width="88" /><br/>

    <!--[if IE]>
    Vous utilisez actuellement IE, un navigateur vieux, dépassé et peu respectueux des standards.
    Si vous souhaitez utiliser ce site de façon optimale, vous êtes <strong>fortement</strong> encouragé à changer de navigateur !
    s<![endif]-->
</div>
</body>
</html>