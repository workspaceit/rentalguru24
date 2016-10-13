<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 9/29/16
  Time: 1:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:directive.include file="layouts/header.jsp"/>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
  <jsp:directive.include file="layouts/navbar-top.jsp"/>
  <jsp:directive.include file="layouts/left-slider.jsp"/>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <jsp:directive.include file="layouts/pageHeader.jsp"/>
    <!-- Main content -->
    <section class="content">
      <!-- Default box -->
      <div class="box">
        <div class="box-header with-border">
          <h3 class="box-title">${adminGlobalNotification.getNotificationTemplate().getTitle()}</h3>
        </div>
        <div class="box-body">
          ${adminGlobalNotification.getNotificationTemplate().getTemplate()}
        </div><!-- /.box-body -->
        <div class="box-footer"></div><!-- /.box-footer-->
        <div class="box-header with-border">
          <h3 class="box-title">Title</h3>
        </div>
        <div class="box-body">
          Start creating your amazing application!
        </div><!-- /.box-body -->
        <div class="box-footer">
          <div class="col-md-3">
            <a class="btn btn-block btn-info" href="${BaseUrl}/admin/user/order-details/${adminGlobalNotification.getRentInf().getId()}">Order Details</a>
          </div>
        </div><!-- /.box-footer-->
      </div><!-- /.box -->
      <!-- /.row -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
</div>
<jsp:directive.include file="layouts/footer.jsp"/>
</body>
</html>

