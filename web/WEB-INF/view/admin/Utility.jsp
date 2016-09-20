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
    <jsp:directive.include file="layouts/pageHeader.jsp" />
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
                        <td><strong>${paypalCredientail.apiKey}</strong></td>
                      </tr>

                      <tr>
                        <td width="200px">Api Secret:</td>
                        <td><strong>${paypalCredientail.apiSecret}</strong></td>
                      </tr>

                    </table>

                    <a style="margin-top: 30px" class="btn btn-success" href="${BaseUrl}/admin/user/edit-paypal-configuration" role="button">Update</a>
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
                        <td width="200px">Commission Type</td>
                        <d:choose>
                          <d:when test="${siteFeesCredientail.fixed==true}">
                            <td><strong>Fixed</strong></td>
                          </d:when>
                          <d:when test="${siteFeesCredientail.percentage==true}">
                            <td><strong>In Percentage</strong></td>
                          </d:when>
                        </d:choose>

                      </tr>

                      <tr>
                        <td width="200px">Commission Amount</td>
                        <d:choose>
                          <d:when test="${siteFeesCredientail.fixed==true}">
                            <td><strong>$ ${siteFeesCredientail.fixedValue}</strong></td>
                          </d:when>
                          <d:when test="${siteFeesCredientail.percentage==true}">
                            <td><strong>${siteFeesCredientail.percentageValue} %</strong></td>

                          </d:when>
                        </d:choose>

                      </tr>

                    </table>

                    <a style="margin-top: 30px" class="btn btn-success" href="${BaseUrl}/admin/user/edit-site-fees" role="button">Update</a>
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
