/**
 * Created by mi on 8/5/16.
 */
function BindErrorsWithHtml(preFix,requestErrors){
    for(var i in requestErrors){
        try{
            var params = requestErrors[i].params;
            var msg = requestErrors[i].msg;
            $("#"+preFix+params).show();
            $("#"+preFix+params).html(msg);
        }catch(err){
            console.log(err.message);
        }
    }
}
function UnBindErrors(preFix){
    $("[id^="+preFix+"]").each(function(){
        $(this).hide();
        $(this).html("");
    });
}
function HideErrorsSection(preFix){
    $("[id^="+preFix+"]").each(function(){
        $(this).hide();
    });
}
function ShowErrorsSection(preFix){
    $("[id^="+preFix+"]").each(function(){
        $(this).hide();
    });
}