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
                            <form role="form">
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
                                        <label for="exampleInputPassword1">Password</label>
                                        <input name="password" type="password" class="form-control" id="exampleInputPassword1" >
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputPassword1">Confrim Password</label>
                                        <input type="password" class="form-control"  >
                                    </div>



                                </div><!-- /.box-body -->

                                <div class="box-footer">
                                    <button  class="btn btn-primary" >Submit</button>
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
    function requestApprove(){




        $.ajax({
            type: "POST",
            url: '${BaseUrl}/api-admin/admin/create-new',

            success: function(data) {


                if(data.responseStat.status==true){
                    $('#' + requestId).fadeOut(300);
                }else{
                    $('#modal-text').text(data.responseStat.msg)
                    $('#myModal').modal('show')
                }



            },
            error: function() {
                alert('Error occured');
            }
        });



    }

    </script>
</body>
</html>
