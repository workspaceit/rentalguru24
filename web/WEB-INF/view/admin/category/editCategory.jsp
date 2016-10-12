<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 9/6/16
  Time: 2:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:directive.include file="../layouts/header.jsp" />
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
  <jsp:directive.include file="../layouts/navbar-top.jsp" />
  <jsp:directive.include file="../layouts/left-slider.jsp" />

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
              <form role="form" onsubmit="return editCategory();">
                <div class="box-body">
                  <div class="form-group">
                    <label for="categoryName" >Category Name</label>
                    <input name="categoryName" id="categoryName" class="form-control" value="${category.getName()}" data-categoryid="${category.getId()}">
                    <p class="help-block error-form" id="errorMsg_name"></p>
                  </div>
                  <div class="form-group" style="width: 50%;height: 50%">
                    <label for="fallback">Add product images</label>
                    <div id="fallback" class="fallback pos-relative">
                      Drop files here or click to upload.
                      <span class="inner-load fileUploadGif" hidden></span>
                    </div>
                    <p class="help-block error-form" id="errorMsg_categoryImageToken"></p>
                    <input type="hidden" value="" id="categoryImageToken" name="categoryImageToken">
                  </div>
                </div><!-- /.box-body -->
                <div class="box-footer">
                  <button class="btn btn-primary" >Submit</button>
                </div>
                <div class="alert alert-success" id="alertSuccess" hidden></div>
                <div class="alert alert-danger" id="errorMsg_categoryName" hidden></div>
              </form>
            </div><!-- /.box-body -->
          </div><!-- /.box -->
        </div><!-- /.col -->
      </div><!-- /.row -->
    </section><!-- /.content -->
  </div><!-- /.content-wrapper -->

    <div class="table table-striped" class="files" id="previews">

      <div id="template" class="file-row">
        <!-- This is used as the file preview template -->
        <div>
          <span class="preview"><img data-dz-thumbnail /></span>
        </div>
        <div>
          <strong class="error text-danger" data-dz-errormessage></strong>
        </div>
        <%--<div>--%>
        <%--<p class="size" data-dz-size></p>--%>
        <%--<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0">--%>
        <%--<div class="progress-bar progress-bar-success" style="width:0%;" data-dz-uploadprogress></div>--%>
        <%--</div>--%>
        <%--</div>--%>
      </div>
    </div>
  <jsp:directive.include file="../layouts/footer.jsp" />
    <script src="<c:url value="/resources/js/dropzone.js"/>"></script>
  <script>
    Dropzone.autoDiscover = false;
    var previewNode = document.querySelector("#template");
    previewNode.id = "";
    var previewTemplate = previewNode.parentNode.innerHTML;
    previewNode.parentNode.removeChild(previewNode);
    var otherImagesTokenArray = [];

    $(function() {

    });
    var categoryImageFile = $("div#fallback").dropzone(
            {
              url: BASEURL+"/fileupload/upload/category-image",
              paramName: "categoryImage",
              maxFilesize: 1,
              thumbnailWidth: 200,
              previewTemplate: previewTemplate,
              thumbnailHeight: 200,
              maxFiles: 1,
              acceptedFiles: "image/jpeg,image/png,image/jpg",
              maxfilesexceeded: function(file) {
                this.removeAllFiles();
                this.addFile(file);
              },
              uploadprogress:function(file, progress){
                $('#addCategoryBtn').attr("disabled", "disabled");
                $('.postProductGif').show();
                $('.fileUploadGif').show();
              },
              success:function(file, response){
                console.log(response);
                if(response.responseStat.status == true) {
                  $('.fileUploadGif').hide();
                  $('#addCategoryBtn').removeAttr("disabled","disabled");
                  $('.postProductGif').hide();
                  $('#categoryImageToken').val(response.responseData);
                }
                else{
                  BindErrorsWithHtml('errorMsg_', response.requestErrors);
                }
              },
              error:function(file, errorMessage, xhr){
                $('.fileUploadGif').hide();
                $('#postProduct').removeAttr("disabled","disabled");
                $('.postProductGif').hide();
              }
            }
    );
    function editCategory(){
      var categoryId = $('#categoryName').data("categoryid");
      var categoryName = $('#categoryName').val();
      var categoryImgToken = $('#categoryImageToken').val();
      $.ajax({
        type: "POST",
        url: '${BaseUrl}/api-admin/category/edit-category',
        data: {
          categoryName: categoryName,
          categoryId: categoryId,
          categoryImgToken:categoryImgToken
        },
        success: function(data) {
          if(data.responseStat.status==true){
            $("#alertSuccess").html(data.responseStat.msg).show().fadeIn(500).delay(2000).fadeOut(500, function () {
              window.location.href =BASEURL+"/admin/user/category-list";
            });
          }else{
            BindErrorsWithHtml("errorMsg_", data.responseStat.requestErrors);
            $("#errorMsg_categoryName").show().fadeIn(500).delay(2000).fadeOut(500, function () {

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