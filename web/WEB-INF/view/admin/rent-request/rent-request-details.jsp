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
<jsp:directive.include file="../layouts/header.jsp"/>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
  <jsp:directive.include file="../layouts/navbar-top.jsp"/>
  <jsp:directive.include file="../layouts/left-slider.jsp"/>
  <!-- Left side column. contains the logo and sidebar -->
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <jsp:directive.include file="../layouts/pageHeader.jsp"/>
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-md-3">

          <!-- Profile Image -->
          <div class="box box-primary">
            <div class="box-body box-profile">
              <d:if test="${not empty rentRequest.getRequestedBy().getUserInf().getProfilePicture().getOriginal().getPath()}">
                <img class="profile-user-img img-responsive img-circle" src="${BaseUrl}/images/profile/${rentRequest.getRequestedBy().getUserInf().getProfilePicture().getOriginal().getPath()}" alt="User profile picture">
              </d:if>
              <d:if test="${empty rentRequest.getRequestedBy().getUserInf().getProfilePicture().getOriginal().getPath()}">
                <d:if test="${rentRequest.getRequestedBy().getUserInf().getGender() == 'male'}">
                  <img src="${BaseUrl}/resources/img/defaultProfileInmage.png" alt=""  />
                </d:if>
                <d:if test="${rentRequest.getRequestedBy().getUserInf().getGender() == 'female'}">
                  <img src="${BaseUrl}/resources/img/defaultProfileInmageFemale.png" alt=""  />
                </d:if>
              </d:if>
              <h3 class="profile-username text-center">${rentRequest.getRequestedBy().getUserInf().getFirstName()} ${rentRequest.getRequestedBy().getUserInf().getLastName()}</h3>
              <hr>

              <strong>Email</strong>
              <p class="text-muted">${rentRequest.getRequestedBy().getEmail()}</p>

              <hr>
              <strong>Address</strong>
              <p class="text-muted">
                ${rentRequest.getRequestedBy().getUserInf().getUserAddress().getAddress()}, ${rentRequest.getRequestedBy().getUserInf().getUserAddress().getCity()}, ${rentRequest.getRequestedBy().getUserInf().getUserAddress().getState()}, ${rentRequest.getRequestedBy().getUserInf().getUserAddress().getZip()}
              </p>
            </div><!-- /.box-body -->
          </div><!-- /.box -->
        </div><!-- /.col -->
        <div class="col-md-9">
          <div class="nav-tabs-custom">
            <ul class="nav nav-tabs">
              <li class="active"><a href="#activity" data-toggle="tab">Product Information</a></li>
            </ul>
            <div class="tab-content">
              <div class="active tab-pane" id="activity">

                <!-- Post -->
                <div class="post">
                  <div class='user-block'>
                    <d:if test="${not empty rentRequest.getRentalProduct().getOwner().getUserInf().getProfilePicture().getOriginal().getPath()}">
                      <img class='img-circle img-bordered-sm' src='${BaseUrl}/images/profile/${rentRequest.getRentalProduct().getOwner().getUserInf().getProfilePicture().getOriginal().getPath()}' alt='user image'>
                    </d:if>
                    <d:if test="${empty rentRequest.getRentalProduct().getOwner().getUserInf().getProfilePicture().getOriginal().getPath()}">
                      <d:if test="${rentRequest.getRentalProduct().getOwner().getUserInf().getGender() == 'male'}">
                        <img src="${BaseUrl}/resources/img/defaultProfileInmage.png" alt=""  />
                      </d:if>
                      <d:if test="${rentRequest.getRentalProduct().getOwner().getUserInf().getGender() == 'female'}">
                        <img src="${BaseUrl}/resources/img/defaultProfileInmageFemale.png" alt=""  />
                      </d:if>
                    </d:if>
                    <span class='username'>
                      ${rentRequest.getRentalProduct().getOwner().getUserInf().getFirstName()} ${rentRequest.getRentalProduct().getOwner().getUserInf().getLastName()}
                      <%--<a href='#' class='pull-right btn-box-tool'><i class='fa fa-times'></i></a>--%>
                    </span>
                  </div><!-- /.user-block -->
                  <div class='row margin-bottom'>
                    <div class='col-sm-6'>
                      <img class='img-responsive' src='${BaseUrl}/images/${rentRequest.getRentalProduct().getProfileImage().getOriginal().getPath()}' alt='Photo'>
                    </div><!-- /.col -->
                    <div class='col-sm-6'>
                      <div class='row'>
                        <h4><strong>Description:</strong></h4>
                        <p>
                          ${rentRequest.rentalProduct.description}
                        </p>
                        <br>
                        <p>
                          <span><b>Requested Date:</b> <fmt:formatDate pattern="MMM d, yyyy h:mm a" value="${rentRequest.createdDate}" /> </span>
                        </p>
                        <p>
                          <span>
                            <b>Duration :</b>
                            <fmt:formatDate pattern="MMM d,yyyy" value="${rentRequest.startDate}" />
                            &nbsp; to &nbsp;
                            <fmt:formatDate pattern="MMM d,yyyy" value="${rentRequest.startDate}" />
                          </span>
                        </p>

                      </div><!-- /.row -->
                    </div><!-- /.col -->
                  </div><!-- /.row -->

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
                    <a href="javascript:void(0);" style="margin-right:15px;" class="btn btn-sm btn-danger btn-flat pull-right">Cancel</a>
                    &nbsp; &nbsp;
                    <a href="javascript:void(0);" class="btn btn-sm btn-success btn-flat pull-right">Approve</a>
                  </div><!-- /.box-footer -->
                </div><!-- /.tab-pane -->
              </div><!-- /.tab-pane -->
            </div><!-- /.tab-content -->
          </div><!-- /.nav-tabs-custom -->
        </div><!-- /.col -->
      </div><!-- /.row -->
    </section><!-- /.content -->
  </div><!-- /.content-wrapper -->
  <jsp:directive.include file="../layouts/footer.jsp"/>
</div><!-- ./wrapper -->

</body>
</html>
