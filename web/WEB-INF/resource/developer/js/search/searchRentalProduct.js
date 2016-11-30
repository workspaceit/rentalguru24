/**
 * Created by mi on 11/29/16.
 */

var options = {

    url: function(phrase){
        console.log(phrase);
        var categoryId = $("#categorySelectedInSearch").val();
        var title = encodeURIComponent(phrase);
        var selectedUsState  = getUsState();
        var url = BASEURL+"/api/product/get-product-suggestion";
        var params = [];

        params.push("limit=8");
        params.push("offset=0");

        params.push("title="+title);

        if(categoryId!=""){
            params.push("categoryId="+categoryId);
        }
        if(selectedUsState.id != undefined){
            params.push("stateId="+selectedUsState.id);
        }
        return url+"?"+params.join("&");
    },
    categories: [{
        listLocation: "responseData",
        maxNumberOfElements: 4,
    }],

    getValue: function(element) {

        element.description = element.description.substring(0,20);
        element.name = element.name.substring(0,20);

        if(element.description.length=20){
            element.description+="...";
        }
        if(element.name.length==20){
            element.name+="...";
        }


        return element.name;
    },

    template: {
        type: "description",
        fields: {
            description: "description"
        }
    },

    list: {
        maxNumberOfElements: 4,
        match: {
            enabled: true
        },
        onChooseEvent: function() {
            console.log("sd");
            var product = $("#searchTxtBox").getSelectedItemData();
            console.log(product);
            if(product!=null && typeof product.id != undefined){
                window.location = BASEURL+"/product/details/"+product.id;
            }
        },

    },

    theme: "square"
};


$("#searchTxtBox").easyAutocomplete(options);

function searchByRange(radiusDistance){
    var latitude = null;
    var longitude = null;

    var x = document.getElementById("demo");
    /* Get User current location */
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
                latitude  = position.coords.latitude;
                longitude  = position.coords.longitude;

                searchByLatLng(radiusDistance,latitude,longitude);
                console.log(position);
            },function (error) {
                if (error.code == error.PERMISSION_DENIED){
                    searchByLatLng(radiusDistance,latitude,longitude);
                }
            }
        );
    } else {
        console.log("Geolocation is not supported by this browser.");
    }

}
function searchByLatLng(radiusDistance,latitude,longitude){
    var productStr = $("#searchTxtBox").val();
    var categorySelectedInSearch = $("#categorySelectedInSearch").val();
    var selectedUsState  = getUsState();
    var url= BASEURL+"/search";
    var params = [];

    if(selectedUsState.id != undefined){
        url+="/"+selectedUsState.code.toLowerCase();
    }
    if(categorySelectedInSearch != ""){
        params.push("cid="+categorySelectedInSearch);
    }
    if(productStr!=""){
        params.push("title="+productStr);
    }

    params.push("radius="+radiusDistance);
    if(latitude!==null && longitude!==null){
        params.push("lat="+latitude);
        params.push("lng="+longitude);
    }
    url+=(params.length>0?"?":"")+params.join("&");
    window.location = url;
}
function doSearchByClick(){
    var productStr = $("#searchTxtBox").val();
    var categorySelectedInSearch = $("#categorySelectedInSearch").val();
    var selectedUsState  = getUsState();
    var url= BASEURL+"/search";
    var params = [];

    if(productStr!=null){
        productStr = productStr.trim();
    }else{
        productStr = "";
    }
    if(selectedUsState.id != undefined){
        url+="/"+selectedUsState.code.toLowerCase();
    }
    if(categorySelectedInSearch != ""){
        params.push("cid="+categorySelectedInSearch);
    }
    if(productStr!=""){
        params.push("title="+productStr);
    }
    url+=(params.length>0?"?":"")+params.join("&");
    window.location = url;
}
function doSearch(event){
    var char = event.which || event.keyCode;
    var productStr = $("#searchTxtBox").val();
    var categorySelectedInSearch = $("#categorySelectedInSearch").val();
    var url= BASEURL+"/search";
    var params = [];
    var selectedUsState  = getUsState();

    if(char==13 &&  productStr != ""){
        if(productStr!=null){
            productStr = productStr.trim();
        }else{
            productStr = "";
        }
        if(selectedUsState.id != undefined){
            url+="/"+selectedUsState.code.toLowerCase();
        }
        if(categorySelectedInSearch != ""){
            params.push("cid="+categorySelectedInSearch);
        }
        if(productStr!=""){
            params.push("title="+productStr);
        }
        url+=(params.length>0?"?":"")+params.join("&");
        window.location = url;
    }
}
function selectUsaState(usStateCode,usStateName){
    $("#chooseAreaSpan").text(usStateName);
    $("#areaFilter").modal("hide");
    window.location = BASEURL+"/search/"+usStateCode.toLowerCase();
}
function getUsState(){
    return JSON.parse($("#selectedUsState").val());
}