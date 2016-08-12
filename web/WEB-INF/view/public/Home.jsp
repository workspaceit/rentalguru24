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
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/styles.css" />"  />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/font-awesome/css/font-awesome.min.css" />" >
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/animate.css" />"  />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/owl.carousel.css" />"  />
        <link rel="stylesheet"  href="<c:url value="/resources/css/flexslider.css" />" />
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
                                <d:if test="${IsLogIn != true}">
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
                                </d:if>
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
                                <d:forEach var="listValue" items="${category}">
                                    <%--<li><a href="#">Action</a></li>--%>
                                    <li><a href="#">${listValue.name}</a></li>
                                </d:forEach>
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

        <!--body content-->
        <div class="home_slider">
            <div class="container">
                <div class="row">
                    <div class="col-md-4 col-sm-4 col-xs-12">
                        <div id="hot-deals" class="carousel slide left_home_car" data-ride="carousel">
                            <div class="left_slide_head">
                                HOT DEALS
                                <!-- Indicators -->
                                <ol class="carousel-indicators custom_car_ind">
                                    <li data-target="#hot-deals" data-slide-to="0" class="active"></li>
                                    <li data-target="#hot-deals" data-slide-to="1"></li>                       
                                </ol>
                            </div>


                            <!-- Wrapper for slides -->
                            <div class="carousel-inner" role="listbox">
                                <div class="item active">
                                    <img src="<c:url value="/resources/img/4.jpg" />" alt="...">
                                    <div class="carousel-caption cap1">
                                        <p><i class="fa fa-shopping-basket"></i> ADD TO CART</p>
                                    </div>
                                    <div class="custom_cap cap2">
                                        <ul class="cap_ul">
                                            <li><span class="amount">25</span><span class="term">DAYS</span></li>
                                            <li><span class="amount">25</span><span class="term">DAYS</span></li>
                                            <li><span class="amount">25</span><span class="term">DAYS</span></li>
                                            <li><span class="amount">25</span><span class="term">DAYS</span></li>
                                        </ul>
                                    </div> 
                                    <div class="custom_cap cap3">
                                        <label class="product_name">Samsung A5 2016</label>
                                        <fieldset class="rating">
                                            <input type="radio" id="star5" name="rating" value="5" /><label class = "full" for="star5" title="Awesome - 5 stars"></label>
                                            <input type="radio" id="star4" name="rating" value="4" /><label class = "full" for="star4" title="Pretty good - 4 stars"></label>
                                            <input type="radio" id="star3" name="rating" value="3" /><label class = "full" for="star3" title="Meh - 3 stars"></label>
                                            <input type="radio" id="star2" name="rating" value="2" /><label class = "full" for="star2" title="Kinda bad - 2 stars"></label>
                                            <input type="radio" id="star1" name="rating" value="1" /><label class = "full" for="star1" title="Sucks big time - 1 star"></label>
                                        </fieldset>
                                        <div class="divider"></div>
                                        <div class="row option_but">
                                            <div class="col-md-5 col-sm-5 col-xs-12">
                                                <div class="option">
                                                    <a href=""><i class="fa fa-eye"></i></a>
                                                    <a href=""><i class="fa fa-heart"></i></a>
                                                    <a href=""><i class="fa  fa-exchange"></i></a>
                                                </div>
                                            </div>
                                            <div class="col-md-7 col-sm-7 col-xs-12">
                                                <label class="prev_price">$350</label>
                                                <label class="present_price">$250</label>
                                            </div>
                                        </div>
                                    </div>                          
                                </div>  



                                <div class="item">
                                    <img src="<c:url value="/resources/img/5.jpg" />" alt="...">
                                    <div class="carousel-caption cap1">
                                        <p><i class="fa fa-shopping-basket"></i> ADD TO CART</p>
                                    </div>
                                    <div class="custom_cap cap2">
                                        <ul class="cap_ul">
                                            <li><span class="amount">25</span><span class="term">DAYS</span></li>
                                            <li><span class="amount">25</span><span class="term">DAYS</span></li>
                                            <li><span class="amount">25</span><span class="term">DAYS</span></li>
                                            <li><span class="amount">25</span><span class="term">DAYS</span></li>
                                        </ul>
                                    </div> 
                                    <div class="custom_cap cap3">
                                        <label class="product_name">Samsung A5 2016</label>
                                        <fieldset class="rating">
                                            <input type="radio" id="star5" name="rating" value="5" /><label class = "full" for="star5" title="Awesome - 5 stars"></label>
                                            <input type="radio" id="star4" name="rating" value="4" /><label class = "full" for="star4" title="Pretty good - 4 stars"></label>
                                            <input type="radio" id="star3" name="rating" value="3" /><label class = "full" for="star3" title="Meh - 3 stars"></label>
                                            <input type="radio" id="star2" name="rating" value="2" /><label class = "full" for="star2" title="Kinda bad - 2 stars"></label>
                                            <input type="radio" id="star1" name="rating" value="1" /><label class = "full" for="star1" title="Sucks big time - 1 star"></label>
                                        </fieldset>
                                        <div class="divider"></div>
                                        <div class="row option_but">
                                            <div class="col-md-5 col-sm-5 col-xs-12">
                                                <div class="option">
                                                    <a href=""><i class="fa fa-eye"></i></a>
                                                    <a href=""><i class="fa fa-heart"></i></a>
                                                    <a href=""><i class="fa  fa-exchange"></i></a>
                                                </div>
                                            </div>
                                            <div class="col-md-7 col-sm-7 col-xs-12">
                                                <label class="prev_price">$350</label>
                                                <label class="present_price">$250</label>
                                            </div>
                                        </div>
                                    </div>                          
                                </div>                       

                            </div>


                        </div>
                    </div>
                    <div class="col-md-8 col-sm-8 col-xs-12">
                        <div id="main-product" class="carousel slide main_product_slider" data-ride="carousel">

                            <!-- Wrapper for slides -->
                            <div class="carousel-inner clearfix" role="listbox">
                                <div class="item active">
                                    <img src="<c:url value="/resources/img/4.jpg" />" alt="...">
                                </div>
                                <div class="item">
                                    <img src="<c:url value="/resources/img/5.jpg" />" alt="...">
                                </div>
                            </div>

                            <!-- Controls -->
                            <a class="left carousel-control" href="#main-product" role="button" data-slide="prev">
                                <span class="" aria-hidden="true"><i class="fa fa-chevron-left"></i></span>
                                <span class="sr-only">Previous</span>
                            </a>
                            <a class="right carousel-control" href="#main-product" role="button" data-slide="next">
                                <span class="" aria-hidden="true"><i class="fa fa-chevron-right"></i></span>
                                <span class="sr-only">Next</span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="speciality">
            <div class="container">
                <div class="row">
                    <div class="col-md-3 col-sm-3 col-xs-6">
                        <div class="spec_container">
                            <label class="spec_head">FREE SHIPPING</label>
                            <label class="spec_body">ALL ORDER</label>
                            <div class="spec_icon">
                                <i class="fa fa-truck"></i>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-3 col-sm-3 col-xs-6">
                        <div class="spec_container">
                            <label class="spec_head">24/7 CUSTOMER</label>
                            <label class="spec_body">SUPPORT</label>
                            <div class="spec_icon">
                                <i class="fa fa-headphones"></i>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-3 col-sm-3 col-xs-6">
                        <div class="spec_container">
                            <label class="spec_head">MONEY BACK</label>
                            <label class="spec_body">GUARANTEE</label>
                            <div class="spec_icon">
                                <i class="fa fa-mail-reply-all "></i>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-3 col-sm-3 col-xs-6">
                        <div class="spec_container">
                            <label class="spec_head">MEMBER DISCOUNT</label>
                            <label class="spec_body">FIRST ORDER</label>
                            <div class="spec_icon">
                                <i class="fa fa-bullhorn"></i>
                            </div>
                        </div>
                    </div>



                </div>
            </div>
        </div>

        <div class="offer">
            <div class="container">
                <div class="row">
                    <div class="col-sm-6 col-xs-12 col-md-6">
                        <div class="big_ass_offer">

                            <img src="<c:url value="/resources/img/1.jpg" />" >
                            <div class="offer_cap_big ">
                                <h2 class="type">GUEST ROOM</h2>
                                <h1 class="name">SOFA</h1>
                                <p class="discount no-margin">-20%</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-xs-12 col-md-6">
                        <div class="small_ass_offer small_1">
                            <img src="<c:url value="/resources/img/2.jpg" />"  >
                            <div class="offer_cap_small_1 ">
                                <div class="small_1_text">
                                    <h3 class="s_type "><strong>OFFICE</strong> CHAIR</h3>
                                    <h4 class="s_name ">COLLECTION</h4>
                                </div>
                                <p class="s_price no-margin">$200.00</p>
                            </div>
                        </div>
                        <div class="small_ass_offer">
                            <img src="<c:url value="/resources/img/3.jpg" />">
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="container product_carousel">
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
                            <d:forEach var="product" items="${products}">
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
        <br>

        <div class="gallery">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 col-sm-12 mcol-xs-12">
                        <p class="gallery_head">GALLERY</p>
                        <div class="home_left_tab">
                            <!-- Nav tabs -->
                            <ul class="nav nav-tabs gallery_nav" role="tablist">
                                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">FEATURED</a></li>
                                <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">TOP SELLER</a></li>
                                <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">SELL OFF</a></li>
                                <li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">TOP RATED</a></li>
                            </ul>

                            <!-- Tab panes -->
                            <div class="tab-content">
                                <div role="tabpanel" class="tab-pane fade in active" id="home">

                                    <!-- Place somewhere in the <body> of your page -->
                                    <div id="slider" class="flexslider">
                                        <ul class="slides">
                                            <li>
                                                <img src="http://placehold.it/700x400" >
                                            </li>
                                            <li>
                                                <img src="http://placehold.it/700x400"   >
                                            </li>
                                            <li>
                                                <img src="http://placehold.it/700x400"  >
                                            </li>
                                            <li>
                                                <img src="http://placehold.it/700x400"   >
                                            </li>
                                            <!-- items mirrored twice, total of 12 -->
                                        </ul>
                                    </div>
                                    <div id="carousel" class="flexslider">
                                        <ul class="slides">
                                            <li>
                                                <img src="http://placehold.it/700x400"  >
                                            </li>
                                            <li>
                                                <img src="http://placehold.it/700x400"  >
                                            </li>
                                            <li>
                                                <img src="http://placehold.it/700x400"  >
                                            </li>
                                            <li>
                                                <img src="http://placehold.it/700x400"  >
                                            </li>
                                            <!-- items mirrored twice, total of 12 -->
                                        </ul>
                                    </div>
                                </div>
                                <div role="tabpanel" class="tab-pane fade " id="profile">dfgdfg</div>
                                <div role="tabpanel" class="tab-pane fade " id="messages">dfgdfg</div>
                                <div role="tabpanel" class="tab-pane fade " id="settings">dfgdfgd</div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-sm-12 mcol-xs-12">
                        <div class="best_offer">
                            <img src="<c:url value="/resources/img/15.jpg" />">
                            <div class="best_offer_text">
                                <div class="offer_rate">
                                    <p class="offer_head"><span class="pre_rate">FROM</span><span class="rate">$250</span></p>
                                </div>
                                <div class="offer_des">
                                    <p class="name">Bedroom Bed</p>
                                    <a href="" class="btn shop_btn"> SHOP NOW</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="container blog_container">
            <div id="carousel-example-generic" class="carousel slide carousel-cstm" data-ride="carousel" data-interval='false'>
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                </ol>
                <h2 class="block-head">Latest Blog</h2>
                <div class="carousel-inner" role="listbox">
                    <div class="item active">
                        <div class="row clearfix">
                            <div class="col-md-4 blog-single">
                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <div class="blog-img">
                                            <img src="<c:url value="/resources/img/18.jpg" />" />
                                        </div>
                                        <div class="blog-desc">
                                            <p class="date-comment">
                                                <span><i class="fa fa-calendar"></i> 15 JAN 2016</span>
                                                <span><i class="fa fa-comments"></i>4 Comments</span>
                                            </p>
                                            <h4 class="blog-title">Products That Fight Static</h4>
                                            <p>
                                                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vulputate luctus enim sit amet dapibus. Praesent sagittis molestie felis, lobortis dictum massa molestie ac.
                                            </p>
                                            <div class="text-center">
                                                <button class="btn-read">Read More</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4 blog-single">
                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <div class="blog-img">
                                            <img src="<c:url value="/resources/img/17.jpg"  />" />
                                        </div>
                                        <div class="blog-desc">
                                            <p class="date-comment">
                                                <span><i class="fa fa-calendar"></i> 15 JAN 2016</span>
                                                <span><i class="fa fa-comments"></i>4 Comments</span>
                                            </p>
                                            <h4 class="blog-title">Products That Fight Static</h4>
                                            <p>
                                                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vulputate luctus enim sit amet dapibus. Praesent sagittis molestie felis, lobortis dictum massa molestie ac.
                                            </p>
                                            <div class="text-center">
                                                <button class="btn-read">Read More</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4 blog-single">
                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <div class="blog-img">
                                            <img src="<c:url value="/resources/img/16.jpg"   />" />
                                        </div>
                                        <div class="blog-desc">
                                            <p class="date-comment">
                                                <span><i class="fa fa-calendar"></i> 15 JAN 2016</span>
                                                <span><i class="fa fa-comments"></i> 4 Comments</span>
                                            </p>
                                            <h4 class="blog-title">Products That Fight Static</h4>
                                            <p>
                                                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vulputate luctus enim sit amet dapibus. Praesent sagittis molestie felis, lobortis dictum massa molestie ac.
                                            </p>
                                            <div class="text-center">
                                                <button class="btn-read">Read More</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="sponser">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12 sponser_slider">
                        <div class="owl-carousel">
                            <div class="item"><img src="<c:url value="/resources/img/19.jpg" />"></div>
                            <div class="item"><img src="<c:url value="/resources/img/19.jpg" />"></div>
                            <div class="item"><img src="<c:url value="/resources/img/19.jpg" />"></div>
                            <div class="item"><img src="<c:url value="/resources/img/19.jpg" />"></div>
                            <div class="item"><img src="<c:url value="/resources/img/19.jpg" />"></div>
                            <div class="item"><img src="<c:url value="/resources/img/19.jpg" />"></div>
                            <div class="item"><img src="<c:url value="/resources/img/19.jpg" />"></div>
                            <div class="item"><img src="<c:url value="/resources/img/19.jpg" />"></div>
                            <div class="item"><img src="<c:url value="/resources/img/19.jpg" />"></div>
                            <div class="item"><img src="<c:url value="/resources/img/19.jpg" />"></div>
                            <div class="item"><img src="<c:url value="/resources/img/19.jpg" />"></div>

                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="testimonial">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12 sponser_slider">
                        <div id="carousel-example-generic" class="carousel slide carousel-cstm" data-ride="carousel" data-interval='false'>
                            <!-- Indicators -->
                            <ol class="carousel-indicators">
                                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                            </ol>
                            <h2 class="block-head">CLIENT FEEDBACK</h2>
                            <div class="carousel-inner" role="listbox">
                                <div class="item active">
                                    <p class="feedback_icon"><i class="fa fa-quote-right fa-2x"></i></p>
                                    <p class="feedback_text">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer at magna elementum, consectetur purus a, euismod odio. Duis mattis libero metus, id eleifend nisl gravida non. Pellentesque felis nibh, fringilla a nibh et, pulvinar blandit nibh. </p>

                                    <div class="client_details">
                                        <div class="client_img">
                                            <img src="<c:url value="/resources/img/20.jpg" />" >
                                        </div>
                                        <div class="client_info">
                                            <p class="client_name no-margin"><strong>John Doe</strong></p>
                                            <p class="client_type no-margin">Furniture Client</p>
                                        </div>
                                    </div>

                                </div>                                
                            </div>
                        </div>
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

        <script>
            var BASEURL = "${BaseUrl}";
        </script>

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


        <!-- Javascript framework and plugins end here -->
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
            $(document).ready(function () {
                $('#hot-deals, #main-product').carousel({
                    interval: 3000
                });

            });
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


