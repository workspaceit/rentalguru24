<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 9/26/16
  Time: 11:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:directive.include file="../layouts/header.jsp" />
<body class="ux">
<!--top Nav Bar-->
<jsp:directive.include file="../layouts/top-nav.jsp" />
<!--mid navbar-->
<jsp:directive.include file="../layouts/mid-nav.jsp" />
<!--main navbar-->
<jsp:directive.include file="../layouts/main-nav.jsp" />
<!--end main Nav-->
<!--Dashboard-->
<div class="container user-dash-container">
  <div class="row">
    <jsp:directive.include file="../layouts/userDashboardLeftMemu.jsp" />
    <div class="col-md-9 side-container">
      <h3>Paypal Account Email</h3>
      <form class="form-signup clearfix form-edit col-md-9" onsubmit="return setPaypalEmail();">
        <div>
          <div class="form-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" placeholder="ex.email@email.com" id="email" name="email" value="${userPaypalCredential.getEmail()}">
            <p class="help-block error-form" id="errorMsg_email"></p>
          </div>

        </div>
        <div class="col-md-12 text-right">
          <button class="btn-cstm-sign  pos-relative" style="margin-right:0px;" id="paypalEmailButton" >
            Save
            <span class="inner-load" hidden id="saveGif"></span>
          </button>
          <br>
          <div class="alert alert-success text-center" role="alert" hidden >Paypal Account Email Save</div>
        </div>
      </form>
    </div>
  </div>
</div>
<!--Dashboard-->
<jsp:directive.include file="../layouts/top-footer.jsp" />
<jsp:directive.include file="../layouts/footer.jsp" />
<!-- Javascript framework and plugins end here -->
</body>
<script>
  function setPaypalEmail(){
    var email = $("#email").val();
    $("#email").attr("disabled","disabled");
    $("#paypalEmailButton").attr("disabled","disabled");
    $("#saveGif").show();
    $.ajax({
      type: "POST",
      url: BASEURL+"/api/auth/paypal/add-update/my-paypal-account-email",
      data:{
        email: email
      },
      success: function(data){
        if(data.responseStat.status == true){
          $("#email").removeAttr("disabled","disabled");
          $("#saveGif").hide();
          $("#paypalEmailButton").removeAttr("disabled","disabled");
          var url =BASEURL+"/user/dashboard/my-paypal-account-email";
          var prevUrl = "";
          prevUrl = location.search.split('r=')[1];
          url=(prevUrl!=undefined)? decodeURIComponent( prevUrl):url;

          $('.alert-success').show().delay(1500).fadeOut(500,function(){
            window.location.href = url;
          });
        }
        else{
          BindErrorsWithHtml("errorMsg_", data.responseStat.requestErrors);
          $("#email").removeAttr("disabled","disabled");
          $("#paypalEmailButton").removeAttr("disabled","disabled");
          $("#saveGif").hide();
        }
      },
      error: function(data){
        console.log("ERROR");
        $("#saveGif").hide();
        $("#email").removeAttr("disabled","disabled");
        $("#paypalEmailButton").removeAttr("disabled","disabled");
      }
    });

    return false;
  }
</script>
</html>

