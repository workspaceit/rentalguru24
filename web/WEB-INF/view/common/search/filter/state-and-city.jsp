<%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mi
  Date: 12/26/16
  Time: 5:45 PM
  To change this template use File | Settings | File Templates.
--%>
<d:set var="selectedUsStateId" value="0" ></d:set>
<d:set var="cityList" value="" ></d:set>
<d:if test="${selectedUsState != null}" >
  <d:set var="selectedUsStateId" value="${selectedUsState.id}" ></d:set>
  <d:set var="cityList" value="${selectedUsState.cities}" ></d:set>
</d:if>
<d:if test="${selectedUsState == null}" >
  <d:set var="selectedUsStateId" value="${stateList[0].id}" ></d:set>
  <d:set var="cityList" value="${stateList[0].cities}" ></d:set>
</d:if>
<div class="row clearfix">
  <h4 class="sidebar-header">Area</h4>
  <div class="list-group search-sidebar" id="areaPageLinkUl">
    <select id="state" class="selectpicker"> <%--onchange="selectUsaState('${usState.code}','${usState.name}')"--%>
      <option value=""  >Select State</option>

      <d:forEach var="usState" items="${stateList}" >
        <option value="${usState.code}"
                <d:if test="${usState.id == selectedUsStateId}" >selected="selected"</d:if>
                >${usState.name}</option>
      </d:forEach>
    </select>
  </div>
</div>
<div class="row clearfix">
  <h4 class="sidebar-header">City</h4>
  <div class="list-group search-sidebar" id="areaPageLinkUl">
    <select id="city" class="selectpicker" > <%--onchange="selectUsaState('${usState.code}','${usState.name}')"--%>
      <option value="">Select City</option>
      <option value="" >All</option>
      <d:forEach var="city" items="${cityList}" >
        <option value="${city.id}"
                <d:if test="${city.id == selectedCityId}" >selected="selected"</d:if>
                >${city.cityName}</option>
      </d:forEach>

    </select>
  </div>
</div>