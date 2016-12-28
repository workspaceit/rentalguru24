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
              <table id="rentPaymentSummaryTbl" class="table table-bordered table-striped">
                <thead>
                    <tr>
                      <th>Total Order</th>
                      <th>Company Earning</th>
                      <th>Renter Earning</th>
                    </tr>
                </thead>
                <tbody>
                  <tr>
                      <td>${rentPaymentSummary.totalOrderCount}</td>
                      <td>$ ${rentPaymentSummary.companyEarning}</td>
                      <td>$ ${rentPaymentSummary.renteerEarning}</td>
                  </tr>
                </tbody>
                <tfoot>
                  <tr>
                    <th>Total Order</th>
                    <th>Company Earning</th>
                    <th>Renter Earning</th>
                  </tr>
                </tfoot>
              </table>
              <table id="rentPaymentTbl" class="table table-bordered table-striped">
                <thead>
                  <tr>
                    <th>Id</th>
                    <th>Order</th>
                    <th>Owner</th>
                    <th>Renter</th>
                    <th>Site fees</th>
                    <th>Owner's earning</th>
                    <th>Refunded amount</th>
                    <th>Amount deposit</th>
                    <th>Status</th>
                    <th>Date</th>
                  </tr>
                </thead>
                <tbody>
                  <d:forEach var="rentPayment" items="${rentPaymentList}" >
                    <tr>
                      <td>${rentPayment.id}</td>
                      <td>
                        <a href="${baseURL}/admin/rent-request/details/${rentPayment.rentRequest.rentalProduct.owner.id}" target="_blank">
                            ${rentPayment.rentRequest.id}
                        </a>


                      </td>
                      <td>
                        <a href="${baseURL}/admin/user/app-user/details/${rentPayment.rentRequest.rentalProduct.owner.id}" target="_blank">
                            ${rentPayment.rentRequest.rentalProduct.owner.userInf.firstName} ${rentPayment.rentRequest.rentalProduct.owner.userInf.lastName}
                        </a>
                      </td>
                      <td>
                        <a href="${baseURL}/admin/user/app-user/details/${rentPayment.rentRequest.requestedBy.id}" target="_blank">
                             ${rentPayment.rentRequest.requestedBy.userInf.firstName} ${rentPayment.rentRequest.requestedBy.userInf.lastName}
                        </a>
                      </td>
                      <td>${rentPayment.siteFee}</td>
                      <td>${rentPayment.rentRequest.rentFee}</td>
                      <td>${rentPayment.refundAmount}</td>
                      <td>${rentPayment.totalAmount}</td>
                      <td>
                          <d:if test="${rentPayment.rentRequest.isRentComplete}">
                                <span>Complete</span>
                          </d:if>
                          <d:if test="${rentPayment.rentRequest.approve && !rentPayment.rentRequest.isRentComplete}">
                            <span>On progress</span>
                          </d:if>
                          <d:if test="${rentPayment.rentRequest.disapprove}">
                            <span>Disapproved by owner</span>
                          </d:if>
                          <d:if test="${rentPayment.rentRequest.requestCancel}">
                            <span>Canceled by requester</span>
                          </d:if>
                          <d:if test="${!rentPayment.rentRequest.approve && !rentPayment.rentRequest.disapprove && !rentPayment.rentRequest.isExpired}">
                            <span>Pending</span>
                          </d:if>
                          <d:if test="${!rentPayment.rentRequest.requestCancel
                                            && !rentPayment.rentRequest.approve
                                            && !rentPayment.rentRequest.disapprove
                                            && rentPayment.rentRequest.isExpired}">
                            <span>Expired</span>
                          </d:if>
                      </td>
                      <td>
                        <span class="utcToLocalDate" style="display: none;">${rentPayment.createdDate.getTime()}</span>
                      </td>
                    </tr>
                  </d:forEach>
                </tbody>
                <tfoot>
                <tr>
                  <th>Id</th>
                  <th>Order</th>
                  <th>Owner</th>
                  <th>Renter</th>
                  <th>Site fees</th>
                  <th>Owner's earning</th>
                  <th>Refunded amount</th>
                  <th>Amount deposit</th>
                  <th>Status</th>
                  <th>Date</th>
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
<script>
  (function(){

    $("#rentPaymentSummaryTbl").DataTable({"searching": false});
    $("#rentPaymentTbl").DataTable();
    /*UTC time to Local*/
    $(".utcToLocalDate").each(function(){
      var timeStamp = $(this).html();
      try{
        timeStamp = parseInt(timeStamp);
        var localDate = convertUTCDateToLocalDate(new Date(timeStamp));
        $(this).html(dateFormat(localDate, "ddd, mmm dS, yyyy")); //, h:MM:ss TT
        $(this).show();
        console.log(localDate);
      }catch(ex){
        console.log(ex);
      }

    });
  //
    console.log("ASD");
  })();
  console.log("ASD1");
</script>
</body>
</html>
