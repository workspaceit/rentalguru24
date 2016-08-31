<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 8/23/16
  Time: 3:37 PM
  To change this template use File | Settings | File Templates.
--%>

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
        Update Paypal Configuration

      </h1>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-body">
            <form role="form" onsubmit="return updatePaypal();">
              <div class="box-body">


                <div class="form-group">
                  <label >Api Key</label>
                  <input value="${paypalCredientail.apiKey}" name="api_key"  class="form-control" id="api_key" >
                </div>


                <div class="form-group">
                  <label >Api Secret</label>
                  <input value="${paypalCredientail.apiSecret}" name="api_Secret"  class="form-control" id="api_Secret" >
                </div>


              </div>
              <div class="box-footer">
                <button class="btn btn-primary" >Submit</button>
              </div>

              <div class="form-group">
                <label id="errorMessage" style="color: red"></label>
                <label id="alertMsg" style="color: green"></label>
              </div>
              </form>





            </div><!-- /.box-body -->


          </div><!-- /.box -->
        </div><!-- /.col -->
      </div><!-- /.row -->
    </section><!-- /.content -->
  </div><!-- /.content-wrapper -->
  <jsp:directive.include file="layouts/footer.jsp" />
  <script>


    function updatePaypal(){

      var apiKey=$('#api_key').val();
      var apiSecret=$('#api_Secret').val();

      console.log(apiKey,apiSecret);


      var flag=true;
      var message="";

      if(!apiKey.trim()){
        message="Api Key is empty";
        flag=false;

      } else if(!apiSecret.trim()){
        message="Api secret empty";
        flag=false;
      }


      if(flag==false){
        $('#errorMessage').text(message);
        message="";
        return false;
      }else{
        $('#errorMessage').hide();
      }




      $.ajax({
        type: "POST",
        url: '${BaseUrl}/api-admin/admin/update-paypal',
        data: {

          api_key: apiKey,
          api_secret: apiSecret,


        },
        success: function(data) {


          if(data.responseStat.status==true){

            $("#alertMsg").html(data.responseStat.msg).fadeIn(500).delay(2000).fadeOut(500, function () {
              window.location.replace("${BaseUrl}/admin/user/get-utility")
            });

          }else{
            alert(data.responseStat.msg);
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
