<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>ReneGuru24</title>
  <!-- Favicons -->
  <link rel="shortcut icon" href="<c:url value="/resources/img/favicon.ico" />">
  <!-- Mobile -->
  <link rel="stylesheet"  href="<c:url value="/resources/css/lightslider.css" />" />
  <meta name="google-signin-client_id" content="109533534799-85f6m6k04935qsuc6on9ubqe7e8rtndj.apps.googleusercontent.com">

  <style>
    ul{
      list-style: none outside none;
      padding-left: 0;
      margin: 0;
    }
    .demo .item{
      margin-bottom: 60px;
    }
    .content-slider li{
      background-color: #ed3020;
      text-align: center;
      color: #FFF;
    }
    .content-slider h3 {
      margin: 0;
      padding: 70px 0;
    }
    .demo{
      width: 800px;
    }
  </style>
  <script src="<c:url value="/resources/js/jquery1.9.1.min.js"  />" ></script>
  <script src="<c:url value="/resources/js/lightslider.js" />" ></script>
  <%--<script>--%>
    <%--$(document).ready(function() {--%>
      <%--$("#content-slider").lightSlider({--%>
        <%--loop:true,--%>
        <%--keyPress:true--%>
      <%--});--%>
      <%--$('#image-gallery').lightSlider({--%>
        <%--gallery:true,--%>
        <%--item:1,--%>
        <%--thumbItem:9,--%>
        <%--slideMargin: 0,--%>
        <%--speed:500,--%>
        <%--auto:true,--%>
        <%--loop:true,--%>
        <%--onSliderLoad: function() {--%>
          <%--$('#image-gallery').removeClass('cS-hidden');--%>
        <%--}--%>
      <%--});--%>
    <%--});--%>
  <%--</script>--%>
  <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
  <!-- CSS start here -->
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css" />" media="screen">
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/styles.css" />" />
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/font-awesome/css/font-awesome.min.css"  />" >
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/animate.css"  />" />
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/owl.carousel.css"  />" />
  <!-- Theme CSS -->
  <!-- <link href="css/clean-blog.css" rel="stylesheet"> -->
  <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,900" rel="stylesheet">
  <!-- Google fonts end here -->
</head>
<body class="ux">
<div class="container-fluid top_nav">
  <div class="row">
    <div class="container">
      <div class="row">
        <div class="col-md-6 col-sm-6 col-xs-5">

        </div>
        <div class="col-md-6 col-sm-6 col-xs-7 ">
          <ul class="top_nav_ul right top_nav_ul">
            <li class="dropdown">
              <a href="${BaseUrl}/home" class="dropdown-toggle top_nav_a"
                 aria-expanded="false">
                <i class="fa fa-home"></i>Home
              </a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</div>
<!--top Nav Bar-->
<div class="container-fluid mid_nav">
  <div class="row">
    <div class="container">
      <div class="row">
        <div class="col-md-6 col-sm-6 col-xs-6">
          <ul class="mid_nav_logo_ul">
            <li class="">
              <a href="${BaseUrl}/home"><img src="<c:url value="/resources/img/logo.gif" />" ></a>
            </li>
          </ul>
        </div>
        <div class="col-md-6 col-sm-6 col-xs-6 ">
          <%--<label class="right mid_nav_contact"><i class="fa fa-mobile fa-2x contact_icon"></i><span class="conatct_number">2300-3560-222</span></label>--%>
        </div>
      </div>
    </div>
  </div>
</div>

  <h1 style="text-align: center; font-size: 200px;">ERROR 404</h1>
  <h2 style="text-align: center; font-size: 80px;">Woops. Looks like this page dosen't exist</h2>
<div class="footer">
  <div class="container">
    <div class="row">
      <ul class="left bottom-nav">
      </ul>
    </div>
  </div>
</div>

<div class="footer_bottom">
  <div class="container">
    <div class="row">
      <div class="col-md-8 col-sm-12 col-xs-12 col-md-offset-4">
        <div class="bottom_footer_content">
          <div class="copyright">
            <p class="no-margin"> &#169; Copyright 2016 Reneguru24 | All Rights Reserved</p>
          </div>
          <div class="social_link">
            <p class="no-margin"> <span class="social_link_i"><i class="fa fa-twitter"></i></span><span class="social_link_i"><i class="fa fa-facebook"></i></span><span class="social_link_i"><i class="fa fa-youtube"></i></span><span class="social_link_i"><i class="fa fa-google-plus"></i></span><span class="social_link_i"><i class="fa fa-linkedin"></i></span><span class="social_link_i"><i class="fa fa-pinterest"></i></span></p>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<script>
  var BASEURL = "${BaseUrl}";
</script>
<!-- Contact end here -->
<!-- Main container start here -->
<!-- Javascript framework and plugins start here -->
<script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />" ></script>
<script src="<c:url value="/resources/js/dropzone.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />" ></script>
<script src="<c:url value="/resources/js/jquery.validate.min.js" />" ></script>
<script src="<c:url value="/resources/js/modernizr.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/appear.js" />" ></script>
<script src="<c:url value="/resources/js/jquery.knob.js" />" ></script>
<script src="<c:url value="/resources/js/jquery.ccountdown.js" />" ></script>
<script src="<c:url value="/resources/js/init.js" />" ></script>
<script src="<c:url value="/resources/js/general.js" />" ></script>
<!-- Theme JavaScript -->
<script src="<c:url value="/resources/js/clean-blog.min.js" />" ></script>
<script src="<c:url value="/resources/js/owl.carousel.js"  />" ></script>
<script src="<c:url value="/resources/js/jquery.enllax.min.js" />" ></script>

<%--Google Js API--%>
<script src="https://apis.google.com/js/api:client.js"></script>
<%--Developer Helpers --%>
<script src="<c:url value="/resources/developer/js/helper/ErrorSuccessModal.js"  />" ></script>
<script src="<c:url value="/resources/developer/js/helper/ErrorMessaging.js" />" ></script>
<!-- Javascript framework and plugins end here -->
</body>
</html>
