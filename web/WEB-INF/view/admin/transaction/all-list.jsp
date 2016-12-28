<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 10/25/16
  Time: 4:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:directive.include file="../layouts/header.jsp" />
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
  <jsp:directive.include file="../layouts/navbar-top.jsp" />
  <jsp:directive.include file="../layouts/left-slider.jsp" />
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <jsp:directive.include file="../layouts/pageHeader.jsp" />
    <!-- Main content -->
    <section class="content">
      <div class="alert alert-success bannerImageDeleteSuccess" hidden>
        Banner Delete Successfully, Redirecting.....
      </div>
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
                      <th>Total Order</th>
                      <th>Company Earning</th>
                      <th>Renter Earning</th>
                      <th>Total Dispute</th>
                    </tr>
                </thead>
                <tbody>
                  <tr>
                      <td>1231</td>
                      <td>$ 454</td>
                      <td>$ 552</td>
                      <td>$ 635</td>
                  </tr>
                </tbody>
                <tfoot>
                  <tr>
                    <th>Total Order</th>
                    <th>Company Earning</th>
                    <th>Renter Earning</th>
                    <th>Total Dispute</th>
                  </tr>
                </tfoot>
              </table>
              <table id="example2" class="table table-bordered table-striped">
                <thead>
                  <tr>
                    <th>Id</th>
                    <th>Order</th>
                    <th>Owner</th>
                    <th>Renter</th>
                    <th>Amount</th>
                    <th>Status</th>
                  </tr>
                </thead>
                <tbody>
                <tr>
                  <td>1231</td>
                  <td>View Order</td>
                  <td>Owner Name</td>
                  <td>Renter</td>
                  <td>$ 898</td>
                  <td>OK</td>
                </tr>
                <tr>
                  <td>1232</td>
                  <td>View Order</td>
                  <td>Owner Name</td>
                  <td>Renter</td>
                  <td>$ 635</td>
                  <td>DISPUTE</td>
                </tr>
                </tbody>
                <tfoot>
                <tr>
                  <th>Id</th>
                  <th>Order</th>
                  <th>Owner</th>
                  <th>Renter</th>
                  <th>Amount</th>
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
  <jsp:directive.include file="../layouts/footer.jsp" />

</body>
</html>
