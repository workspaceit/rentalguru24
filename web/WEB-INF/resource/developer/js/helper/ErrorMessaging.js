/**
 * Created by mi on 8/5/16.
 */
function ErrorMessageAlias() {
    this.params = "";
    this.msg = "";
    this.replacedMsg = "";
}
var ErrorMessageAliasList = [];
function BindErrorsWithHtml(preFix,requestErrors){
    UnBindErrors(preFix);
    for(var i in requestErrors){
        try{
            var params = requestErrors[i].params;
            var msg = requestErrors[i].msg;
            var aliasMessage = getAliasMessage(params,msg);
            if(aliasMessage!=null){
                msg = aliasMessage;
            }
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
function getAliasMessage(params,msg){
    for(var i in ErrorMessageAliasList){
        if(ErrorMessageAliasList[i].params == params && ErrorMessageAliasList[i].msg == msg){
            return ErrorMessageAliasList[i].replacedMsg;
        }

    }
    return null;
}
function setAliasMessage(params,msg,replacedMsg){
    var errorMessageAlias = new ErrorMessageAlias();

    errorMessageAlias.params = params;
    errorMessageAlias.msg = msg;
    errorMessageAlias.replacedMsg = replacedMsg;

    ErrorMessageAliasList.push(errorMessageAlias);
}