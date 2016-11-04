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
                  <th>Product title</th>
                  <th>Requested By</th>
                  <th>Product Owner</th>
                  <th>Date</th>
                  <th>Status</th>
                  <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <d:forEach var="rentRequest" items="${rentRequests}">
                <tr>

                  <td>${rentRequest.rentalProduct.name}</td>
                  <td>${rentRequest.requestedBy.userInf.firstName}&nbsp;${rentRequest.requestedBy.userInf.lastName}</td>
                  <td>${rentRequest.rentalProduct.owner.userInf.firstName}&nbsp;${rentRequest.rentalProduct.owner.userInf.lastName}</td>
                  <td>${rentRequest.createdDate}</td>
                  <td>
                    <d:choose>
                      <d:when test="${!rentRequest.approve && !rentRequest.disapprove && !rentRequest.isRentComplete}">Pending</d:when>
                      <d:when test="${rentRequest.approve && !rentRequest.isRentComplete }">On progress</d:when>
                      <d:when test="${rentRequest.isRentComplete}">Complete</d:when>
                    </d:choose>
                  </td>
                  <td><a href="${BaseUrl}/admin/rent-request/details/${rentRequest.id}" class="btn btn-default btn-lg active" role="button">Details</a></td>
                </tr>
                </d:forEach>
                </tbody>
                <tfoot>
                <tr>
                  <th>Product title</th>
                  <th>Requested By</th>
                  <th>Product Owner</th>
                  <th>Date</th>
                  <th>Status</th>
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
