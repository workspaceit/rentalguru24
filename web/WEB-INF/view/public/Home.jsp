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
                                ${topRentalProductHeadTitle}
                                <!-- Indicators -->
                                <ol class="carousel-indicators custom_car_ind">
                                    <li data-target="#hot-deals" data-slide-to="0" class="active"></li>
                                    <li data-target="#hot-deals" data-slide-to="1"></li>                       
                                </ol>
                            </div>
                            <!-- Wrapper for slides -->
                            <div class="carousel-inner home_left_carousel" role="listbox">
                                <d:set var="topProductActive" value="active" />
                                <d:forEach var="topProduct" items="${rentalProductsTop}">
                                <div class="item ${topProductActive}">
                                    <d:set var="topProductActive" value="" />
                                    <a href="${BaseUrl}/product/details/${topProduct.getId()}"><img src="<c:url value="${BaseUrl}/images/${topProduct.profileImage.original.path}" />" /></a>
                                    <div class="carousel-caption cap1">
                                        <p onclick="showRentRequestPopUp(${topProduct.getId()})"><i class="fa fa-shopping-basket"></i> Rent Now </p>
                                    </div>
                                    <div class="custom_cap cap3">
                                        <label class="title-label productName"><a href="${BaseUrl}/product/details/${topProduct.getId()}">${fn:substring(topProduct.name, 0, 20)}<d:if test="${fn:length(topProduct.name)>20}">....</d:if></a></label>
                                        <d:if test="${topProduct.averageRating != 0}">
                                        <fieldset class="rating ">
                                            <input <d:if test="${fn:substringBefore(topProduct.averageRating,'.') == '5'}">checked</d:if> type="radio" id="star5__rated${topProduct.getId()}" name="rating_rated${topProduct.getId()}" value="5" />
                                            <label class = "full" for="star5_rated${topProduct.getId()}" title="Awesome - 5 stars"></label>

                                            <input <d:if test="${fn:substringBefore(topProduct.averageRating,'.') == '4'}">checked</d:if> type="radio" id="star4__rated${topProduct.getId()}" name="rating_rated${topProduct.getId()}" value="4" />
                                            <label class = "full" for="star4__rated${topProduct.getId()}" title="Pretty good - 4 stars"></label>

                                            <input <d:if test="${fn:substringBefore(topProduct.averageRating,'.') == '3'}">checked</d:if> type="radio" id="star3__rated${topProduct.getId()}" name="rating_rated${topProduct.getId()}" value="3" />
                                            <label  class = "full" for="star3__rated${topProduct.getId()}" title="Meh - 3 stars"></label>

                                            <input <d:if test="${fn:substringBefore(topProduct.averageRating,'.') == '2'}">checked</d:if> type="radio" id="star2__rated${topProduct.getId()}" name="rating_rated${topProduct.getId()}" value="2" />
                                            <label class = "full" for="star2__rated${topProduct.getId()}" title="Kinda bad - 2 stars"></label>

                                            <input <d:if test="${fn:substringBefore(topProduct.averageRating,'.') == '1'}">checked</d:if> type="radio" id="star1__rated${topProduct.getId()}" name="rating_rated${topProduct.getId()}" value="1" />
                                            <label class = "full" for="star1__rated${topProduct.getId()}" title="Sucks big time - 1 star"></label>
                                        </fieldset>
                                        </d:if>
                                        <d:if test="${topProduct.averageRating == 0}">
                                            <label class="no-rating-label">Not rated yet</label>
                                        </d:if>
                                        <div class="divider"></div>
                                        <div class="row option_but">
                                            <div class="col-md-5 col-sm-5 col-xs-12">
                                                <%--<div class="option">--%>
                                                    <%--<a href=""><i class="fa fa-heart"></i></a>--%>
                                                    <%--<a href=""><i class="fa  fa-exchange"></i></a>--%>
                                                <%--</div>--%>
                                            </div>
                                            <div class="col-md-7 col-sm-7 col-xs-12">
                                                <%--<label class="prev_price">$350</label>--%>
                                                <div class="price-tag">$ ${topProduct.rentFee}/${topProduct.rentType.name}</div>
                                            </div>
                                        </div>
                                    </div>                          
                                </div>
                                </d:forEach>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-8 col-sm-8 col-xs-12">
                        <div id="main-product" class="carousel slide main_product_slider" data-ride="carousel">
                            <!-- Wrapper for slides -->
                            <div  class="carousel-inner clearfix" role="listbox">
                                <d:set var="topProductImageActive" value="active" />
                                <d:forEach var="topProduct" items="${rentalProductsTop}">
                                    <div class="item ${topProductImageActive}">
                                        <d:set var="topProductImageActive" value="" />
                                        <a href="${BaseUrl}/product/details/${topProduct.getId()}"><img src="<c:url value="${BaseUrl}/images/${topProduct.profileImage.original.path}" />" /></a>
                                    </div>
                                </d:forEach>
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
        <%--<div class="speciality">--%>
            <%--<div class="container">--%>
                <%--<div class="row">--%>
                    <%--<div class="col-md-3 col-sm-3 col-xs-6">--%>
                        <%--<div class="spec_container">--%>
                            <%--<label class="spec_head">FREE SHIPPING</label>--%>
                            <%--<label class="spec_body">ALL ORDER</label>--%>
                            <%--<div class="spec_icon">--%>
                                <%--<i class="fa fa-truck"></i>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="col-md-3 col-sm-3 col-xs-6">--%>
                        <%--<div class="spec_container">--%>
                            <%--<label class="spec_head">24/7 CUSTOMER</label>--%>
                            <%--<label class="spec_body">SUPPORT</label>--%>
                            <%--<div class="spec_icon">--%>
                                <%--<i class="fa fa-headphones"></i>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="col-md-3 col-sm-3 col-xs-6">--%>
                        <%--<div class="spec_container">--%>
                            <%--<label class="spec_head">MONEY BACK</label>--%>
                            <%--<label class="spec_body">GUARANTEE</label>--%>
                            <%--<div class="spec_icon">--%>
                                <%--<i class="fa fa-mail-reply-all "></i>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="col-md-3 col-sm-3 col-xs-6">--%>
                        <%--<div class="spec_container">--%>
                            <%--<label class="spec_head">MEMBER DISCOUNT</label>--%>
                            <%--<label class="spec_body">FIRST ORDER</label>--%>
                            <%--<div class="spec_icon">--%>
                                <%--<i class="fa fa-bullhorn"></i>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="offer">--%>
            <%--<div class="container">--%>
                <%--<div class="row">--%>
                    <%--<div class="col-sm-6 col-xs-12 col-md-6">--%>
                        <%--<div class="big_ass_offer">--%>
                            <%--<img src="<c:url value="${BaseUrl}/images/${rentalProductsRandom2.getProfileImage().getOriginal().getPath()}" />">--%>
                            <%--<div class="offer_cap_big ">--%>
                                <%--<h2 class="type"></h2>--%>
                                <%--<h1 class="name"><a href="${BaseUrl}/product/details/${rentalProductsRandom2.getId()}">${fn:substring(rentalProductsRandom2.name, 0, 20)}<d:if test="${fn:length(rentalProductsRandom2.name)>20}">....</d:if></a></h1>--%>
                                <%--<p class="discount no-margin">$${rentalProductsRandom2.rentFee}</p>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="col-sm-6 col-xs-12 col-md-6">--%>
                        <%--<div class="small_ass_offer small_1">--%>
                            <%--<img src="<c:url value="${BaseUrl}/images/${rentalProductsRandom3.getProfileImage().getOriginal().getPath()}" />">--%>
                            <%--<div class="offer_cap_small_1 ">--%>
                                <%--<div class="small_1_text">--%>
                                    <%--&lt;%&ndash;<h3 class="s_type "><strong>${rentalProductsRandom3.getProductCategories().getCategory().getName()}</strong></h3>&ndash;%&gt;--%>
                                <%--</div>--%>
                                <%--<p class="s_price no-margin">$${rentalProductsRandom3.rentFee}</p>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="small_ass_offer">--%>
                            <%--<img src="<c:url value="${BaseUrl}/images/${rentalProductsRandom4.getProfileImage().getOriginal().getPath()}" />">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <div class="container product_carousel" id="newProductPartialRender">
            <div id="carousel-example-generic" class="carousel slide carousel-cstm" data-ride="carousel" data-interval='false'>
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                </ol>
                <h2 class="block-head">${productListTitle}</h2>
                <!-- Wrapper for slides -->
                <div class="carousel-inner" role="listbox">
                    <div class="item active">
                        <div id="productListDiv"  class="row clearfix">
                            <d:forEach var="product" items="${products}">
                            <div class="col-md-3 col-sm-6 col-xs-12 single-item">
                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <div class="img-single product_home_img_wrap">
                                            <a href="${BaseUrl}/product/details/${product.getId()}"><img src="<c:url value="${BaseUrl}/images/${product.profileImage.original.path}" />" /></a>
                                            <div class="product-btn-grp">
                                                <a href="${BaseUrl}/product/details/${product.getId()}" class="gbtn left">View details</a>
                                                <%--data-toggle="modal" data-target="#rentPopUp"--%>
                                                <a href="javascript:void(0)" onclick="showRentRequestPopUp(${product.getId()})" class="gbtn right">Rent Now</a>
                                            </div>
                                        </div>
                                        <div class="block-desc">
                                            <label class="title-label productName"><a href="${BaseUrl}/product/details/${product.getId()}">${fn:substring(product.name, 0, 20)}<d:if test="${fn:length(product.name)>20}">....</d:if></a></label>
                                            <br>
                                            <d:if test="${product.averageRating != 0}">
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
                                            </d:if>
                                            <d:if test="${product.averageRating == 0}">
                                                <label class="no-rating-label">Not rated yet</label>
                                            </d:if>
                                        </div>
                                        <div class="block-action">
                                            <%--<button class="btn-fav pull-left"><i class="fa fa-heart-o"></i></button>--%>
                                            <%--<button class="btn-compare pull-left"><i class="fa  fa-exchange"></i></button>--%>
                                            <div class="price-tag">$${product.rentFee}/${product.rentType.name}</div>
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
        <div class="col-md-12 text-center">
            <button class="btn-cstm-sign pos-relative" id="loadMoreButton" onclick="loadMoreProduct()" >Load More
                <span id="loadMoreButtonLoader" class="inner-load " hidden="hidden"></span>
            </button>
        </div>

        <br>
        <%--<div class="img-single">--%>
            <%--<div class="container">--%>
                <%--<div class="row">--%>
                    <%--<div class="col-md-6 col-sm-12 mcol-xs-12">--%>
                        <%--<p class="gallery_head">GALLERY</p>--%>
                        <%--<div class="home_left_tab">--%>
                            <%--<!-- Nav tabs -->--%>
                            <%--<ul class="nav nav-tabs gallery_nav" role="tablist">--%>
                                <%--<li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">FEATURED</a></li>--%>
                                <%--&lt;%&ndash;<li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">TOP SELLER</a></li>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">SELL OFF</a></li>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">TOP RATED</a></li>&ndash;%&gt;--%>
                            <%--</ul>--%>
                            <%--<!-- Tab panes -->--%>
                            <%--<div class="tab-content">--%>
                                <%--<div role="tabpanel" class="tab-pane fade in active" id="home">--%>
                                    <%--<!-- Place somewhere in the <body> of your page -->--%>
                                    <%--<div id="slider" class="flexslider">--%>
                                        <%--<ul class="slides">--%>
                                            <%--<d:forEach var="ascendingProduct" items="${productsAscending}">--%>
                                                <%--<li>--%>
                                                    <%--<img src="<c:url value="${BaseUrl}/images/${ascendingProduct.getProfileImage().getOriginal().getPath()}" />" >--%>
                                                <%--</li>--%>
                                            <%--</d:forEach>--%>
                                            <%--<!-- items mirrored twice, total of 12 -->--%>
                                        <%--</ul>--%>
                                    <%--</div>--%>
                                    <%--<div id="carousel" class="flexslider">--%>
                                        <%--<ul class="slides">--%>
                                            <%--<d:forEach var="ascendingProduct" items="${productsAscending}">--%>
                                                <%--<li>--%>
                                                    <%--<img src="<c:url value="${BaseUrl}/images/${ascendingProduct.getProfileImage().getOriginal().getPath()}" />" >--%>
                                                <%--</li>--%>
                                            <%--</d:forEach>--%>
                                            <%--<!-- items mirrored twice, total of 12 -->--%>
                                        <%--</ul>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--&lt;%&ndash;<div role="tabpanel" class="tab-pane fade " id="profile">dfgdfg</div>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<div role="tabpanel" class="tab-pane fade " id="messages">dfgdfg</div>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<div role="tabpanel" class="tab-pane fade " id="settings">dfgdfgd</div>&ndash;%&gt;--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="col-md-6 col-sm-12 mcol-xs-12">--%>
                        <%--<div class="best_offer">--%>
                            <%--<img src="<c:url value="${BaseUrl}/images/${rentalProductsRandom1.getProfileImage().getOriginal().getPath()}" />">--%>
                            <%--<div class="best_offer_text">--%>
                                <%--<div class="offer_rate">--%>
                                    <%--<p class="offer_head"><span class="pre_rate">FROM</span><span class="rate">$${rentalProductsRandom1.rentFee}</span></p>--%>
                                <%--</div>--%>
                                <%--<div class="offer_des">--%>
                                    <%--<p class="name"><a href="${BaseUrl}/product/details/${rentalProductsRandom1.getId()}">${fn:substring(rentalProductsRandom1.name, 0, 20)}<d:if test="${fn:length(rentalProductsRandom1.name)>20}">....</d:if></a></p>--%>
                                    <%--<a href="javascript:void(0)" onclick="showRentRequestPopUp(${product.getId()})" class="btn shop_btn">RENT NOW</a>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="container blog_container">--%>

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
        <jsp:directive.include file="../layouts/top-footer.jsp" />
<!--Rent request form Modal -->
<jsp:directive.include file="../modals/rent_request_modal.jsp" />


<%--Hidden Values for Front Developer --%>
<input type="hidden" value="" id="currentProductId" />


<jsp:directive.include file="../layouts/footer.jsp" />
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-datepicker.js" />" ></script>

<%--Rent request JS--%>
<script type="text/javascript" src="<c:url value="/resources/developer/js/rent/rent_request.js" />" ></script>

<script>


</script>
</body>
</html>
