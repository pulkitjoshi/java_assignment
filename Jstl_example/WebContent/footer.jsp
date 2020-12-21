<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<c:set value="admin@avc.com" var="email"></c:set>
<hr>
<a href="mailTo:${email}" style="text-align:center;">Contact</a>