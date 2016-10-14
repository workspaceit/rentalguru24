<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 8/23/16
  Time: 2:53 PM
  To change this template use File | Settings | File Templates.
--%>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>RentGuru24 "ADMIN"</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.5 -->
  <link rel="stylesheet" href="<c:url value="/admin-resources/bootstrap/css/bootstrap.min.css"/>">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  <!-- DataTables -->
  <link rel="stylesheet" href="<c:url value="/admin-resources/plugins/datatables/dataTables.bootstrap.css"/>">
  <!-- Theme style -->
  <link rel="stylesheet" href="<c:url value="/admin-resources/dist/css/AdminLTE.min.css"/>">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="<c:url value="/admin-resources/dist/css/skins/_all-skins.min.css"/>">
  <link rel="stylesheet" href="<c:url value="/admin-resources/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css"/>">
  <!-- Custom By Developer -->
  <link rel="stylesheet" href="<c:url value="/admin-resources/developer/css/style.css"/>">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>