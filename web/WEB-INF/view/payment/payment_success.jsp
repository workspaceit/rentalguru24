<!DOCTYPE html>
<html lang="en">
<jsp:directive.include file="../layouts/header.jsp" />
<body class="ux">
<!--top Nav Bar-->

<jsp:directive.include file="../layouts/top-nav.jsp" />
<jsp:directive.include file="../layouts/mid-nav.jsp" />

<div class="parallax-window bg1" data-enllax-ratio="0.7">
  <div class="container title-block">
    <h1>${payPalStatusMsg}</h1>
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
      <d:set var="siteFees" value="0" ></d:set>
      <d:if test="${adminSitesFees.fixed}">
        <d:set var="siteFees" value="${adminSitesFees.fixedValue}" ></d:set>
      </d:if>
      <d:if test="${adminSitesFees.percentage}">
        <d:set var="siteFees" value="${adminSitesFees.percentageValue}" ></d:set>
      </d:if>
      <div class="col-md-5 col-sm-5 col-xs-12">
        <h5 class="color invoice_head">Transaction Details</h5>
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
              <td>Status</td>
              <td></td>
              <td>${payPalStatusMsg}</td>
            </tr>
            <tr>
              <td>Start Date</td>
              <td></td>
              <td><fmt:formatDate pattern="MMM d, yyyy" value="${rentRequest.startDate}"/></td>
            </tr>
            <tr>
              <td>End Date</td>
              <td></td>
              <td><fmt:formatDate pattern="MMM d, yyyy" value="${rentRequest.endDate}"/></td>
            </tr>
            <tr>
              <td>Rent/${rentRequest.rentalProduct.rentType.name}</td>
              <td></td>
              <td>$${rentRequest.rentalProduct.rentFee}</td>
            </tr>
            <tr>
              <td>Site Fees</td>
              <td></td>
              <td>$${siteFees}</td>
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
              <td>Deposit amount</td>
              <td></td>
              <td>$${rentRequest.advanceAmount}</td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
              <th><strong>Total</strong></th>
              <th></th>
              <th><strong>$${rentRequest.advanceAmount+siteFees}</strong></th>
            </tr>
            </tfoot>
          </table>
        </div>
        <h5>Remarks: </h5>
        <p class="justify no-margin p_des">${rentRequest.getRemark()} </p>
        <ul class="confirmation_link_ul">
          <d:if test="${rentRequest.rentalProduct.id == appCredential.id}">
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
            </d:if>
          </d:if>
        </ul>

        <ul id="productReceiveConfirmationUl" style="display: none;" class="confirmation_link_ul">

          <d:if test="${rentRequest.rentalProduct.owner.id == appCredential.id}">
            <li>
              <p>Have you receive the product ?</p>
              <BUTTON id="productReceiveConfirmBtn" class="cancel_btn approval_btn"  >Confirm
                <span id="productReceiveConfirmBtnProgressIm" class="inner-load approveGif" hidden></span>
              </BUTTON>
              <BUTTON id="productReceiveDisputeBtn" class="approve_btn approval_btn"  >Dispute
                <span id="productReceiveDisputeProgressImg" class="inner-load disapproveGif" hidden></span>
              </BUTTON>
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

</div>
<jsp:directive.include file="../layouts/top-footer.jsp" />
<jsp:directive.include file="../layouts/footer.jsp" />
<input type="hidden" value="${rentRequest.getId()}" id="rentRequestId" />

</body>
</html>




