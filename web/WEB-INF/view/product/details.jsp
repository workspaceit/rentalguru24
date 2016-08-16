<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 8/16/16
  Time: 4:53 PM
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
  <!--<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
  <script src="js/lightslider.js"></script> -->
  <script>
    //  $(document).ready(function() {
    //     $("#content-slider").lightSlider({
    //         loop:true,
    //         keyPress:true
    //     });
    //     $('#image-gallery').lightSlider({
    //         gallery:true,
    //         item:1,
    //         thumbItem:9,
    //         slideMargin: 0,
    //         speed:500,
    //         auto:true,
    //         loop:true,
    //         onSliderLoad: function() {
    //             $('#image-gallery').removeClass('cS-hidden');
    //         }
    //     });
    // });
  </script>
  <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />

  <!-- CSS start here -->
  <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/bootstrap.min.css" />" />
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/styles.css" />"  />
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/font-awesome/css/font-awesome.min.css" />" >
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/animate.css" />"  />
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/owl.carousel.css" />"  />
  <link rel="stylesheet"  href="<c:url value="/resources/css/flexslider.css" />" />
  <link type="text/css" href="<c:url value="/resources/css/jquery-ui-1.10.0.custom.css" />" rel="stylesheet" />
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/cloudzoom.css" />" />
  <!-- Theme CSS -->
  <!-- <link href="css/clean-blog.css" rel="stylesheet"> -->

  <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,900" rel="stylesheet">
  <!-- Google fonts end here -->
</head>
<body class="ux">
<!--top Nav Bar-->
<div class="container-fluid top_nav">
  <div class="row">
    <div class="container">
      <div class="row">
        <div class="col-md-6 col-sm-6 col-xs-6">
          <ul class="top_nav_ul">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle top_nav_a" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">English <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="#">Action</a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle top_nav_a" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Help <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="#">Action</a></li>
              </ul>
            </li>
          </ul>
        </div>

        <div class="col-md-6 col-sm-6 col-xs-6 ">
          <ul class="top_nav_ul right top_nav_ul">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle top_nav_a" data-toggle="dropdown" role="button" aria-haspopup="true"
                 aria-expanded="false">
                <i class="fa fa-user"></i>Login
              </a>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle top_nav_a" data-toggle="dropdown" role="button" aria-haspopup="true"
                 aria-expanded="false">
                <i class="fa fa-lock"></i>Register
              </a>
            </li>
            <li class="dropdown no-margin">
              <a href="#" class="dropdown-toggle top_nav_a" data-toggle="dropdown" role="button" aria-haspopup="true"
                 aria-expanded="false">
                <i class="fa fa-bars"></i>
              </a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</div>

<!--mid navbar-->
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

<!--main navbar-->
<nav class="main_nav navbar navbar-default">
  <div class="container">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>

    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse no-padding " id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="dropdown catagory_drop">
          <a href="#" class="dropdown-toggle catagory_drop_a" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="fa fa-bars"></i>Select a category <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Separated link</a></li>
          </ul>
        </li>
        <form class="navbar-form navbar-left no-padding main_search_form" role="search">

          <input class="form-control" placeholder="Search" name="srch-term" id="srch-term" type="text">
          <div class="input-group-btn search_button">
            <button class="btn btn-default search_btn" type="submit" style="padding:6px;"><i class="fa fa-search"></i></button>
          </div>
        </form>

      </ul>
      <ul class="nav navbar-nav navbar-right main_navigation">
        <li><a href="#">HOME</a></li>
        <li><a href="#">HOME APPLIANCE</a></li>
        <li><a href="#">FURNITURE</a></li>
        <li><a href="#">GAMING & PARTY</a></li>
        <li><a href="#">COOL GADGETS</a></li>
        <li><a href="#">BLOG</a></li>

      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>

<!--end main Nav-->

<!--maincontainer-->

<div class="contentwrap">
  <div class="parallax-window rent_bg1" data-enllax-ratio="0.7">
    <div class="container ">
      <div class="row">
        <div class="col-xs-12 col-md-6 col-sm-6">
          <h3><strong> FURNITURE</strong></h3>
        </div>
        <div class="col-xs-12 col-md-6 col-sm-6">
          <div class="breadcrumb rent_page_bread">
            <ol class="breadcrumb">
              <li><a href="#">Home</a></li>
              <li><a href="#">Library</a></li>
              <li class="active">Data</li>
            </ol>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="container">
    <div class="row product_div">
      <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="row">
          <div class="col-md-6 col-sm-6 col-xs-12">
            <div>
              <div id="slider" class="flexslider">
                <ul class="slides">
                  <li>
                    <img class="cloudzoom" data-cloudzoom = "zoomImage: '${BaseUrl}/images/${rentedProduct.profileImage.original.path}'"  src="<c:url value="${BaseUrl}/images/${rentedProduct.profileImage.original.path}" />">
                  </li>
                  <li>
                    <img class="cloudzoom" data-cloudzoom = "zoomImage: '${BaseUrl}/images/${rentedProduct.profileImage.original.path}'"  src="<c:url value="${BaseUrl}/images/${rentedProduct.profileImage.original.path}" />">
                  </li>
                  <li>
                    <img class="cloudzoom" data-cloudzoom = "zoomImage: '${BaseUrl}/images/${rentedProduct.profileImage.original.path}'"  src="<c:url value="${BaseUrl}/images/${rentedProduct.profileImage.original.path}" />">
                  </li>
                  <li>
                    <img class="cloudzoom" data-cloudzoom = "zoomImage: '${BaseUrl}/images/${rentedProduct.profileImage.original.path}'"  src="<c:url value="${BaseUrl}/images/${rentedProduct.profileImage.original.path}" />">
                  </li>
                  <!-- items mirrored twice, total of 12 -->
                </ul>
              </div>
              <div id="carousel" class="flexslider">
                <ul class="slides">
                  <li>
                    <img class="thumb_flex" src="<c:url value="${BaseUrl}/images/${rentedProduct.profileImage.original.path}" />">
                  </li>
                  <li>
                    <img class="thumb_flex" src="<c:url value="${BaseUrl}/images/${rentedProduct.profileImage.original.path}" />">
                  </li>
                  <li>
                    <img class="thumb_flex" src="<c:url value="${BaseUrl}/images/${rentedProduct.profileImage.original.path}" />">
                  </li>
                  <li>
                    <img class="thumb_flex" src="<c:url value="${BaseUrl}/images/${rentedProduct.profileImage.original.path}" />">
                  </li>
                  <!-- items mirrored twice, total of 12 -->
                </ul>
              </div>
            </div>
          </div>
          <div class="col-md-6 col-sm-6 col-xs-12 ">
            <div class="rent_product_info">
              <div class="row">
                <div class="col-md-8 col-xs-8 col-sm-8">
                  <h4 class="no-margin"><strong>${rentedProduct.getName()}</strong></h4>
                  <div class="row">
                    <div class="col-md-4 col-sm-4 col-xs-12">
                      <fieldset class="rating rent_rating">
                        <input type="radio" id="star5" name="rating" value="5" /><label class = "full" for="star5" title="Awesome - 5 stars"></label>
                        <input type="radio" id="star4" name="rating" value="4" /><label class = "full" for="star4" title="Pretty good - 4 stars"></label>
                        <input type="radio" id="star3" name="rating" value="3" /><label class = "full" for="star3" title="Meh - 3 stars"></label>
                        <input type="radio" id="star2" name="rating" value="2" /><label class = "full" for="star2" title="Kinda bad - 2 stars"></label>
                        <input type="radio" id="star1" name="rating" value="1" /><label class = "full" for="star1" title="Sucks big time - 1 star"></label>
                      </fieldset>
                    </div>
                    <div class="col-md-3 col-sm-3 col-xs-12">
                      <p class="total_review">(${rentedProduct.getAverageRating()} Review)</p>
                    </div>
                    <div class="col-md-5 col-sm-5 col-xs-12">
                      <a href=""><p class="total_review">Add Your Review</p></a>
                    </div>
                  </div>
                </div>
                <div class="col-md-4 col-xs-4 col-sm-4 text-right right">
                  <button class="prev_next_rent_btn prev"><i class="fa fa-chevron-left"></i></button>
                  <button class="prev_next_rent_btn next"><i class="fa fa-chevron-right"></i></button>
                </div>
              </div>
            </div>
            <div class="rent_product_price">
              <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <%--<p class="price"><span class="prev_price">$350</span><span class="cur_price">$250</span></p>--%>
                  <p class="price"><span class="cur_price">$${rentedProduct.getRentFee()}</span></p>
                </div>
              </div>
            </div>
            <div class="buying_option">
              <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <a class="rent_now_btn" href=""><i class="fa fa-shopping-basket"></i>Rent Now</a>
                  <a class="ehe_btn" href=""><i class="fa fa-eye"></i></a>
                  <a class="ehe_btn" href=""><i class="fa fa-heart"></i></a>
                  <a class="ehe_btn" href=""><i class="fa  fa-exchange"></i></a>
                </div>
              </div>
              <div class="row">
                <div class="col-md-3 col-sm-3 col-xs-3">
                  <p class="value_input_label">Quantity</p>
                </div>
                <div class="col-md-4 col-sm-4 col-xs-6 input-group spinner">
                  <input type="text" class="form-control value_input" value="42">
                  <div class="input-group-btn-vertical">
                    <button class="btn btn-default value_controller" type="button"><i class="fa fa-plus"></i></button>
                    <button class="btn btn-default value_controller" type="button"><i class="fa fa-minus"></i></button>
                  </div>
                </div>
              </div>
            </div>
            <div class="product_overview">
              <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <h5><strong>Short Overview</strong></h5>
                  <p class="overview_content no-margin">${rentedProduct.getDescription()}</p>
                </div>
              </div>
            </div>
            <div class="product_stock">
              <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <d:if test="${rentedProduct.isCurrentlyAvailable() == true}">
                    <p class="no-margin"><strong>Avaialability:</strong><span class="avail_span">Available</span></p>
                  </d:if>
                  <d:if test="${rentedProduct.isCurrentlyAvailable() == false}">
                    <p class="no-margin"><strong>Avaialability:</strong><span class="avail_span">Unavailable</span></p>
                  </d:if>
                  <p class="no-margin"><strong>Category:</strong><span class="avail_span"></span></p>

                </div>
              </div>
            </div>
            <div class="scl_share">
              <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <ul class="scl_share_ul">
                    <li class="facebook"><a href=""><i class="fa fa-facebook"></i><span class="scl_share_label">Share</span></a></li>
                    <li class="google"><a href=""><i class="fa fa-google-plus google"></i><span class="scl_share_label">Google+</span></a></li>
                    <li class="twitter"><a href=""><i class="fa fa-twitter twitter"></i><span class="scl_share_label">Twitter</span></a></li>
                    <li class="pinterest"><a href=""><i class="fa fa-pinterest pinterest"></i><span class="scl_share_label">Pinterest</span></a></li>
                    <li class="linkedin"><a href=""><i class="fa fa-linkedin linkedin"></i><span class="scl_share_label">Linkedin</span></a></li>
                  </ul>

                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="row review_tabs">

      <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="row">
          <!-- Nav tabs -->
          <div class="col-md-6 col-sm-12 col-xs-12 no-padding">
            <ul class="nav nav-tabs" role="tablist">
              <li role="presentation"><a href="#description" aria-controls="description" role="tab" data-toggle="tab">Description</a></li>
              <li role="presentation" class="active"><a href="#reviews" aria-controls="reviews" role="tab" data-toggle="tab">Reviews(0)</a></li>
              <li role="presentation"><a href="#specification" aria-controls="specification" role="tab" data-toggle="tab">Specification</a></li>
              <li role="presentation"><a href="#custom" aria-controls="custom" role="tab" data-toggle="tab">Custom tab</a></li>
            </ul>
          </div>
        </div>

        <!-- Tab panes -->
        <div class="tab-content">
          <div role="tabpanel" class="tab-pane " id="description">1</div>
          <div role="tabpanel" class="tab-pane active" id="reviews">
            <p class="no-review">There are no reviews for this product</p>
            <p  class="no-review"><strong>Add Review</strong></p>
            <p  class="no-review">Your Rating</p>
            <form class="review-form" action="" role="form">
              <div class="row">
                <div class="col-md-1 col-sm-4 col-xs-4">
                  <p  class="review_cond">Bad</p>
                </div>
                <div class="col-md-2 col-sm-4 col-xs-4">
                  <fieldset class="rating rent_rating">
                    <input type="radio" id="star5" name="rating" value="5" /><label class = "full" for="star5" title="Awesome - 5 stars"></label>
                    <input type="radio" id="star4" name="rating" value="4" /><label class = "full" for="star4" title="Pretty good - 4 stars"></label>
                    <input type="radio" id="star3" name="rating" value="3" /><label class = "full" for="star3" title="Meh - 3 stars"></label>
                    <input type="radio" id="star2" name="rating" value="2" /><label class = "full" for="star2" title="Kinda bad - 2 stars"></label>
                    <input type="radio" id="star1" name="rating" value="1" /><label class = "full" for="star1" title="Sucks big time - 1 star"></label>
                  </fieldset>
                </div>
                <div class="col-md-1 col-sm-4 col-xs-4">
                  <p  class="review_cond">Good</p>
                </div>
              </div>

              <div class="form-group">
                <label for="email">Your Review:</label>
                <textarea type="text" class="form-control review_form_element" id="review" ></textarea>
              </div>
              <div class="row">
                <div class="col-md-5 col-sm-5 col-xs-12">
                  <div class="form-group">
                    <input type="text" class="form-control review_form_element" placeholeder="Your Name" id="mail">
                  </div>
                </div>
                <div class="col-md-6 col-sm-6 col-xs-12">
                  <div class="form-group">
                    <input type="email" class="form-control review_form_element" placeholeder="Email" id="name">
                  </div>
                </div>
                <div class="col-md-1 col-sm-1 col-xs-12 center">
                  <button type="submit" class="review_submit">Continue</button>
                </div>
              </div>
            </form>



          </div>
          <div role="tabpanel" class="tab-pane" id="specification">3</div>
          <div role="tabpanel" class="tab-pane" id="custom">4</div>
        </div>
      </div>
    </div>
    <div class="row related_rent_slider">
      <div id="carousel-example-generic" class="carousel slide carousel-cstm" data-ride="carousel" data-interval='false'>
        <!-- Indicators -->
        <ol class="carousel-indicators">
          <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
          <li data-target="#carousel-example-generic" data-slide-to="1"></li>
          <li data-target="#carousel-example-generic" data-slide-to="2"></li>
        </ol>
        <h2 class="block-head">New Product</h2>
        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">
          <div class="item active">
            <div class="row clearfix">
              <d:forEach var="product" items="${newProducts}">
                <div class="col-md-3 single-item">
                  <div class="panel panel-default">
                    <div class="panel-body">
                      <div class="img-single">
                        <img src="<c:url value="${BaseUrl}/images/${product.profileImage.original.path}" />" />
                        <div class="product-btn-grp">
                          <a href="#" class="gbtn left">Quick view</a>
                          <a href="#" class="gbtn right">Rent Now</a>
                        </div>
                      </div>
                      <div class="block-desc">
                        <label class="title-label productName">${fn:substring(product.name, 0, 20)}<d:if test="${fn:length(product.name)>20}">....</d:if></label>
                        <br>
                        <fieldset class="rating">
                          <input type="radio" id="star5" name="rating" value="5" /><label class = "full" for="star5" title="Awesome - 5 stars"></label>
                          <input type="radio" id="star4" name="rating" value="4" /><label class = "full" for="star4" title="Pretty good - 4 stars"></label>
                          <input type="radio" id="star3" name="rating" value="3" /><label class = "full" for="star3" title="Meh - 3 stars"></label>
                          <input type="radio" id="star2" name="rating" value="2" /><label class = "full" for="star2" title="Kinda bad - 2 stars"></label>
                          <input type="radio" id="star1" name="rating" value="1" /><label class = "full" for="star1" title="Sucks big time - 1 star"></label>
                        </fieldset>

                      </div>
                      <div class="block-action">
                        <button class="btn-fav pull-left"><i class="fa fa-heart-o"></i></button>
                        <button class="btn-compare pull-left"><i class="fa  fa-exchange"></i></button>
                        <div class="price-tag">$${product.rentFee}</div>
                      </div>
                    </div>
                  </div>
                </div>
              </d:forEach>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="newsletter">
  <div class="container">
    <div class="row">
      <div class="col-md-6 col-sm-6 col-xs-12 text-right">
        <h3 class="newsletter_label"><strong>NEWSLETTER</strong></h3>
      </div>
      <div class="col-md-6 col-sm-6 col-xs-12 left">
        <form class="form-inline" role="form">
          <div class="form-group newsletter_div">
            <input type="email" class="form-control newsletter_input" id="email" placeholder='&#xf075;       |            Enter Your Email Address'>
          </div>

          <a type="submit" class="newsletter_submit"><i class="fa fa-arrow-right"></i></a>
        </form>
      </div>
    </div>
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




<!-- Contact end here -->
<!-- Main container start here -->
<!-- Javascript framework and plugins start here -->
<script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"  />" ></script>
<script src="<c:url value="/resources/js/jquery.validate.min.js"  />" ></script>
<script src="<c:url value="/resources/js/modernizr.js"  />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/appear.js"  />" ></script>
<script src="<c:url value="/resources/js/jquery.knob.js"  />" ></script>
<script src="<c:url value="/resources/js/jquery.ccountdown.js"  />" ></script>
<script src="<c:url value="/resources/js/init.js"  />" ></script>
<script src="<c:url value="/resources/js/general.js"  />" ></script>
<script src="<c:url value="/resources/js/jquery.flexslider.js"  />" ></script>
<!-- Theme JavaScript -->
<script src="<c:url value="/resources/js/clean-blog.min.js"  />" ></script>
<script src="<c:url value="/resources/js/owl.carousel.js"  />" ></script>
<script src="<c:url value="/resources/js/jquery.enllax.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery-ui-1.9.2.custom.min.js" />" type="text/javascript"></script>
<script type="text/javascript" src="<c:url value="/resources/js/cloudzoom.js" />"></script>


<!-- Javascript framework and plugins end here -->
<script type="text/javascript">
  CloudZoom.quickStart();
</script>
<script type="text/javascript">
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
    $('.spinner .btn:first-of-type').on('click', function() {
      $('.spinner input').val( parseInt($('.spinner input').val(), 10) + 1);
    });
    $('.spinner .btn:last-of-type').on('click', function() {
      $('.spinner input').val( parseInt($('.spinner input').val(), 10) - 1);
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
</body>
</html>
