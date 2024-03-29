<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 8/19/16
  Time: 11:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <d:if test="${pageTitle != null}">
    <title>${fn:escapeXml(pageTitle)}</title>
  </d:if>
  <d:if test="${pageTitle == null}">
    <title>RentGuru24</title>
  </d:if>
  <link rel="shortcut icon" href="<c:url value="/resources/img/favicon.ico" />">
  <link rel="stylesheet"  href="<c:url value="/resources/css/lightslider.css"/>"/>
  <link rel="stylesheet" href="<c:url value="/resources/css/easyzoom.css"/>" />
  <link rel="stylesheet" href="<c:url value="/resources/css/chosen.css"/>" />
  <link rel="stylesheet" href="http://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css">
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
    .datepicker.dropdown-menu {
      z-index: 9999999 !important;
    }
  </style>
  <script type="text/javascript" src="<c:url value="/resources/js/jquery.js"/>"></script>
  <script src="<c:url value="/resources/js/jquery.zoom.js"/>"></script>
  <script src="<c:url value="/resources/js/jquery1.9.1.min.js"  />" ></script>
  <script src="<c:url value="/resources/js/chosen.jquery.min.js"  />" ></script>

  <script src="<c:url value="/resources/js/lightslider.js" />" > </script>
  <%--<script>--%>
    <%--$(document).ready(function () {--%>
      <%--$("#content-slider").lightSlider({--%>
        <%--loop: true,--%>
        <%--keyPress: true--%>
      <%--});--%>
      <%--$('#image-gallery').lightSlider({--%>
        <%--gallery: true,--%>
        <%--item: 1,--%>
        <%--thumbItem: 9,--%>
        <%--slideMargin: 0,--%>
        <%--speed: 500,--%>
        <%--auto: true,--%>
        <%--loop: true,--%>
        <%--onSliderLoad: function () {--%>
          <%--$('#image-gallery').removeClass('cS-hidden');--%>
        <%--}--%>
      <%--});--%>
    <%--});--%>
  <%--</script>--%>
  <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
  <!-- CSS start here -->
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css" />"  media="screen">
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap-select.min.css"/>" />
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/styles.css" />"  />
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/font-awesome/css/font-awesome.min.css" />" >
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/animate.css" />"  />
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/owl.carousel.css" />"  />
  <link rel="stylesheet"  href="<c:url value="/resources/css/flexslider.css" />" />
  <link type="text/css" href="<c:url value="/resources/css/jquery-ui-1.10.0.custom.css"/>" rel="stylesheet" />
  <script src="<c:url value="/resources/js/bootstrap-select.js"/>"></script>
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/cloudzoom.css" />" />
  <link rel="stylesheet" href="<c:url value="/resources/css/easyzoom.css"/> " />
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/datepicker.css"/> " />
  <link rel="stylesheet" href="<c:url value="/resources/css/zoom.css"/>" />
  <script src="<c:url value="/resources/js/dropzone.js"/>"></script>
  <link rel="stylesheet" href="http://www.elevateweb.co.uk/wp-content/themes/radial/jquery.fancybox.css" />
  <!-- Theme CSS -->
  <!-- <link href="css/clean-blog.css" rel="stylesheet"> -->
  <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,900" rel="stylesheet">
  <!-- Google fonts end here -->
</head>

