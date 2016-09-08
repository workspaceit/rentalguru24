<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 9/8/16
  Time: 11:38 AM
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
      </section>
      <!-- Main content -->
      <section class="content">
        <div class="row">
          <div class="col-md-12">
            <div class="box box-info">
              <div class="box-header">
                <h3 class="box-title"> CMS Page <small></small></h3>
              </div><!-- /.box-header -->
              <div class="box-body pad">
                <form onsubmit="return addCMSPage();">
                  <div class="box-body">
                    <div class="form-group">
                      <label for="pageName" >Page Name</label>
                      <input name="pageName" id="pageName" class="form-control" >
                      <p class="help-block error-form" id="errorMsg_pageName"></p>
                    </div>
                  </div>
                  <div class="box-body">
                    <div class="form-group">
                      <label for="pageKey" >Page url</label>
                      <p class="info" >Link to : ${BaseUrl}/static/{Page Url}</p>
                      <input name="pageKey" id="pageKey" class="form-control" >
                      <p class="help-block error-form" id="errorMsg_pageKey"></p>
                    </div>
                  </div>
                  <div class="box-body">
                    <label for="editor1" >Page Content</label>
                    <textarea id="editor1" name="editor1" rows="10" cols="80"></textarea>
                    <p class="help-block error-form" id="errorMsg_pageContent"></p>
                  </div>
                  <div class="box-footer">
                    <button class="btn btn-primary" >Submit</button>
                  </div>
                  <div class="alert alert-success" hidden></div>
                </form>
              </div>
            </div><!-- /.box -->
          </div><!-- /.col-->
        </div><!-- ./row -->
      </section><!-- /.content -->
    </div><!-- /.content-wrapper -->
  <jsp:directive.include file="layouts/footer.jsp" />
</body>
<script>
  function addCMSPage(){
    var pageName = $('#pageName').val();
    var pageKey = $('#pageKey').val();

    var find = ' ';
    var replace = new RegExp(find, 'g');
    pageKey = pageKey.replace(replace, '');
    pageKey = pageKey.toLowerCase();
    $('#pageKey').val(pageKey);
    var pageContent = CKEDITOR.instances.editor1.getData();;
    $.ajax({
      url: BASEURL+'/api-admin/cms/add-page',
      type: 'POST',
      data:{
        pageName :pageName,
        pageKey : pageKey,
        pageContent : pageContent
      },
      success: function(data){
        if(data.responseStat.status == true){
          $('.alert-success').html(data.responseStat.msg);
          $('.alert-success').show().fadeIn(500).delay(2000).fadeOut(500, function () {
            window.location.href =BASEURL+"/admin/cms/get-all";
          });
        }
        else{
          BindErrorsWithHtml("errorMsg_", data.responseStat.requestErrors);
        }
      }
    });
    return false;
  }
</script>
</html>

