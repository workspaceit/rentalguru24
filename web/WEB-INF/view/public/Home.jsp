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
<div id="slider-top" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
        <d:set var="sliderTopImageActive" value="active" />
        <d:set var="sliderTopImageCount" value="${0}" />
        <d:forEach var="topProduct" items="${bannerImageList}">
            <li data-target="#slider-top" data-slide-to="${sliderTopImageCount}" class="${sliderTopImageActive}">
                <d:set var="sliderTopImageActive" value="" />
                <d:set var="sliderTopImageCount" value="${sliderTopImageCount + 1}" />
            </li>
        </d:forEach>
    </ol>
    <!-- Wrapper for slides -->
    <div  class="carousel-inner " role="listbox">
        <d:if test="${bannerImageList != null}">
            <d:set var="bannerImageActive" value="active" />
            <d:forEach var="topProduct" items="${bannerImageList}">
                <div class="item ${bannerImageActive}">
                    <d:set var="bannerImageActive" value="" />
                    <a href="<c:url value="${topProduct.getUrl()}"/>" target = "_blank">
                        <img src="<c:url value="${BaseUrl}/images-banner/${topProduct.getImagePath()}" />" />
                    </a>
                </div>
            </d:forEach>
        </d:if>
        <d:if test="${bannerImageList == null}">
            <img src="<c:url value="${BaseUrl}/resources/img/no_image.png" />" />
        </d:if>
    </div>
    <!-- Controls -->
    <a class="left carousel-control" href="#slider-top" role="button" data-slide="prev">
        <span class="" aria-hidden="true"><i class="fa fa-chevron-left"></i></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#slider-top" role="button" data-slide="next">
        <span class="" aria-hidden="true"><i class="fa fa-chevron-right"></i></span>
        <span class="sr-only">Next</span>
    </a>
</div>
    <div class="home_slider" style="margin-bottom: 0px;">
        <div class="container">
            <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                    <%--<div id="slider-top" class="carousel slide" data-ride="carousel">--%>
                        <%--<ol class="carousel-indicators">--%>
                            <%--<li data-target="#slider-top" data-slide-to="0" class="active"></li>--%>
                            <%--<li data-target="#slider-top" data-slide-to="1"></li>--%>
                            <%--<li data-target="#slider-top" data-slide-to="2"></li>--%>
                            <%--<li data-target="#slider-top" data-slide-to="3"></li>--%>
                        <%--</ol>--%>
                        <%--<!-- Wrapper for slides -->--%>
                        <%--<div  class="carousel-inner " role="listbox">--%>
                            <%--<d:if test="${bannerImageList != null}">--%>
                                <%--<d:set var="bannerImageActive" value="active" />--%>
                                <%--<d:forEach var="topProduct" items="${bannerImageList}">--%>
                                    <%--<div class="item ${bannerImageActive}">--%>
                                        <%--<d:set var="bannerImageActive" value="" />--%>
                                        <%--<a href="<c:url value="${topProduct.getUrl()}"/>" target = "_blank">--%>
                                            <%--<img src="<c:url value="${BaseUrl}/images-banner/${topProduct.getImagePath()}" />" />--%>
                                        <%--</a>--%>
                                    <%--</div>--%>
                                <%--</d:forEach>--%>
                            <%--</d:if>--%>
                            <%--<d:if test="${bannerImageList == null}">--%>
                                <%--<img src="<c:url value="${BaseUrl}/resources/img/no_image.png" />" />--%>
                            <%--</d:if>--%>
                        <%--</div>--%>
                        <%--<!-- Controls -->--%>
                        <%--<a class="left carousel-control" href="#slider-top" role="button" data-slide="prev">--%>
                            <%--<span class="" aria-hidden="true"><i class="fa fa-chevron-left"></i></span>--%>
                            <%--<span class="sr-only">Previous</span>--%>
                        <%--</a>--%>
                        <%--<a class="right carousel-control" href="#slider-top" role="button" data-slide="next">--%>
                            <%--<span class="" aria-hidden="true"><i class="fa fa-chevron-right"></i></span>--%>
                            <%--<span class="sr-only">Next</span>--%>
                        <%--</a>--%>
                    <%--</div>--%>
                </div>
            </div>
            <div class="row">
                <%--<div class="col-md-4 col-sm-4 col-xs-12">--%>
                    <%--<div id="hot-deals" class="carousel slide left_home_car" data-ride="carousel">--%>
                        <%--<div class="left_slide_head">--%>
                                <%--${topRentalProductHeadTitle}--%>
                            <%--<!-- Indicators -->--%>
                                    <%--<ol class="carousel-indicators custom_car_ind">--%>
                                        <%--<li data-target="#hot-deals" data-slide-to="0" class="active"></li>--%>
                                        <%--<li data-target="#hot-deals" data-slide-to="1"></li>--%>
                                    <%--</ol>--%>
                        <%--</div>--%>
                        <%--<!-- Wrapper for slides -->--%>
                        <%--<div class="carousel-inner home_left_carousel" role="listbox">--%>
                            <%--<d:set var="topProductActive" value="active" />--%>
                            <%--<d:forEach var="topProduct" items="${rentalProductsTop}">--%>
                                <%--<div class="item ${topProductActive}">--%>
                                    <%--<d:set var="topProductActive" value="" />--%>
                                    <%--<a href="${BaseUrl}/product/details/${topProduct.getId()}">--%>
                                        <%--<d:if test="${topProduct.profileImage.original.path != null}">--%>
                                            <%--<img src="<c:url value="${BaseUrl}/images/${topProduct.profileImage.original.path}" />" />--%>
                                        <%--</d:if>--%>
                                        <%--<d:if test="${topProduct.profileImage.original.path == null}">--%>
                                            <%--<img src="<c:url value="${BaseUrl}/resources/img/no_image.png" />" />--%>
                                        <%--</d:if>--%>
                                    <%--</a>--%>
                                    <%--<a href="javascript:void(0);"><div class="carousel-caption cap1">--%>
                                        <%--<p onclick="showRentRequestPopUp(${topProduct.getId()})"><i class="fa fa-shopping-basket"></i> Rent Now </p>--%>
                                    <%--</div></a>--%>
                                    <%--<div class="custom_cap cap3">--%>
                                        <%--<label class="title-label productName"><a href="${BaseUrl}/product/details/${topProduct.getId()}">${fn:substring(topProduct.name, 0, 20)}<d:if test="${fn:length(topProduct.name)>20}">....</d:if></a></label>--%>
                                        <%--<d:if test="${topProduct.averageRating == 0}">--%>
                                            <%--<label class="no-rating-label">Not rated yet</label>--%>
                                        <%--</d:if>--%>
                                        <%--<div class="divider"></div>--%>
                                        <%--<div class="row option_but">--%>
                                            <%--<div class="col-md-5 col-sm-5 col-xs-12"></div>--%>
                                            <%--<div class="col-md-7 col-sm-7 col-xs-12">--%>
                                                <%--<div class="price-tag">$ ${topProduct.rentFee}/${topProduct.rentType.name}</div>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</d:forEach>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="col-md-8 col-sm-8 col-xs-12">--%>
                    <%--<div id="main-product" class="carousel slide main_product_slider" data-ride="carousel">--%>
                        <%--<!-- Wrapper for slides -->--%>
                        <%--<div  class="carousel-inner clearfix" role="listbox">--%>
                            <%--<d:if test="${bannerImageList != null}">--%>
                                <%--<d:set var="bannerImageActive" value="active" />--%>
                                <%--<d:forEach var="topProduct" items="${bannerImageList}">--%>
                                    <%--<div class="item ${bannerImageActive}">--%>
                                        <%--<d:set var="bannerImageActive" value="" />--%>
                                        <%--<a href="<c:url value="${topProduct.getUrl()}"/>" target = "_blank">--%>
                                            <%--<img src="<c:url value="${BaseUrl}/images-banner/${topProduct.getImagePath()}" />" />--%>
                                        <%--</a>--%>
                                    <%--</div>--%>
                                <%--</d:forEach>--%>
                            <%--</d:if>--%>
                            <%--<d:if test="${bannerImageList == null}">--%>
                                <%--<img src="<c:url value="${BaseUrl}/resources/img/no_image.png" />" />--%>
                            <%--</d:if>--%>
                        <%--</div>--%>
                        <%--<!-- Controls -->--%>
                        <%--<a class="left carousel-control" href="#main-product" role="button" data-slide="prev">--%>
                            <%--<span class="" aria-hidden="true"><i class="fa fa-chevron-left"></i></span>--%>
                            <%--<span class="sr-only">Previous</span>--%>
                        <%--</a>--%>
                        <%--<a class="right carousel-control" href="#main-product" role="button" data-slide="next">--%>
                            <%--<span class="" aria-hidden="true"><i class="fa fa-chevron-right"></i></span>--%>
                            <%--<span class="sr-only">Next</span>--%>
                        <%--</a>--%>
                    <%--</div>--%>
                <%--</div>--%>
            </div>
        </div>
    </div>


<div class="container-fluid h-w-container" style="margin-top:0px;padding-bottom:0px;padding-top: 10px;">
    <div class="container">
        <h2 class="block-head">How it works</h2>
        <%--<img src="<c:url value="${BaseUrl}/resources/img/imgpsh_fullsize.png"  />" class="img-responsive" />--%>
        <div class="col-md-6" style="float: none;margin: 0px auto;">

        <div class="embed-responsive embed-responsive-16by9">
            <iframe class="embed-responsive-item" src="https://player.vimeo.com/video/196047371?color=3d96d2&amp;title=0&amp;badge=0&amp;byline=0&amp;portrait=0"></iframe>
        </div>
        </div>
</div>

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
                    <d:if test="${showTopGallery}">
                        <div class="container">
                            <div class="col-md-4">
                                <p>
                                    <label for="radiusDistance">Radius range:</label>
                                    <input type="text" id="radiusDistance" readonly style="border:0; color:#f6931f; font-weight:bold;"  > KM
                                </p>
                                <div id="slider-range"></div>
                                <p class="help">Please Choose a radius</p>
                            </div>
                            <br><br><br>
                        </div>
                    </d:if>
                    <jsp:directive.include file="../common/product/rental_product/rental_product_list.jsp" />
                </div>
            </div>
        </div>
    </div>
</div>

<d:set var="loadMoreProductCssStr" value=""></d:set>
<d:if test="${rentalProducts.size()==0}">
    <d:set var="loadMoreProductCssStr" value="none"></d:set>
</d:if>
<d:if test="${rentalProducts.size()>=12}">
<div id="loadMoreButtonParent" class="col-md-12 text-center" style="display: ${loadMoreProductCssStr};">
    <button class="btn-cstm-sign pos-relative" id="loadMoreButton" onclick="loadMoreProduct()" >Load More
        <span id="loadMoreButtonLoader" class="inner-load " hidden="hidden"></span>
    </button>
</div>
</d:if>
<!--Quick view  Modal -->
<jsp:directive.include file="../common/product/rental_product/quickView.jsp" />
<!--Quick view  Modal end-->
<br>

<div class="testimonial">
    <div class="container">
    </div>
    <br>
    <br>
    <br>
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


</body>
</html>
