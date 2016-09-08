<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 9/5/16
  Time: 10:20 AM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<jsp:directive.include file="../layouts/header.jsp" />
<body class="ux">
<div class="container-fluid top_nav">
  <div class="row">
    <div class="container">
      <div class="row">
        <div class="col-md-6 col-sm-6 col-xs-5">
          <ul class="top_nav_ul">
            <%--<li class="dropdown">--%>
            <%--<a href="#" class="dropdown-toggle top_nav_a" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">English <span class="caret"></span></a>--%>
            <%--<ul class="dropdown-menu">--%>
            <%--<li><a href="#">Action</a></li>--%>
            <%--</ul>--%>
            <%--</li>--%>
            <%--<li class="dropdown">--%>
            <%--<a href="#" class="dropdown-toggle top_nav_a" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Help <span class="caret"></span></a>--%>
            <%--<ul class="dropdown-menu">--%>
            <%--<li><a href="#">Action</a></li>--%>
            <%--</ul>--%>
            <%--</li>--%>
          </ul>
        </div>
        <div class="col-md-6 col-sm-6 col-xs-7 ">
          <ul class="top_nav_ul right top_nav_ul">
            <li class="dropdown">
              <a href="${BaseUrl}/signin" class="dropdown-toggle top_nav_a"
                 aria-expanded="false">
                <i class="fa fa-user"></i>Login
              </a>
            </li>
            <li class="dropdown">
              <a href="${BaseUrl}/signup" class="dropdown-toggle top_nav_a"
                 aria-expanded="false">
                <i class="fa fa-lock"></i>Register
              </a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</div>
<!--top Nav Bar-->
<div class="container-fluid mid_nav">
  <div class="row">
    <div class="container">
      <div class="row">
        <div class="col-md-6 col-sm-6 col-xs-6">
          <ul class="mid_nav_logo_ul">
            <li class="">
              <a href="${BaseUrl}/home"><img src="<c:url value="/resources/img/logo.png" />" ></a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="parallax-window bg1" data-enllax-ratio="0.7">
  <div class="container title-block">
    <h1>Change Password</h1>
    <p></p>
  </div>
</div>
<div class="container center-bg">
  <form class="form-signup clearfix" onsubmit="return false;">
    <div class="col-md-12">
      <div class="form-group">
        <label for="email">Email</label>
        <input type="email" class="form-control" placeholder="ex.email@email.com" id="email" name="email">
        <p class="help-block error-form" id="errorMsg_email"></p>
      </div>
    </div>
    <div class="col-md-12" >
      <div class="form-group">
        <label for="password">Password</label>
        <input type="password" class="form-control" placeholder="" id="password" name="password">
        <p class="help-block error-form" id="errorMsg_password"></p>
      </div>
    </div>
    <div class="col-md-12">
      <div class="form-group">
        <label for="confirmPassword">Confirm Password</label>
        <input type="password" class="form-control" placeholder="" id="confirmPassword" name="confirmPassword">
        <p class="help-block error-form" id="errorMsg_conPassword"></p>
      </div>
    </div>
    <div class="col-md-12 text-center">
      <button class="btn-cstm-sign pos-relative" id="changePassword" onclick="submitChangePassword()">Change Password
        <span id="changePasswordGif" class="inner-load " hidden></span>
      </button>
      <div id="alertMsg" class="alert alert-success text-center" role="alert" hidden></div>
      <div id="errorMsg_token" class="alert alert-danger text-center" role="alert" hidden></div>
    </div>
    <input hidden id="_token" name="_token" value="${token}">
  </form>
</div>

<jsp:directive.include file="../layouts/top-footer.jsp" />
<jsp:directive.include file="../layouts/footer.jsp" />
<script>
  var BASEURL = "${BaseUrl}";
</script>
<!-- Contact end here -->
<!-- Main container start here -->
<!-- Javascript framework and plugins start here -->
<script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />" ></script>
<script src="<c:url value="/resources/js/dropzone.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />" ></script>
<script src="<c:url value="/resources/js/jquery.validate.min.js" />" ></script>
<script src="<c:url value="/resources/js/modernizr.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/appear.js" />" ></script>
<script src="<c:url value="/resources/js/jquery.knob.js" />" ></script>
<script src="<c:url value="/resources/js/jquery.ccountdown.js" />" ></script>
<script src="<c:url value="/resources/js/init.js" />" ></script>
<script src="<c:url value="/resources/js/general.js" />" ></script>
<!-- Theme JavaScript -->
<script src="<c:url value="/resources/js/clean-blog.min.js" />" ></script>
<script src="<c:url value="/resources/js/owl.carousel.js"  />" ></script>
<script src="<c:url value="/resources/js/jquery.enllax.min.js" />" ></script>
<script src="<c:url value="/resources/developer/js/helper/ErrorSuccessModal.js"  />" ></script>
<script src="<c:url value="/resources/developer/js/helper/ErrorMessaging.js" />" ></script>


<!-- Javascript framework and plugins end here -->
<script type="text/javascript">
  $("div#fallback").dropzone({ url: BASEURL+"/file/post" });

  $('.main_product_slider').carousel();
  $('.owl-carousel').owlCarousel({
    rtl:true,
    loop:true,
    margin:10,
    nav:true,
    responsive:{
      0:{
        items:1
      },
      600:{
        items:3
      },
      1000:{
        items:5
      }
    }
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
  function submitChangePassword(){
    $("#changePassword").attr("disabled","disabled");
    $("#changePasswordGif").show();
    var email = $("#email").val();
    var password = $("#password").val()
    var confirmPassword = $("#confirmPassword").val()
    var token = $("#_token").val();

    $.ajax({
      url: BASEURL+'/api/reset-password/change-password/'+token,
      type: 'POST',
      data: {
        email: email,
        password: password,
        conPassword: confirmPassword
      },
      success: function(data){
        console.log(data);
        if(data.responseStat.status == true){
          $("#alertMsg").html(data.responseStat.msg).fadeIn(500).delay(2000).fadeOut(500,function(){
            window.location.href =BASEURL+"/signin";
          });
        }else{
          BindErrorsWithHtml('errorMsg_', data.responseStat.requestErrors);
          $("#changePassword").removeAttrs("disabled","disabled");
        }
        $("#changePasswordGif").hide();
      }
    });
    return false;
  }
</script>

</body>
</html>

