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
        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">
            <div class="item active">
                <div class="row clearfix">
                    <div class="col-md-3">
                        <jsp:directive.include file="../common/search/filter/state-and-city.jsp" />
                        <div class="row clearfix">
                            <h4 class="sidebar-header">Categories</h4>
                            <div class="list-group search-sidebar" id="categoryPageLinkUl">
                                <d:forEach var="listValue" items="${category}">

                                    <%--onclick="selectedCategory(${listValue.id})"--%>
                                    <a

                                            href="${BaseUrl}${categoryBySearchUrl}&cid=${listValue.id}"
                                            categoryId="${listValue.id}"

                                            id="categoryAnchor_${listValue.id}"
                                            data-category-name="${listValue.name}" class="list-group-item scrollToSection developerCategoryAnchore">${listValue.name} </a>
                                   <%-- (${listValue.productCount})--%>
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
    });
    $("#city").on("change", function(){
        var stateCode = $("#state option:selected").val();
        var stateName = $("#state option:selected").text();
        var cityId = $(this).val();
        // selectUsaState(code,name);

        var categoryId = $("#categorySelectedInSearch").val();

        var serachParams =[];
        var url = "search/"+stateCode.toLowerCase();
        if(cityId!=""){
            serachParams["cityId"]=cityId;
        }

        if(categoryId!=""){
            serachParams["cid"]=categoryId;
        }
        var paramsStr = getArrayToUriParams(serachParams);
        paramsStr = (paramsStr=="")?"":"?"+paramsStr;
        url +=paramsStr;
        window.location = BASEURL+"/"+url;

    });

</script>
</body>
</html>
