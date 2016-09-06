<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 9/6/16
  Time: 11:01 AM
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
              <form role="form" onsubmit="return addSubCategory();">
                <div class="box-body">
                  <div class="form-group">
                    <label>Category</label>
                    <select class="form-control" id="category">
                      <option value="0">Please Select A Category</option>
                      <d:forEach var="category" items="${category}">
                      <option value="${category.getId()}">${category.getName()}</option>
                      </d:forEach>
                    </select>
                  </div>
                  <div class="form-group">
                    <label for="subCategoryName" >Subcategory Name</label>
                    <input name="subCategoryName" id="subCategoryName" class="form-control" >
                  </div>
                </div><!-- /.box-body -->
                <div class="box-footer">
                  <button class="btn btn-primary" >Submit</button>
                </div>
                <div class="alert alert-success" id="alertSuccess" hidden>Subcategory Add Successful</div>
                <div class="alert alert-danger" id="errorMsg_subCategoryName" hidden></div>
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
    function addSubCategory(){
      var categoryId = $("#category option:selected").val();
      var subCategoryName = $("#subCategoryName").val();
      $.ajax({
        url: BASEURL+'/api-admin/category/add-sub-category',
        type: 'POST',
        data:{
          categoryId:categoryId,
          subCategoryName:subCategoryName
        },
        success: function(data){
          console.log(data);
          if(data.responseStat.status==true){
            $("#alertSuccess").show().fadeIn(500).delay(2000).fadeOut(500, function () {
            });
          }else{
            BindErrorsWithHtml("errorMsg_", data.responseStat.requestErrors);
            $("#errorMsg_"+data.responseStat.requestErrors[0].params).show().fadeIn(500).delay(2000).fadeOut(500, function () {
            });
          }
        }
      });
      return false;
    }
  </script>
</body>
</html>

