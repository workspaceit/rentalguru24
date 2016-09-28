<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 9/28/16
  Time: 4:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d" %>

<a href="#" class="dropdown-toggle" data-toggle="dropdown">
  <i class="fa fa-bell-o"></i>
  <span class="label label-warning">${adminGlobalNotifications.size()}</span>
</a>
<ul class="dropdown-menu">
  <li class="header">You have ${adminGlobalNotifications.size()} notifications</li>
  <li>
    <!-- inner menu: contains the actual data -->
    <ul class="menu">
      <d:forEach var="newNotification" items="${adminGlobalNotifications}">
        <li>
          <a href="#">
            ${newNotification.getDetails()}
          </a>
        </li>
      </d:forEach>
    </ul>
  </li>
  <li class="footer"><a href="${baseURL}/admin/user/test/test">View all</a></li>
</ul>
