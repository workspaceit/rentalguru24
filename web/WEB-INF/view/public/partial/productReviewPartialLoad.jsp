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
    <hr>
  </d:forEach>
</d:if>

