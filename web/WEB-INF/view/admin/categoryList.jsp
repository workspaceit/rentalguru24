<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 9/6/16
  Time: 11:35 AM
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
            <div class="box-header">
              <h3 class="box-title">Category And Subcategory Table</h3>
            </div><!-- /.box-header -->
            <div class="box-body">
              <table id="example1" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>Category Name</th>
                  <th>Action</th>
                  <th>Subcategory</th>
                </tr>
                </thead>
                <tbody>
                <d:forEach var="category" items="${category}">
                  <tr>
                    <td>${category.getName()}</td>
                    <th>
                      <button class="btn btn-block btn-info" onclick="location.href='${BaseUrl}/admin/user/edit-category/'+${category.getId()}">Edit</button>
                      <button class="btn btn-block btn-danger" onclick="deleteCategory(${category.getId()})">Delete</button>
                    </th>
                    <td>
                      <d:if test="${category.getSubcategory().size() > 0}">
                        <table id="example2" class="table table-bordered table-striped">
                          <tr>
                            <th>Subcategory Name</th>
                            <th>Action</th>
                          </tr>
                            <d:forEach var="subCategory" items="${category.getSubcategory()}">
                              <tr>
                                <td>${subCategory.getName()}</td>
                                <td>
                                  <button class="btn btn-block btn-info" onclick="location.href='${BaseUrl}/admin/user/edit-sub-category/'+${subCategory.getId()}">Edit</button>
                                  <button class="btn btn-block btn-danger" onclick="deleteSubcategory(${subCategory.getId()})">Delete</button>
                                </td>
                              </tr>
                            </d:forEach>
                          <tr>
                            <th>Subcategory Name</th>
                            <th>Action</th>
                          </tr>
                        </table>
                      </d:if>
                    </td>
                  </tr>
                </d:forEach>
                </tbody>
                <tfoot>
                <tr>
                  <th>Category Name</th>
                  <th>Action</th>
                  <th>Subcategory</th>
                </tr>
                </tfoot>
              </table>
            </div><!-- /.box-body -->
          </div><!-- /.box -->
        </div><!-- /.col -->
      </div><!-- /.row -->
    </section><!-- /.content -->
    <div class="example-modal">
      <div class="modal modal-success">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <%--<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>--%>
              <h4 class="modal-title">Success</h4>
            </div>
            <div class="modal-body success">
            </div>
            <div class="modal-footer">
              <%--<button type="button" class="btn btn-outline pull-left" data-dismiss="modal">Close</button>--%>
            </div>
          </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
      </div><!-- /.modal -->
    </div><!-- /.example-modal -->

    <div class="example-modal">
      <div class="modal modal-danger">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <%--<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>--%>
              <h4 class="modal-title">Error</h4>
            </div>
            <div class="modal-body danger">
            </div>
            <div class="modal-footer">
              <%--<button type="button" class="btn btn-outline pull-left" data-dismiss="modal-danger">Close</button>--%>
            </div>
          </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
      </div><!-- /.modal -->
    </div><!-- /.example-modal -->
  </div><!-- /.content-wrapper -->
  <jsp:directive.include file="layouts/footer.jsp" />
</body>
<script>
  function deleteCategory(categoryId){
    $.ajax({
      url: BASEURL+"/api-admin/category/delete-category",
      type: "POST",
      data:{
        categoryId:categoryId
      },
      success: function(data){
        if(data.responseStat.status == true){
          $('.success').html(data.responseStat.msg);
          $('.modal-success').show().fadeIn(500).delay(2000).fadeOut(500, function (){
              $('.modal-success').modal('hide');
              window.location.href =BASEURL+"/admin/user/category-list";
          });

        }else{
          $('.danger').html(data.responseStat.requestErrors[0].msg);
          $('.modal-danger').show().fadeIn(500).delay(2000).fadeOut(500, function (){
            $('.modal-danger').modal('hide');
          });
        }
      }
    });
  }
  function deleteSubcategory(subCategoryid){
    $.ajax({
      url: BASEURL+"/api-admin/category/delete-subcategory",
      type: "POST",
      data:{
        subCategoryid:subCategoryid
      },
      success: function(data){
        if(data.responseStat.status == true){
          $('.success').html(data.responseStat.msg);
          $('.modal-success').show().fadeIn(500).delay(2000).fadeOut(500, function (){
            $('.modal-success').modal('hide');
            window.location.href =BASEURL+"/admin/user/category-list";
          });

        }else{
          $('.danger').html(data.responseStat.requestErrors[0].msg);
          $('.modal-danger').show().fadeIn(500).delay(2000).fadeOut(500, function (){
            $('.modal-danger').modal('hide');
          });
        }
      }
    });
  }
</script>
</html>

