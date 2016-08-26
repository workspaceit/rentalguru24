<!DOCTYPE html>
<html lang="en">
<jsp:directive.include file="../layouts/header.jsp" />
<body class="ux">
        <!--top Nav Bar-->
        <jsp:directive.include file="../layouts/top-nav.jsp" />
        <!--mid navbar-->
        <jsp:directive.include file="../layouts/mid-nav.jsp" />
        <!--main navbar-->
        <jsp:directive.include file="../layouts/main-nav.jsp" />
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
                                        <p><i class="fa fa-shopping-basket"></i> RENT NOW</p>
                                    </div>
                                    <div class="custom_cap cap2">
                                        <ul class="cap_ul">
                                            <li><span class="amount">7</span><span class="term">DAYS</span></li>
                                            <li><span class="amount">54</span><span class="term">WEEK</span></li>
                                            <li><span class="amount">250</span><span class="term">MONTH</span></li>
                                            <li><span class="amount">5000</span><span class="term">YEAR</span></li>
                                        </ul>
                                    </div> 
                                    <div class="custom_cap cap3">
                                        <label class="product_name">Casual couch</label>
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
                                        <p><i class="fa fa-shopping-basket"></i>RENT NOW</p>
                                    </div>
                                    <div class="custom_cap cap2">
                                        <ul class="cap_ul">
                                            <li><span class="amount">5</span><span class="term">DAYS</span></li>
                                            <li><span class="amount">40</span><span class="term">WEEK</span></li>
                                            <li><span class="amount">150</span><span class="term">MONTH</span></li>
                                            <li><span class="amount">500</span><span class="term">YEAR</span></li>
                                        </ul>
                                    </div> 
                                    <div class="custom_cap cap3">
                                        <label class="product_name">Casual couch</label>
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
                            <img src="<c:url value="${BaseUrl}/images/${rentalProductsRandom2.getProfileImage().getOriginal().getPath()}" />">
                            <div class="offer_cap_big ">
                                <h2 class="type"></h2>
                                <h1 class="name"><a href="${BaseUrl}/product/details/${rentalProductsRandom2.getId()}">${fn:substring(rentalProductsRandom2.name, 0, 20)}<d:if test="${fn:length(rentalProductsRandom2.name)>20}">....</d:if></a></h1>
                                <p class="discount no-margin">$${rentalProductsRandom2.rentFee}</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-xs-12 col-md-6">
                        <div class="small_ass_offer small_1">
                            <img src="<c:url value="${BaseUrl}/images/${rentalProductsRandom3.getProfileImage().getOriginal().getPath()}" />">
                            <div class="offer_cap_small_1 ">
                                <div class="small_1_text">
                                    <%--<h3 class="s_type "><strong>${rentalProductsRandom3.getProductCategories().getCategory().getName()}</strong></h3>--%>
                                </div>
                                <p class="s_price no-margin">$${rentalProductsRandom3.rentFee}</p>
                            </div>
                        </div>
                        <div class="small_ass_offer">
                            <img src="<c:url value="${BaseUrl}/images/${rentalProductsRandom4.getProfileImage().getOriginal().getPath()}" />">
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
                                            <a href="${BaseUrl}/product/details/${product.getId()}"><img src="<c:url value="${BaseUrl}/images/${product.profileImage.original.path}" />" /></a>
                                            <div class="product-btn-grp">
                                                <a href="" class="gbtn left">Quick view</a>
                                                <%--data-toggle="modal" data-target="#rentPopUp"--%>
                                                <a href="javascript:void(0)" onclick="showRentRequestPopUp(${product.getId()})" class="gbtn right">Rent Now</a>
                                            </div>
                                        </div>
                                        <div class="block-desc">
                                            <label class="title-label productName"><a href="${BaseUrl}/product/details/${product.getId()}">${fn:substring(product.name, 0, 20)}<d:if test="${fn:length(product.name)>20}">....</d:if></a></label>
                                            <br>
                                            <fieldset class="rating ">
                                                <input <d:if test="${fn:substringBefore(product.averageRating,'.') == '5'}">checked</d:if> type="radio" id="star5_${product.getId()}" name="rating${product.getId()}" value="5" />
                                                <label class = "full" for="star5_${product.getId()}" title="Awesome - 5 stars"></label>

                                                <input <d:if test="${fn:substringBefore(product.averageRating,'.') == '4'}">checked</d:if> type="radio" id="star4_${product.getId()}" name="rating${product.getId()}" value="4" />
                                                <label class = "full" for="star4_${product.getId()}" title="Pretty good - 4 stars"></label>

                                                <input <d:if test="${fn:substringBefore(product.averageRating,'.') == '3'}">checked</d:if> type="radio" id="star3_${product.getId()}" name="rating${product.getId()}" value="3" />
                                                <label  class = "full" for="star3_${product.getId()}" title="Meh - 3 stars"></label>

                                                <input <d:if test="${fn:substringBefore(product.averageRating,'.') == '2'}">checked</d:if> type="radio" id="star2_${product.getId()}" name="rating${product.getId()}" value="2" />
                                                <label class = "full" for="star2_${product.getId()}" title="Kinda bad - 2 stars"></label>

                                                <input <d:if test="${fn:substringBefore(product.averageRating,'.') == '1'}">checked</d:if> type="radio" id="star1_${product.getId()}" name="rating${product.getId()}" value="1" />
                                                <label class = "full" for="star1_${product.getId()}" title="Sucks big time - 1 star"></label>
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
                                <%--<li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">TOP SELLER</a></li>--%>
                                <%--<li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">SELL OFF</a></li>--%>
                                <%--<li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">TOP RATED</a></li>--%>
                            </ul>
                            <!-- Tab panes -->
                            <div class="tab-content">
                                <div role="tabpanel" class="tab-pane fade in active" id="home">
                                    <!-- Place somewhere in the <body> of your page -->
                                    <div id="slider" class="flexslider">
                                        <ul class="slides">
                                            <d:forEach var="ascendingProduct" items="${productsAscending}">
                                                <li>
                                                    <img src="<c:url value="${BaseUrl}/images/${ascendingProduct.getProfileImage().getOriginal().getPath()}" />" >
                                                </li>
                                            </d:forEach>
                                            <!-- items mirrored twice, total of 12 -->
                                        </ul>
                                    </div>
                                    <div id="carousel" class="flexslider">
                                        <ul class="slides">
                                            <d:forEach var="ascendingProduct" items="${productsAscending}">
                                                <li>
                                                    <img src="<c:url value="${BaseUrl}/images/${ascendingProduct.getProfileImage().getOriginal().getPath()}" />" >
                                                </li>
                                            </d:forEach>
                                            <!-- items mirrored twice, total of 12 -->
                                        </ul>
                                    </div>
                                </div>
                                <%--<div role="tabpanel" class="tab-pane fade " id="profile">dfgdfg</div>--%>
                                <%--<div role="tabpanel" class="tab-pane fade " id="messages">dfgdfg</div>--%>
                                <%--<div role="tabpanel" class="tab-pane fade " id="settings">dfgdfgd</div>--%>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-sm-12 mcol-xs-12">
                        <div class="best_offer">
                            <img src="<c:url value="${BaseUrl}/images/${rentalProductsRandom1.getProfileImage().getOriginal().getPath()}" />">
                            <div class="best_offer_text">
                                <div class="offer_rate">
                                    <p class="offer_head"><span class="pre_rate">FROM</span><span class="rate">$${rentalProductsRandom1.rentFee}</span></p>
                                </div>
                                <div class="offer_des">
                                    <p class="name"><a href="${BaseUrl}/product/details/${rentalProductsRandom1.getId()}">${fn:substring(rentalProductsRandom1.name, 0, 20)}<d:if test="${fn:length(rentalProductsRandom1.name)>20}">....</d:if></a></p>
                                    <a href="javascript:void(0)" onclick="showRentRequestPopUp(${product.getId()})" class="btn shop_btn">RENT NOW</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%--<div class="container blog_container">--%>
            <%--<div id="carousel-example-generic" class="carousel slide carousel-cstm" data-ride="carousel" data-interval='false'>--%>
                <%--<!-- Indicators -->--%>
                <%--<ol class="carousel-indicators">--%>
                    <%--<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>--%>
                    <%--<li data-target="#carousel-example-generic" data-slide-to="1"></li>--%>
                    <%--<li data-target="#carousel-example-generic" data-slide-to="2"></li>--%>
                <%--</ol>--%>
                <%--<h2 class="block-head">Latest Blog</h2>--%>
                <%--<div class="carousel-inner" role="listbox">--%>
                    <%--<div class="item active">--%>
                        <%--<div class="row clearfix">--%>
                            <%--<div class="col-md-4 blog-single">--%>
                                <%--<div class="panel panel-default">--%>
                                    <%--<div class="panel-body">--%>
                                        <%--<div class="blog-img">--%>
                                            <%--<img src="<c:url value="/resources/img/18.jpg" />" />--%>
                                        <%--</div>--%>
                                        <%--<div class="blog-desc">--%>
                                            <%--<p class="date-comment">--%>
                                                <%--<span><i class="fa fa-calendar"></i> 15 JAN 2016</span>--%>
                                                <%--<span><i class="fa fa-comments"></i>4 Comments</span>--%>
                                            <%--</p>--%>
                                            <%--<h4 class="blog-title">Products That Fight Static</h4>--%>
                                            <%--<p>--%>
                                                <%--Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vulputate luctus enim sit amet dapibus. Praesent sagittis molestie felis, lobortis dictum massa molestie ac.--%>
                                            <%--</p>--%>
                                            <%--<div class="text-center">--%>
                                                <%--<button class="btn-read">Read More</button>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="col-md-4 blog-single">--%>
                                <%--<div class="panel panel-default">--%>
                                    <%--<div class="panel-body">--%>
                                        <%--<div class="blog-img">--%>
                                            <%--<img src="<c:url value="/resources/img/17.jpg"  />" />--%>
                                        <%--</div>--%>
                                        <%--<div class="blog-desc">--%>
                                            <%--<p class="date-comment">--%>
                                                <%--<span><i class="fa fa-calendar"></i> 15 JAN 2016</span>--%>
                                                <%--<span><i class="fa fa-comments"></i>4 Comments</span>--%>
                                            <%--</p>--%>
                                            <%--<h4 class="blog-title">Products That Fight Static</h4>--%>
                                            <%--<p>--%>
                                                <%--Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vulputate luctus enim sit amet dapibus. Praesent sagittis molestie felis, lobortis dictum massa molestie ac.--%>
                                            <%--</p>--%>
                                            <%--<div class="text-center">--%>
                                                <%--<button class="btn-read">Read More</button>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="col-md-4 blog-single">--%>
                                <%--<div class="panel panel-default">--%>
                                    <%--<div class="panel-body">--%>
                                        <%--<div class="blog-img">--%>
                                            <%--<img src="<c:url value="/resources/img/16.jpg"   />" />--%>
                                        <%--</div>--%>
                                        <%--<div class="blog-desc">--%>
                                            <%--<p class="date-comment">--%>
                                                <%--<span><i class="fa fa-calendar"></i> 15 JAN 2016</span>--%>
                                                <%--<span><i class="fa fa-comments"></i> 4 Comments</span>--%>
                                            <%--</p>--%>
                                            <%--<h4 class="blog-title">Products That Fight Static</h4>--%>
                                            <%--<p>--%>
                                                <%--Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vulputate luctus enim sit amet dapibus. Praesent sagittis molestie felis, lobortis dictum massa molestie ac.--%>
                                            <%--</p>--%>
                                            <%--<div class="text-center">--%>
                                                <%--<button class="btn-read">Read More</button>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="sponser">--%>
            <%--<div class="container">--%>
                <%--<div class="row">--%>
                    <%--<div class="col-md-12 col-sm-12 col-xs-12 sponser_slider">--%>
                        <%--<div class="owl-carousel">--%>
                            <%--<div class="item"><img src="<c:url value="/resources/img/19.jpg" />"></div>--%>
                            <%--<div class="item"><img src="<c:url value="/resources/img/19.jpg" />"></div>--%>
                            <%--<div class="item"><img src="<c:url value="/resources/img/19.jpg" />"></div>--%>
                            <%--<div class="item"><img src="<c:url value="/resources/img/19.jpg" />"></div>--%>
                            <%--<div class="item"><img src="<c:url value="/resources/img/19.jpg" />"></div>--%>
                            <%--<div class="item"><img src="<c:url value="/resources/img/19.jpg" />"></div>--%>
                            <%--<div class="item"><img src="<c:url value="/resources/img/19.jpg" />"></div>--%>
                            <%--<div class="item"><img src="<c:url value="/resources/img/19.jpg" />"></div>--%>
                            <%--<div class="item"><img src="<c:url value="/resources/img/19.jpg" />"></div>--%>
                            <%--<div class="item"><img src="<c:url value="/resources/img/19.jpg" />"></div>--%>
                            <%--<div class="item"><img src="<c:url value="/resources/img/19.jpg" />"></div>--%>

                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="testimonial">--%>
            <%--<div class="container">--%>
                <%--<div class="row">--%>
                    <%--<div class="col-md-12 col-sm-12 col-xs-12 sponser_slider">--%>
                        <%--<div id="carousel-example-generic" class="carousel slide carousel-cstm" data-ride="carousel" data-interval='false'>--%>
                            <%--<!-- Indicators -->--%>
                            <%--<ol class="carousel-indicators">--%>
                                <%--<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>--%>
                                <%--<li data-target="#carousel-example-generic" data-slide-to="1"></li>--%>
                                <%--<li data-target="#carousel-example-generic" data-slide-to="2"></li>--%>
                            <%--</ol>--%>
                            <%--<h2 class="block-head">CLIENT FEEDBACK</h2>--%>
                            <%--<div class="carousel-inner" role="listbox">--%>
                                <%--<div class="item active">--%>
                                    <%--<p class="feedback_icon"><i class="fa fa-quote-right fa-2x"></i></p>--%>
                                    <%--<p class="feedback_text">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer at magna elementum, consectetur purus a, euismod odio. Duis mattis libero metus, id eleifend nisl gravida non. Pellentesque felis nibh, fringilla a nibh et, pulvinar blandit nibh. </p>--%>

                                    <%--<div class="client_details">--%>
                                        <%--<div class="client_img">--%>
                                            <%--<img src="<c:url value="/resources/img/20.jpg" />" >--%>
                                        <%--</div>--%>
                                        <%--<div class="client_info">--%>
                                            <%--<p class="client_name no-margin"><strong>John Doe</strong></p>--%>
                                            <%--<p class="client_type no-margin">Furniture Client</p>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>                                --%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
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


<!--Rent form Modal -->
<div id="requestRentPopUp" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title center">Rent Form</h4>
            </div>
            <form class="rent_submission_form" action="" method="post" onsubmit="return makeRentRequest()">
                <div class="modal-body">
                    <div class="row clearfix">
                        <div class="col-md-6">
                            <div class="form-group date-con">
                                <label>From</label>
                                <input type="text" class="form-control datepicker" id="rentRequestFrom" placeholder="">
                                <p class="help-block error-form" id="errorMsg_startDate" ></p>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group date-con">
                                <label>To</label>
                                <input type="text" class="form-control datepicker" id="rentRequestTill" placeholder="">
                                <p class="help-block error-form" id="errorMsg_endsDate" ></p>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group ">
                                <label>Remarks</label>
                                <textarea id="rentRequestRemarks" type="text" class="form-control "  placeholder="" style="resize: none;"></textarea>
                                <p class="help-block error-form" id="errorMsg_remark" ></p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <p class="help-block error-form" id="errorMsg_productId" ></p>
                    <p class="help-block error-form" id="serviceResponseMsg" ></p>
                    <button type="submit" class="btn-submit">Submit</button>
                </div>
            </form>
        </div>
    </div>
</div>


<%--Hidden Values for Front Developer --%>
<input type="hidden" value="" id="currentProductId" />
<script>
    var isLoggedin =${IsLogIn};
</script>

<jsp:directive.include file="../layouts/footer.jsp" />
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-datepicker.js" />" ></script>
<script>
    var nowTemp = new Date();
    var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);

    var checkin = $('#rentRequestFrom').datepicker({
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
        $('#rentRequestTill')[0].focus();
    }).data('datepicker');

    var checkout = $('#rentRequestTill').datepicker({
        onRender: function (date) {
            return date.valueOf() <= checkin.date.valueOf() ? 'disabled' : '';
        }
    }).on('changeDate', function (ev) {
        checkout.hide();
    }).data('datepicker');


    function showRentRequestPopUp(productId){
        if(isLoggedin){
            setCurrentSelectedRentalProductId(productId);
            $("#rentRequestFrom").val("");
            $("#rentRequestTill").val("");
            $("#rentRequestRemarks").val("");
            UnBindErrors('errorMsg_');
            $("#requestRentPopUp").modal("show");
        }else{
            showInfoAndHide("Please sign to rent product");
        }
    }
    function makeRentRequest(){
        UnBindErrors('errorMsg_');
        $("#serviceResponseMsg").html("");

        var productId = getCurrentSelectedRentalProductId();

        var startDate = $("#rentRequestFrom").val();
        var endsDate = $("#rentRequestTill").val();
        var remark = $("#rentRequestRemarks").val();

        startDate = getDateInFormat(startDate);
        endsDate = getDateInFormat(endsDate);

        if(productId==null || productId==""){
            return false;
        }

        $.ajax({
            url: BASEURL+'/api/auth/rent/make-request/'+productId,
            type: 'POST',
            data: {
                   startDate:startDate,
                   endsDate:endsDate,
                   remark:remark
            },
            success: function(data){
                if(data.responseStat.status == true){
                    /* Hide Rent Request Modal Form */
                    $("#requestRentPopUp").modal("hide");
                    showSuccessAndHide(data.responseStat.msg);
                }else{
                    $("#serviceResponseMsg").html(data.responseStat.msg);
                    BindErrorsWithHtml('errorMsg_', data.responseStat.requestErrors);
                }
            }
        });
        return false;
//
    }
    function getDateInFormat(datesArray,format){

        if(typeof datesArray == "string"){
            try{
                datesArray = (datesArray!=null || datesArray=="")?datesArray.split("/"):[];
            }catch(ex){
                console.log(ex);
                return "";
            }
        }

        if(format == undefined){
            format = "";
        }
        if(datesArray.length!=3){
            return "";
        }

        var day = datesArray[1];
        var month = datesArray[0];
        var year = datesArray[2];

        return day+"-"+month+"-"+year;
    }
    function getCurrentSelectedRentalProductId(){
        try{
            return parseInt($("#currentProductId").val());
        }catch(ex){
            console.log(ex);
            return 0;
        }

    }
    function setCurrentSelectedRentalProductId(productId){
        $("#currentProductId").val(productId);
    }

</script>
</body>
</html>
