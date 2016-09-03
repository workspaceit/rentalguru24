/**
 * Created by mi on 8/23/16.
 */
function showSuccessAndHide(msg){
        msg = (msg.length>50)?msg.substring(0,50)+"...":msg;
        $("#successMsg").html(msg);
        $("#successModal").modal('show');
        window.setTimeout(function(){
                $("#successModal").modal('hide');
        }, 2000);
}

function showErrorAndHide(msg){
        msg = (msg.length>50)?msg.substring(0,50)+"...":msg;
        $("#errorMsg").html(msg);
        $("#errorModal").modal('show');
        window.setTimeout(function(){
                $("#errorModal").modal('hide');
        }, 2000);
}

function showInfoAndHide(msg,delay){
        if(delay == undefined){
                delay = 5000;
        }
        msg = (msg.length>50)?msg.substring(0,50)+"...":msg;
        $("#infoMsg").html(msg);
        $("#infoModal").modal('show');
        window.setTimeout(function(){
                $("#infoModal").modal('hide');
        }, delay);
}
function showInfo(msg){
        if(delay == undefined){
                delay = 5000;
        }
        msg = (msg.length>50)?msg.substring(0,50)+"...":msg;
        $("#infoMsg").html(msg);
        $("#infoModal").modal('show');
}
function hideInfo(){
        $("#infoModal").modal('hide');
}