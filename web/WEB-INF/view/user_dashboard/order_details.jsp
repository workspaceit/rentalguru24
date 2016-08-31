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
                    <div class="col-md-7 col-sm-7 col-xs-12">
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
                    <div class="col-md-5 col-sm-5 col-xs-12">
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
                                    <tr>
                                        <td>Number of Days</td>
                                        <td></td>
                                        <td>15 days</td>
                                    </tr>
                                    <tr>
                                        <td>Rent/${rentRequest.getRentalProduct().getRentType().getName()}</td>
                                        <td></td>
                                        <td>$${rentRequest.getRentalProduct().getRentFee()}</td>
                                    </tr>
                                    <tr>
                                        <td>Total Rent</td>
                                        <td></td>
                                        <td>$1200</td>                                        
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <th><strong>Total</strong></th>
                                        <th></th>
                                        <th><strong>$1700</strong></th>  
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <h5>Remarks: </h5>
                        <p class="justify no-margin p_des">${rentRequest.getRemark()} </p>
                        <ul class="confirmation_link_ul">
                            <d:if test="${rentRequest.getApprove() == false && rentRequest.getDisapprove() == false}">
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
            
            <%--<div class="other_order_wrap">--%>
                <%--<div class="row">--%>
                    <%--<div class="col-md-12 col-sm-12 col-xs-12">--%>
                        <%--<h3 class="aproval_container_title">Previous Orders of this client</h3>--%>
                    <%--</div>--%>
                    <%--<div class="col-md-12 col-sm-12 col-xs-12">--%>
                        <%--<div id="carousel-example-generic" class="carousel slide carousel-cstm" data-ride="carousel" data-interval='false'>--%>
                        <%--<!-- Indicators -->--%>
                            <%--<ol class="carousel-indicators">--%>
                                <%--<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>--%>
                                <%--<li data-target="#carousel-example-generic" data-slide-to="1"></li>--%>
                                <%--<li data-target="#carousel-example-generic" data-slide-to="2"></li>--%>
                            <%--</ol>--%>
                            <%----%>
                            <%--<!-- Wrapper for slides -->--%>
                            <%--<div class="carousel-inner" role="listbox">--%>
                                <%--<div class="item active">--%>
                                    <%--<div class="row clearfix">--%>
                                        <%--<div class="col-md-3 single-item">--%>
                                            <%--<div class="panel panel-default">--%>
                                                <%--<div class="panel-body">--%>
                                                    <%--<div class="img-single">--%>
                                                        <%--<img src="img/6.jpg" />                           --%>
                                                    <%--</div>--%>
                                                    <%--<div class="block-desc">--%>
                                                        <%--<label>Aenean Ru Bristique</label>--%>
                                                    <%--</div>--%>
                                                <%--</div>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                        <%--<div class="col-md-3 single-item">--%>
                                            <%--<div class="panel panel-default">--%>
                                                <%--<div class="panel-body">--%>
                                                    <%--<div class="img-single">--%>
                                                        <%--<img src="img/6.jpg" />                           --%>
                                                    <%--</div>--%>
                                                    <%--<div class="block-desc">--%>
                                                        <%--<label>Aenean Ru Bristique</label>--%>
                                                    <%--</div>--%>
                                                <%--</div>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                        <%--<div class="col-md-3 single-item">--%>
                                            <%--<div class="panel panel-default">--%>
                                                <%--<div class="panel-body">--%>
                                                    <%--<div class="img-single">--%>
                                                        <%--<img src="img/6.jpg" />                           --%>
                                                    <%--</div>--%>
                                                    <%--<div class="block-desc">--%>
                                                        <%--<label>Aenean Ru Bristique</label>--%>
                                                    <%--</div>--%>
                                                <%--</div>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                        <%--<div class="col-md-3 single-item">--%>
                                            <%--<div class="panel panel-default">--%>
                                                <%--<div class="panel-body">--%>
                                                    <%--<div class="img-single">--%>
                                                        <%--<img src="img/6.jpg" />                           --%>
                                                    <%--</div>--%>
                                                    <%--<div class="block-desc">--%>
                                                        <%--<label>Aenean Ru Bristique</label>--%>
                                                    <%--</div>--%>
                                                <%--</div>--%>
                                            <%--</div>--%>
                                        <%--</div>                                    --%>
                                    <%--</div>--%>
                                <%--</div>                            --%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        </div>
        <jsp:directive.include file="../layouts/top-footer.jsp" />
        <jsp:directive.include file="../layouts/footer.jsp" />
        <script>
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
                        console.log(data);
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
        </script>
    </body>
</html>


