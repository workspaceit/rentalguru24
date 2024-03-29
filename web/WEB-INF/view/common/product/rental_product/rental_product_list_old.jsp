<d:forEach var="rentalProduct" items="${rentalProducts}">
    <div class="col-md-3 col-sm-6 col-xs-12 single-item">
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="img-single product_home_img_wrap">
                    <a href="${BaseUrl}/product/details/${rentalProduct.getId()}"><img src="<c:url value="${BaseUrl}/images/${rentalProduct.profileImage.original.path}" />" /></a>
                    <div class="product-btn-grp">
                        <a href="${BaseUrl}/product/details/${rentalProduct.getId()}" class="gbtn left">View details</a>
                            <%--data-toggle="modal" data-target="#rentPopUp"--%>
                        <a href="javascript:void(0)" onclick="showRentRequestPopUp(${rentalProduct.getId()})" class="gbtn right">Rent Now</a>
                    </div>
                </div>
                <div class="block-desc">
                    <label class="title-label productName"><a href="${BaseUrl}/product/details/${rentalProduct.getId()}">${fn:substring(rentalProduct.name, 0, 20)}<d:if test="${fn:length(rentalProduct.name)>20}">....</d:if></a></label>
                    <br>
                    <d:if test="${rentalProduct.averageRating != 0}">
                        <fieldset class="rating ">
                            <input <d:if test="${fn:substringBefore(rentalProduct.averageRating,'.') == '5'}">checked</d:if> type="radio" id="star5_${rentalProduct.getId()}" name="rating${rentalProduct.getId()}" value="5" />
                            <label class = "full" for="star5_${rentalProduct.getId()}" title="Awesome - 5 stars"></label>

                            <input <d:if test="${fn:substringBefore(rentalProduct.averageRating,'.') == '4'}">checked</d:if> type="radio" id="star4_${rentalProduct.getId()}" name="rating${rentalProduct.getId()}" value="4" />
                            <label class = "full" for="star4_${rentalProduct.getId()}" title="Pretty good - 4 stars"></label>

                            <input <d:if test="${fn:substringBefore(rentalProduct.averageRating,'.') == '3'}">checked</d:if> type="radio" id="star3_${rentalProduct.getId()}" name="rating${rentalProduct.getId()}" value="3" />
                            <label  class = "full" for="star3_${rentalProduct.getId()}" title="Meh - 3 stars"></label>

                            <input <d:if test="${fn:substringBefore(rentalProduct.averageRating,'.') == '2'}">checked</d:if> type="radio" id="star2_${rentalProduct.getId()}" name="rating${rentalProduct.getId()}" value="2" />
                            <label class = "full" for="star2_${rentalProduct.getId()}" title="Kinda bad - 2 stars"></label>

                            <input <d:if test="${fn:substringBefore(rentalProduct.averageRating,'.') == '1'}">checked</d:if> type="radio" id="star1_${rentalProduct.getId()}" name="rating${rentalProduct.getId()}" value="1" />
                            <label class = "full" for="star1_${rentalProduct.getId()}" title="Sucks big time - 1 star"></label>
                        </fieldset>
                    </d:if>
                    <d:if test="${rentalProduct.averageRating == 0}">
                        <label class="no-rating-label">Not rated yet</label>
                    </d:if>
                </div>
                <div class="block-action">
                        <%--<button class="btn-fav pull-left"><i class="fa fa-heart-o"></i></button>--%>
                        <%--<button class="btn-compare pull-left"><i class="fa  fa-exchange"></i></button>--%>
                    <div class="price-tag">$${rentalProduct.rentFee}/${rentalProduct.rentType.name}</div>
                </div>
            </div>
        </div>
    </div>

</d:forEach>
<d:if test="${rentalProducts.size()==0}">
    <div class="col-md-12 text-center developer-no-product-found">
        <p>No Product found</p>
    </div>
</d:if>