<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 9/28/16
  Time: 1:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:directive.include file="layouts/header.jsp" />
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
  <jsp:directive.include file="layouts/navbar-top.jsp" />
  <jsp:directive.include file="layouts/left-slider.jsp" />
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <jsp:directive.include file="layouts/pageHeader.jsp" />
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <%--<h3 class="box-title">Data Table With Full Features</h3>--%>
            </div><!-- /.box-header -->
            <div class="box-body">
              <table id="example1" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>Type</th>
                  <th>Description</th>
                  <th>Status</th>
                </tr>
                </thead>
                <tbody>
                <d:forEach var="notification" items="${adminGlobalNotifications}">
                  <tr>
                    <td>${notification.getType()}</td>
                    <td>${notification.getDetails()}</td>
                    <d:if test="${notification.getIsRead() == true}">
                      <td>Read</td>
                    </d:if>
                    <d:if test="${notification.getIsRead() == false}">
                      <td>Unread</td>
                    </d:if>
                  </tr>
                </d:forEach>
                </tbody>
                <tfoot>
                <tr>
                  <th>Type</th>
                  <th>Description</th>
                  <th>Status</th>
                </tr>
                </tfoot>
              </table>
            </div><!-- /.box-body -->
          </div><!-- /.box -->
        </div><!-- /.col -->
      </div><!-- /.row -->
    </section><!-- /.content -->
  </div><!-- /.content-wrapper -->
  <jsp:directive.include file="layouts/footer.jsp" />
</body>
</html>