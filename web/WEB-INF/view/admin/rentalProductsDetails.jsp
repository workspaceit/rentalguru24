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
                                        <td><img width="100px" height="100px" src="${BaseUrl}/images/${product.profileImage.original.path}"></td>
                                        <td width="200px">${product.name}</td>
                                        <td>${product.owner.userInf.firstName}</td>
                                        <td>${product.currentValue}</td>
                                        <td>${product.rentFee}</td>
                                        <d:choose>
                                            <d:when test="${product.reviewStatus==true}">
                                                <td style="color: green">Appoved</td>
                                            </d:when>
                                            <d:otherwise>
                                                <td style="color: red">Disapproved</td>
                                            </d:otherwise>
                                        </d:choose>

                                        <td>
                                            <div class="btn-group">
                                                <button type="button" class="btn btn-success">action</button>
                                                <button type="button" class="btn btn-success dropdown-toggle"
                                                        data-toggle="dropdown">
                                                    <span class="caret"></span>
                                                    <span class="sr-only">Toggle Dropdown</span>
                                                </button>
                                                <ul class="dropdown-menu" role="menu">
                                                    <li ><a href="#">Approve</a></li>
                                                    <li ><a href="#">Disapprove</a></li>
                                                </ul>
                                            </div>
                                        </td>
                                    </tr>
                                </d:forEach>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>Email Address</th>
                                    <th>Identity Document</th>
                                    <th>Active/Deactive</th>
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
    <jsp:directive.include file="layouts/footer.jsp"/>
</body>
</html>
