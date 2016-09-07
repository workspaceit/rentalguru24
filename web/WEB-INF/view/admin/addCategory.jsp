<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 9/5/16
  Time: 2:53 PM
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
    <section class="content-header">
      <h1>
        Create New Category
      </h1>
    </section>
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-body">
              <form role="form" onsubmit="return addNewCategor();" id="addCategoryForm">
                <div class="box-body">
                  <div class="form-group">
                    <label for="categoryName" >Category Name</label>
                    <input name="categoryName" id="categoryName" class="form-control" >
                    <p class="help-block error-form" id="errorMsg_name"></p>
                  </div>
                </div><!-- /.box-body -->
                <div class="box-footer">
                  <button class="btn btn-primary" >Submit</button>
                </div>
                <div class="alert alert-success" id="alertSuccess" hidden>Category Add Successful </div>
                <div class="alert alert-danger" id="errorMsg_categoryName" hidden></div>
              </form>
            </div><!-- /.box-body -->
          </div><!-- /.box -->
        </div><!-- /.col -->
      </div><!-- /.row -->
    </section><!-- /.content -->
  </div><!-- /.content-wrapper -->
  <jsp:directive.include file="layouts/footer.jsp" />
  <script>
    function addNewCategor(){
      $('.btn-primary').attr('disabled', 'disabled');
      $(':input').attr('disabled','disabled');
      var categoryName = $('#categoryName').val();
      $.ajax({
        type: "POST",
        url: '${BaseUrl}/api-admin/category/add-category',
        data: {
          categoryName: categoryName
        },
        success: function(data) {
          if(data.responseStat.status==true){
            $("#alertSuccess").show().fadeIn(500).delay(2000).fadeOut(500, function () {
              $('.btn-primary').removeAttr('disabled', 'disabled');
              $(':input').removeAttr('disabled','disabled');
              window.location.href =BASEURL+"/admin/user/category-list";
            });
          }else{
            BindErrorsWithHtml("errorMsg_", data.responseStat.requestErrors);
            $("#errorMsg_categoryName").show().fadeIn(500).delay(2000).fadeOut(500, function () {
              $(':input').removeAttr('disabled','disabled');
              $('.btn-primary').removeAttr('disabled', 'disabled');
            });
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

