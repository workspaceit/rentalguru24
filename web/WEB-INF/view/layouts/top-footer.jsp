<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 8/29/16
  Time: 2:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<div class="footer">--%>
  <%--<div class="container">--%>
    <%--<div class="row">--%>
      <%--<div class="col-md-3 col-sm-6 col-xs-12">--%>
        <%--<p class="footer_head">INFORMATION</p>--%>
        <%--<ul class="footer_ul">--%>
          <%--<li>About US</li>--%>
          <%--<li>Privacy</li>--%>
          <%--<li>Conditions</li>--%>
          <%--<li>Online Support</li>--%>
        <%--</ul>--%>
      <%--</div>--%>
      <%--<div class="col-md-3 col-sm-6 col-xs-12">--%>
        <%--<p class="footer_head">MY ACCOUNT</p>--%>
        <%--<ul class="footer_ul">--%>
          <%--<li>Login</li>--%>
        <%--</ul>--%>
      <%--</div>--%>
      <%--<div class="col-md-3 col-sm-6 col-xs-12">--%>
        <%--<p class="footer_head">INFORMATION</p>--%>
        <%--<ul class="footer_ul">--%>
          <%--<li>Specials</li>--%>
          <%--<li>New Products</li>--%>
          <%--<li>Best Sellers</li>--%>
          <%--<li>Our Stored</li>--%>
        <%--</ul>--%>
      <%--</div>--%>
      <%--<div class="col-md-3 col-sm-6 col-xs-12">--%>
        <%--<p class="footer_head">ORDERS</p>--%>
        <%--<ul class="footer_ul">--%>
          <%--<li>Payment Option</li>--%>
          <%--<li>Shipping Delivery</li>--%>
          <%--<li>Returns</li>--%>
          <%--<li>Shipping</li>--%>
        <%--</ul>--%>
      <%--</div>--%>
    <%--</div>--%>
  <%--</div>--%>
<%--</div>--%>
<div class="footer">
  <div class="container">
    <div class="row">

        <ul class="left bottom-nav">
          <d:forEach var="cmsPage" items="${cmsPages}" >
            <li><a href="${BaseUrl}/static/${cmsPage.pageKey}">${cmsPage.pageName}</a></li>
          </d:forEach>
        </ul>

  </div>
</div>
</div>
