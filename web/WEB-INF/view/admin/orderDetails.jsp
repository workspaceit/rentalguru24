<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 10/13/16
  Time: 12:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:directive.include file="layouts/header.jsp"/>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
  <jsp:directive.include file="layouts/navbar-top.jsp"/>
  <jsp:directive.include file="layouts/left-slider.jsp"/>
  <!-- Left side column. contains the logo and sidebar -->
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <jsp:directive.include file="layouts/pageHeader.jsp"/>
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-md-3">

          <!-- Profile Image -->
          <div class="box box-primary">
            <div class="box-body box-profile">
              <img class="profile-user-img img-responsive img-circle" src="${BaseUrl}/profile-image/${rentInf.getRentRequest().getRequestedBy().getUserInf().getProfilePicture().getOriginal().getPath()}" alt="User profile picture">
              <h3 class="profile-username text-center">${rentInf.getRentRequest().getRequestedBy().getUserInf().getFirstName()} ${rentInf.getRentRequest().getRequestedBy().getUserInf().getLastName()}</h3>
              <hr>

              <strong>Email</strong>
              <p class="text-muted">${rentInf.getRentRequest().getRequestedBy().getEmail()}</p>

              <hr>
              <strong>Address</strong>
              <p class="text-muted">
                ${rentInf.getRentRequest().getRequestedBy().getUserInf().getUserAddress().getAddress()}, ${rentInf.getRentRequest().getRequestedBy().getUserInf().getUserAddress().getCity()}, ${rentInf.getRentRequest().getRequestedBy().getUserInf().getUserAddress().getState()}, ${rentInf.getRentRequest().getRequestedBy().getUserInf().getUserAddress().getZip()}
              </p>
            </div><!-- /.box-body -->
          </div><!-- /.box -->
        </div><!-- /.col -->
        <div class="col-md-9">
          <div class="nav-tabs-custom">
            <ul class="nav nav-tabs">
              <li class="active"><a href="#activity" data-toggle="tab">Product Information</a></li>
              <%--<li><a href="#timeline" data-toggle="tab">Transaction Details</a></li>--%>
            </ul>
            <div class="tab-content">
              <div class="active tab-pane" id="activity">

                <!-- Post -->
                <div class="post">
                  <div class='user-block'>
                    <img class='img-circle img-bordered-sm' src='${BaseUrl}/profile-image/${rentInf.getRentalProduct().getOwner().getUserInf().getProfilePicture().getOriginal().getPath()}' alt='user image'>
                        <span class='username'>
                          <a href="#">${rentInf.getRentalProduct().getOwner().getUserInf().getFirstName()} ${rentInf.getRentalProduct().getOwner().getUserInf().getLastName()}</a>
                          <a href='#' class='pull-right btn-box-tool'><i class='fa fa-times'></i></a>
                        </span>
                  </div><!-- /.user-block -->
                  <div class='row margin-bottom'>
                    <div class='col-sm-6'>
                      <img class='img-responsive' src='${BaseUrl}/images/product/${rentInf.getRentalProduct().getProfileImage().getOriginal().getPath()}' alt='Photo'>
                    </div><!-- /.col -->
                    <div class='col-sm-6'>
                      <div class='row'>
                        <h4><strong>Description:</strong></h4>
                        <p>
                          ${rentInf.getRentalProduct().getDescription()}
                        </p>
                        <br>
                        <p><span>From: <fmt:formatDate pattern="MMM d,yyyy" value="${rentInf.getStartDate()}" /> </span>&nbsp;to&nbsp;<span>To: <fmt:formatDate pattern="MMM d,yyyy" value="${rentInf.getEndsDate()}" /></span></p>
                      </div><!-- /.row -->
                    </div><!-- /.col -->
                  </div><!-- /.row -->

                  <ul class="list-inline">
                    <li><a href="#" class="link-black text-sm"><i class="fa fa-share margin-r-5"></i> Share</a></li>
                    <li><a href="#" class="link-black text-sm"><i class="fa fa-thumbs-o-up margin-r-5"></i> Like</a></li>
                    <li class="pull-right"><a href="#" class="link-black text-sm"><i class="fa fa-comments-o margin-r-5"></i> Comments (5)</a></li>
                  </ul>

                  <input class="form-control input-sm" type="text" placeholder="Type a comment">
                </div><!-- /.post -->
              </div><!-- /.tab-pane -->
              <div class="tab-pane" id="timeline">
                <div class="box" style="border-top:0px;">
                  <div class="box-header with-border">
                    <h3 class="box-title">Latest Orders</h3>
                    <div class="box-tools pull-right">
                      <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                      <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                    </div>
                  </div><!-- /.box-header -->
                  <div class="box-body">
                    <div class="table-responsive">
                      <table class="table no-margin">
                        <thead>
                        <tr>
                          <th>Order ID</th>
                          <th>Item</th>
                          <th>Status</th>
                          <th>Popularity</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                          <td><a href="pages/examples/invoice.html">OR9842</a></td>
                          <td>Call of Duty IV</td>
                          <td><span class="label label-success">Shipped</span></td>
                          <td><div class="sparkbar" data-color="#00a65a" data-height="20">90,80,90,-70,61,-83,63</div></td>
                        </tr>
                        <tr>
                          <td><a href="pages/examples/invoice.html">OR1848</a></td>
                          <td>Samsung Smart TV</td>
                          <td><span class="label label-warning">Pending</span></td>
                          <td><div class="sparkbar" data-color="#f39c12" data-height="20">90,80,-90,70,61,-83,68</div></td>
                        </tr>
                        <tr>
                          <td><a href="pages/examples/invoice.html">OR7429</a></td>
                          <td>iPhone 6 Plus</td>
                          <td><span class="label label-danger">Delivered</span></td>
                          <td><div class="sparkbar" data-color="#f56954" data-height="20">90,-80,90,70,-61,83,63</div></td>
                        </tr>
                        <tr>
                          <td><a href="pages/examples/invoice.html">OR7429</a></td>
                          <td>Samsung Smart TV</td>
                          <td><span class="label label-info">Processing</span></td>
                          <td><div class="sparkbar" data-color="#00c0ef" data-height="20">90,80,-90,70,-61,83,63</div></td>
                        </tr>
                        <tr>
                          <td><a href="pages/examples/invoice.html">OR1848</a></td>
                          <td>Samsung Smart TV</td>
                          <td><span class="label label-warning">Pending</span></td>
                          <td><div class="sparkbar" data-color="#f39c12" data-height="20">90,80,-90,70,61,-83,68</div></td>
                        </tr>
                        <tr>
                          <td><a href="pages/examples/invoice.html">OR7429</a></td>
                          <td>iPhone 6 Plus</td>
                          <td><span class="label label-danger">Delivered</span></td>
                          <td><div class="sparkbar" data-color="#f56954" data-height="20">90,-80,90,70,-61,83,63</div></td>
                        </tr>
                        <tr>
                          <td><a href="pages/examples/invoice.html">OR9842</a></td>
                          <td>Call of Duty IV</td>
                          <td><span class="label label-success">Shipped</span></td>
                          <td><div class="sparkbar" data-color="#00a65a" data-height="20">90,80,90,-70,61,-83,63</div></td>
                        </tr>
                        </tbody>
                      </table>
                    </div><!-- /.table-responsive -->
                  </div><!-- /.box-body -->
                  <div class="box-footer clearfix">
                    <a href="javascript::;" style="margin-right:15px;" class="btn btn-sm btn-danger btn-flat pull-right">Cancel</a>
                    &nbsp; &nbsp;
                    <a href="javascript::;" class="btn btn-sm btn-success btn-flat pull-right">Approve</a>
                  </div><!-- /.box-footer -->
                </div><!-- /.tab-pane -->
              </div><!-- /.tab-pane -->
            </div><!-- /.tab-content -->
          </div><!-- /.nav-tabs-custom -->
        </div><!-- /.col -->
      </div><!-- /.row -->
    </section><!-- /.content -->
  </div><!-- /.content-wrapper -->
  <jsp:directive.include file="layouts/footer.jsp"/>
</div><!-- ./wrapper -->

</body>
</html>
