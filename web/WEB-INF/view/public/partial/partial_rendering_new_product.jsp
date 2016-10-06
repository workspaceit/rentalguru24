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
      <div id="productListDiv" class="row clearfix">
        <jsp:directive.include file="../../common/product/rental_product/rental_product_list.jsp" />
      </div>
    </div>
  </div>
</div>

