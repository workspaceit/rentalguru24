<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 12/21/16
  Time: 12:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:directive.include file="../layouts/header.jsp" />
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
  <jsp:directive.include file="../layouts/navbar-top.jsp" />
  <jsp:directive.include file="../layouts/left-slider.jsp" />

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <jsp:directive.include file="../layouts/pageHeader.jsp" />
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">

            <div class="box-body">
              <form role="form" onsubmit="return updateAppUser();">
                <div class="box-body">
                  <div class="form-group">
                    <label  >First Name</label>
                    <input value="${appUser.getUserInf().getFirstName()}" name="firstName"  class="form-control" id="fname" >
                  </div>

                  <div class="form-group">
                    <label >Last Name</label>
                    <input value="${appUser.getUserInf().getLastName()}" name="lastName"  class="form-control" id="lname" >
                  </div>

                  <div class="form-group">
                    <label for="email">Email address</label>
                    <input value="${appUser.getEmail()}" type="email"  class="form-control" id="email">
                  </div>

                  <div class="form-group">
                    <label for="address">Address</label>
                    <input value="${appUser.getUserInf().getUserAddress().getAddress()}" name="address"  class="form-control" id="address" >
                  </div>

                  <div class="form-group">
                    <label for="city">City</label>
                    <input value="${appUser.getUserInf().getUserAddress().getCity()}" name="city"  class="form-control" id="city" >
                  </div>

                  <div class="form-group">
                    <label for="state">State</label>
                    <input value="${appUser.getUserInf().getUserAddress().getState()}" name="state"  class="form-control" id="state" >
                  </div>

                  <div class="form-group">
                    <label for="zip">Zip</label>
                    <input value="${appUser.getUserInf().getUserAddress().getZip()}" name="zip"  class="form-control" id="zip" >
                  </div>

                  <div class="form-group">
                    <label for="phoneNumber">Phone Number</label>
                    <input value="${appUser.getUserInf().getPhoneNumber()}" name="phoneNumber"  class="form-control" id="phoneNumber" >
                  </div>

                  <div class="form-group">
                    <label for="gender">Gender</label>
                    <select class="form-control select2" id="gender" name="gender" style="width: 100%;">
                      <option value="0">Please select a gender</option>
                      <option value="male" <d:if test="${appUser.getUserInf().getGender() == 'male'}">selected="selected"</d:if> >Male</option>
                      <option value="female" <d:if test="${appUser.getUserInf().getGender() == 'female'}">selected="selected"</d:if>>Female</option>
                    </select>
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
  <jsp:directive.include file="../layouts/footer.jsp" />
  <script>
    function isValidEmailAddress(emailAddress) {
      var pattern = /^([a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+(\.[a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+)*|"((([ \t]*\r\n)?[ \t]+)?([\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\[\x01-\x09\x0b\x0c\x0d-\x7f\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))*(([ \t]*\r\n)?[ \t]+)?")@(([a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.)+([a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.?$/i;
      return pattern.test(emailAddress);
    };

    function updateAppUser(){

      var lname=$('#lname').val();
      var fname=$('#fname').val();
      var email=$('#email').val();
      var address = $('#address').val();
      var city = $('#city').val();
      var state = $('#state').val();
      var zip = $('#zip').val();
      var phoneNumber = $('#phoneNumber').val();
      var gender = $('#gender option:selected').val();

      var flag=true;
      var message="";

      if(!isValidEmailAddress(email)){
        message="Email is not valid";
        flag=false;
      }

      if(flag==false){
        $('#errorMessage').text(message);
        message="";
        return false;
      }else{
        $('#errorMessage').hide();
      }


      console.log(lname, fname, email, address, city, state, zip, phoneNumber, gender);


      $.ajax({
        type: "POST",
        url: '${BaseUrl}/api-admin/app-user/update-app-user-profile',
        data: {
          id:${appUser.id},
          firstName: fname,
          lastName: lname,
          email: email,
          address: address,
          city:city,
          state: state,
          zip: zip,
          phoneNumber: phoneNumber,
          gender: gender
        },
        success: function(data) {

          console.log("success Data");
          console.log(data);
          if(data.responseStat.status==true){
            $("#alertMsg").html(data.responseStat.msg).fadeIn(500).delay(2000).fadeOut(500, function () {
              window.location.replace("${BaseUrl}/admin/user/app-user")
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
