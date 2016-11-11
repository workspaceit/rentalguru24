<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 8/23/16
  Time: 3:37 PM
  To change this template use File | Settings | File Templates.
--%>

<!DOCTYPE html>
<html>
<jsp:directive.include file="layouts/header.jsp"/>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">


    <jsp:directive.include file="layouts/navbar-top.jsp"/>
    <jsp:directive.include file="layouts/left-slider.jsp"/>

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


                            <form role="form" onsubmit="return updateSiteFees();">
                                <div class="box-body">


                                    <div class="form-group">
                                        <label>Commission Type</label>
                                        <br>


                                        <d:choose>
                                            <d:when test="${siteFeesCredientail.fixed==true}">
                                                <input type="radio" name="type" value="1" checked="checked"> Fixed<br>
                                                <input type="radio" name="type" value="2"> In Percentage<br>
                                            </d:when>
                                            <d:otherwise>
                                                <input type="radio" name="type" value="1"> Fixed<br>
                                                <input type="radio" name="type" value="2"
                                                       checked="checked"> In Percentage<br>
                                            </d:otherwise>
                                        </d:choose>


                                    </div>


                                    <div class="form-group">
                                        <label>Amount</label>

                                        <d:choose>
                                            <d:when test="${siteFeesCredientail.fixed==true}">
                                                <input value="${siteFeesCredientail.fixedValue}" name="amount"
                                                      type="number" step="0.01" class="form-control" id="amount">
                                            </d:when>
                                            <d:otherwise>
                                                <input value="${siteFeesCredientail.percentageValue}" name="amount"
                                                       type="number" step="0.01" class="form-control" id="amount">
                                            </d:otherwise>
                                        </d:choose>

                                    </div>


                                </div>
                                <div class="box-footer">
                                    <button class="btn btn-primary">Update</button>
                                </div>

                                <div class="form-group">
                                    <label id="errorMessage" style="color: red"></label>
                                    <label id="alertMsg" style="color: green"></label>
                                </div>
                            </form>


                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    <jsp:directive.include file="layouts/footer.jsp"/>
    <script>


        function updateSiteFees() {

            var type=$('input[name=type]:checked').val();
            var amount=$('#amount').val();




            var flag = true;
            var message = "";
            console.log(type,amount);
            if (!amount.trim()) {
                message = "Amount is empty";
                flag = false;

            }


            if (flag == false) {

                $('#errorMessage').text(message);
                message = "";
                return false;
            } else {
                $('#errorMessage').hide();
            }


            $.ajax({
                type: "POST",
                url: '${BaseUrl}/api-admin/admin/update-site-fees',
                data: {
                    type:type,
                    amount:amount


                },
                success: function (data) {


                    if (data.responseStat.status == true) {
                        $("#alertMsg").html(data.responseStat.msg).fadeIn(500).delay(2000).fadeOut(500, function () {
                            window.location.replace("${BaseUrl}/admin/user/get-utility")
                        });
                    } else {
                        alert(data.responseStat.msg);
                    }


                },
                error: function () {
                    alert('Error occured');
                }
            });


            return false;


        }

    </script>
</body>
</html>
