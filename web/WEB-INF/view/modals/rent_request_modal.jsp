<div id="requestRentPopUp" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title center">Rent Form</h4>
      </div>
      <form id="makeRentRequestForm" class="rent_submission_form" action="" method="post" onsubmit="return makeRentRequest()">
        <div class="modal-body">
          <div class="row clearfix">
            <div class="col-md-6">
              <div class="form-group date-con">
                <label>From</label>
                <input type="text" class="form-control datepicker" id="rentRequestFrom" placeholder="">
                <p class="help-block error-form" id="errorMsg_startDate" ></p>
              </div>
            </div>
            <div class="col-md-6">
              <div class="form-group date-con">
                <label>To</label>
                <input type="text" class="form-control datepicker" id="rentRequestTill" placeholder="">
                <p class="help-block error-form" id="errorMsg_endsDate" ></p>
              </div>
            </div>
            <div class="col-md-12">
              <div class="form-group ">
                <label>Remarks</label>
                <textarea id="rentRequestRemarks" type="text" class="form-control "  placeholder="" style="resize: none;"></textarea>
                <p class="help-block error-form" id="errorMsg_remark" ></p>
              </div>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <p class="help-block error-form" id="errorMsg_productId" ></p>
          <p class="help-block error-form" id="serviceResponseMsg" ></p>
          <button id="makeRequestBtn" type="submit" class="btn-submit">Submit</button>
        </div>
      </form>
    </div>
  </div>
</div>