<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 8/16/16
  Time: 2:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="controller.BaseHttp" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
  <script src="<c:url value="/resources/js/lightslider.js" />" > </script>
  <script>
    $(document).ready(function () {
      $("#content-slider").lightSlider({
        loop: true,
        keyPress: true
      });
      $('#image-gallery').lightSlider({
        gallery: true,
        item: 1,
        thumbItem: 9,
        slideMargin: 0,
        speed: 500,
        auto: true,
        loop: true,
        onSliderLoad: function () {
          $('#image-gallery').removeClass('cS-hidden');
        }
      });
    });
  </script>
  <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />

  <!-- CSS start here -->
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css" />"  media="screen">
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap-select.min.css"/>" />
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/styles.css" />"  />
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/font-awesome/css/font-awesome.min.css" />" >
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/animate.css" />"  />
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/owl.carousel.css" />"  />


  <!-- Include Date Range Picker -->
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/datepicker.css"/>" />

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
              <img src="<c:url value="/resources/img/logo.png" />">
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


<div class="parallax-window bg2" data-enllax-ratio="0.7">
  <div class="container title-block">
    <h1>Post your product</h1>
    <p>Post your product to get some good deal</p>
  </div>
</div>


<div class="container center-bg">
  <h5 class="post-heading">Please fill up the following form to add products.Give the exact current price of the product so the customers don't get confused</h5>
  <div class="row clearfix">
    <div class="post-product clearfix">
      <div class="col-md-6">
        <div class="form-group">
          <label>Choose Category</label>
          <%--<select class="selectpicker" onchange="subCategory()" id="category">--%>
          <select onchange="subCategory()" id="category">
            <option value="0">PLEASE SELECT A CATEGORY</option>
            <d:forEach var="listValue" items="${category}">
              <option value="${listValue.id}">${listValue.name}</option>
            </d:forEach>
          </select>
          <p class="help-block error-form" id="errorMsg_categoryIds"></p>
        </div>
        <div class="form-group">
          <label>Choose Sub Category</label>
          <%--<select class="selectpicker" id="subCategory">--%>
          <select id="subCategory">
            <option value="0">PLEASE SELECT A SUB CATEGORY</option>
          </select>
          <p class="help-block error-form" id="errorMsg_categoryIds"></p>
        </div>
        <div class="form-group">
          <label>Choose Rent Type</label>
          <select  id="rentTypeId">
            <option value="0">PLEASE SELECT A RENT TYPE</option>
            <d:forEach var="listValue" items="${rentTypes}">
              <option value="${listValue.id}">${listValue.name}</option>
            </d:forEach>
          </select>
          <p class="help-block error-form" id="errorMsg_rentTypeId"></p>
        </div>
        <div class="form-group">
          <label>Product Title</label>
          <input type="text" class="form-control" placeholder="" id="name" name="name">
          <p class="help-block error-form" id="errorMsg_name"></p>
        </div>
        <div class="form-group">
          <label>Product Description</label>
          <textarea class="form-control cstm-desc" id="description" name="description"></textarea>
          <p class="help-block error-form" id="errorMsg_description"></p>
        </div>
        <div class="row clearfix">
          <div class="col-md-6">
            <div class="form-group date-con">
              <label>From</label>
              <input type="text"  class="form-control datepicker" id="availableFrom" placeholder="" name="availableFrom">
              <p class="help-block error-form" id="errorMsg_availableFrom"></p>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group date-con">
              <label>To</label>
              <input type="text"  class="form-control datepicker" id="availableTill" placeholder="" name="availableTill">
              <p class="help-block error-form" id="errorMsg_availableTill"></p>
            </div>
          </div>
        </div>
        <div class="form-group">
          <label>Product Location</label>
          <input type="text" class="form-control" placeholder="" id="formattedAddress" name="formattedAddress">
          <p class="help-block error-form" id="errorMsg_formattedAddress"></p>
        </div>
        <div class="row clearfix">
          <div class="col-md-4">
            <div class="form-group">
              <label>Zip Code</label>
              <input type="text"  class="form-control" placeholder="" id="zip" name="zip">
              <p class="help-block error-form" id="errorMsg_zip"></p>
            </div>
          </div>
          <div class="col-md-8">
            <div class="form-group">
              <label>City</label>
              <input type="text"  class="form-control" placeholder="" id="city" name="city">
              <p class="help-block error-form" id="errorMsg_city"></p>
            </div>
          </div>
        </div>
      </div>
      <div class="col-md-6">
        <div class="form-group">
          <label >Product Current price</label>
          <input type="text" class="form-control" placeholder="" id="currentValue" name="currentValue">
          <p class="help-block error-form" id="errorMsg_currentValue"></p>
        </div>
        <div class="form-group">
          <label>Rent price</label>
          <input type="text" class="form-control" placeholder="" id="rentFee" name="rentFee">
          <p class="help-block error-form" id="errorMsg_rentFee"></p>
        </div>
        <div class="form-group">
          <label for="fallback">Add product images</label>
          <div id="fallback" class="fallback pos-relative">
            Drop files here or click to upload.
            <span class="inner-load fileUploadGif" hidden></span>
          </div>
          <p class="help-block error-form" id="errorMsg_profileImageToken"></p>
          <input type="hidden" value="" id="profileImageToken" name="profileImageToken">
        </div>
        <div class="row preview-container">
          <p>Preview Area</p>
          <div class="col-xs-6 col-md-6">
            <a href="#" class="thumbnail">
              <img src="http://placehold.it/200x200" alt="...">
            </a>
          </div>
          <div class="col-xs-6 col-md-6">
            <a href="#" class="thumbnail">
              <img src="http://placehold.it/200x200" alt="...">
            </a>
          </div>
        </div>

      </div>
    </div>
    <div class="col-md-12 text-right" style="padding:0px 100px 20px 0px;">
      <button class="btn-cstm-sign  pos-relative" id="postProduct" onclick="postProduct()">
        Post Product
        <span class="inner-load postProductGif" hidden></span>
      </button>
    </div>
  </div>
  <div class="alert alert-success text-center" role="alert" hidden>
    Product Upload Successfully
  </div>
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
<script src="<c:url value="/resources/js/dropzone.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"  />" ></script>
<script src="<c:url value="/resources/js/jquery.validate.min.js"  />" ></script>
<script src="<c:url value="/resources/js/modernizr.js"  />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/appear.js"  />" ></script>
<script src="<c:url value="/resources/js/jquery.knob.js"  />" ></script>
<script src="<c:url value="/resources/js/jquery.ccountdown.js"  />" ></script>
<script src="<c:url value="/resources/js/init.js"  />" ></script>
<script src="<c:url value="/resources/js/general.js"  />" ></script>
<!-- Theme JavaScript -->
<script src="<c:url value="/resources/js/clean-blog.min.js"  />" ></script>
<script src="<c:url value="/resources/js/owl.carousel.js"  />" ></script>
<script src="<c:url value="/resources/js/jquery.enllax.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap-select.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-datepicker.js"/>"></script>
<script src="<c:url value="/resources/developer/js/helper/ErrorMessaging.js" />" ></script>


<script>
  var nowTemp = new Date();
  var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);

  var checkin = $('#availableFrom').datepicker({
    format: 'dd/mm/yyyy',
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
    $('#availableTill')[0].focus();
  }).data('datepicker');
  var checkout = $('#availableTill').datepicker({
    format: 'dd/mm/yyyy',
    onRender: function (date) {
      return date.valueOf() <= checkin.date.valueOf() ? 'disabled' : '';
    }
  }).on('changeDate', function (ev) {
    checkout.hide();
  }).data('datepicker');
</script>

<!-- Javascript framework and plugins end here -->
<script type="text/javascript">
//  $("div#fallback").dropzone({url: "/file/post"});

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
<script type="text/javascript">
  window.onload = function () {
    $('.selectpicker').selectpicker();
    $('.rm-mustard').click(function () {
      $('.remove-example').find('[value=Mustard]').remove();
      $('.remove-example').selectpicker('refresh');
    });
    $('.rm-ketchup').click(function () {
      $('.remove-example').find('[value=Ketchup]').remove();
      $('.remove-example').selectpicker('refresh');
    });
    $('.rm-relish').click(function () {
      $('.remove-example').find('[value=Relish]').remove();
      $('.remove-example').selectpicker('refresh');
    });
    $('.ex-disable').click(function () {
      $('.disable-example').prop('disabled', true);
      $('.disable-example').selectpicker('refresh');
    });
    $('.ex-enable').click(function () {
      $('.disable-example').prop('disabled', false);
      $('.disable-example').selectpicker('refresh');
    });

    // scrollYou
    $('.scrollMe .dropdown-menu').scrollyou();

    prettyPrint();
  };
</script>
<script>
  function subCategory(){
    $('#subCategory').find('option:not(:first)').remove();
    var categoryId = $("#category option:selected").val();
    $.ajax({
      url: BASEURL+'/api/utility/get-subcategory/'+categoryId,
      type: 'GET',
      success:function(data){
        if(data.responseStat.status != false){
          data.responseData[0].subcategory.forEach(function(subCategories){
          var subCategory = document.getElementById("subCategory");
          var option = document.createElement("option");
          option.text = subCategories.name;
          option.value = subCategories.id;
          subCategory.add(option, subCategory[1]);
          });
        }
      }
    });
  }
</script>
<script>
  Dropzone.autoDiscover = false;
  $(function() {
    var productImageFile = $("div#fallback").dropzone(
      {
        url: BASEURL+"/fileupload/upload/product-image",
        paramName: "productImage",
        maxFilesize: 1,
        uploadprogress:function(file, progress){
          $('#postProduct').addAttribute("disabled", "disabled");
          $('.postProductGif').show();
          $('.fileUploadGif').show();
        },
        success:function(file, response){
          console.log(response);
          if(response.responseStat.status == true) {
            $('.fileUploadGif').hide();
            $('#postProduct').removeAttrs("disabled","disabled");
            $('.postProductGif').hide();
            $('#profileImageToken').val(response.responseData);
          }
          else{
            BindErrorsWithHtml('errorMsg_', response.requestErrors);
          }
        },
        error:function(file, errorMessage, xhr){
          $('.fileUploadGif').hide();
          $('#postProduct').removeAttrs("disabled","disabled");
          $('.postProductGif').hide();
        }
      }
    );
  });
</script>
<script>
  function postProduct(){
    $('.postProductGif').show();
    var categoryId = $('#category option:selected').val();
    var subCategory = $('#subCategory option:selected').val();

    if(subCategory != null){
      var categoryIds = JSON.stringify([subCategory]);
    }else{
      var categoryIds = JSON.stringify([categoryId]);
    }

    var fromDate = $('#availableFrom').val();
    var tillDate = $('#availableTill').val();

    var name = $('#name').val();
    var description = $('#description').val();
    var profileImageToken = $('#profileImageToken').val();
    var currentValue = $('#currentValue').val();
    var rentFee = $('#rentFee').val();
    var availableFrom = fromDate.replace(/\//g,"-");
    var availableTill = tillDate.replace(/\//g,"-");
    var formattedAddress = $('#formattedAddress').val();
    var rentTypeId = $('#rentTypeId option:selected').val();
    var zip = $('#zip').val();
    var city = $('#city').val();

//    console.log(categoryIds, name, description, profileImageToken, currentValue, rentFee, availableFrom, availableTill, formattedAddress, rentTypeId, zip, city)
//    console.log(availableFrom, availableTill);

    $.ajax({
      url: BASEURL+'/api/product/upload',
      type: 'POST',
      data: {
        name: name,
        description:description,
        profileImageToken:profileImageToken,
        currentValue:currentValue,
        rentFee:rentFee,
        availableFrom:availableFrom,
        availableTill:availableTill,
        categoryIds:categoryIds,
        formattedAddress:formattedAddress,
        rentTypeId:rentTypeId,
        zip:zip,
        city:city
      },
      success: function(data){
        console.log(data);
        if(data.responseStat.status == false){
          BindErrorsWithHtml("errorMsg_", data.responseStat.requestErrors);
        }else{
          $('.alert-success').show().delay(5000).fadeOut(500,function(){
            window.location.href = BASEURL+"/home";
          });
        }
        $('.postProductGif').hide().delay(4998).fadeOut();
      }
    });
  }
</script>

</body>
</html>


