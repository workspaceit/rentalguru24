<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 8/23/16
  Time: 3:37 PM
  To change this template use File | Settings | File Templates.
--%>

<!DOCTYPE html>
<html>
<jsp:directive.include file="layouts/header.jsp" />
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
  <jsp:directive.include file="layouts/navbar-top.jsp" />
  <jsp:directive.include file="layouts/left-slider.jsp" />

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <jsp:directive.include file="layouts/pageHeader.jsp" />
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">

            <div class="box-body">
              <form role="form" onsubmit="return updateAdmin();">
                <div class="box-body">
                  <div class="form-group">
                    <label  >First Name</label>
                    <input value="${adminUser.userInf.firstName}" name="firstName"  class="form-control" id="fname" >
                  </div>

                  <div class="form-group">
                    <label >Last Name</label>
                    <input value="${adminUser.userInf.lastName}" name="lastName"  class="form-control" id="lname" >
                  </div>

                  <div class="form-group">
                    <label for="email">Email address</label>
                    <input value="${adminUser.email}" type="email"  class="form-control" id="email">
                  </div>
                  <div class="form-group">
                    <label for="password">Password</label>
                    <input name="password" type="password" class="form-control" id="password" >
                  </div>
                  <div class="form-group">
                    <label for="confirmPassword">Confirm Password</label>
                    <input id="confirmPassword" type="password" class="form-control"  >
                  </div>



                </div><!-- /.box-body -->

                <div class="box-footer">
                  <button class="btn btn-primary" >Update</button>
                </div>

                <div class="form-group">
                  <label id="errorMessage" style="color: red"></label>
                  <label id="alertMsg" style="color: green"></label>

                </div>

              </form>



            </div><!-- /.box-body -->
          </div><!-- /.box -->
        </div><!-- /.col -->
      </div><!-- /.row -->
    </section><!-- /.content -->
  </div><!-- /.content-wrapper -->
  <jsp:directive.include file="layouts/footer.jsp" />
  <script>
    function isValidEmailAddress(emailAddress) {
      var pattern = /^([a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+(\.[a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+)*|"((([ \t]*\r\n)?[ \t]+)?([\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\[\x01-\x09\x0b\x0c\x0d-\x7f\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))*(([ \t]*\r\n)?[ \t]+)?")@(([a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.)+([a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.?$/i;
      return pattern.test(emailAddress);
    };

    function updateAdmin(){

      var lname=$('#lname').val();
      var fname=$('#fname').val();
      var email=$('#email').val();
      var password=$('#password').val();
      var confirmPassword=$('#confirmPassword').val();

      console.log(lname,fname,email,password,confirmPassword);
      var flag=true;
      var message="";

      if(!isValidEmailAddress(email)){
        message="Email is not valid";
        flag=false;

      } else if(!password.match(confirmPassword)){
        message="Confirm Password mismatched";
        flag=false;
      }


      if(flag==false){
        $('#errorMessage').text(message);
        message="";
        return false;
      }else{
        $('#errorMessage').hide();
      }




      $.ajax({
        type: "POST",
        url: '${BaseUrl}/api-admin/admin/update-admin/',
        data: {
          id:${adminUser.id},
          firstName: fname,
          lastName: lname,
          email: email,
          password: password

        },
        success: function(data) {


          if(data.responseStat.status==true){
            $("#alertMsg").html(data.responseStat.msg).fadeIn(500).delay(2000).fadeOut(500, function () {
              window.location.replace("${BaseUrl}/admin/user/get-all-admin")
            });


          }else{
            alert(data.responseStat.msg);
          }



        },
        error: function() {
          alert('Error occured');
        }
      });



      return false;





    }

  </script>
</body>
</html>
