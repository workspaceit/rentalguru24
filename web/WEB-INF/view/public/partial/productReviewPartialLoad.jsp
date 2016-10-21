<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 10/14/16
  Time: 4:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<d:if test="${productRatingList.size() == 0}">
  <p class="no-review">There are no reviews for this product</p>
</d:if>
<d:if test="${productRatingList.size() != 0}">
  <d:forEach items="${productRatingList}" var="reviewList">
    <div class="col-md-8 comment-review">
      <div class="media">
        <div class="media-left pull-left">
          <a href="javascript:void(0);">
            <img alt="64x64" class="media-object" data-src="holder.js/64x64" style="width: 64px; height: 64px;" src="${BaseUrl}/profile-image/${reviewList.getAppCredential().getUserInf().getProfilePicture().getOriginal().getPath()}" data-holder-rendered="true">
          </a>
        </div>
        <div class="media-body">
          <h4 class="media-heading">${reviewList.getAppCredential().getUserInf().getFirstName()} ${reviewList.getAppCredential().getUserInf().getLastName()}</h4> ${reviewList.getReviewText()}
        </div>
      </div>
    </div>
    <d:if test="${reviewList.getRateValue() != 0}">
      <div class="col-md-4 col-sm-4 col-xs-4 text-left">
        <fieldset class="rating rent_rating">
          <input type="radio" id="star5" name="rating" value="5" <d:if test="${reviewList.getRateValue() == 5}">checked</d:if> /><label class = "full" for="star5" title="Awesome - 5 stars"></label>
          <input type="radio" id="star4" name="rating" value="4" <d:if test="${reviewList.getRateValue() == 4}">checked</d:if> /><label class = "full" for="star4" title="Pretty good - 4 stars"></label>
          <input type="radio" id="star3" name="rating" value="3" <d:if test="${reviewList.getRateValue() == 3}">checked</d:if> /><label class = "full" for="star3" title="Meh - 3 stars"></label>
          <input type="radio" id="star2" name="rating" value="2" <d:if test="${reviewList.getRateValue() == 2}">checked</d:if> /><label class = "full" for="star2" title="Kinda bad - 2 stars"></label>
          <input type="radio" id="star1" name="rating" value="1" <d:if test="${reviewList.getRateValue() == 1}">checked</d:if> /><label class = "full" for="star1" title="Sucks big time - 1 star"></label>
        </fieldset>
      </div>
    </d:if>
    </div>
    <hr>
  </d:forEach>
</d:if>

