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