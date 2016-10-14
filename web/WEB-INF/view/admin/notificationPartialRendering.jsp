<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 9/28/16
  Time: 4:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d" %>
<!-- inner menu: contains the actual data -->
<ul class="menu">
  <d:forEach var="newNotification" items="${adminGlobalNotifications}">
    <li>
      <a href="javascript:void(0);" onclick="notificationRead(${newNotification.getId()})">
          ${newNotification.getDetails()}
      </a>
    </li>
  </d:forEach>
</ul>
<script>
  function notificationRead(notificationId){
    $.ajax({
      type: "POST",
      url: BASEURL+"/api-admin/notification-read/"+notificationId,
      success: function(data){
        if(data.responseStat.status == true){
          window.location = BASEURL+"/admin/user/notification/global/"+notificationId;
        }
      }
    });
  }
</script>