<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 8/30/16
  Time: 5:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<jsp:directive.include file="../layouts/header.jsp" />
<body class="ux">
<!--top Nav Bar-->
<jsp:directive.include file="../layouts/top-nav.jsp" />
<!--mid navbar-->
<jsp:directive.include file="../layouts/mid-nav.jsp" />
<!--main navbar-->
<jsp:directive.include file="../layouts/main-nav.jsp" />
<!--body content-->
<p>
  ${payPalStatusMsg}
</p>
<!--footer top---->
<jsp:directive.include file="../layouts/top-footer.jsp" />
<!--footer bottom---->
<jsp:directive.include file="../layouts/footer.jsp" />
</body>
</html>

