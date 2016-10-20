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


        <d:if test="${!showTopGallery}">
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
                                            <a href="javascript:void(0);"><div class="carousel-caption cap1">
                                                <p onclick="showRentRequestPopUp(${topProduct.getId()})"><i class="fa fa-shopping-basket"></i> Rent Now </p>
                                            </div></a>
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
        </d:if>
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
        <div id="loadMoreButtonParent" class="col-md-12 text-center" style="display: ${loadMoreProductCssStr};">
            <button class="btn-cstm-sign pos-relative" id="loadMoreButton" onclick="loadMoreProduct()" >Load More
                <span id="loadMoreButtonLoader" class="inner-load " hidden="hidden"></span>
            </button>
        </div>



        <br>

            <div class="testimonial">

                <div class="container">
                    <d:if test="${!clientFeedback}">
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
                    </d:if>
                </div>

            </div>

        <jsp:directive.include file="../layouts/top-footer.jsp" />

        <!--Quick view  Modal -->
        <div class="modal fade quickview-modal" id="quickview" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <%--<div class="modal-header">--%>
                    <%--<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>--%>
                    <%--<h4 class="modal-title" id="myModalLabel">Modal title</h4>--%>
                    <%--</div>--%>
                    <div class="modal-body" id="developerPartialRenderView">
                    </div>
                    <%--<div class="modal-footer">--%>
                    <%--<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>--%>
                    <%--<button type="button" class="btn btn-primary">Save changes</button>--%>
                    <%--</div>--%>
                </div>
            </div>
        </div>

        <script>
            function viewProductDetails(productId){
                $("#developerPartialRenderView").html("<h1>Loading.....</h1>");
                $.ajax({
                    type: "GET",
                    url: BASEURL+"/product/product-details/partial-load-modal/"+productId,
                    success: function(data){
                        $("#developerPartialRenderView").html(data);
                    }
                });
            }
        </script>
        <!--Quick view  Modal end-->
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
