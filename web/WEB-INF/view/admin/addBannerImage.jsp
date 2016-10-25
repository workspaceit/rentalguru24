<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 10/24/16
  Time: 4:23 PM
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
        <div class="row">
          <div class="col-xs-12">
            <div class="box">
              <div class="box-body">
                <form role="form" onsubmit="return addBannerImage();">
                  <div class="form-group">
                    <label for="fallback">Add Banner Image</label>
                    <div id="fallback"  class="fallback" >
                      Drop files here or click to upload.
                    </div>
                    <input id="bannerImageToken" hidden>
                  </div>
                  <div class="form-group">
                    <label for="url">Url</label>
                    <input name="url"  class="form-control" id="url" >
                  </div>
                  <div class="box-footer">
                    <button class="btn btn-primary" id="bannerImageButton">Upload</button>
                  </div>
                </form>
              </div><!-- /.box-body -->
            </div><!-- /.box -->
          </div><!-- /.col -->
        </div><!-- /.row -->
        <%------------------------------------------------------------------%>
        <div class="table table-striped" class="files" id="previews">
          <div id="template" class="file-row">
            <!-- This is used as the file preview template -->
            <div>
              <span class="preview"><img data-dz-thumbnail /></span>
            </div>
            <div>
              <strong class="error text-danger" data-dz-errormessage></strong>
            </div>
          </div>
        </div>
        <%------------------------------------------------------------------%>
      </section><!-- /.content -->
    </div><!-- /.content-wrapper -->
    <jsp:directive.include file="layouts/footer.jsp" />
    <script>
      Dropzone.autoDiscover = false;
      var previewNode = document.querySelector("#template");
      previewNode.id = "";
      var previewTemplate = previewNode.parentNode.innerHTML;
      previewNode.parentNode.removeChild(previewNode);
      var bannerImage = $("div#fallback").dropzone(
              { url: BASEURL+"/fileupload/upload/document-identity",
                paramName: "documentIdentity",
                maxFilesize: 1,
                previewTemplate: previewTemplate,
                thumbnailWidth: 200,
                thumbnailHeight: 200,
                maxFiles: 1,
                acceptedFiles: "image/jpeg,image/png",
                maxfilesexceeded: function(file) {
                  this.removeAllFiles();
                  this.addFile(file);
                },
                success:function(file, response){
                  if(response.responseStat.status == true) {
                    $("#bannerImageToken").val(response.responseData);
                  }
                }
              }
      );
    </script>
    <script>

      function addBannerImage(){
        var bannerImageToken = $("#bannerImageToken").val();
        var url = $("#url").val();
        console.log(bannerImageToken+"---"+url);
        return false;
      }
    </script>
</body>
</html>

