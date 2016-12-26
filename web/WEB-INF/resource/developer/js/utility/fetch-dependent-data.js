/**
 * Created by mi on 12/26/16.
 */
function fetchCitiesByStateCode(){
    var stateCode = $("#state option:selected").val();
    if(stateCode==""){
        return;
    }
    $('#city').attr("disabled","disabled");
    $('#city').find('option:not(:first)').remove();
    $.ajax({
        url: BASEURL+'/api/utility/get-city-by-code/'+stateCode,
        type: 'GET',
        success:function(data){
            if(data.responseStat.status != false){
                var citySelectBox = document.getElementById("city");
                var cities = data.responseData;

                var option = document.createElement("option");
                option.text = "All";
                option.value = "";
                citySelectBox.add(option);

                cities.forEach(function(city){
                    option = document.createElement("option");
                    option.text = city.cityName;
                    option.value = city.id;
                    /*if(subCategories.id == subCategoryId){
                     option.selected = "selected";
                     }*/
                    citySelectBox.add(option);
                });
                if(cities.length>0){
                    $('#city').removeAttr("disabled");
                }else{
                    option = document.createElement("option");
                    option.text = "No city found";
                    option.value = 0;
                    option.selected = "selected";
                    citySelectBox.add(option);
                }
            }
            $('#city').selectpicker('refresh');
        }
    });
}
function fetchCitiesByStateId(){
    var stateId = $("#state option:selected").val();
    if(stateId==""){
        return;
    }
    $('#city').attr("disabled","disabled");
    $('#city').find('option:not(:first)').remove();
    $.ajax({
        url: BASEURL+'/api/utility/get-city-by-state-id/'+stateId,
        type: 'GET',
        success:function(data){
            if(data.responseStat.status != false){
                var citySelectBox = document.getElementById("city");
                var cities = data.responseData;
                cities.forEach(function(city){
                    var option = document.createElement("option");
                    option.text = city.cityName;
                    option.value = city.id;
                    /*if(subCategories.id == subCategoryId){
                     option.selected = "selected";
                     }*/
                    citySelectBox.add(option);
                });
                if(cities.length>0){
                    $('#city').removeAttr("disabled");
                }else{
                    var option = document.createElement("option");
                    option.text = "No city found";
                    option.value = 0;
                    option.selected = "selected";
                    citySelectBox.add(option);
                }
            }
            $('#city').selectpicker('refresh');
        }
    });
}