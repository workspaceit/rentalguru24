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
        <section class="content-header">
            <h1>
                Data Tables
                <small>advanced tables</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="#">Tables</a></li>
                <li class="active">Data tables</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">Data Table With Full Features</h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <table id="example1" class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>Email Address</th>
                                    <th>Status</th>
                                    <th>Active/Deactive</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <d:forEach var="adminUser" items="${adminUsers}">
                                    <tr>

                                        <td>${adminUser.getUserInf().getFirstName()}</td>
                                        <td>${adminUser.getUserInf().getLastName()}</td>
                                        <td>${adminUser.getEmail()}</td>


                                        <d:choose>
                                            <d:when test="${adminUser.verified==true}">
                                                <td id="${adminUser.id}" style="color: green">Active</td>
                                            </d:when>
                                            <d:otherwise>
                                                <td id="${adminUser.id}" style="color: red">Deactivate</td>
                                            </d:otherwise>

                                        </d:choose>


                                        <td width="80">
                                            <div class="btn-group">
                                                <button class="btn btn-success">action</button>
                                                <button type="button" class="btn btn-success dropdown-toggle"
                                                        data-toggle="dropdown">
                                                    <span class="caret"></span>
                                                    <span class="sr-only">Toggle Dropdown</span>
                                                </button>
                                                <ul class="dropdown-menu" role="menu">
                                                    <li><a href="javascript:void(0)" onclick="activeAdmin(${adminUser.id})" )>Active</a>
                                                    </li>
                                                    <li><a href="javascript:void(0)" onclick="deactiveAdmin(${adminUser.id})">Deactive</a></li>
                                                </ul>
                                            </div>
                                        </td>

                                        <td><a href="${BaseUrl}/admin/user/get-admin-edit-page/${adminUser.id}">Edit</a></td>

                                    </tr>
                                </d:forEach>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>Email Address</th>

                                    <th>Active/Deactivate</th>
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
    function activeAdmin(adminId) {
        var status = $('#' + adminId).html();

        if (status == 'Active') {
            $('#modal-text').text('This Admin is already activated');
            $('#myModal').modal('show');
        } else {
            $.ajax({
                type: "POST",
                url: '${BaseUrl}/api-admin/admin/active-admin/'+adminId,
                success: function (data) {


                    $('#modal-text').text(data.responseStat.msg);
                    $('#myModal').modal('show')
                    $('#' + adminId).text('Active').css("color", "green");
                },
                error: function () {
                    alert('Error occured');
                }
            });
        }


    }

    function deactiveAdmin(adminId) {
        var status = $('#' + adminId).html();

        if (status == 'Deactivate') {
            $('#modal-text').text('This Admin is already deactivated');
            $('#myModal').modal('show')
        } else {
            $.ajax({
                type: "POST",
                url: '${BaseUrl}/api-admin/admin/deactive-admin/'+adminId,
                success: function (data) {


                    $('#modal-text').text(data.responseStat.msg);
                    $('#myModal').modal('show')
                    $('#' + adminId).text('Deactivate').css("color", "red");
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
