/**
 * Created by mi on 11/29/16.
 */

var options = {

    url: function(phrase){
        console.log(phrase);
        var categoryId = $("#categorySelectedInSearch").val();
        var title = encodeURIComponent(phrase);
        var url = BASEURL+"/api/product/get-product-with-title?limit=8&offset=0&title="+title;
        if(categoryId!=""){
            url = BASEURL+"/api/product/get-product-with-category-title?limit=8&offset=0&categoryId="+categoryId+"&title="+title;
        }
        return url;
    },
//    url: "http://localhost:9090/resources/auto_complete/dummy.json",

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
    var url= "";

    if(categorySelectedInSearch != ""){
        url = BASEURL+"/search?cid="+categorySelectedInSearch+"&title="+productStr+"&radius="+radiusDistance;
    }else{
        url = BASEURL+"/search?title="+productStr+"&radius="+radiusDistance;
    }

    if(latitude!==null && longitude!==null){
        url += "&lat="+latitude+"&lng="+longitude;
    }
    window.location = url;


}
function doSearchByClick(){
    var productStr = $("#searchTxtBox").val();
    var categorySelectedInSearch = $("#categorySelectedInSearch").val();
    if(productStr!=null){
        productStr = productStr.trim();
    }else{
        productStr = "";
    }
    if(productStr != ""){
        console.log("TIME TO GO");
        if(categorySelectedInSearch != ""){
            window.location = BASEURL+"/search?cid="+categorySelectedInSearch+"&title="+productStr;
        }else{
            window.location = BASEURL+"/search?title="+productStr;
        }
    }
}
function doSearch(event){
    var char = event.which || event.keyCode;
    var productStr = $("#searchTxtBox").val();
    var categorySelectedInSearch = $("#categorySelectedInSearch").val();
    if(productStr!=null){
        productStr = productStr.trim();
    }else{
        productStr = "";
    }
    if(char==13 &&  productStr != ""){
        console.log("TIME TO GO");
        if(categorySelectedInSearch != ""){
            window.location = BASEURL+"/search?cid="+categorySelectedInSearch+"&title="+productStr;
        }else{
            window.location = BASEURL+"/search?title="+productStr;
        }
    }
}