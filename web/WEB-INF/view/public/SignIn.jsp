<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d" %>
<!DOCTYPE html>
<html lang="en">
<jsp:directive.include file="../layouts/header.jsp" />

<body class="ux">
<div class="container-fluid top_nav">
  <div class="row">
    <div class="container">
      <div class="row">
        <div class="col-md-6 col-sm-6 col-xs-12 support-div">
          <a href="mailto:support@rentguru24.com" class="support-a"><i class="fa fa-envelope" aria-hidden="true">&nbsp;&nbsp;&nbsp;&nbsp;support@rentguru24.com</i></a>
          <%--<ul class="top_nav_ul">--%>
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
          <%--</ul>--%>
        </div>
        <div class="col-md-6 col-sm-6 col-xs-7 ">
          <ul class="top_nav_ul right top_nav_ul">
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
              <a href="${BaseUrl}/home"><img src="<c:url value="/resources/img/logo.gif" />" ></a>
            </li>
          </ul>
        </div>
        <div class="col-md-6 col-sm-6 col-xs-6 ">
          <%--<label class="right mid_nav_contact"><i class="fa fa-mobile fa-2x contact_icon"></i><span class="conatct_number">2300-3560-222</span></label>--%>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="parallax-window bg1" data-enllax-ratio="0.7">
  <div class="container title-block">
    <h1>Sign in</h1>
    <p>Sign in for the great Deals. Explore More</p>
  </div>
</div>
<div class="container center-bg">
  <form class="form-signup clearfix" onsubmit="return false;">
    <div class="col-md-6" id="signin_left">
      <div class="col-md-12">
        <div class="form-group">
          <label for="email">Email</label>
          <input type="email" class="form-control" placeholder="ex.email@email.com" id="email" name="email">
          <p class="help-block error-form" id="errorMsg_email" for="email" custom-validation="required" errorMsg="Please provide email" ></p>
        </div>
        <div class="form-group">
          <label for="password">Password</label>
          <input type="password" class="form-control" placeholder="ex.password" id="password" name="password">
          <p class="help-block error-form" id="errorMsg_password" for="password" custom-validation="required" errorMsg="Please provide password" ></p>
        </div>
        <div class="checkbox">
          <label><input type="checkbox"> Remember me</label>
          <label style="float: right"><a href="${BaseUrl}/reset-password/request">forgot your password ?</a></label>
        </div>
      </div>

    </div>
    <div class="col-md-1 pos-relative">
      <div class="divider-sign">
        or
      </div>
    </div>
    <div class="col-md-5 social-sign">
      <jsp:directive.include file="SocialLogin.jsp" />
    </div>
    <%--<div class="col-md-5 social-sign">--%>
      <%--<a class="btn btn-block btn-social btn-facebook" onclick="loginWithFacebook()" >--%>
        <%--<i class="fa fa-facebook"></i> Sign in with Facebook--%>
        <%--<span id="fbLoginProgressImg" class="inner-load signUpGif" style="display:none;"></span>--%>
      <%--</a>--%>
      <%--<a class="btn btn-block btn-social btn-google-plus" id="loginWithGooGleBtn" >--%>
        <%--<i class="fa fa-google-plus"></i> Sign in with Google--%>
        <%--<span id="googleLoginInProgressImg" class="inner-load signUpGif" style="display:none;"></span>--%>
      <%--</a>--%>
    <%--</div>--%>

    <div class="col-md-12 text-center">
      <button class="btn-cstm-sign pos-relative" id="signBtn" onclick="submitSignInData()">Sign in
        <span id="signInProgressImg" class="inner-load signUpGif" hidden></span>
      </button>
      <div id="alertMsg" class="alert alert-success text-center" role="alert" hidden>
      </div>
    </div>
  </form>
</div>
<jsp:directive.include file="../layouts/top-footer.jsp" />

<div class="footer_bottom">
  <div class="container">
    <div class="row">
      <div class="col-md-8 col-sm-12 col-xs-12 col-md-offset-4">
        <div class="bottom_footer_content">
          <div class="copyright">
            <p class="no-margin"> &#169; Copyright 2016 Reneguru24 | All Rights Reserved</p>
          </div>
          <div class="social_link">
            <p class="no-margin"> <span class="social_link_i"><i class="fa fa-twitter"></i></span><span class="social_link_i"><i class="fa fa-facebook"></i></span><span class="social_link_i"><i class="fa fa-youtube"></i></span><span class="social_link_i"><i class="fa fa-google-plus"></i></span><span class="social_link_i"><i class="fa fa-linkedin"></i></span><span class="social_link_i"><i class="fa fa-pinterest"></i></span></p>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
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

<%--Google Js API--%>
<%--<script src="https://apis.google.com/js/api:client.js"></script>--%>
<%--Developer Helpers --%>
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
  function submitSignInData(){
    UnBindErrors("errorMsg_");
    if(!requiredValidation("errorMsg_")){
      return false;
    }
    $("#signBtn").attr("disabled","disabled");
    $("#alertMsg").hide();
    $("#signInProgressImg").show();
    var email = $("#email").val();
    var password = $("#password").val();
    $.ajax({
      url: BASEURL+'/api/signin/by-email-password',
      type: 'POST',
      data: {
        email: email,
        password : password
      },
      success: function(data){
        console.log(data);
        if(data.responseStat.status == true){
          $("#alertMsg").html(data.responseStat.msg).fadeIn(500).delay(2000).fadeOut(500,function(){
            var url =BASEURL+"/home";
            var prevUrl = "";
            prevUrl = location.search.split('r=')[1];
            url=(prevUrl!=undefined)? decodeURIComponent( prevUrl):url;
            window.location.href = url;
          });
        }else{
          $("#alertMsg").html(data.responseStat.msg).fadeIn(500).delay(3000).fadeOut(500,function(){
            $("#signBtn").removeAttr("disabled","disabled");
          });
        }
        $("#signInProgressImg").hide();

      }
    });
    return false;
  }
</script>
<%--<script>--%>


  <%--window.fbAsyncInit = function() {--%>
    <%--FB.init({--%>
      <%--appId      : '48614701501', // Office Live : 591163881067974 , Local : 141550766294824--%>
      <%--cookie     : true,  // enable cookies to allow the server to access--%>
                          <%--// the session--%>
      <%--xfbml      : true,  // parse social plugins on this page--%>
      <%--version    : 'v2.5' // use graph api version 2.5--%>
    <%--});--%>


  <%--};--%>

  <%--// Load the SDK asynchronously--%>
  <%--(function(d, s, id) {--%>
    <%--var js, fjs = d.getElementsByTagName(s)[0];--%>
    <%--if (d.getElementById(id)) return;--%>
    <%--js = d.createElement(s); js.id = id;--%>
    <%--js.src = "//connect.facebook.net/en_US/sdk.js";--%>
    <%--fjs.parentNode.insertBefore(js, fjs);--%>
  <%--}(document, 'script', 'facebook-jssdk'));--%>

  <%--function loginWithFacebook(){--%>
    <%--FB.login(function(response) {--%>
      <%--if (response.authResponse) {--%>
        <%--loginWithFaceBookAccessToken(response.authResponse.accessToken);--%>
      <%--} else {--%>
        <%--console.log('User cancelled login or did not fully authorize.');--%>
      <%--}--%>
    <%--},{scope: 'email,public_profile'});--%>
  <%--}--%>

  <%--function loginWithFaceBookAccessToken(accessToken){--%>
    <%--$("#fbLoginProgressImg").show();--%>

      <%--$.ajax({--%>
        <%--url: BASEURL+'/api/social-media/facebook/login/by-facebook-access-token',--%>
        <%--type: 'POST',--%>
        <%--data: {--%>
          <%--accessToken:accessToken--%>
        <%--},--%>
        <%--success: function(data){--%>
          <%--$("#fbLoginProgressImg").hide();--%>
          <%--if(data.responseStat.status == true){--%>
            <%--$("#alertMsg").html("Login success").fadeIn(500).delay(2000).fadeOut(500,function(){--%>
              <%--window.location.href =BASEURL+"/home";--%>
            <%--});--%>
          <%--}else{--%>
            <%--$("#alertMsg").html(data.responseStat.msg).fadeIn(500).delay(3000).fadeOut(500,function(){--%>
              <%--$("#signBtn").removeAttrs("disabled","disabled");--%>
            <%--});--%>
          <%--}--%>


        <%--}--%>
      <%--});--%>
  <%--}--%>

  <%--/*Google Login */--%>
  <%--var googleUser = {};--%>
  <%--var loginWithGoogle = function() {--%>
    <%--gapi.load('auth2', function(){--%>
      <%--// Retrieve the singleton for the GoogleAuth library and set up the client.--%>
      <%--auth2 = gapi.auth2.init({--%>
        <%--client_id: '109533534799-85f6m6k04935qsuc6on9ubqe7e8rtndj.apps.googleusercontent.com',--%>
        <%--cookiepolicy: 'single_host_origin',--%>
        <%--// Request scopes in addition to 'profile' and 'email'--%>
        <%--//scope: 'additional_scope'--%>
      <%--});--%>
      <%--attachSignin(document.getElementById('loginWithGooGleBtn'));--%>
    <%--});--%>
  <%--};--%>

  <%--function attachSignin(element) {--%>
    <%--auth2.attachClickHandler(element, {},--%>
            <%--function(googleUser) {--%>
              <%--try{--%>
                <%--loginWithGoogleAccessToken(googleUser.Zi.access_token);--%>
              <%--}catch(ex){--%>
                <%--console.log(ex);--%>
              <%--}--%>
            <%--}, function(error) {--%>
              <%--alert(JSON.stringify(error, undefined, 2));--%>
            <%--});--%>
  <%--}--%>
  <%--function loginWithGoogleAccessToken(accessToken){--%>
    <%--$("#googleLoginInProgressImg").show();--%>

    <%--$.ajax({--%>
      <%--url: BASEURL+'/api/social-media/google/login/by-google-access-token',--%>
      <%--type: 'POST',--%>
      <%--data: {--%>
        <%--accessToken:accessToken--%>
      <%--},--%>
      <%--success: function(data){--%>
        <%--$("#googleLoginInProgressImg").hide();--%>

        <%--if(data.responseStat.status == true){--%>
          <%--$("#alertMsg").html("Login success").fadeIn(500).delay(2000).fadeOut(500,function(){--%>
            <%--window.location.href =BASEURL+"/home";--%>
          <%--});--%>
        <%--}else{--%>
          <%--$("#alertMsg").html(data.responseStat.msg).fadeIn(500).delay(3000).fadeOut(500,function(){--%>
            <%--$("#signBtn").removeAttrs("disabled","disabled");--%>
          <%--});--%>
        <%--}--%>


      <%--}--%>
    <%--});--%>
  <%--}--%>

  <%--loginWithGoogle();--%>
  <%--$(document).ready(function(){--%>
    <%--$("#email").focus();--%>
  <%--});--%>
<%--</script>--%>






<script>

</script>
</body>
</html>
