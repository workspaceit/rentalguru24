<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Reneguru</title>
  <!-- Favicons -->
  <!--    <link rel="shortcut icon" href="favicon.ico">-->

  <!-- Mobile -->
  <link rel="stylesheet"  href="<c:url value="/resources/css/lightslider.css" />" />
  <style>
    ul{
      list-style: none outside none;
      padding-left: 0;
      margin: 0;
    }
    .demo .item{
      margin-bottom: 60px;
    }
    .content-slider li{
      background-color: #ed3020;
      text-align: center;
      color: #FFF;
    }
    .content-slider h3 {
      margin: 0;
      padding: 70px 0;
    }
    .demo{
      width: 800px;
    }
  </style>
  <script src="<c:url value="/resources/js/jquery1.9.1.min.js"  />" ></script>
  <script src="<c:url value="/resources/js/lightslider.js" />" ></script>
  <script>
    $(document).ready(function() {
      $("#content-slider").lightSlider({
        loop:true,
        keyPress:true
      });
      $('#image-gallery').lightSlider({
        gallery:true,
        item:1,
        thumbItem:9,
        slideMargin: 0,
        speed:500,
        auto:true,
        loop:true,
        onSliderLoad: function() {
          $('#image-gallery').removeClass('cS-hidden');
        }
      });
    });
  </script>
  <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />

  <!-- CSS start here -->
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css" />" media="screen">
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/styles.css" />" />
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/font-awesome/css/font-awesome.min.css"  />" >
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/animate.css"  />" />
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/owl.carousel.css"  />" />
  <!-- Theme CSS -->
  <!-- <link href="css/clean-blog.css" rel="stylesheet"> -->

  <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,900" rel="stylesheet">
  <!-- Google fonts end here -->
</head>
<body class="ux">
<!--top Nav Bar-->
<div class="container-fluid mid_nav">
  <div class="row">
    <div class="container">
      <div class="row">
        <div class="col-md-6 col-sm-6 col-xs-6">
          <ul class="mid_nav_logo_ul">
            <li class=>
              <img src="<c:url value="/resources/img/logo.png" />" >
            </li>
          </ul>
        </div>

        <div class="col-md-6 col-sm-6 col-xs-6 ">
          <label class="right mid_nav_contact"><i class="fa fa-mobile fa-2x contact_icon"></i><span class="conatct_number">2300-3560-222</span></label>
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
  <form class="form-signup clearfix" onsubmit="return submitSignInData();">
    <div class="col-md-12">
      <div class="form-group">
        <label for="email">Email</label>
        <input type="email" class="form-control" placeholder="ex.email@email.com" id="email" name="email">
      </div>
      <div class="form-group">
        <label for="password">Password</label>
        <input type="password" class="form-control" placeholder="ex.password" id="password" name="password">
      </div>
      <div class="checkbox">
        <label><input type="checkbox"> Remember me</label>
      </div>

    </div>

    <div class="col-md-12 text-center">
      <button class="btn-cstm-sign pos-relative" id="signBtn">Sign in
        <span id="signInProgressImg" class="inner-load signUpGif" hidden></span>
      </button>
      <div id="alertMsg" class="alert alert-success text-center" role="alert" hidden>

      </div>
    </div>
  </form>
</div>



<div class="footer">
  <div class="container">
    <div class="row">
      <div class="col-md-3 col-sm-6 col-xs-12">
        <p class="footer_head">INFORMATION</p>
        <ul class="footer_ul">
          <li>About US</li>
          <li>Privacy</li>
          <li>Conditions</li>
          <li>Online Support</li>
        </ul>
      </div>
      <div class="col-md-3 col-sm-6 col-xs-12">
        <p class="footer_head">MY ACCOUNT</p>
        <ul class="footer_ul">
          <li>Login</li>
          <li>My Cart</li>
          <li>Wishlist</li>
          <li>Checkout</li>
        </ul>
      </div>
      <div class="col-md-3 col-sm-6 col-xs-12">
        <p class="footer_head">INFORMATION</p>
        <ul class="footer_ul">
          <li>Specials</li>
          <li>New Products</li>
          <li>Best Sellers</li>
          <li>Our Stored</li>
        </ul>
      </div>
      <div class="col-md-3 col-sm-6 col-xs-12">
        <p class="footer_head">ORDERS</p>
        <ul class="footer_ul">
          <li>Payment Option</li>
          <li>Shipping Delivery</li>
          <li>Returns</li>
          <li>Shipping</li>
        </ul>
      </div>
    </div>
  </div>
</div>

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
            window.location.href =BASEURL+"/home";
          });
        }else{
          $("#alertMsg").html(data.responseStat.msg).fadeIn(500).delay(3000).fadeOut(500,function(){
            $("#signBtn").removeAttrs("disabled","disabled");
          });
        }
        $("#signInProgressImg").hide();

      }
    });
    return false;
  }
</script>

</body>
</html>
