<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 9/20/16
  Time: 12:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<jsp:directive.include file="../layouts/header.jsp" />
<body class="ux" >
<!--top Nav Bar-->
<jsp:directive.include file="../layouts/top-nav.jsp" />
<!--mid navbar-->
<jsp:directive.include file="../layouts/mid-nav.jsp" />
<!--main navbar-->
<jsp:directive.include file="../layouts/main-nav.jsp" />
<!--end main Nav-->
<div class="parallax-window bg2" data-enllax-ratio="0.7">
    <div class="container title-block">
        <h1>Edit your product</h1>
        <%--<p>Post your product to get some good deal</p>--%>
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
                            <option value="${listValue.id}" <d:if test="${listValue.id == categoryId}">selected="selected"</d:if>>${listValue.name}</option>
                        </d:forEach>
                    </select>
                    <p class="help-block error-form" id="errorMsg_categoryIds"></p>
                </div>
                <div class="form-group">
                    <label>Choose Sub Category</label>
                    <%--<select class="selectpicker" id="subCategory">--%>
                    <select id="subCategory" class="selectpicker" disabled="disabled" >
                        <option value="0">PLEASE SELECT A SUB CATEGORY</option>
                    </select>
                    <p class="help-block error-form" id="errorMsg_rentTypeId"></p>
                </div>
                <div class="form-group">
                    <label for="name">Product Title</label>
                    <input type="text" class="form-control" placeholder="" value="${rentalProduct.getName()}" id="name" name="name">
                    <p class="help-block error-form" id="errorMsg_name"></p>
                </div>
                <div class="form-group">
                    <label for="description">Product Description</label>
                    <textarea class="form-control cstm-desc" id="description" name="description">${rentalProduct.getDescription()}</textarea>
                    <p class="help-block error-form" id="errorMsg_description"></p>
                </div>
                <div class="row clearfix">
                    <div class="col-md-6">
                        <div class="form-group date-con">
                            <label for="dpd1">From</label>
                            <input type="text"  class="form-control datepicker" id="dpd1" name="dpd1" placeholder="" value="<fmt:formatDate pattern="MM/dd/yyyy" value="${rentalProduct.getAvailableFrom()}" />">
                            <p class="help-block error-form" id="errorMsg_availableFrom"></p>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group date-con">
                            <label for="dpd2">To</label>
                            <input type="text"  class="form-control datepicker" id="dpd2" name="dpd2" placeholder="" value="<fmt:formatDate pattern="MM/dd/yyyy" value="${rentalProduct.getAvailableTill()}" />">
                            <p class="help-block error-form" id="errorMsg_availableTill"></p>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="formattedAddress">Product Location</label>
                    <input type="text" class="form-control" placeholder="" id="formattedAddress" name="formattedAddress" value="${rentalProduct.getProductLocation().getFormattedAddress()}">
                    <p class="help-block error-form" id="errorMsg_formattedAddress"></p>
                </div>
                <div class="row clearfix">
                    <div class="col-md-4">
                        <div class="form-group">
                            <label for="zip">Zip Code</label>
                            <input type="text"  class="form-control" id="zip" name="zip" value="${rentalProduct.getProductLocation().getZip()}">
                            <p class="help-block error-form" id="errorMsg_zip"></p>
                        </div>
                    </div>
                    <div class="col-md-8">
                        <div class="form-group">
                            <label for="city">City</label>
                            <input type="text"  class="form-control" id="city" name="city" value="${rentalProduct.getProductLocation().getCity()}">
                            <p class="help-block error-form" id="errorMsg_city"></p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label for="currentValue">Product Current price</label>
                    <input type="text" class="form-control" placeholder="" name="currentValue" id="currentValue" value="${rentalProduct.getCurrentValue()}">
                    <p class="help-block error-form" id="errorMsg_currentValue"></p>
                </div>
                <div class="form-group">
                    <label for="rentFee">Rent price</label>
                    <input type="text" class="form-control" id="rentFee" name="rentFee" placeholder="" value="${rentalProduct.getRentFee()}">
                    <p class="help-block error-form" id="errorMsg_rentFee"></p>
                </div>
                <div class="form-group clearfix main-img-block">
                    <label for="fallback">Product main image</label>
                    <div class="profile-pic main-img clearfix">
                        <div class="main-review text-center">
                            <img id="profileImage" src="${BaseUrl}/images/${rentalProduct.getProfileImage().getOriginal().getPath()}" alt="...">
                        </div>
                        <div class="" style="margin-bottom:0px;">
                            <div id="fallback" class="fallback pos-relative fall-small" >
                                Change main Image
                                <!-- <span class="inner-load"></span> -->
                            </div>
                        </div>
                    </div>
                    <p class="help-block error-form" id="errorMsg_profileImageToken"></p>
                </div>
                <div class="form-group clearfix clearfix">
                    <label for="fallbackOther">Product other images</label>
                    <div class="profile-pic small-img-block clearfix">
                        <div class="main-review small-other clearfix" id="productOtherimage">
                            <d:forEach items="${rentalProduct.getOtherImages()}" var="otherimages">
                                <div class="col-md-3 pos-relative">
                                    <img src="${BaseUrl}/images/${otherimages.getOriginal().getPath()}" alt="...">
                                    <span class="img-cross" onclick="deleteOtherImage(${rentalProduct.getId()},'${otherimages.getOriginal().getPath()}')">X</span>
                                </div>
                            </d:forEach>
                        </div>
                        <div class="" style="margin-bottom:0px;">
                            <div id="fallbackOther" class="fallback pos-relative fall-small" >
                                Add Another Image
                                <!-- <span class="inner-load"></span> -->
                            </div>
                        </div>
                    </div>
                    <p class="help-block error-form" id="errorMsg_"></p>
                </div>
            </div>
        </div>
        <div class="col-md-12 text-right" style="padding:0px 100px 20px 0px;">
            <button class="btn-cstm-sign  pos-relative" onclick="postEditProduct()">
                Post Product
                <span class="inner-load" hidden></span>
            </button>
        </div>
    </div>
    <div class="alert alert-success" id="productEditSuccess" hidden>
        Product Edit Success
    </div>
</div>
<%-------------------------------------------------------------------%>
<div class="table table-striped" class="files" id="previews">
    <div id="template" class="file-row">
        <div>
            <span class="preview"><img data-dz-thumbnail /></span>
        </div>
        <div>
            <strong class="error text-danger" data-dz-errormessage></strong>
        </div>
    </div>
</div>
<div class="table table-striped" class="files" id="small-previews">
    <div id="templateSmall" class="file-row">
        <div class="col-md-3 pos-relative">
            <img data-dz-thumbnail />
            <span class="img-cross" data-dz-remove>X</span>
        </div>
    </div>
</div>
<%-------------------------------------------------------------------%>
<input hidden value="${rentalProduct.getProductCategories()}">
<input hidden value="${subCategoryId}" id="selectedSubCategory">
<input type="hidden" value="" id="profileImageToken" name="profileImageToken">
<input type="hidden" value="" id="otherImagesToken" name="otherImagesToken">
<!-- Contact end here -->
<!-- Main container start here -->
<jsp:directive.include file="../layouts/top-footer.jsp" />
<jsp:directive.include file="../layouts/footer.jsp" />
<!-- Javascript framework and plugins start here -->
<script>
    Dropzone.autoDiscover = false;
    var previewNode = document.querySelector("#template");
    var previewSmall = document.querySelector("#templateSmall");
    previewSmall.id = "";
    previewNode.id = "";
    var previewTemplate = previewNode.parentNode.innerHTML;
    var previewTemplateSmall = previewSmall.parentNode.innerHTML;
    previewNode.parentNode.removeChild(previewNode);
    previewSmall.parentNode.removeChild(previewSmall);
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
                    previewsContainer: ".main-review",
                    maxfilesexceeded: function(file) {
                        this.removeAllFiles();
                        this.addFile(file);
                    },
                    uploadprogress:function(file, progress){
                    },
                    success:function(file, response){
                        if(response.responseStat.status == true) {
                            $('#profileImageToken').val(response.responseData);
                            $('#profileImage').remove();
                        }
                        else{
                            BindErrorsWithHtml('errorMsg_', response.requestErrors);
                        }
                    },
                    error:function(file, errorMessage, xhr){
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
                    previewTemplate: previewTemplateSmall,
                    thumbnailWidth: 200,
                    thumbnailHeight: 200,
                    acceptedFiles: "image/jpeg,image/png,image/jpg",
                    previewsContainer: ".small-other",
                    removedfile:function(file){
                        $(file.previewElement).hide();
                        var responce = JSON.parse(file.xhr.response);
                        var otherImageTokenArray = JSON.parse($("#otherImagesToken").val());
                        var removeItem = otherImageTokenArray.indexOf(responce.responseData);
                        if(removeItem != -1) {
                            otherImageTokenArray.splice(removeItem, 1);
                        }
                        var otherImagesToken = JSON.stringify(otherImageTokenArray);
                        $('#otherImagesToken').val(otherImagesToken);
                    },
                    uploadprogress:function(file, progress){
                    },
                    success:function(file, response){
                        if(response.responseStat.status == true) {
                            otherImagesTokenArray.push(response.responseData);
                            var otherImagesToken = JSON.stringify(otherImagesTokenArray);
                            $('#otherImagesToken').val(otherImagesToken);
                        }
                        else{
                            BindErrorsWithHtml('errorMsg_', response.requestErrors);
                        }
                    },
                    error:function(file, errorMessage, xhr){
                    }
                }
        );
    });
</script>
<script>
    $(document).ready(fetchSubcategory);

    function fetchSubcategory(){
        var categoryId = $("#category option:selected").val();
        var subCategoryId = $("#selectedSubCategory").val();
        if(categoryId==""){
            return;
        }
        $('#subCategory').find('option:not(:first)').remove();
        $('#subCategory').attr("disabled","disabled");selectedSubCategory
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
                        if(subCategories.id == subCategoryId){
                            option.selected = "selected";
                        }
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
    function postEditProduct(){
        var category = $("#category option:selected").val();
        var subCategory = $("#subCategory option:selected").val();

        var categorySelected;
        if(subCategory == "0"){
            categorySelected = category;
        }else{
            categorySelected = subCategory;
        }

        var name = $("#name").val();
        var description = $("#description").val();
        var availableFrom = $("#dpd1").val();
        var availableTill = $("#dpd2").val();
        var formattedAddress = $("#formattedAddress").val();
        var zip = $("#zip").val();
        var city = $("#city").val();
        var productCurrentPrice = $("#currentValue").val();
        var rentPrice = $("#rentFee").val();
        var profileImageToken = $("#profileImageToken").val();
        var otherImagesToken = $("#otherImagesToken").val();
        $.ajax({
            type: "POST",
            url: BASEURL+"/api/auth/product/update-Product/"+${rentalProduct.getId()},
            data:{
                categoryId : categorySelected,
                subCategory : subCategory,
                name : name,
                description : description,
                availableFrom : availableFrom,
                availableTill : availableTill,
                formattedAddress : formattedAddress,
                zip : zip,
                city : city,
                productCurrentPrice : productCurrentPrice,
                rentPrice : rentPrice,
                profileImageToken : profileImageToken,
                otherImagesToken : otherImagesToken
            },
            success: function(data){
                if(data.responseStat.status != false){
                    $('#productEditSuccess').show().delay(1500).fadeOut(500,function(){
                        window.location.href = BASEURL+"/user/dashboard/my-products";
                    });
                }else{
                    console.log(data);
                    BindErrorsWithHtml('errorMsg_', data.responseStat.requestErrors);
                }
            },
            error: function(data){
            }
        });
    }
</script>
<script>
    function deleteOtherImage(product_id, path){
        decreaseVal($('#selectedProductOtherImage'));
        $.ajax({
            type: "POST",
            url: BASEURL+"/api/auth/product/delete-product/other-image",
            data:{
                productId : product_id,
                path : path
            },
            success: function(data){
                if(data.responseStat.status == true){
                    $.ajax({
                        type: "GET",
                        url: BASEURL+"/product/other-image/partial-load/"+product_id,
                        success: function(data){
                            $("#productOtherimage").html(data);
                        },
                        error: function(){
                            console.log("ERROR");
                        }
                    });
                    $("#otherImageSuccess").show().delay(1000).fadeOut(500,function(){

                    });
                }else{
                    console.log("ERROR");
                }
            },
            error: function(){
                console.log("ERROR");
            }
        });
    }
    function deleteEditedOtherImage(elem){
//        var otherImageTokenArray = JSON.parse($("#otherImagesToken").val());
        var index = 0;
       $(elem).parents("#productOtherimage").first().children().each(function(){
            if($(this) === elem){
                console.log(index);
           }
           index++;
        });
        console.log(index);
//        console.log(otherImageTokenArray);
    }
</script>
<script>
    function decreaseVal(selector) {
        var $item = selector;
        var $curVal = $item.attr("value");
        $item.attr("value", parseInt($curVal) - 1 );
    }
</script>
</body>
</html>


