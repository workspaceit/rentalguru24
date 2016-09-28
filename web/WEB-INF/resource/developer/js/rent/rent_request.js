/**
 * Created by mi on 9/28/16.
 */

function showRentRequestPopUp(productId){
    if(isLoggedin){
        if(!isUserVerified){
            showUserVerificationAlert();
            return;
        }
        setCurrentSelectedRentalProductId(productId);
        $("#rentRequestFrom").val("");
        $("#rentRequestTill").val("");
        $("#rentRequestRemarks").val("");
        UnBindErrors('errorMsg_');
        $("#requestRentPopUp").modal("show");
    }else{
        showInfoAndHide("Please sign to rent product");
    }
}
function makeRentRequest(){
    UnBindErrors('errorMsg_');
    $("#serviceResponseMsg").html("Processing....");
    var makeRentRequestformElement = $("#makeRentRequestForm").find("input,textarea,button");
    $(makeRentRequestformElement).attr("disabled","disabled");

    var productId = getCurrentSelectedRentalProductId();

    var startDate = $("#rentRequestFrom").val();
    var endsDate = $("#rentRequestTill").val();
    var remark = $("#rentRequestRemarks").val();

    startDate = getDateInFormat(startDate);
    endsDate = getDateInFormat(endsDate);

    if(productId==null || productId==""){
        return false;
    }

    $.ajax({
        url: BASEURL+'/api/auth/rent/make-request/'+productId,
        type: 'POST',
        data: {
            startDate:startDate,
            endsDate:endsDate,
            remark:remark
        },
        success: function(data){
            if(data.responseStat.status == true){
                /* Hide Rent Request Modal Form */
                $("#requestRentPopUp").modal("hide");
                showSuccessAndHide(data.responseStat.msg,5000);
                window.setTimeout(function(){
                    showSuccessAndHide("Redirecting to order details ...",3000);
                },2000);
                window.setTimeout(function(){
                    console.log(BASEURL+"/rent/request/"+data.responseData.id);
                    var locationUrl = BASEURL+"/rent/request/"+data.responseData.id;
                    window.location = locationUrl;
                },5000);
            }else{
                $("#serviceResponseMsg").html(data.responseStat.msg);
                BindErrorsWithHtml('errorMsg_', data.responseStat.requestErrors);
            }
            $(makeRentRequestformElement).removeAttrs("disabled");
        },
        error: function(e){
            $(makeRentRequestformElement).removeAttrs("disabled");
        }
    });
    return false;
//
}
var nowTemp = new Date();
var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);

var checkin = $('#rentRequestFrom').datepicker({
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
    $('#rentRequestTill')[0].focus();
}).data('datepicker');

var checkout = $('#rentRequestTill').datepicker({
    onRender: function (date) {
        return date.valueOf() <= checkin.date.valueOf() ? 'disabled' : '';
    }
}).on('changeDate', function (ev) {
    checkout.hide();
}).data('datepicker');


function getDateInFormat(datesArray,format){

    if(typeof datesArray == "string"){
        try{
            datesArray = (datesArray!=null || datesArray=="")?datesArray.split("/"):[];
        }catch(ex){
            console.log(ex);
            return "";
        }
    }

    if(format == undefined){
        format = "";
    }
    if(datesArray.length!=3){
        return "";
    }

    var day = datesArray[1];
    var month = datesArray[0];
    var year = datesArray[2];

    return day+"-"+month+"-"+year;
}
function getCurrentSelectedRentalProductId(){
    try{
        return parseInt( localStorage.getItem("productId"));
    }catch(ex){
        console.log(ex);
        return 0;
    }

}
function setCurrentSelectedRentalProductId(productId){
    localStorage.setItem("productId",productId)
}