<%--
  Created by IntelliJ IDEA.
  User: Tomal
  Date: 8/24/2016
  Time: 12:24 PM
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
        <jsp:directive.include file="layouts/pageHeader.jsp"/>
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <%--<h3 class="box-title">Data Table With Full Features</h3>--%>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <table id="example1" class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th>Picture</th>
                                    <th>Title</th>
                                    <th>Owner Name</th>
                                    <th>Current Value</th>
                                    <th>Rent Fee</th>
                                    <th>Status</th>
                                    <th>Active/Deactive</th>
                                </tr>
                                </thead>
                                <tbody>
                                <d:forEach var="product" items="${rentalProducts}">
                                    <tr>
                                        <td><img width="100px" height="100px"
                                                 src="${BaseUrl}/images/${product.profileImage.original.path}"></td>
                                        <td width="200px"><a href="${BaseUrl}/product/details/${product.id}" target="_blank">${product.name}</a></td>
                                        <td>${product.owner.userInf.firstName}</td>
                                        <td>${product.currentValue}</td>
                                        <td>${product.rentFee}</td>
                                        <d:choose>
                                            <d:when test="${product.reviewStatus==true}">
                                                <td style="color: green">
                                                    <div id="${product.id}">Appoved</div>
                                                </td>
                                            </d:when>
                                            <d:otherwise>
                                                <td style="color: red">
                                                    <div id="${product.id}">Disapproved</div>
                                                </td>
                                            </d:otherwise>
                                        </d:choose>

                                        <td width="80">
                                            <div class="btn-group">
                                                <button type="button" class="btn btn-success">action</button>
                                                <button type="button" class="btn btn-success dropdown-toggle"
                                                        data-toggle="dropdown">
                                                    <span class="caret"></span>
                                                    <span class="sr-only">Toggle Dropdown</span>
                                                </button>
                                                <ul class="dropdown-menu" role="menu">
                                                    <li><a href="javascript:void(0)" onclick="approve(${product.id})">Approve</a>
                                                    </li>
                                                    <li><a href="javascript:void(0)"
                                                           onclick="disapprove(${product.id})">Disapprove</a></li>
                                                </ul>
                                            </div>
                                        </td>
                                    </tr>
                                </d:forEach>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <th>Picture</th>
                                    <th>Title</th>
                                    <th>Owner Name</th>
                                    <th>Current Value</th>
                                    <th>Rent Fee</th>
                                    <th>Status</th>
                                </tr>
                                </tfoot>
                            </table>
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

    <div class="modal" id="myModal" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Message</h4>
                </div>
                <div class="modal-body">
                    <p id="modal-text"></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>

</div>


<jsp:directive.include file="layouts/footer.jsp"/>
<script>

    $(document).ready(function () {
        $('#myModal').modal('hide');
    });
    function approve(prdocutId) {
        var status = $('#' + prdocutId).html();

        if (status == 'Appoved') {
            $('#modal-text').text('This Product is already approved');
            $('#myModal').modal('show')
        } else {
            $.ajax({
                type: "POST",
                url: '${BaseUrl}/api-admin/product/approve-product',
                data: ({
                    pid: prdocutId
                }),
                success: function (data) {
                    $('#modal-text').text(data.responseStat.msg);
                    $('#myModal').modal('show')
                    $('#' + prdocutId).text('Appoved').css("color", "green");
                },
                error: function () {
                    alert('Error occured');
                }
            });
        }


    }

    function disapprove(prdocutId) {
        var status = $('#' + prdocutId).html();
        if (status == 'Disapproved') {
            $('#modal-text').text('This Product is already Disapproved');
            $('#myModal').modal('show');
        } else {
            $.ajax({
                type: "POST",
                url: '${BaseUrl}/api-admin/product/disapprove-product',
                data: ({
                    pid: prdocutId
                }),
                success: function (data) {

                    $('#modal-text').text(data.responseStat.msg);
                    $('#myModal').modal('show')
                    $('#' + prdocutId).text('Disapproved').css("color", "red");
                },
                error: function () {
                    alert('Error occured');
                }
            });
        }

    }


</script>

</body>
</html>
