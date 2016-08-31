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
        <section class="content-header">
            <h1>
                Create New Admin

            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">

                        <div class="box-body">
                            <form role="form" onsubmit="return addNewAdmin();">
                                <div class="box-body">
                                    <div class="form-group">
                                        <label  >First Name</label>
                                        <input name="firstName"  class="form-control" id="fname" >
                                    </div>

                                    <div class="form-group">
                                        <label >Last Name</label>
                                        <input name="lastName"  class="form-control" id="lname" >
                                    </div>

                                    <div class="form-group">
                                        <label for="email">Email address</label>
                                        <input type="email"  class="form-control" id="email">
                                    </div>
                                    <div class="form-group">
                                        <label for="password">Password</label>
                                        <input name="password" type="password" class="form-control" id="password" >
                                    </div>
                                    <div class="form-group">
                                        <label for="confirmPassword">Confrim Password</label>
                                        <input id="confirmPassword" type="password" class="form-control"  >
                                    </div>



                                </div><!-- /.box-body -->

                                <div class="box-footer">
                                    <button class="btn btn-primary" >Submit</button>
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

    function addNewAdmin(){

        var lname=$('#lname').val();
        var fname=$('#fname').val();
        var email=$('#email').val();
        var password=$('#password').val();
        var confirmPassword=$('#confirmPassword').val();

        console.log(lname,fname,email,password,confirmPassword);
        var flag=true;
        var message="";
        if(!fname.trim()){
            message="First Name Required";
            flag=false;
        }else if(!lname.trim()){
            message="Last Name is required";
            flag=false;
        }else if(!email.trim() || !isValidEmailAddress(email)){
            message="Invalid Email Address ";
            flag=false;
        }else if(!password.trim()){
            message="Password is required";
            flag=false;
        }else if(password.length<6){
            message="Password length must be 6 characters";
            flag=false;
        }else if(!confirmPassword.trim()){
            message="Confirm Password field is empty";
            flag=false;
        }else if(!password===confirmPassword){
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
            url: '${BaseUrl}/api-admin/admin/create-new',
            data: {
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
