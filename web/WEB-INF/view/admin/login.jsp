<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Reneguru-Admin | Log in</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="<c:url value="/admin-resources/bootstrap/css/bootstrap.min.css" />">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="<c:url value="/admin-resources/dist/css/AdminLTE.min.css"/> ">
    <!-- iCheck -->
    <link rel="stylesheet" href="<c:url value="/admin-resources/plugins/iCheck/square/blue.css"/> ">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body class="hold-transition login-page">
    <div class="login-box">
      <div class="login-logo">
        <%--<a href="<c:url value="/admin-resources/index2.html"/>"><b>Admin</b>LTE</a>--%>
      </div><!-- /.login-logo -->
      <div class="login-box-body">
        <p class="login-box-msg">Sign in to start your session</p>
        <form onsubmit="return submitSignInData();">
          <div class="form-group has-feedback">
            <input type="email" class="form-control" placeholder="Email" id="email" name="email">
            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <input type="oldPassword" class="form-control" placeholder="Password" id="oldPassword" name="oldPassword">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
          <div class="row">
            <div class="col-xs-8">
              <div class="checkbox icheck">
                <label>
                  <input type="checkbox"> Remember Me
                </label>
              </div>
            </div><!-- /.col -->
            <div class="col-xs-4">
              <button type="submit" class="btn btn-primary btn-block btn-flat">Sign In</button>
            </div><!-- /.col -->
          </div>
        </form>
        <div id="alertMsg" class="alert alert-success text-center" role="alert" hidden>
        </div>
        <%--<a href="#">I forgot my oldPassword</a><br>--%>
        <%--<a href="register.html" class="text-center">Register a new membership</a>--%>

      </div><!-- /.login-box-body -->
    </div><!-- /.login-box -->

    <!-- jQuery 2.1.4 -->
    <script src="<c:url value="/admin-resources/plugins/jQuery/jQuery-2.1.4.min.js" />"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="<c:url value="/admin-resources/bootstrap/js/bootstrap.min.js"/> "></script>
    <!-- iCheck -->
    <script src="<c:url value="/admin-resources/plugins/iCheck/icheck.min.js"/>"></script>
    <script>
      var BASEURL = "${BaseUrl}";
    </script>
    <script>
      $(function () {
        $('input').iCheck({
          checkboxClass: 'icheckbox_square-blue',
          radioClass: 'iradio_square-blue',
          increaseArea: '20%' // optional
        });
      });
    </script>
    <script>
        function submitSignInData(){
          var email = $('#email').val();
          var oldPassword = $('#oldPassword').val();
//          console.log(email, oldPassword);
          $.ajax({
            url: BASEURL + '/admin-signin/by-email-oldPassword',
            type: 'POST',
            data: {
              email: email,
              oldPassword: oldPassword
            },
            success: function (data) {
              if (data.responseStat.status == true) {
                $("#alertMsg").html(data.responseStat.msg).fadeIn(500).delay(2000).fadeOut(500, function () {
                  window.location.href = BASEURL + "/admin/user/dashboard";
                });
              } else {
                $("#alertMsg").html(data.responseStat.msg).fadeIn(500).delay(3000).fadeOut(500);
              }
            },
          });
          return false;
        }
    </script>
  </body>
</html>
