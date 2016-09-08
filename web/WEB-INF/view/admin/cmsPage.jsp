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
                <!-- tools box -->
                <%--<div class="pull-right box-tools">--%>
                  <%--<button class="btn btn-info btn-sm" data-widget="collapse" data-toggle="tooltip" title="Collapse"><i class="fa fa-minus"></i></button>--%>
                  <%--<button class="btn btn-info btn-sm" data-widget="remove" data-toggle="tooltip" title="Remove"><i class="fa fa-times"></i></button>--%>
                <%--</div><!-- /. tools -->--%>
              </div><!-- /.box-header -->
              <div class="box-body pad">
                <form>
                  <div class="box-body">
                    <div class="form-group">
                      <label for="pageName" >Page Name</label>
                      <input name="pageName" id="pageName" class="form-control" >
                      <p class="help-block error-form" id="errorMsg_pageName"></p>
                    </div>
                  </div>
                  <div class="box-body">
                    <div class="form-group">
                      <label for="pageKey" >Page Key</label>
                      <input name="pageKey" id="pageKey" class="form-control" >
                      <p class="help-block error-form" id="errorMsg_pageKey"></p>
                    </div>
                  </div>
                  <div class="box-body">
                    <label for="editor1" >Page Content</label>
                  <textarea id="editor1" name="editor1" rows="10" cols="80"></textarea>
                    <p class="help-block error-form" id="errorMsg_editor1"></p>
                  </div>
                  <div class="box-footer">
                    <button class="btn btn-primary" >Submit</button>
                  </div>
                </form>
              </div>
            </div><!-- /.box -->
          </div><!-- /.col-->
        </div><!-- ./row -->
      </section><!-- /.content -->
    </div><!-- /.content-wrapper -->
  <jsp:directive.include file="layouts/footer.jsp" />
</body>
</html>

