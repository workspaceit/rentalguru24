<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 12/21/16
  Time: 3:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
              <d:if test="${not empty user.getUserInf().getProfilePicture().getOriginal().getPath()}">
                <img class="profile-user-img img-responsive img-circle" src="${BaseUrl}/profile-image/${user.getUserInf().getProfilePicture().getOriginal().getPath()}" alt="User profile picture">
              </d:if>
              <d:if test="${empty user.getUserInf().getProfilePicture().getOriginal().getPath()}">
                <img class="profile-user-img img-responsive img-circle" src="${BaseUrl}/admin-resources/dist/img/defaultProfileImages.png" alt="User profile picture">
              </d:if>

              <h3 class="profile-username text-center">${user.getUserInf().getFirstName()} ${user.getUserInf().getLastName()}</h3>
              <hr>
              <strong>Gender</strong>
              <p class="text-muted">${user.getUserInf().getGender()}</p>

              <hr>
              <strong>Email</strong>
              <p class="text-muted">${user.getEmail()}</p>

              <hr>
              <strong>Address</strong>
              <p class="text-muted">${user.getUserInf().getUserAddress().getAddress()}, ${user.getUserInf().getUserAddress().getState()}, ${user.getUserInf().getUserAddress().getCity()}-${user.getUserInf().getUserAddress().getZip()}</p>

              <hr>
              <strong>Phone Number</strong>
              <p class="text-muted">${user.getUserInf().getPhoneNumber()}</p>
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
                <div class="box" style="border-top:0px;">
                  <div class="box-body">
                    <div class="table-responsive">
                      <table id="example" class="table table-bordered table-striped">
                        <thead>
                        <tr>
                          <th>Product title</th>
                          <th>Status</th>
                          <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <d:forEach var="ownerRentalProduct" items="${ownerRentalProductList}">
                          <tr>
                          <td>${ownerRentalProduct.getName()}</td>
                          <td>
                            <d:if test="${ownerRentalProduct.isReviewStatus() == true}">Approved</d:if>
                            <d:if test="${ownerRentalProduct.isReviewStatus() == false}">Disapproved</d:if>
                          </td>
                            <td>
                              <a href="${BaseUrl}/product/details/${ownerRentalProduct.getId()}" target="_blank" class="btn btn-block btn-info" role="button">Details</a>
                            </td>
                          </tr>
                        </d:forEach>
                        </tbody>
                        <tfoot>
                        <tr>
                          <th>Product title</th>
                          <th>Status</th>
                          <th>Action</th>
                        </tr>
                        </tfoot>
                      </table>
                    </div><!-- /.table-responsive -->
                  </div><!-- /.box-body -->
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
