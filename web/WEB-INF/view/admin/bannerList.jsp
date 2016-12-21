<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 10/25/16
  Time: 4:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <jsp:directive.include file="layouts/pageHeader.jsp" />
    <!-- Main content -->
    <section class="content">
      <div class="alert alert-success bannerImageDeleteSuccess" hidden>
        Banner Delete Successfully, Redirecting.....
      </div>
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <%--<h3 class="box-title">Data Table With Full Features</h3>--%>
            </div><!-- /.box-header -->
            <div class="box-body">
              <table id="example1" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>Banner Image</th>
                  <th>Url</th>
                  <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <d:forEach var="bannerImage" items="${bannerImageList}">
                  <tr>
                    <td><img src="${BaseUrl}/images/bannerImage/${bannerImage.getImagePath()}" style="width: 80%; float: left"></td>
                    <td>${bannerImage.getUrl()}</td>
                    <td><button type="button" class="btn btn-block btn-danger" onclick="deleteBannerImage(${bannerImage.getId()})">Delete</button></td>
                  </tr>
                </d:forEach>
                </tbody>
                <tfoot>
                <tr>
                  <th>Banner Image</th>
                  <th>Url</th>
                  <th>Action</th>
                </tr>
                </tfoot>
              </table>
            </div><!-- /.box-body -->
          </div><!-- /.box -->
        </div><!-- /.col -->
      </div><!-- /.row -->
    </section><!-- /.content -->
  </div><!-- /.content-wrapper -->
  <jsp:directive.include file="layouts/footer.jsp" />
  <script>
    function deleteBannerImage(id){
      $.ajax({
        type: "POST",
        url: BASEURL+"/admin-lhacoec/banner-image/delete-banner-image/"+id,
        success: function(data){
          console.log(data.responseStat.status);
          if(data.responseStat.status == true){
            $(".bannerImageDeleteSuccess").show().delay(5000).fadeOut(500,function(){
              window.location.href = BASEURL+"/admin/user/banner-image-list";
            });
          }
        }
      });
    }
  </script>
</body>
</html>
