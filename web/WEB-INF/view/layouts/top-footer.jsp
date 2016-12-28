<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 8/29/16
  Time: 2:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="footer">
  <%--<div class="container">--%>
    <%--<div class="row">--%>
        <%--<ul class="left bottom-nav">--%>
          <%----%>
        <%--</ul>--%>
    <%--</div>--%>
  <%--</div>--%>



    <div class="container">
      <div class="row">
        <div class="col-md-2 col-sm-6 col-xs-12">
          <p class="footer_head">INFORMATION</p>
          <ul class="footer_ul">
            <d:forEach var="cmsPage" items="${cmsPages}" >
              <li><a href="${BaseUrl}/static/${cmsPage.pageKey}">${cmsPage.pageName}</a></li>
            </d:forEach>
            <%--<li><a href="#">About US</a></li>--%>
            <%--<li><a href="#">Privacy</a></li>--%>
            <%--<li><a href="#">Conditions</a></li>--%>
            <%--<li><a href="#">Online Support</a></li>--%>
          </ul>
        </div>
        <div class="col-md-4 col-sm-6 col-xs-12" style="text-align: center">
          <p class="footer_head">DOWNLOAD OUR APP</p>
          <ul class="footer_ul">
            <li><a href="#"><img src="/resources/img/appstore.png" /></a></li>
            <li><a href="#"><img src="/resources//img/playstore.png"></a></li>
          </ul>
        </div>
        <div class="col-md-3 col-sm-6 col-xs-12">
          <p class="footer_head">GET SUPPORT</p>
          <ul class="footer_ul">
            <li><a href="mailto:support@rentguru24.com">Email : support@rentguru24.com</a></li>
            <li><a href="#">Phone : +12345678</a></li>
          </ul>
          </div>
        <%--</div>&lt;%&ndash;&ndash;%&gt;--%>
        <div class="col-md-3 col-sm-6 col-xs-12">
          <%--<h3 style="vertical-align: middle">Stay Connected</h3>--%>
            <p class="footer_head">STAY CONNECTED</p>
            <ul class="footer_ul"    style="margin-top: 15px;">
          <a href="https://www.facebook.com/RentGuru24/" target="_blank"><img src="/resources/img/facebook_ico.png" style="height: 50px;"></a>
          <a href="javascript:void(0)" target="_blank"><img src="/resources/img/googlePlus_ico.png" style="height: 50px;"></a>
          <a href="javascript:void(0)" target="_blank"><img src="/resources/img/twitter_ico.png" style="height: 50px;"></a>
          <a href="javascript:void(0)" target="_blank"><img src="/resources/img/youtube_ico.png" style="height: 50px;"></a>
              </ul>
        </div>
      </div>
    </div>
</div>