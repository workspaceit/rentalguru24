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

    <div class="container">
        <div class="col-md-6 center-block-slider">
            <p class="radius-block">
                <input type="text" class="range-number" id="radiusDistance" readonly  style="border:0; color:#f6931f; font-weight:bold;"> KM
            </p>
            <div id="slider-range"></div>
            <p class="help">**Please Choose a radius for finding the nearest available products</p>
        </div>
    </div>

    <div class="container product_carousel search-carousel" id="newProductPartialRender">
    <div id="carousel-example-generic" class="carousel slide carousel-cstm" data-ride="carousel" data-interval='false'>
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
        </ol>
        <d:if test="${rentalProducts.size()!=0}">
            <h2 class="search-head">${productListTitle}</h2>
        </d:if>

        <%--<h2 class="block-head">${productListTitle}</h2>--%>
        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">
            <div class="item active">
                <div id="productListDiv"  class="row clearfix">
                    <%--<div class="container">--%>
                        <%--<div class="col-md-4">--%>
                            <%--<p>--%>
                                <%--<label for="radiusDistance">Radius range:</label>--%>
                                <%--<input type="text" id="radiusDistance" readonly style="border:0; color:#f6931f; font-weight:bold;"  > KM--%>
                            <%--</p>--%>
                            <%--<div id="slider-range"></div>--%>
                            <%--<p class="help">Please Choose a radius</p>--%>
                        <%--</div>--%>
                        <%--<br><br><br>--%>
                    <%--</div>--%>



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

<!--Quick view  Modal -->
<jsp:directive.include file="../common/product/rental_product/quickView.jsp" />
<!--Quick view  Modal end-->

<br>

<div class="testimonial">

    <div class="container">

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
