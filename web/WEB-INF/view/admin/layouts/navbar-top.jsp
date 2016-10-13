<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 8/23/16
  Time: 3:19 PM
  To change this template use File | Settings | File Templates.
--%>
<header class="main-header">
  <!-- Logo -->
  <a href="#" class="logo">
    <!-- mini logo for sidebar mini 50x50 pixels -->
    <span class="logo-mini"><b>R</b>G</span>
    <!-- logo for regular state and mobile devices -->
    <span class="logo-lg"><b>Rental</b>GURU</span>
  </a>
  <!-- Header Navbar: style can be found in header.less -->
  <nav class="navbar navbar-static-top" role="navigation">
    <!-- Sidebar toggle button-->
    <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </a>
    <div class="navbar-custom-menu">
      <ul class="nav navbar-nav">
        <!-- Messages: style can be found in dropdown.less-->
        <li class="dropdown notifications-menu">
          <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" onclick="getNotification();">
            <i class="fa fa-bell-o"></i>
            <span class="label label-warning" id="unreadMessageCount"></span>
          </a>
          <ul class="dropdown-menu">
            <li class="header" id="unreadMessageCountText"></li>
            <li id="notificationInnerMenu">

            </li>
            <li class="footer"><a href="${baseURL}/admin/user/test/test">View all</a></li>
          </ul>
        </li>
        <!-- Notifications: style can be found in dropdown.less -->
        <!-- Tasks: style can be found in dropdown.less -->
        <!-- User Account: style can be found in dropdown.less -->
        <li class="dropdown user user-menu">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            <img src="<c:url value="/admin-resources/dist/img/defaultProfileImages.png"/>" class="user-image" alt="User Image">
            <span class="hidden-xs">${adminUser.getUserInf().getFirstName()}</span>
          </a>
          <ul class="dropdown-menu">
            <!-- User image -->
            <li class="user-header">
              <img src="<c:url value="/admin-resources/dist/img/defaultProfileImages.png"/>" class="img-circle" alt="User Image">
              <p>
                ${adminUser.getUserInf().getFirstName()}
              </p>
            </li>
            <!-- Menu Body -->
            <li class="user-body">
              <div class="col-xs-4 text-center">
                <a href="#">Followers</a>
              </div>
              <div class="col-xs-4 text-center">
                <a href="#">Sales</a>
              </div>
              <div class="col-xs-4 text-center">
                <a href="#">Friends</a>
              </div>
            </li>
            <!-- Menu Footer-->
            <li class="user-footer">
              <div class="pull-left">
                <a href="#" class="btn btn-default btn-flat">Profile</a>
              </div>
              <div class="pull-right">
                <a  class="btn btn-default btn-flat" onclick="signout()">Sign out</a>
              </div>
            </li>
          </ul>
        </li>
        <!-- Control Sidebar Toggle Button -->
        <li>
          <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
        </li>
      </ul>
    </div>
  </nav>
</header>
<script>
  window.onload = function(){
    $.ajax({
      type: "GET",
      url: BASEURL+"/api-admin/get-notification-count",
      success: function(data){
        if(data.responseStat.status == true){
          $("#unreadMessageCount").html(data.responseData.globalNotification);
          $("#unreadMessageCountText").html("You have "+data.responseData.globalNotification+" unread notifications");
        }else{
          $("#unreadMessageCount").html(0);
          $("#unreadMessageCountText").html("You have 0 unread notifications");
        }
      },
      error: function(data){
        console.log(data);
      }
    });
  };
  function getNotification(){
    $.ajax({
      type: "GET",
      url: BASEURL+"/admin/user/get-unread-notification-partial-render",
      data: {
        limit : 4,
        offset : 0
      },
      success: function(data){
        $("#notificationInnerMenu").html(data);
      },
      error: function(data){
        console.log(data);
      }
    });
  }
</script>