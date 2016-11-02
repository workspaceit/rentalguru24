<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 8/24/16
  Time: 5:51 PM
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
<!--end main Nav-->
<!-- Dashboard-->
<div class="container user-dash-container">
  <div class="row">
    <jsp:directive.include file="../layouts/userDashboardLeftMemu.jsp" />
    <div class="col-md-9 side-container">
      <h2> Welcome ${appCredential.getUserInf().getFirstName()} ${appCredential.getUserInf().getLastName()}</h2>
    </div>
  </div>
</div>
<!--Dashboard-->
<jsp:directive.include file="../layouts/top-footer.jsp" />
<jsp:directive.include file="../layouts/footer.jsp" />
<!-- Javascript framework and plugins end here -->
</body>
</html>


