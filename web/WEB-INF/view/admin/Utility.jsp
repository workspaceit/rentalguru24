<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 8/23/16
  Time: 3:37 PM
  To change this template use File | Settings | File Templates.
--%>

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
    <section class="content-header">
      <h1>
        Utility

      </h1>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">

            <div class="box-body">
              <section class="invoice">
                <!-- title row -->
                <div class="row">
                  <div class="col-xs-12">
                    <h2 class="page-header">
                      <i class="fa fa-money"></i> Paypal Configuration

                    </h2>
                  </div><!-- /.col -->
                </div>
                <!-- info row -->
                <div class="row invoice-info">
                  <div class="col-sm-4 invoice-col">
                    <table>
                      <tr>
                        <td width="200px">Api Key:</td>
                        <td><strong>asdqw423wadfafdgq346tdafgafg</strong></td>
                      </tr>

                      <tr>
                        <td width="200px">Api Secret:</td>
                        <td><strong>asdqw423wadfafdgq346tdafgafg</strong></td>
                      </tr>

                    </table>

                    <button style="margin-top: 30px" class="btn btn-success">Update</button>
                  </div><!-- /.col -->
                  </div>
                </section>


              <section class="invoice">
                <!-- title row -->
                <div class="row">
                  <div class="col-xs-12">
                    <h2 class="page-header">
                      <i class="fa  fa-cube"></i> Commision Configuration

                    </h2>
                  </div><!-- /.col -->
                </div>
                <!-- info row -->
                <div class="row invoice-info">
                  <div class="col-sm-4 invoice-col">
                    <table>
                      <tr>
                        <td width="200px">Api Key:</td>
                        <td><strong>asdqw423wadfafdgq346tdafgafg</strong></td>
                      </tr>

                      <tr>
                        <td width="200px">Api Secret:</td>
                        <td><strong>asdqw423wadfafdgq346tdafgafg</strong></td>
                      </tr>

                    </table>

                    <button style="margin-top: 30px" class="btn btn-success">Update</button>
                  </div><!-- /.col -->
                </div>
              </section>

            </div><!-- /.box-body -->
          </div><!-- /.box -->
        </div><!-- /.col -->
      </div><!-- /.row -->
    </section><!-- /.content -->
  </div><!-- /.content-wrapper -->
  <jsp:directive.include file="layouts/footer.jsp" />

</body>
</html>