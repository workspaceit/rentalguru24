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
                <div class="row clearfix">
                    <div class="col-md-3">
                        <jsp:directive.include file="../common/search/filter/state-and-city.jsp" />

                        <%--CATEGORIES --%>

                        <div class="row clearfix">
                            <h4 class="sidebar-header">Categories</h4>
                            <div class="list-group search-sidebar" id="categoryPageLinkUl">
                                <a
                                        href="javascript:void(0)"
                                        categoryId="${categorySelected.id}"
                                        onclick="selectedCategory(${categorySelected.id})"
                                        id="categoryAnchor_${categorySelected.id}"
                                        data-category-name="${categorySelected.name}" class="list-group-item scrollToSection developerCategoryAnchore">${categorySelected.name} (${categorySelected.productCount})</a>
                                <d:forEach var="subcategory" items="${categorySelected.subcategory}">
                                    <a
                                            href="javascript:void(0)"
                                            categoryId="${subcategory.id}"
                                            onclick="selectedCategory(${subcategory.id})"
                                            id="categoryAnchor_${subcategory.id}"
                                            data-category-name="${subcategory.name}" class="sub-cat-s scrollToSection developerCategoryAnchore">${subcategory.name} (${subcategory.productCount})</a>
                                </d:forEach>
                            </div>
                        </div>



                        <%--<div class="row clearfix">--%>
                            <%--<h4 class="sidebar-header">Area</h4>--%>
                            <%--<div class="list-group search-sidebar" id="areaPageLinkUl">--%>
                                <%--<d:forEach var="usState" items="${stateList}" >--%>
                                <%--<a class="list-group-item" onclick="selectUsaState('${usState.code}','${usState.name}')">${usState.name}</a>--%>
                                <%--</d:forEach>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    </div>
                    <div class="col-md-9 clearfix">
                        <div id="productListDiv" class="row clearfix">
                            <jsp:directive.include file="../common/product/rental_product/rental_product_list.jsp" />
                        </div>
                        <d:if test="${rentalProducts.size()>=12}">
                            <div id="loadMoreButtonParent" class="col-md-12 text-center" style="display: ${loadMoreProductCssStr};">
                                <button class="btn-cstm-sign pos-relative" id="loadMoreButton" onclick="loadMoreProduct()" >Load More
                                    <span id="loadMoreButtonLoader" class="inner-load " hidden="hidden"></span>
                                </button>
                            </div>
                        </d:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<d:set var="loadMoreProductCssStr" value=""></d:set>
<d:if test="${rentalProducts.size()==0}">
    <d:set var="loadMoreProductCssStr" value="none"></d:set>
</d:if>


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
    $("#state").on("change", function(){
        fetchCitiesByStateCode();
    /*   var code = $("#state option:selected").val();
        var name = $("#state option:selected").text();
        selectUsaStateFromCategoryPage(code,name,"${categorySelected.id}");*/
//        console.log(code,name );
    });
    $("#city").on("change",function(){
        var code = $("#state option:selected").val();
        var name = $("#state option:selected").text();
        var catId = "${categorySelected.id}";
        var cityId = $(this).val();

        selectCityAndStateFromCategoryPage(code,name,cityId,catId);
    });
</script>
</body>
</html>
