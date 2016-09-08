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
    <div>
      <div class="container product_carousel min-h" >

        <%--Dynamic content --%>
        <d:out value="${adminCmsPage.pageContent}" escapeXml="false" >

        </d:out>
        </div>

    </div>

    <jsp:directive.include file="../layouts/top-footer.jsp" />
    <jsp:directive.include file="../layouts/footer.jsp" />

  </body>
</html>
