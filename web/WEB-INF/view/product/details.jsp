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
<jsp:directive.include file="../layouts/header.jsp" />
<body class="ux">
<!--top Nav Bar-->
<jsp:directive.include file="../layouts/top-nav.jsp" />
<!--mid navbar-->
<jsp:directive.include file="../layouts/mid-nav.jsp" />
<!--main navbar-->
<jsp:directive.include file="../layouts/main-nav.jsp" />
<!--end main Nav-->
<!--maincontainer-->
<div class="contentwrap">
  <div class="parallax-window rent_bg1" data-enllax-ratio="0.7">
    <div class="container ">
      <div class="row">
        <div class="col-xs-12 col-md-6 col-sm-6">
          <%--<h3><strong> FURNITURE</strong></h3>--%>
        </div>
        <div class="col-xs-12 col-md-6 col-sm-6">
          <div class="breadcrumb rent_page_bread">
            <ol class="breadcrumb">
              <ol class="breadcrumb">
                <d:forEach var="breadCrumb" items="${breadCrumbStr}">
                  <li><a href="${breadCrumb.url}">${breadCrumb.text}</a></li>
                </d:forEach>
              </ol>
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
            <!-- Zoom plugin -->
            <div class="easyzoom easyzoom--adjacent easyzoom--with-thumbnails">
              <d:if test="${rentalProduct.profileImage.original.path != null}">
              <a href="<c:url value="${BaseUrl}/images/${rentalProduct.profileImage.original.path}" />">
                <img src="<c:url value="${BaseUrl}/images/${rentalProduct.profileImage.original.path}" />" alt="" class="main_img" />
              </a>
              </d:if>
              <d:if test="${rentalProduct.profileImage.original.path == null}">
              <a href="<c:url value="${BaseUrl}/resources/img/no_image.png" />">
                <img src="<c:url value="${BaseUrl}/resources/img/no_image.png" />" alt="" class="main_img" />
              </a>
              </d:if>
            </div>

            <ul class="thumbnails">
              <li>
                <d:if test="${rentalProduct.profileImage.original.path != null}">
                <a href="<c:url value="${BaseUrl}/images/${rentalProduct.profileImage.original.path}" />" data-standard="<c:url value="${BaseUrl}/images/${rentalProduct.profileImage.original.path}" />">
                  <img src="<c:url value="${BaseUrl}/images/${rentalProduct.profileImage.original.path}" />" alt=""  />
                </a>
                </d:if>
                <d:if test="${rentalProduct.profileImage.original.path == null}">
                <a href="<c:url value="${BaseUrl}/resources/img/no_image.png" />" data-standard="<c:url value="${BaseUrl}/images/no_image.png" />">
                  <img src="<c:url value="${BaseUrl}/resources/img/no_image.png" />" alt=""  />
                </a>
                </d:if>
              </li>
              <d:if test="${rentalProduct.getOtherImages() != null}">
                <d:forEach var="productOtherImages" items="${rentalProduct.getOtherImages()}">
                  <li>
                    <a href="<c:url value="${BaseUrl}/images/${productOtherImages.original.path}" />" data-standard="<c:url value="${BaseUrl}/images/${productOtherImages.original.path}" />">
                      <img src="<c:url value="${BaseUrl}/images/${productOtherImages.original.path}" />" alt=""  />
                    </a>
                  </li>
                </d:forEach>
              </d:if>
            </ul>
          </div>
          <div class="col-md-6 col-sm-6 col-xs-12 ">
            <div class="rent_product_info">
              <div class="row">
                <div class="col-md-8 col-xs-8 col-sm-8">
                  <h4 class="no-margin"><strong>${fn:escapeXml(rentalProduct.getName())}</strong></h4>
                  <div class="row">
                    <d:if test="${rentalProduct.averageRating != 0}">
                      <div class="col-md-4 col-sm-4 col-xs-12">
                        <fieldset class="rating rent_rating">
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

                      </div>
                    </d:if>
                    <d:if test="${rentalProduct.averageRating != 0}">
                      <div class="col-md-3 col-sm-3 col-xs-12">
                        <p class="total_review">(${rentalProduct.getAverageRating()} Review)</p>
                      </div>
                    </d:if>
                    <div class="col-md-5 col-sm-5 col-xs-12">
                      <a href=""><p class="total_review">Add Your Review</p></a>
                    </div>
                  </div>
                </div>
                <div class="col-md-4 col-xs-4 col-sm-4 text-right right">
                  <%--<button class="prev_next_rent_btn prev"><i class="fa fa-chevron-left"></i></button>--%>
                  <%--<button class="prev_next_rent_btn next"><i class="fa fa-chevron-right"></i></button>--%>
                </div>
              </div>
            </div>
            <div class="rent_product_price">
              <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <%--<p class="price"><span class="prev_price">$350</span><span class="cur_price">$250</span></p>--%>
                  <p class="price"><span class="cur_price">$${rentalProduct.getRentFee()}/${rentalProduct.getRentType().getName()}</span></p>
                </div>
              </div>
            </div>
            <div class="buying_option">
              <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <a class="rent_now_btn" href="javascript:void(0)" onclick="showRentRequestPopUp(${rentalProduct.getId()})"><i class="fa fa-shopping-basket"></i>Rent Now</a>
                  <%--<a class="ehe_btn" href=""><i class="fa fa-eye"></i></a>--%>
                  <%--<a class="ehe_btn" href=""><i class="fa fa-heart"></i></a>--%>
                  <%--<a class="ehe_btn" href=""><i class="fa  fa-exchange"></i></a>--%>
                </div>
              </div>
              <%--<div class="row">--%>
              <%--<div class="col-md-3 col-sm-3 col-xs-3">--%>
              <%--<p class="value_input_label">Quantity</p>--%>
              <%--</div>--%>
              <%--<div class="col-md-4 col-sm-4 col-xs-6 input-group spinner">--%>
              <%--<input type="text" class="form-control value_input" value="42">--%>
              <%--<div class="input-group-btn-vertical">--%>
              <%--<button class="btn btn-default value_controller" type="button"><i class="fa fa-plus"></i></button>--%>
              <%--<button class="btn btn-default value_controller" type="button"><i class="fa fa-minus"></i></button>--%>
              <%--</div>--%>
              <%--</div>--%>
              <%--</div>--%>
            </div>
            <div class="product_overview">
              <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <h5><strong>Short Overview</strong></h5>
                  <p class="overview_content no-margin">${fn:substring(fn:escapeXml(rentalProduct.getDescription()), 0, 1500)}<d:if test="${fn:length(rentalProduct.getDescription())>1500}">....</d:if></p>
                </div>
              </div>
            </div>
            <div class="product_stock">
              <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <d:if test="${rentalProduct.isCurrentlyAvailable() == true}">
                    <p class="no-margin"><strong>Avaialability:</strong><span class="avail_span">Available</span></p>
                  </d:if>
                  <d:if test="${rentalProduct.isCurrentlyAvailable() == false}">
                    <p class="no-margin"><strong>Avaialability:</strong><span class="avail_span">Unavailable</span></p>
                  </d:if>
                  <p class="no-margin"><strong>Category:</strong><span class="avail_span">${rentalProduct.getProductCategories().get(0).getCategory().getName()}</span></p>
                  <d:if test="${rentalProduct.productLocation!=null}" >
                    <p class="no-margin"><strong>Pickup/Product location:</strong><span class="avail_span">${fn:escapeXml(rentalProduct.productLocation.formattedAddress)}</span></p>
                  </d:if>
                </div>
              </div>
            </div>
            <div class="scl_share">
              <div class="row">
                <%--<div class="col-md-12 col-sm-12 col-xs-12">--%>
                  <%--<ul class="scl_share_ul">--%>
                    <%--<li class="facebook"><a href=""><i class="fa fa-facebook"></i><span class="scl_share_label">Share</span></a></li>--%>
                    <%--<li class="google"><a href=""><i class="fa fa-google-plus google"></i><span class="scl_share_label">Google+</span></a></li>--%>
                    <%--<li class="twitter"><a href=""><i class="fa fa-twitter twitter"></i><span class="scl_share_label">Twitter</span></a></li>--%>
                    <%--<li class="pinterest"><a href=""><i class="fa fa-pinterest pinterest"></i><span class="scl_share_label">Pinterest</span></a></li>--%>
                    <%--<li class="linkedin"><a href=""><i class="fa fa-linkedin linkedin"></i><span class="scl_share_label">Linkedin</span></a></li>--%>
                  <%--</ul>--%>

                <%--</div>--%>
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
          <div class="col-md-12 col-sm-12 col-xs-12 no-padding">
            <ul class="nav nav-tabs" role="tablist">
              <li role="presentation" class="active"><a href="#description" aria-controls="description" role="tab" data-toggle="tab">Description</a></li>
              <li role="presentation" ><a href="#reviews" aria-controls="reviews" role="tab" data-toggle="tab" onclick="getProductReview(${rentalProduct.getId()})">Reviews</a></li>
            </ul>
          </div>
        </div>

        <!-- Tab panes -->
        <div class="tab-content">
          <div role="tabpanel" class="tab-pane active" id="description">${rentalProduct.getDescription()}</div>
          <div role="tabpanel" class="tab-pane" id="reviews">
            <form class="review-form" action="" role="form">
              <span id="reviewRatingInnerLoader" class="inner-load" ></span>
              <div class="row" id="productReviewTab">
              </div>
            </form>
          </div>
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
                      <div class="img-single prod-img">
                        <img src="<c:url value="${BaseUrl}/images/${product.profileImage.original.path}" />" />
                        <a class="caption-link meta-icon" data-toggle="modal" data-target=".quickview-modal" href="javascript:void(0);" onclick="viewProductDetails(${product.getId()})"> <i class="fa fa-eye"> </i> </a>
                      </div>
                      <div class="block-desc">
                        <label class="title-label productName"><a href="${BaseUrl}/product/details/${product.getId()}">${fn:substring(product.name, 0, 20)}<d:if test="${fn:length(product.name)>20}">....</d:if></a></label>
                      </div>
                      <div class="block-action">
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
                        <div class="price-tag">$${product.rentFee}/${product.rentType.name}</div>
                      </div>
                      <div class="block-inline">
                        <ul class="prod-meta">
                          <li class="pull-left"> <a href="${BaseUrl}/product/details/${product.getId()}" class="theme-btn btn-black pull-left">View Details </a> </li>
                          <li class="pull-right"> <a href="javascript:void(0)" onclick="showRentRequestPopUp(${product.getId()})" class="theme-btn btn-black pull-right"> Rent Now </a> </li>
                        </ul>
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

<!--Quick view  Modal -->
<jsp:directive.include file="../common/product/rental_product/quickView.jsp" />
<!--Quick view  Modal end-->

<!--Rent request form Modal -->
<jsp:directive.include file="../modals/rent_request_modal.jsp" />

<%--<jsp:directive.include file="../layouts/newsletter.jsp" />--%>
<jsp:directive.include file="../layouts/top-footer.jsp" />
<jsp:directive.include file="../layouts/footer.jsp" />

<%--Rent request JS--%>
<script type="text/javascript" src="<c:url value="/resources/developer/js/rent/rent_request.js" />" ></script>

<!-- Javascript framework and plugins end here -->
<script>
  // Instantiate EasyZoom instances
  var $easyzoom = $('.easyzoom').easyZoom();
  // Setup thumbnails example
  var api1 = $easyzoom.filter('.easyzoom--with-thumbnails').data('easyZoom');
  $('.thumbnails').on('click', 'a', function(e) {
    var $this = $(this);
    e.preventDefault();
    // Use EasyZoom's `swap` method
    api1.swap($this.data('standard'), $this.attr('href'));
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
  function getProductReview(productId){
    $("#reviewRatingInnerLoader").show();
      if($(".comment-review").length == 0){
        $.ajax({
          url: BASEURL+"/product/review/partial-load/"+productId,
          type: "GET",
          success: function(data){
            $("#reviewRatingInnerLoader").hide();
            $("#productReviewTab").html(data);
          }
        });
      }else{
        $("#reviewRatingInnerLoader").hide();
      }
  }
</script>

</body>
</html>
