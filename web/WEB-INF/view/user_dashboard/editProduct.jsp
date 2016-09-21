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
    <body class="ux">
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
                                    <d:set var="isSelected" value=""></d:set>
                                    <d:if test="${rentalProduct.productCategories !=null
                                                  && rentalProduct.productCategories.size()>0
                                                  && !rentalProduct.productCategories.get(0).category.isSubcategory }" >

                                        <d:if test="${rentalProduct.productCategories.get(0).category.id == listValue.id}" >
                                            <d:set var="isSelected" value="selected"></d:set>
                                        </d:if>
                                    </d:if>
                                    <option value="${listValue.id}" ${isSelected} >${listValue.name}</option>
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
                            <label>Product Title</label>
                            <input type="text" class="form-control" placeholder="" value="${rentalProduct.getName()}">
                            <p class="help-block error-form" id="errorMsg_name"></p>
                        </div>
                        <div class="form-group">
                            <label>Product Description</label>
                            <textarea class="form-control cstm-desc">${rentalProduct.getDescription()}</textarea>
                            <p class="help-block error-form" id="errorMsg_description"></p>
                        </div>
                        <div class="row clearfix">
                            <div class="col-md-6">
                                <div class="form-group date-con">
                                    <label>From</label>
                                    <input type="text"  class="form-control datepicker" id="dpd1" placeholder="" value="<fmt:formatDate pattern="MM/dd/yyyy" value="${rentalProduct.getAvailableFrom()}" />">
                                    <p class="help-block error-form" id="errorMsg_availableFrom"></p>
                                </div>  
                            </div>
                            <div class="col-md-6">
                                <div class="form-group date-con">
                                    <label>To</label>
                                    <input type="text"  class="form-control datepicker" id="dpd2" placeholder="" value="<fmt:formatDate pattern="MM/dd/yyyy" value="${rentalProduct.getAvailableTill()}" />">
                                    <p class="help-block error-form" id="errorMsg_availableTill"></p>
                                </div>  
                            </div>
                        </div>
                        <div class="form-group">
                            <label>Product Location</label>
                            <input type="text" class="form-control" placeholder="" value="${rentalProduct.getProductLocation().getFormattedAddress()}">
                            <p class="help-block error-form" id="errorMsg_formattedAddress"></p>
                        </div>
                        <div class="row clearfix">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label>Zip Code</label>
                                    <input type="text"  class="form-control" placeholder="" value="${rentalProduct.getProductLocation().getZip()}">
                                    <p class="help-block error-form" id="errorMsg_zip"></p>
                                </div>  
                            </div>
                            <div class="col-md-8">
                                <div class="form-group">
                                    <label>City</label>
                                    <input type="text"  class="form-control" placeholder="" value="${rentalProduct.getProductLocation().getCity()}">
                                    <p class="help-block error-form" id="errorMsg_city"></p>
                                </div>  
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label >Product Current price</label>
                            <input type="text" class="form-control" placeholder="" value="${rentalProduct.getCurrentValue()}">
                            <p class="help-block error-form" id="errorMsg_currentValue"></p>
                        </div>
                        <div class="form-group">
                            <label>Rent price</label>
                            <input type="text" class="form-control" placeholder="" value="${rentalProduct.getRentFee()}">
                            <p class="help-block error-form" id="errorMsg_rentFee"></p>
                        </div>
                        <div class="form-group clearfix main-img-block">
                            <label for="terms">Product main image</label>
                            <div class="profile-pic main-img clearfix">
                                <div class="main-review text-center">
                                    <img src="${BaseUrl}/images/${rentalProduct.getProfileImage().getOriginal().getPath()}" alt="...">
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
                            <label for="terms">Product other images</label>
                            <div class="profile-pic small-img-block clearfix">
                                <div class="main-review small-other clearfix">
                                    <d:forEach items="${rentalProduct.getOtherImages()}" var="otherimages">
                                    <div class="col-md-3 pos-relative">
                                        <img src="${BaseUrl}/images/${otherimages.getOriginal().getPath()}" alt="...">
                                        <span class="img-cross">X</span>
                                    </div>
                                    </d:forEach>
                                </div>
                                <div class="" style="margin-bottom:0px;">
                                    <div id="fallback" class="fallback pos-relative fall-small" >
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
                    <button class="btn-cstm-sign  pos-relative">
                        Post Product
                        <span class="inner-load" hidden></span>
                    </button>
                </div>
            </div>
        </div>
        <input hidden value="${rentalProduct.getProductCategories()}">
        <!-- Contact end here --> 
        <!-- Main container start here -->
        <jsp:directive.include file="../layouts/top-footer.jsp" />
        <jsp:directive.include file="../layouts/footer.jsp" />
        <!-- Javascript framework and plugins start here -->
        <%--<script>--%>
            <%--Dropzone.autoDiscover = false;--%>
            <%--var previewNode = document.querySelector("#template");--%>
            <%--previewNode.id = "";--%>
            <%--var previewTemplate = previewNode.parentNode.innerHTML;--%>
            <%--previewNode.parentNode.removeChild(previewNode);--%>
            <%--var otherImagesTokenArray = [];--%>
            <%--$(function() {--%>
                <%--var productImageFile = $("div#fallback").dropzone(--%>
                        <%--{--%>
                            <%--url: BASEURL+"/fileupload/upload/product-image",--%>
                            <%--paramName: "productImage",--%>
                            <%--maxFilesize: 1,--%>
                            <%--previewTemplate: previewTemplate,--%>
                            <%--thumbnailWidth: 200,--%>
                            <%--thumbnailHeight: 200,--%>
                            <%--maxFiles: 1,--%>
                            <%--acceptedFiles: "image/jpeg,image/png,image/jpg",--%>
                            <%--maxfilesexceeded: function(file) {--%>
                                <%--this.removeAllFiles();--%>
                                <%--this.addFile(file);--%>
                            <%--},--%>
                            <%--uploadprogress:function(file, progress){--%>
                                <%--$('#postProduct').attr("disabled", "disabled");--%>
                                <%--$('.postProductGif').show();--%>
                                <%--$('.fileUploadGif').show();--%>
                            <%--},--%>
                            <%--success:function(file, response){--%>
                                <%--console.log(response);--%>
                                <%--if(response.responseStat.status == true) {--%>
                                    <%--$('.fileUploadGif').hide();--%>
                                    <%--$('#postProduct').removeAttrs("disabled","disabled");--%>
                                    <%--$('.postProductGif').hide();--%>
                                    <%--$('#profileImageToken').val(response.responseData);--%>
                                <%--}--%>
                                <%--else{--%>
                                    <%--BindErrorsWithHtml('errorMsg_', response.requestErrors);--%>
                                <%--}--%>
                            <%--},--%>
                            <%--error:function(file, errorMessage, xhr){--%>
                                <%--$('.fileUploadGif').hide();--%>
                                <%--$('#postProduct').removeAttrs("disabled","disabled");--%>
                                <%--$('.postProductGif').hide();--%>
                            <%--}--%>
                        <%--}--%>
                <%--);--%>
            <%--});--%>

            <%--$(function() {--%>
                <%--var productOtherImageFile = $("div#fallbackOther").dropzone(--%>
                        <%--{--%>
                            <%--url: BASEURL+"/fileupload/upload/product-image",--%>
                            <%--paramName: "productImage",--%>
                            <%--maxFilesize: 1,--%>
                            <%--previewTemplate: previewTemplate,--%>
                            <%--thumbnailWidth: 200,--%>
                            <%--thumbnailHeight: 200,--%>
                            <%--maxFiles: 3,--%>
                            <%--acceptedFiles: "image/jpeg,image/png,image/jpg",--%>
                            <%--maxfilesexceeded: function(file) {--%>
                                <%--this.removeFile(file);--%>
                                <%--$('#otherImageWarning').show().delay(2000).fadeOut(300, function(){--%>
                                <%--});--%>
                            <%--},--%>
                            <%--uploadprogress:function(file, progress){--%>
                                <%--$('#postProduct').attr("disabled", "disabled");--%>
                                <%--$('.postProductGif').show();--%>
                                <%--$('.otherFileUploadGif').show();--%>
                            <%--},--%>
                            <%--success:function(file, response){--%>
                                <%--if(response.responseStat.status == true) {--%>
                                    <%--$('.otherFileUploadGif').hide();--%>
                                    <%--$('#postProduct').removeAttrs("disabled","disabled");--%>
                                    <%--$('.postProductGif').hide();--%>
                                    <%--otherImagesTokenArray.push(response.responseData);--%>
                                    <%--var otherImagesToken = JSON.stringify(otherImagesTokenArray);--%>
                                    <%--$('#otherImagesToken').val(otherImagesToken);--%>
                                <%--}--%>
                                <%--else{--%>
                                    <%--BindErrorsWithHtml('errorMsg_', response.requestErrors);--%>
                                <%--}--%>
                            <%--},--%>
                            <%--error:function(file, errorMessage, xhr){--%>
                                <%--$('.fileUploadGif').hide();--%>
                                <%--$('#postProduct').removeAttrs("disabled","disabled");--%>
                                <%--$('.postProductGif').hide();--%>
                            <%--}--%>
                        <%--}--%>
                <%--);--%>
            <%--});--%>
        <%--</script>--%>
        <script>
            $( document ).ready( function fetchSubcategory(){
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
            });
        </script>
    </body>
</html>


