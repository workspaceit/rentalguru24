<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 8/23/16
  Time: 3:37 PM
  To change this template use File | Settings | File Templates.
--%>

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
    <jsp:directive.include file="../layouts/pageHeader.jsp" />
    <!-- Main content -->
    <section class="content">
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
                  <th>First Name</th>
                  <th>Last Name</th>
                  <th>Email Address</th>
                  <%--<th>Identity Document</th>--%>
                  <th>Active/De-active</th>
                  <th>Edit</th>
                  <th>Details</th>
                </tr>
                </thead>
                <tbody>
                <d:forEach var="users" items="${allUsers}">
                <tr>

                  <td>${users.getUserInf().getFirstName()}</td>
                  <td>${users.getUserInf().getLastName()}</td>
                  <td>${users.getEmail()}</td>
                <%--  <td>
                    <a class="btn btn-block" target="_blank" href="${BaseUrl}/identitydoc-file/${users.getUserInf().getIdentityDocUrl()}">
                      <i class="fa fa-download"></i> Download Document
                    </a>
                  </td>--%>
                  <td>
                    <div class="btn-group">
                      <button type="button" class="btn <d:if test="${users.verified == true}">btn-success</d:if> <d:if test="${users.verified == false}">btn-danger</d:if>" id="actionButton_${users.id}"><d:if test="${users.verified == true}">Active</d:if> <d:if test="${users.verified == false}">Deactive</d:if></button>
                      <button type="button" class="btn <d:if test="${users.verified == true}">btn-success</d:if> <d:if test="${users.verified == false}">btn-danger</d:if> dropdown-toggle" data-toggle="dropdown" id="actionButtonDropdown_${users.id}">
                        <span class="caret"></span>
                        <span class="sr-only">Toggle Dropdown</span>
                      </button>
                      <ul class="dropdown-menu" role="menu">
                        <li><a href="javascript:void(0);" onclick="setActiveDeactivAppUsers(${users.id}, 1)">Active</a></li>
                        <li><a href="javascript:void(0);" onclick="setActiveDeactivAppUsers(${users.id}, 0)">Deactive</a></li>
                      </ul>
                    </div>
                  </td>
                  <td>
                    <a type="button" href="${BaseUrl}/admin/user/app-user/edit/${users.id}" class="btn btn-block btn-info">Edit</a>
                  </td>
                  <td>
                    <a type="button" href="${BaseUrl}/admin/user/app-user/details/${users.id}" class="btn btn-block btn-info">Details</a>
                  </td>
                </tr>
                </d:forEach>
                </tbody>
                <tfoot>
                <tr>
                  <th>First Name</th>
                  <th>Last Name</th>
                  <th>Email Address</th>
                 <%-- <th>Identity Document</th>--%>
                  <th>Active/De-active</th>
                  <th>Edit</th>
                  <th>Details</th>
                </tr>
                </tfoot>
              </table>
            </div><!-- /.box-body -->
          </div><!-- /.box -->
        </div><!-- /.col -->
      </div><!-- /.row -->
    </section><!-- /.content -->
  </div><!-- /.content-wrapper -->
    <jsp:directive.include file="../layouts/footer.jsp" />
  <script>
    function setActiveDeactivAppUsers(id, status){
      $("#actionButton_"+id).attr("disabled","disabled");
      $("#actionButtonDropdown_"+id).attr("disabled","disabled");
      var appUserId = id;
      var varified = status;
//      console.log(appUserId, varified);
      $.ajax({
        url: BASEURL+"/api-admin/app-user/active-app-user/"+appUserId+"/"+varified,
        type: 'POST',
        data:{
          appUserId: appUserId,
          varified: varified,
        },
        success: function (data){
          console.log(data);
          if(data.responseStat.status == true){
            if(status == 1){
              $("#actionButton_"+id).removeClass("btn-danger")
              $("#actionButtonDropdown_"+id).removeClass("btn-danger")
              $("#actionButton_"+id).addClass("btn-success");
              $("#actionButtonDropdown_"+id).addClass("btn-success");
              $("#actionButton_"+id).html("Active");
            }else{
              $("#actionButton_"+id).removeClass("btn-success")
              $("#actionButtonDropdown_"+id).removeClass("btn-success")
              $("#actionButton_"+id).addClass("btn-danger");
              $("#actionButtonDropdown_"+id).addClass("btn-danger");
              $("#actionButton_"+id).html("Deactive");
            }
            $("#actionButton_"+id).removeAttr("disabled","disabled");
            $("#actionButtonDropdown_"+id).removeAttr("disabled","disabled");
            setTimeout(function(){
              window.location = BASEURL+"/admin/user/app-user";
            },1000);
          }else{
            console.log("fail");
          }
        },
        error:function(data){
          console.log(data);
        }
      });
    }
  </script>
</body>
</html>
