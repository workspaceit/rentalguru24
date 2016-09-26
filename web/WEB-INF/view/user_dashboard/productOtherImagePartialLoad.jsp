<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 9/26/16
  Time: 5:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d" %>

<d:forEach items="${rentalProduct.getOtherImages()}" var="otherimages">
  <div class="col-md-3 pos-relative">
    <img src="${BaseUrl}/images/${otherimages.getOriginal().getPath()}" alt="...">
    <span class="img-cross" onclick="deleteOtherImage(${rentalProduct.getId()},'${otherimages.getOriginal().getPath()}')">X</span>
  </div>
</d:forEach>