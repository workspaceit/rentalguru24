<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 8/16/16
  Time: 2:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="controller.BaseHttp" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<jsp:directive.include file="../layouts/header.jsp" />
<body class="ux">
<!--top Nav Bar-->
<jsp:directive.include file="../layouts/top-nav.jsp" />
<jsp:directive.include file="../layouts/mid-nav.jsp" />

<div class="parallax-window bg2" data-enllax-ratio="0.7">
  <div class="container title-block">
    <h1>Post your product</h1>
    <p>Post your product to get some good deal</p>
  </div>
</div>


<div class="container center-bg">
  <h5 class="post-heading">Please fill up the following form to add products.Give the exact current price of the product so the customers don't get confused</h5>
  <div class="row clearfix">
    <div class="post-product clearfix">
      <div class="col-md-6">
        <div class="form-group">
          <label>Choose Category</label>
          <select onchange="fetchSubcategory()" id="category" class="selectpicker" >
            <option value="0">PLEASE SELECT A CATEGORY</option>
            <d:forEach var="listValue" items="${category}">
              <option value="${listValue.id}">${listValue.name}</option>
            </d:forEach>
          </select>
          <p class="help-block error-form" id="errorMsg_categoryIds"></p>
        </div>
        <div class="form-group">
          <label>Choose Sub Category</label>
          <%--<select class="selectpicker" id="subCategory">--%>
          <select id="subCategory" class="selectpicker" disabled="disabled" >
            <option value="0">PLEASE SELECT A SUB CATEGORY</option>
            <%--<optgroup id="subcategoryParentLabel" label="" disabled>--%>
            <%--</optgroup>--%>
          </select>
          <p class="help-block error-form" id="errorMsg_categoryIds"></p>
        </div>
        <div class="form-group">
          <label>Choose Rent Type</label>
          <select  id="rentTypeId" class="selectpicker" >
            <option value="0">PLEASE SELECT A RENT TYPE</option>
            <d:forEach var="listValue" items="${rentTypes}">
              <option value="${listValue.id}">${listValue.name}</option>
            </d:forEach>
          </select>
          <p class="help-block error-form" id="errorMsg_rentTypeId"></p>
        </div>
        <div class="form-group">
          <label>Product Title</label>
          <input type="text" class="form-control" placeholder="" id="name" name="name">
          <p class="help-block error-form" id="errorMsg_name"></p>
        </div>
        <div class="form-group">
          <label>Product Description</label>
          <textarea class="form-control cstm-desc" id="description" name="description"></textarea>
          <p class="help-block error-form" id="errorMsg_description"></p>
        </div>
        <div class="row clearfix">
          <div class="col-md-6">
            <div class="form-group date-con">
              <label>From</label>
              <input type="text"  class="form-control datepicker" id="availableFrom" placeholder="" name="availableFrom">
              <p class="help-block error-form" id="errorMsg_availableFrom"></p>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group date-con">
              <label>To</label>
              <input type="text"  class="form-control datepicker" id="availableTill" placeholder="" name="availableTill">
              <p class="help-block error-form" id="errorMsg_availableTill"></p>
            </div>
          </div>
        </div>
        <div class="form-group">
          <label>Product Location</label>
          <input type="text" class="form-control" placeholder="" id="formattedAddress" name="formattedAddress">
          <p class="help-block error-form" id="errorMsg_formattedAddress"></p>
        </div>
        <div class="row clearfix">
          <div class="col-md-4">
            <div class="form-group">
              <label>Zip Code</label>
              <input type="text"  class="form-control" placeholder="" id="zip" name="zip">
              <p class="help-block error-form" id="errorMsg_zip"></p>
            </div>
          </div>
          <div class="col-md-8">
            <div class="form-group">
              <label>City</label>
              <input type="text"  class="form-control" placeholder="" id="city" name="city">
              <p class="help-block error-form" id="errorMsg_city"></p>
            </div>
          </div>
        </div>
      </div>
      <div class="col-md-6">
        <div class="form-group">
          <label >Product Current price</label>
          <input type="number" class="form-control" placeholder="" id="currentValue" name="currentValue" min="1">
          <p class="help-block error-form" id="errorMsg_currentValue"></p>
        </div>
        <div class="form-group">
          <label>Rent price</label>
          <input type="number" class="form-control" placeholder="" id="rentFee" name="rentFee" min="1" >
          <p class="help-block error-form" id="errorMsg_rentFee"></p>
        </div>
        <%--Dropzone for product profile image--%>
        <div class="form-group">
          <label for="fallback">Add product banner images</label>
          <div id="fallback" class="fallback pos-relative">
            Drop files here or click to upload.
            <span class="inner-load fileUploadGif" hidden></span>
          </div>
          <p class="help-block error-form" id="errorMsg_profileImageToken"></p>
          <input type="hidden" value="" id="profileImageToken" name="profileImageToken">
        </div>

        <%--Dropzone For Other Images--%>
        <div class="form-group">
          <label for="fallbackOther">Add product Other images</label>
          <div id="fallbackOther" class="fallback pos-relative">
            Drop files here or click to upload.
            <span class="inner-load otherFileUploadGif" hidden></span>
          </div>
          <p class="help-block error-form" id="errorMsg_"></p>
          <input type="hidden" value="" id="otherImagesToken" name="otherImagesToken">
        </div>
        <div class="alert alert-warning" id="otherImageWarning" hidden>
          You can only upload 3 picture
        </div>
        <%----%>
        <%--<div class="row preview-container">--%>
          <%--<p>Preview Area</p>--%>
          <%--<div class="col-xs-6 col-md-6">--%>
            <%--<a href="#" class="thumbnail">--%>
              <%--<img src="http://placehold.it/200x200" alt="...">--%>
            <%--</a>--%>
          <%--</div>--%>
          <%--<div class="col-xs-6 col-md-6">--%>
            <%--<a href="#" class="thumbnail">--%>
              <%--<img src="http://placehold.it/200x200" alt="...">--%>
            <%--</a>--%>
          <%--</div>--%>
        <%--</div>--%>
      </div>
    </div>
    <div class="col-md-12 text-right" style="padding:0px 100px 20px 0px;">
      <button class="btn-cstm-sign  pos-relative" id="postProductBtn" onclick="postProduct()">
        Post Product
        <span class="inner-load postProductGif" hidden></span>
      </button>
    </div>
  </div>
  <div class="alert alert-success text-center" role="alert" hidden>
    Product Upload Successfully
  </div>
  <div class="alert alert-danger text-center" role="alert" hidden>
    Product Upload Successfully
  </div>
</div>
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
    <%--<div>--%>
    <%--<p class="size" data-dz-size></p>--%>
    <%--<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0">--%>
    <%--<div class="progress-bar progress-bar-success" style="width:0%;" data-dz-uploadprogress></div>--%>
    <%--</div>--%>
    <%--</div>--%>
  </div>
</div>
<%------------------------------------------------------------------------------------%>
<jsp:directive.include file="../layouts/top-footer.jsp" />
<jsp:directive.include file="../layouts/footer.jsp" />

<script>
  var nowTemp = new Date();
  var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);

  var checkin = $('#availableFrom').datepicker({
    format: 'dd/mm/yyyy',
    onRender: function (date) {
      return date.valueOf() < now.valueOf() ? 'disabled' : '';
    }
  }).on('changeDate', function (ev) {
    if (ev.date.valueOf() > checkout.date.valueOf()) {
      var newDate = new Date(ev.date)
      newDate.setDate(newDate.getDate() + 1);
      checkout.setValue(newDate);
    }
    checkin.hide();
    $('#availableTill')[0].focus();
  }).data('datepicker');
  var checkout = $('#availableTill').datepicker({
    format: 'dd/mm/yyyy',
    onRender: function (date) {
      return date.valueOf() <= checkin.date.valueOf() ? 'disabled' : '';
    }
  }).on('changeDate', function (ev) {
    checkout.hide();
  }).data('datepicker');
</script>

<!-- Javascript framework and plugins end here -->
<script type="text/javascript">
  //  $("div#fallback").dropzone({url: "/file/post"});

  $('.main_product_slider').carousel();
  $('.owl-carousel').owlCarousel({
    rtl: true,
    loop: true,
    margin: 10,
    nav: true,
    responsive: {
      0: {
        items: 1
      },
      600: {
        items: 3
      },
      1000: {
        items: 5
      }
    }
  });
</script>
<script>

  (function ($) {

    //Plugin activation
    $(window).enllax();

    //            $('#some-id').enllax();

    //            $('selector').enllax({
    //                type: 'background', // 'foreground'
    //                ratio: 0.5,
    //                direction: 'vertical' // 'horizontal'
    //            });

  })(jQuery);



</script>
<script type="text/javascript">
  window.onload = function () {
    $('.selectpicker').selectpicker();
    $('.rm-mustard').click(function () {
      $('.remove-example').find('[value=Mustard]').remove();
      $('.remove-example').selectpicker('refresh');
    });
    $('.rm-ketchup').click(function () {
      $('.remove-example').find('[value=Ketchup]').remove();
      $('.remove-example').selectpicker('refresh');
    });
    $('.rm-relish').click(function () {
      $('.remove-example').find('[value=Relish]').remove();
      $('.remove-example').selectpicker('refresh');
    });
    $('.ex-disable').click(function () {
      $('.disable-example').prop('disabled', true);
      $('.disable-example').selectpicker('refresh');
    });
    $('.ex-enable').click(function () {
      $('.disable-example').prop('disabled', false);
      $('.disable-example').selectpicker('refresh');
    });

    // scrollYou
    $('.scrollMe .dropdown-menu').scrollyou();

    prettyPrint();
  };
</script>
<script>
  function fetchSubcategory(){
    var categoryId = $("#category option:selected").val();
    if(categoryId==""){
      return;
    }
    $('#subCategory').find('option:not(:first)').remove();
    $('#subCategory').attr("disabled","disabled");
    $.ajax({
      url: BASEURL+'/api/utility/get-subcategory/'+categoryId,
      type: 'GET',
      success:function(data){
        if(data.responseStat.status != false){
          var subCategorySelectBox = document.getElementById("subCategory");
          var subcategoryArray = data.responseData[0].subcategory;


          subcategoryArray.forEach(function(subCategories){

            var option = document.createElement("option");
            option.text = subCategories.name;
            option.value = subCategories.id;

            subCategorySelectBox.add(option, subCategory[1]);
          });
          if(subcategoryArray.length>0){
            $('#subCategory').removeAttr("disabled");
          }

        }
        $('#subCategory').selectpicker('refresh');

      }
    });
  }
</script>
<script>
  Dropzone.autoDiscover = false;
  var previewNode = document.querySelector("#template");
  previewNode.id = "";
  var previewTemplate = previewNode.parentNode.innerHTML;
  previewNode.parentNode.removeChild(previewNode);
  var otherImagesTokenArray = [];
  $(function() {
    var productImageFile = $("div#fallback").dropzone(
            {
              url: BASEURL+"/fileupload/upload/product-image",
              paramName: "productImage",
              maxFilesize: 1,
              previewTemplate: previewTemplate,
              thumbnailWidth: 200,
              thumbnailHeight: 200,
              maxFiles: 1,
              acceptedFiles: "image/jpeg,image/png,image/jpg",
              maxfilesexceeded: function(file) {
                this.removeAllFiles();
                this.addFile(file);
              },
              uploadprogress:function(file, progress){
                $('#postProduct').attr("disabled", "disabled");
                $('.postProductGif').show();
                $('.fileUploadGif').show();
              },
              success:function(file, response){
                console.log(response);
                if(response.responseStat.status == true) {
                  $('.fileUploadGif').hide();
                  $('#postProduct').removeAttrs("disabled","disabled");
                  $('.postProductGif').hide();
                  $('#profileImageToken').val(response.responseData);
                }
                else{
                  BindErrorsWithHtml('errorMsg_', response.requestErrors);
                }
              },
              error:function(file, errorMessage, xhr){
                $('.fileUploadGif').hide();
                $('#postProduct').removeAttrs("disabled","disabled");
                $('.postProductGif').hide();
              }
            }
    );
  });

  $(function() {
    var productOtherImageFile = $("div#fallbackOther").dropzone(
            {
              url: BASEURL+"/fileupload/upload/product-image",
              paramName: "productImage",
              maxFilesize: 1,
              previewTemplate: previewTemplate,
              thumbnailWidth: 200,
              thumbnailHeight: 200,
              maxFiles: 3,
              acceptedFiles: "image/jpeg,image/png,image/jpg",
              maxfilesexceeded: function(file) {
                this.removeFile(file);
                $('#otherImageWarning').show().delay(2000).fadeOut(300, function(){
                });
              },
              uploadprogress:function(file, progress){
                $('#postProduct').attr("disabled", "disabled");
                $('.postProductGif').show();
                $('.otherFileUploadGif').show();
              },
              success:function(file, response){
                if(response.responseStat.status == true) {
                  $('.otherFileUploadGif').hide();
                  $('#postProduct').removeAttrs("disabled","disabled");
                  $('.postProductGif').hide();
                  otherImagesTokenArray.push(response.responseData);
                  var otherImagesToken = JSON.stringify(otherImagesTokenArray);
                  $('#otherImagesToken').val(otherImagesToken);
                }
                else{
                  BindErrorsWithHtml('errorMsg_', response.requestErrors);
                }
              },
              error:function(file, errorMessage, xhr){
                $('.fileUploadGif').hide();
                $('#postProduct').removeAttrs("disabled","disabled");
                $('.postProductGif').hide();
              }
            }
    );
  });

</script>
<script>
  var latitude = null;
  var longitude = null;
  $(document).ready(function(){
    var x = document.getElementById("demo");
    /* Get User current location */
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(function(position) {
        latitude  = position.coords.latitude;
        longitude  = position.coords.longitude;
        console.log(position)
      });
    } else {
      console.log("Geolocation is not supported by this browser.");
    }

  });
  function postProduct(){
    if(!isUserVerified){
      showUserVerificationAlert();
      return;
    }
    $('#postProduct').attr("disabled", "disabled");
    $('.postProductGif').show();
    var categoryId = $('#category option:selected').val();
    var subCategory = $('#subCategory option:selected').val();

    var categoryArray = [];
    if(subCategory == "0"){
      categoryArray.push(parseInt(categoryId));
    }else{
      categoryArray.push(parseInt(subCategory));
    }

    var fromDate = $('#availableFrom').val();
    var tillDate = $('#availableTill').val();

    var name = $('#name').val();
    var description = $('#description').val();
    var profileImageToken = $('#profileImageToken').val();
    var otherImagesToken = $('#otherImagesToken').val();
    var currentValue = $('#currentValue').val();
    var rentFee = $('#rentFee').val();
    var categoryIds= JSON.stringify(categoryArray);
    var availableFrom = fromDate.replace(/\//g,"-");
    var availableTill = tillDate.replace(/\//g,"-");
    var formattedAddress = $('#formattedAddress').val();
    var rentTypeId = $('#rentTypeId option:selected').val();
    var zip = $('#zip').val();
    var city = $('#city').val();

//        console.log(categoryIds, name, description, profileImageToken, currentValue, rentFee, availableFrom, availableTill, formattedAddress, rentTypeId, zip, city)
//        console.log(otherImagesToken);

    $.ajax({
      url: BASEURL+'/api/auth/product/upload',
      type: 'POST',
      data: {
        name: name,
        description:description,
        profileImageToken:profileImageToken,
        otherImagesToken:otherImagesToken,
        currentValue:currentValue,
        rentFee:rentFee,
        availableFrom:availableFrom,
        availableTill:availableTill,
        categoryIds:categoryIds,
        formattedAddress:formattedAddress,
        rentTypeId:rentTypeId,
        zip:zip,
        city:city,
        lat:latitude ,
        lng:longitude
      },
      success: function(data){
        console.log(data);
        $('#postProduct').removeAttrs("disabled");
        if(!data.responseStat.isLogin){
          $('.alert-danger').show().delay(2000).fadeOut(300, function(){
            window.location.href= BASEURL+"/signin";
          });
        }
        if(data.responseStat.status == false){
          BindErrorsWithHtml("errorMsg_", data.responseStat.requestErrors);
        }else{
          $('.alert-success').show().delay(2000).fadeOut(500,function(){
            window.location.href = BASEURL+"/home";
          });
        }
        $('.postProductGif').hide().delay(1998).fadeOut();
      },error:function(){
        alert("Internal server error!! Please try again later.")
      }
    });
  }


  $(document).ready(function(){
    setAliasMessage("categoryIds","Category not in valid format","Please select category");
    setAliasMessage("categoryIds","Category not in valid format","Category not found for id = 0");
    setAliasMessage("currentValue","typeMismatch","Current value required");
    setAliasMessage("categoryIds", "Category not found for id = 0", "Please select category");
    setAliasMessage("rentTypeId", "No rent type found by id  0", "Please select rent type");
  });
</script>
</body>
</html>


