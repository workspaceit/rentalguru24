<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 8/24/16
  Time: 5:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<jsp:directive.include file="../layouts/header.jsp"/>
<body class="ux">
<!--top Nav Bar-->
<jsp:directive.include file="../layouts/top-nav.jsp"/>
<!--mid navbar-->
<jsp:directive.include file="../layouts/mid-nav.jsp"/>
<!--main navbar-->
<jsp:directive.include file="../layouts/main-nav.jsp"/>
<!--end main Nav-->
<!-- Dashboard-->
<div class="container user-dash-container">
  <div class="row">
    <jsp:directive.include file="../layouts/userDashboardLeftMemu.jsp" />
    <div class="col-md-9 side-container">
      <h3>My Booking</h3>

      <%--<div class="row clearfix no-margin">--%>
        <%--<div class="col-md-4 col-sm-4 col-xs-12">--%>
          <%--<div class="form-group date-con">--%>
            <%--<label>From</label>--%>
            <%--<input type="text" class="form-control datepicker" id="dpd1" placeholder="">--%>

            <%--<p class="help-block error-form">Please fill up the field</p>--%>
          <%--</div>--%>
        <%--</div>--%>

        <%--<div class="col-md-4 col-sm-4 col-xs-12">--%>
          <%--<div class="form-group date-con">--%>
            <%--<label>To</label>--%>
            <%--<input type="text" class="form-control datepicker" id="dpd2" placeholder="">--%>

            <%--<p class="help-block error-form">Please fill up the field</p>--%>
          <%--</div>--%>
        <%--</div>--%>
        <%--<button class="btn-filter user_dboard_btn">Date Filters</button>--%>
      <%--</div>--%>
      <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
          <table id="example1" class="table table-bordered table-striped user_dashboard_table">
            <thead>
            <tr>
              <th>Product Title</th>
              <th>Requested By</th>
              <th>Start Date</th>
              <th>End Date</th>
              <th>Action</th>

            </tr>
            </thead>
            <tbody>
            <d:forEach var="myRentRequest" items="${myRentRequests}">
            <tr id="${myRentRequest.id}">
              <td width="300px">${myRentRequest.rentalProduct.name}<br><br><a href="${BaseUrl}/rent/request/${myRentRequest.getId()}" target="_blank">Order Details</a></td>
              <td>${myRentRequest.requestedBy.userInf.firstName}</td>
              <td><fmt:formatDate value="${myRentRequest.startDate}" pattern="MMM d,yyyy"></fmt:formatDate></td>
              <td><fmt:formatDate value="${myRentRequest.endDate}" pattern="MMM d,yyyy"></fmt:formatDate></td>
              <td width="100px">
                <div class="actions">
                  <button class="btn btn-delete" onclick="cancelRequest(${myRentRequest.id})">Cancel</button>
                </div>
              </td>

            </tr>
            </d:forEach>
          </table>
        </div>
      </div>
    </div>

  </div>
  <div class="modal" id="myModal" role="dialog">
    <div class="modal-dialog">

      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Message</h4>
        </div>
        <div class="modal-body">
          <p id="modal-text"></p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>

    </div>
  </div>
</div>
<!--Dashboard-->
<jsp:directive.include file="../layouts/top-footer.jsp" />
<jsp:directive.include file="../layouts/footer.jsp"/>
<!-- Javascript framework and plugins end here -->
<script>
  function cancelRequest(requestId){
    $.ajax({
      type: "GET",
      url: '${BaseUrl}/api/auth/rent/cancel-request/'+requestId,
      success: function(data) {
        if(data.responseStat.status==true){
          $('#' + requestId).fadeOut(300);
        }else{
          $('#modal-text').text(data.responseStat.msg)
          $('#myModal').modal('show')
        }
      },
      error: function() {
        alert('Error occured');
      }
    });
  }
</script>
</body>
</html>


