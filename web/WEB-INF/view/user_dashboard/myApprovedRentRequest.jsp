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
      <h3>My Approve Rent Request</h3>

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
              <th>Product Name</th>
              <th>Requested By</th>
              <th>Start Date</th>
              <th>End Date</th>
              <th>Status</th>


            </tr>
            </thead>
            <tbody>
            <d:forEach var="rentRequest" items="${rentRequests}">
            <tr id="${rentRequest.id}">
              <td width="300px">${rentRequest.rentalProduct.name}<br><br><a href="${BaseUrl}/rent/request/${rentRequest.getId()}" target="_blank">Order Details</a></td>
              <td>${rentRequest.requestedBy.userInf.firstName}</td>
              <td><fmt:formatDate value="${rentRequest.startDate}" pattern="MMM d,yyyy"></fmt:formatDate></td>
              <td><fmt:formatDate value="${rentRequest.endDate}" pattern="MMM d,yyyy"></fmt:formatDate></td>
              <td style="color: green">Approved</td>
            </tr>
            </d:forEach>

          </table>
        </div>
      </div>
    </div>

  </div>

</div>
<!--Dashboard-->
<jsp:directive.include file="../layouts/footer.jsp"/>
<!-- Javascript framework and plugins end here -->



<script>
  var nowTemp = new Date();
  var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);

  var checkin = $('#dpd1').datepicker({
    onRender: function (date) {
      return date.valueOf() < now.valueOf() ? 'disabled' : '';
    }
  }).on('changeDate', function (ev) {
    if (ev.date.valueOf() > checkout.date.valueOf()) {
      var newDate = new Date(ev.date)
      newDate.setDate(newDate.getDate() + 1);
      checkout.setValue(newDate);
    }
    checkin.hide();
    $('#dpd2')[0].focus();
  }).data('datepicker');
  var checkout = $('#dpd2').datepicker({
    onRender: function (date) {
      return date.valueOf() <= checkin.date.valueOf() ? 'disabled' : '';
    }
  }).on('changeDate', function (ev) {
    checkout.hide();
  }).data('datepicker');
</script>
<script type="text/javascript">
  $('.main_product_slider').carousel();
  $('.owl-carousel').owlCarousel({
    rtl: true,
    loop: true,
    margin: 10,
    nav: true,
    responsive: {
      0: {
        items: 1
      },
      600: {
        items: 3
      },
      1000: {
        items: 5
      }
    }
  });
</script>
<script>
  (function ($) {
    $('.spinner .btn:first-of-type').on('click', function () {
      $('.spinner input').val(parseInt($('.spinner input').val(), 10) + 1);
    });
    $('.spinner .btn:last-of-type').on('click', function () {
      $('.spinner input').val(parseInt($('.spinner input').val(), 10) - 1);
    });
  })(jQuery);
</script>
<script>
  $('#h-slider').slider({
    range: true,
    values: [17, 67]
  });
</script>
<script>

  (function ($) {

    //Plugin activation
    $(window).enllax();

    //            $('#some-id').enllax();

    //            $('selector').enllax({
    //                type: 'background', // 'foreground'
    //                ratio: 0.5,
    //                direction: 'vertical' // 'horizontal'
    //            });

  })(jQuery);
</script>
<script>
  $(window).load(function () {
    // The slider being synced must be initialized first
    $('#carousel').flexslider({
      animation: "slide",
      controlNav: false,
      animationLoop: false,
      slideshow: false,
      itemWidth: 210,
      itemMargin: 5,
      asNavFor: '#slider'
    });

    $('#slider').flexslider({
      animation: "slide",
      controlNav: false,
      animationLoop: false,
      slideshow: false,
      sync: "#carousel"
    });
  });
</script>
<script type="text/javascript">
  //  $(document).ready(function () {
  //    $("#successModal").modal('show');
  //  });
</script>
<script>
  $(document).ready(function () {
    $('#example1').DataTable();
  });
</script>
<script>
  var nowTemp = new Date();
  var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);

  var checkin = $('#dpd1').datepicker({
    onRender: function (date) {
      return date.valueOf() < now.valueOf() ? 'disabled' : '';
    }
  }).on('changeDate', function (ev) {
    if (ev.date.valueOf() > checkout.date.valueOf()) {
      var newDate = new Date(ev.date)
      newDate.setDate(newDate.getDate() + 1);
      checkout.setValue(newDate);
    }
    checkin.hide();
    $('#dpd2')[0].focus();
  }).data('datepicker');
  var checkout = $('#dpd2').datepicker({
    onRender: function (date) {
      return date.valueOf() <= checkin.date.valueOf() ? 'disabled' : '';
    }
  }).on('changeDate', function (ev) {
    checkout.hide();
  }).data('datepicker');
</script>

</body>
</html>


