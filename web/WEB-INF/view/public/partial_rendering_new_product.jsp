<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 8/31/16
  Time: 1:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <d:forEach var="product" items="${products}">
          <div class="col-md-3 single-item">
            <div class="panel panel-default">
              <div class="panel-body">
                <div class="img-single">
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

