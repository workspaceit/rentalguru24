<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 10/19/16
  Time: 3:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="controller.BaseHttp" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!--maincontainer-->
<%--<div class="contentwrap">--%>
  <div class="container">
    <div class="row product_div_row">
      <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="row">
          <div class="col-md-6 col-sm-6 col-xs-12">
            <!-- Zoom plugin -->
            <div class="easyzoom easyzoom--adjacent easyzoom--with-thumbnails">
              <%--<a href="<c:url value="${BaseUrl}/images/${rentalProduct.profileImage.original.path}" />">--%>
                <img src="<c:url value="${BaseUrl}/images/${rentalProduct.profileImage.original.path}" />" alt="" class="main_img" />
              <%--</a>--%>
            </div>

            <ul class="thumbnails">
              <li>
                <%--<a href="<c:url value="${BaseUrl}/images/${rentalProduct.profileImage.original.path}" />" data-standard="<c:url value="${BaseUrl}/images/${rentalProduct.profileImage.original.path}" />">--%>
                  <img src="<c:url value="${BaseUrl}/images/${rentalProduct.profileImage.original.path}" />" alt=""  />
                <%--</a>--%>
              </li>
              <d:forEach var="productOtherImages" items="${rentalProduct.getOtherImages()}">
                <li>
                  <%--<a href="<c:url value="${BaseUrl}/images/${productOtherImages.original.path}" />" data-standard="<c:url value="${BaseUrl}/images/${productOtherImages.original.path}" />">--%>
                    <img src="<c:url value="${BaseUrl}/images/${productOtherImages.original.path}" />" alt=""  />
                  <%--</a>--%>
                </li>
              </d:forEach>
            </ul>
          </div>
          <div class="col-md-6 col-sm-6 col-xs-12 ">
            <div class="rent_product_info">
              <div class="row">
                <div class="col-md-8 col-xs-8 col-sm-8">
                  <h4 class="no-margin"><strong>${rentalProduct.getName()}</strong></h4>
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
                      <div class="col-md-12 col-sm-12 col-xs-12">
                        <p class="total_review">(${rentalProduct.getAverageRating()} Review)</p>
                      </div>
                    </d:if>
                    <d:if test="${rentalProduct.averageRating == 0}">
                      <div class="col-md-12 col-sm-12 col-xs-12">
                        <p class="total_review">Not rated yet</p>
                      </div>
                    </d:if>
                  </div>
                </div>

              </div>
            </div>
            <div class="rent_product_price">
              <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <p class="price"><span class="cur_price">$${rentalProduct.getRentFee()}/${rentalProduct.getRentType().getName()}</span></p>
                </div>
              </div>
            </div>
            <div class="product_overview">
              <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <h5><strong>Short Overview</strong></h5>
                  <p class="overview_content no-margin">${fn:substring(rentalProduct.getDescription(), 0, 1500)}<d:if test="${fn:length(rentalProduct.getDescription())>1500}">....</d:if></p>
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

                </div>
              </div>
            </div>
            <div class="block-inline">
              <ul class="prod-meta">
                <li class="pull-left"> <a href="${BaseUrl}/product/details/${rentalProduct.getId()}" class="theme-btn btn-black pull-left">View Details </a> </li>
                <li class="pull-right"> <a href="javascript:void(0)" onclick="showRentRequestPopUp(${rentalProduct.getId()})" class="theme-btn btn-black pull-right"> Rent Now </a> </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
<%--</div>--%>