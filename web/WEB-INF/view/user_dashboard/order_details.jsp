<!DOCTYPE html>
<html lang="en">
<jsp:directive.include file="../layouts/header.jsp" />
    <body class="ux">
        <!--top Nav Bar-->

        <jsp:directive.include file="../layouts/top-nav.jsp" />
        <jsp:directive.include file="../layouts/mid-nav.jsp" />

         <div class="parallax-window bg1" data-enllax-ratio="0.7">
            <div class="container title-block">
                <h1>Approve Request</h1>
            </div>
         </div>

        <div class="container rqst_main_content_wrap">
            <div class="user_info_wrap">
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <h3 class="aproval_container_title">Order Information</h3>
                    </div>
                    <div class="col-md-5 col-sm-5 col-xs-12">
                        <div class="row">
                            <div class="col-md-3 col-xs-3 col-xs-12">
                                <div class="product_img_container">

                                    <d:if test="${rentRequest.getRequestedBy().getUserInf().getProfilePicture().getOriginal().getPath() != null}">
                                        <img src="${BaseUrl}/profile-image/${rentRequest.getRequestedBy().getUserInf().getProfilePicture().getOriginal().getPath()}" class=" img-responsive user_img">
                                    </d:if>
                                    <d:if test="${rentRequest.getRequestedBy().getUserInf().getProfilePicture() == null}">
                                        <img src="${BaseUrl}/resources/img/defaultProfileInmage.png" class=" img-responsive user_img">
                                    </d:if>
                                </div>
                            </div>
                            <div class="col-md-9 col-xs-9 col-xs-12">
                                <h3>${rentRequest.getRequestedBy().getUserInf().getFirstName()}, ${rentRequest.getRequestedBy().getUserInf().getLastName()}</h3>

                                <h5 class="social_link_header">Social Links:</h5>
                                <ul class="social_link_client_rqst">
                                    <li><a href=""><i class="fa fa-facebook-square facebook"></i></a></li>
                                    <li><a href=""><i class="fa fa-linkedin-square linkedin"></i></a></li>
                                    <li><a href=""><i class="fa fa-twitter-square twitter"></i></a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <h5 class="rent_client_other_info_head">Other Informations</h5>
                                <div class="all_info">
                                    <div class="info_title">
                                        <p class="no-margin"><strong>Email</strong></p>
                                    </div>
                                    <div class="detail_info">
                                        <p class="justify no-margin">${rentRequest.getRequestedBy().getEmail()}</p>
                                    </div>
                                </div>
                                <div class="all_info">
                                    <div class="info_title">
                                        <p class="no-margin"><strong>Address</strong></p>
                                    </div>
                                    <div class="detail_info">
                                        <p class="justify no-margin">${rentRequest.getRequestedBy().getUserInf().getUserAddress().getAddress()} ${rentRequest.getRequestedBy().getUserInf().getUserAddress().getCity()} ${rentRequest.getRequestedBy().getUserInf().getUserAddress().getState()} ${rentRequest.getRequestedBy().getUserInf().getUserAddress().getZip()}</p>
                                    </div>
                                </div>
                            </div>
                            
                        </div>
                    </div>
                    <div class="col-md-7 col-sm-7 col-xs-12">
                        <h5 class="color invoice_head">Transection Details</h5>
                        <div class="table-responsive">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>Order Info</th>
                                        <th></th>
                                        <th>Order Details</th>  
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Start Date</td>
                                        <td></td>
                                        <td><fmt:formatDate pattern="MMM d, yyyy" value="${rentRequest.getStartDate()}"/></td>
                                    </tr>
                                    <tr>
                                        <td>End Date</td>
                                        <td></td>
                                        <td><fmt:formatDate pattern="MMM d, yyyy" value="${rentRequest.getEndDate()}"/></td>
                                    </tr>
                                    <%--<tr>--%>
                                        <%--<td>Number of Days</td>--%>
                                        <%--<td></td>--%>
                                        <%--<td>15 days</td>--%>
                                    <%--</tr>--%>
                                    <tr>
                                        <td>Rent/${rentRequest.getRentalProduct().getRentType().getName()}</td>
                                        <td></td>
                                        <td>$${rentRequest.getRentalProduct().getRentFee()}</td>
                                    </tr>
                                    <tr>
                                        <td>Product current price</td>
                                        <td></td>
                                        <td>$${rentRequest.rentalProduct.currentValue}</td>
                                    </tr>
                                    <tr>
                                        <td>Total Rent</td>
                                        <td></td>
                                        <td>$${rentRequest.rentFee}</td>
                                    </tr>
                                    <tr>
                                        <td>Deposite amount</td>
                                        <td></td>
                                        <td>$${rentRequest.advanceAmount}</td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <th><strong>Total</strong></th>
                                        <th></th>
                                        <th><strong>$${rentRequest.advanceAmount}</strong></th>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <h5>Remarks: </h5>
                        <p class="justify no-margin p_des">${rentRequest.getRemark()} </p>
                        <ul class="confirmation_link_ul">
                            <d:if test="${rentRequest.rentalProduct.owner.id == appCredential.id}">
                                <d:if test="${rentRequest.getApprove() == false && rentRequest.getDisapprove() == false}">
                                    <d:if test="${rentRequest.isExpired==false && rentRequest.requestCancel==false}">

                                        <li>
                                            <BUTTON onclick="requestDisapprove(${rentRequest.getId()})" class="cancel_btn approval_btn">Disapprove
                                                <span id="disapproveProgressImg" class="inner-load approveGif" hidden></span>
                                            </BUTTON>
                                        </li>
                                        <li>
                                            <BUTTON onclick="requestApprove(${rentRequest.getId()})" class="approve_btn approval_btn">Approve
                                                <span id="approveProgressImg" class="inner-load disapproveGif" hidden></span>
                                            </BUTTON>
                                        </li>
                                    </d:if>
                                </d:if>
                            </d:if>
                        </ul>
                        <ul class="confirmation_link_ul">
                            <d:if test="${rentRequest.getApprove() == true}">
                                <div class="alert alert-success">
                                    <strong>Product Approveed For Rent</strong>.
                                </div>
                            </d:if>
                            <d:if test="${rentRequest.getDisapprove() == true}">
                                <div class="alert alert-danger">
                                    <strong>Product Disapproveed For Rent</strong>.
                                </div>
                            </d:if>
                        </ul>
                        <ul>
                            <d:set var="statusMsg" value="" ></d:set>
                            <d:if test="${rentRequest.requestCancel}">
                                <d:if test="${rentRequest.requestedBy.id == appCredential.id}">
                                    <d:set var="statusMsg" value="You have canceled the rent request" ></d:set>
                                </d:if>

                                <d:if test="${rentRequest.rentalProduct.owner.id == appCredential.id}">
                                    <d:set var="statusMsg" value="Rent request is canceled" ></d:set>
                                </d:if>
                            </d:if>
                            <d:if test="${rentRequest.isExpired}">
                                <d:set var="statusMsg" value="Rent request is expired" ></d:set>
                            </d:if>
                            <d:if test="${statusMsg!=''}">
                                <li>
                                    <div class="alert alert-danger">
                                        <strong>${statusMsg}</strong>
                                    </div>
                                </li>
                            </d:if>

                        </ul>
                        <ul class="confirmation_link_ul">
                            <d:if test="${rentRequest.requestedBy.id == appCredential.id && !rentRequest.isPaymentComplete}">
                                <d:if test="${rentRequest.isExpired==false && rentRequest.requestCancel==false}">
                                    <li>
                                        <BUTTON id="proceedToPaymentBtn" class="approve_btn approval_btn" onclick="proceedToPayment(${rentRequest.id})" >Proceed to payment
                                            <span id="proceedToPaymentProgressIm" class="inner-load approveGif" hidden></span>
                                        </BUTTON>
                                    </li>
                                    <li>
                                        <p id="paymentCreateResponsePar" style="display:none;">
                                            <span id="paymentCreateResponseMsg"></span>&nbsp;
                                            <span><a href="${BaseUrl}/user/dashboard/my-paypal-account-email?r=${BaseUrl}/rent/request/${rentRequest.id}">Click here</a> to add paypal account</span>
                                        </p>
                                    </li>
                                </d:if>
                            </d:if>
                            <d:if test="${rentRequest.requestedBy.id == appCredential.id && rentRequest.isPaymentComplete}">
                                <d:if test="${rentRequest.approve==false && rentRequest.disapprove==false}">
                                    <li>
                                        <div>
                                            <div  class="alert alert-danger">
                                                <strong id="productReceiveConfirmStrong" >Confirmation pending</strong>
                                            </div>
                                        </div>
                                    </li>
                                </d:if>

                            </d:if>
                        </ul>

                        <ul id="productReceiveConfirmationUl" style="display: none;" class="confirmation_link_ul">

                            <d:if test="${rentRequest.rentalProduct.owner.id == appCredential.id}">
                                <li>
                                    <p>Have you received the product ?</p>
                                    <BUTTON id="productReceiveConfirmBtn" class="cancel_btn approval_btn"  >Confirm
                                        <span id="productReceiveConfirmBtnProgressIm" class="inner-load approveGif" hidden></span>
                                    </BUTTON>
                                    <BUTTON id="productReceiveDisputeBtn" class="approve_btn approval_btn">Dispute
                                        <span id="productReceiveDisputeProgressImg" class="inner-load disapproveGif" hidden></span>
                                    </BUTTON>
                                </li>
                                <li>
                                    <p id="productReceiveConfirmationPar" style="display:none;">
                                        <span id="errorMsg_paypalCredential"></span>&nbsp;
                                        <span><a href="${BaseUrl}/user/dashboard/my-paypal-account-email?r=${BaseUrl}/rent/request/${rentRequest.id}">Click here</a> to add paypal account</span>
                                    </p>
                                </li>

                            </d:if>
                        </ul>
                        <ul  class="confirmation_link_ul" >
                            <d:if test="${rentRequest.rentalProduct.owner.id == appCredential.id}">
                                <li>
                                    <BUTTON id="productReturnRequestBtn" class="cancel_btn approval_btn" style="display: none;" >Request to return
                                        <span class="inner-load productReturnRequestGif" hidden></span>
                                    </BUTTON>
                                </li>
                            </d:if>
                            <d:if test="${rentRequest.requestedBy.id == appCredential.id}">
                                <li>
                                    <BUTTON id="productReturnBtn"  class="cancel_btn approval_btn" style="display: none;" >Return product
                                        <span class="inner-load productReturnGif" hidden></span>
                                    </BUTTON>
                                </li>
                            </d:if>
                        </ul>
                        <ul  class="confirmation_link_ul" >
                            <li>
                                <div  id="productReceiveConfirmParentDiv" style="display: none;" >
                                    <p>Product received : </p>
                                    <div  class="alert alert-danger">
                                        <strong id="productReceiveConfirmStrong" >Confirmed</strong>
                                    </div>
                                </div>

                            </li>
                            <li>
                                <div  id="productReceiveDisputeParentDiv" style="display: none;" >
                                    <p>Product received : </p>
                                    <div class="alert alert-danger">
                                        <strong id="productReceiveDisputeStrong"  >Disputed</strong>
                                    </div>
                                </div>
                            </li>
                            <d:if test="${rentRequest.requestedBy.id == appCredential.id}">

                                    <div id="productReturnRequestParentDiv" style="display: none;" class="alert alert-danger">
                                        <strong id="productReturnRequestString" >User have requested to return product </strong>
                                    </div>

                                <li>
                                    <div  id="productReceivePendingParentDiv" style="display: none;" >
                                        <p>Product received : </p>
                                        <div  class="alert alert-danger">
                                            <strong >User have not confirmed yet</strong>
                                        </div>
                                    </div>

                                </li>
                            </d:if>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="alert alert-danger" id="errorMsg_requestId" hidden></div>
            <div class="alert alert-danger" id="approveError" hidden>Something Went Wrong</div>
            <div class="alert alert-danger" id="disApproveError" hidden>Something Went Wrong</div>
            <div class="alert alert-success" id="approveSuccess" hidden>Product Approve</div>
            <div class="alert alert-success" id="disApproveSuccess" hidden>Product Disapprove</div>
            <h3 class="aproval_container_title">Product Information</h3>
            <div class="product_info_wrap">
                <div class="row">
                    <div class="col-md-6 col-sm-6 col-xs-12 ">
                        <div class="product_img_container">
                            <img src="${BaseUrl}/images/${rentRequest.getRentalProduct().getProfileImage().getOriginal().getPath()}" class=" img-responsive">
                        </div>
                    </div>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <div class="product_info_container">
                            
                            <div class="req_information">
                                <h3 class="color p_name ">${rentRequest.getRentalProduct().getName()}</h3>
                                <h5 class=" p_des_head">Description</h5>
                                <p class="justify no-margin p_des">${fn:substring(rentRequest.getRentalProduct().getDescription(), 0, 1500)}<d:if test="${fn:length(rentRequest.getRentalProduct().getDescription())>1500}">....</d:if></p>
                                <ul class="order_time_interval">
                                    <li>From</li>
                                    <li><strong><span class="time_date"><fmt:formatDate pattern="MMM d,yyyy" value="${rentRequest.getRentalProduct().getAvailableFrom()}"/></span></strong></li>
                                    <li>To</li>
                                    <li><strong><span class="time_date"><fmt:formatDate pattern="MMM d,yyyy" value="${rentRequest.getRentalProduct().getAvailableTill()}"/></span></strong></li>
                                </ul>
                            </div>
                        </div>
                        <div class="other_image_rqst_product">
                            <h5 class=" p_des_head no-padding">Other Images</h5>                            
                            <div class=" rqst_product_slider">
                                <div class="owl-carousel">
                                    <d:forEach var="productOtherImages" items="${rentRequest.getRentalProduct().getOtherImages()}">
                                        <div class="item"><img src="${BaseUrl}/images/${productOtherImages.getOriginal().getPath()}"></div>
                                    </d:forEach>
                                </div>
                            </div> 
                        </div>
                    </div>
                </div>
            </div>

            <div role="tabpanel" class="tab-pane active" id="reviews" style="display: none;">
                <h3 class="aproval_container_title">Add Review</h3>
                <p  class="no-review">Your Rating</p>
                <div class="alert alert-success rateSuccess" hidden>Product rated successful</div>
                <form class="review-form" action="" role="form" onsubmit="return setProductRating(${rentRequest.getRentalProduct().getId()});">
                    <div class="row">
                        <div class="col-md-1 col-sm-4 col-xs-4">
                            <p class="review_cond">Bad</p>
                        </div>
                        <div class="col-md-2 col-sm-4 col-xs-4">
                            <fieldset class="rating rent_rating">
                                <input type="radio" id="star5" name="rating" value="5" /><label class = "full" for="star5" title="Awesome - 5 stars"></label>
                                <input type="radio" id="star4" name="rating" value="4" /><label class = "full" for="star4" title="Pretty good - 4 stars"></label>
                                <input type="radio" id="star3" name="rating" value="3" /><label class = "full" for="star3" title="Meh - 3 stars"></label>
                                <input type="radio" id="star2" name="rating" value="2" /><label class = "full" for="star2" title="Kinda bad - 2 stars"></label>
                                <input type="radio" id="star1" name="rating" value="1" /><label class = "full" for="star1" title="Sucks big time - 1 star"></label>
                            </fieldset>
                            <p class="help-block error-form" id="errorMsg_rateValue"></p>
                        </div>
                        <div class="col-md-1 col-sm-4 col-xs-4">
                            <p  class="review_cond">Good</p>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="reviewText">Your Review:</label>
                        <textarea type="text" class="form-control review_form_element" id="reviewText" ></textarea>
                        <p class="help-block error-form" id="errorMsg_reviewText"></p>
                    </div>
                    <div class="row">
                        <div class="col-md-1 col-sm-1 col-xs-12 center">
                            <button type="submit" class="review_submit">Continue</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <jsp:directive.include file="../layouts/top-footer.jsp" />
        <jsp:directive.include file="../layouts/footer.jsp" />
        <input type="hidden" value="${rentRequest.getId()}" id="rentRequestId" />
        <input type="hidden" value="0" id="rentInfId" />
        <script>
            $(document).ready(function(){
                fetchRentInfByRentRequestId($("#rentRequestId").val());
            });
            function requestApprove(requestId){
                $('.approve_btn').attr("disabled", "disabled");
                $('#approveProgressImg').show();
                $.ajax({
                    type: "GET",
                    url: '${BaseUrl}/api/auth/rent/approve-request/'+requestId,
                    success: function(data) {

                        if(data.responseStat.status==true){
                            $('.approve_btn').removeAttrs("disabled", "disabled");
                            $('#approveProgressImg').hide();
                            $("#approveSuccess").fadeIn(500).delay(2000).fadeOut(500,function(){
                                setTimeout(function(){
                                    location.reload();
                                }, 2000);
                            });
                        }else{
                            BindErrorsWithHtml('errorMsg_', data.responseStat.requestErrors);
                            $('.approve_btn').removeAttrs("disabled", "disabled");
                            $('#approveProgressImg').hide();
                            $("#approveError").fadeIn(500).delay(2000).fadeOut(500,function(){
                                setTimeout(function(){
                                    location.reload();
                                });
                            });
                        }
                    },
                    error: function() {
                        alert('Error occured');
                        $('.approve_btn').removeAttrs("disabled", "disabled");
                        $('#approveProgressImg').hide();
                    }
                });
            }
            function requestDisapprove(requestId) {
                $('.cancel_btn').attr("disabled", "disabled");
                $('#disapproveProgressImg').show();
                $.ajax({
                    type: "GET",
                    url: '${BaseUrl}/api/auth/rent/disapprove-request/'+requestId,

                    success: function (data) {
                        if(data.responseStat.status==true){
                            $('.cancel_btn').removeAttrs("disabled", "disabled");
                            $('#disapproveProgressImg').hide();
                            $("#disApproveSuccess").fadeIn(500).delay(2000).fadeOut(500,function(){
                                window.location.href =BASEURL+"/user/dashboard/rentrequest";
                            });

                        }else{
                            BindErrorsWithHtml('errorMsg_', data.responseStat.requestErrors);
                            $('.cancel_btn').removeAttrs("disabled", "disabled");
                            $('#disapproveProgressImg').hide();
                            $("#disApproveError").fadeIn(500).delay(2000).fadeOut(500,function(){
                                window.location.href =BASEURL+"/user/dashboard/rentrequest";
                            });
                        }
                    },
                    error: function () {
                        alert('Error occured');
                        $('.cancel_btn').removeAttrs("disabled", "disabled");
                        $('#disapproveProgressImg').hide();
                    }
                });
            }
            function fetchRentInfByRentRequestId(requestId) {

                $.ajax({
                    type: "GET",
                    url: '${BaseUrl}/api/auth/rent-inf/get-by-rent-request-id/'+requestId,

                    success: function (data) {
                        if(data.responseStat.status==true){
                            var rentInf = data.responseData;

                             if(rentInf.rentalProductReturned != undefined){
                                if(rentInf.rentalProductReturned.confirm==false && rentInf.rentalProductReturned.dispute==false ){
                                    $("#productReceiveConfirmationUl").fadeIn();
                                    $("#productReceivePendingParentDiv").fadeIn(); // Showing that user have not confirm nither dispute
                                    $("#productReceiveConfirmBtn:not(.bound)").addClass('bound').bind("click",function(){
                                        productReceiveConfirm(rentInf.rentalProductReturned.id);
                                    });
                                    $("#productReceiveDisputeBtn:not(.bound)").addClass('bound').bind("click",function(){
                                        productReceiveDispute(rentInf.rentalProductReturned.id);
                                    });
                                }else if(rentInf.rentalProductReturned.confirm){
                                    var rentalProductReturnedHistories = rentInf.rentalProductReturned.rentalProductReturnedHistories;
                                    var latestHistory = rentalProductReturnedHistories[rentalProductReturnedHistories.length - 1];

                                    var createdDate =  dateFormat(new Date(latestHistory.createdDate), "dddd, mmm dS, yyyy, h:MM:ss TT");

                                    $("#productReceiveConfirmStrong").append(" on "+createdDate);
                                    $("#productReceiveConfirmParentDiv").fadeIn();
                                    $("#rentInfId").val(rentInf.id);
                                    $("#reviews").show();

                                }else if(rentInf.rentalProductReturned.dispute){
                                    var rentalProductReturnedHistories = rentInf.rentalProductReturned.rentalProductReturnedHistories;
                                    var latestHistory = rentalProductReturnedHistories[rentalProductReturnedHistories.length - 1];

                                    var createdDate =  dateFormat(new Date(latestHistory.createdDate), "dddd, mmm dS, yyyy, h:MM:ss TT");
                                    $("#productReceiveDisputeStrong").append(" on "+createdDate);
                                    $("#productReceiveDisputeParentDiv").fadeIn();

                                }
                            }

                            if(rentInf.rentalProductReturnRequest != undefined){
                                var createdDate =  dateFormat(new Date(rentInf.rentalProductReturnRequest.createdDate), "dddd, mmm dS, yyyy, h:MM:ss TT");
                                $("#productReturnRequestParentDiv").fadeIn();
                                $("#productReturnRequestString").append(" On "+createdDate).fadeIn();
                            }
                            if(rentInf.rentalProductReturned == undefined ){
                                $("#productReturnRequestBtn").fadeIn().addClass('bound').bind("click",function(){
                                    requestToReturnProduct(rentInf.id);
                                });
                                $("#productReturnBtn").fadeIn().addClass('bound').bind("click",function(){
                                    returnProduct(rentInf.id);
                                });
                            }
                        }else{

                        }
                    },
                    error: function () {
                    }
                });
            }

            /* For Product Owner */
            function requestToReturnProduct(rentalInfId){
                var remarks = "";
                $('.productReturnRequestGif').show();
                $.ajax({
                    type: "POST",
                    url: BASEURL+'/api/auth/return-request/make-request/'+rentalInfId,
                    data:{remarks:remarks},
                    success: function (data) {
                        if(data.responseStat.status == true){
                            $('.productReturnRequestGif').hide();
                            location.reload();
                        }else{

                        }
                    },
                    error: function () {
                        alert('Error occured');
                        $('.productReturnRequestGif').hide();
                    }
                });
            }
            function productReceiveConfirm(rentalProductReturnId){
                $("#productReceiveConfirmationPar").hide();
                var remarks = "";
                $('#productReceiveConfirmBtnProgressIm').show();
                $.ajax({
                    type: "POST",
                    url: BASEURL+'/api/auth/receive-product/confirm-receive/'+rentalProductReturnId,
                    data:{remarks:remarks},
                    success: function (data) {
                        $('#productReceiveConfirmBtnProgressIm').hide();
                        if(data.responseStat.status == true){
                            location.reload();

                        }else{
                            BindErrorsWithHtml("errorMsg_", data.responseStat.requestErrors);
                            if($("#errorMsg_paypalCredential").text()!=""){
                              $("#productReceiveConfirmationPar").show();
                            }
                        }
                    },
                    error: function () {
                        alert('Error occured');
                        $('#productReceiveConfirmBtnProgressIm').hide();
                    }
                });
            }
            function productReceiveDispute(rentalProductReturnId){
                var remarks = "";
                $('#productReceiveDisputeProgressImg').show();
                $.ajax({
                    type: "POST",
                    url: BASEURL+'/api/auth/receive-product/dispute-receive/'+rentalProductReturnId,
                    data:{remarks:remarks},
                    success: function (data) {
                        if(data.responseStat.status == true){
                            $('#productReceiveDisputeProgressImg').hide();
                            location.reload();
                        }else{

                        }
                    },
                    error: function () {
                        alert('Error occured');
                        $('#productReceiveDisputeProgressImg').hide();
                    }
                });
            }
            function returnProduct(rentalInfId){
                var remarks = "";
                $('.productReturnGif').show();
                $.ajax({
                    type: "POST",
                    url: BASEURL+'/api/auth/return-product/confirm-return/'+rentalInfId,
                    data:{remarks:remarks},
                    success: function (data) {
                        if(data.responseStat.status == true){
                            $('.productReturnGif').hide();
                            location.reload();
                        }else{

                        }
                    },
                    error: function () {
                        alert('Error occured');
                        $('.productReturnGif').hide();
                    }
                });
            }
            function proceedToPayment(rentRequestId){
                var remarks = "";
                $('#proceedToPaymentBtn').attr("disabled","disabled");
                $('#proceedToPaymentProgressIm').show();
                $.ajax({
                    type: "GET",
                    url: BASEURL+'/api/auth/rent-payment/create-payment/'+rentRequestId,
                    success: function (data) {
                        if(data.responseStat.status == true){

                            window.location = data.responseData.url;
                        }else{
                            $("#paymentCreateResponseMsg").text(data.responseStat.msg);
                            $("#paymentCreateResponsePar").show();
                        }
                        $('#proceedToPaymentBtn').removeAttrs("disabled");
                        $('#proceedToPaymentProgressIm').hide();
                    },
                    error: function () {
                        alert('Error occured');
                        $('#proceedToPaymentBtn').removeAttrs("disabled");
                        $('.productReturnGif').hide();
                    }
                });
            }

        </script>
        <script>
            function setProductRating(productId){
                var productId = productId;
                var rateValue = $('input[name=rating]:checked').val();
                var reviewText = $("#reviewText").val();
                var rentInfId =  $("#rentInfId").val();
                var rentRequestId = $("#rentRequestId").val();

                $.ajax({
                    type: "POST",
                    url: BASEURL+"/api/auth/product/review",
                    data: {
                        productId: productId,
                        rateValue: rateValue,
                        reviewText: reviewText
                    },
                    success: function(data){
                        console.log(data);
                        if(data.responseStat.status == true){
                            $(".rateSuccess").show().delay(1500).fadeOut(500,function() {
                                location.reload();
                            });
                        }else{
                            BindErrorsWithHtml('errorMsg_', data.responseStat.requestErrors);
                        }
                    }

                });

                return false;
            }
        </script>
    </body>
</html>


