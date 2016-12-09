/**
 * Created by mi on 10/13/16.
 */
$(function() {
    $('.drop-div').css("width","180");
    $('.mega-list').css("width","100%");
    $('.drop-div .mega-list li').hover(function() {

        var categoryId = $(this).find('a').first().attr("categoryId");
        console.log( categoryId);
        $('.drop-div').css("width","600");
        $('.mega-list').css("width","30%");
        $('.sub-list').hide();
        $('.sub-list#subCategoryOf_'+categoryId).show();
    });
});


/*****************  Fetch product by category ***********/
$("#categoryPageLinkUl,.sub-list,#categoryMobileSelectBox, #areaPageLinkUl").find(".developerCategoryAnchore").click(function(event){
    var categoryId = $(this).attr("categoryId");
    //$(this).bind('click', fetchProductByCategoryAndScrollDown(categoryId,this,event));
    $(this).bind('click', redirectTocategoryPage(categoryId));
});
$("#categoryMobileSelectBox").change(function(event){
    var categoryId = $(this).val();
    redirectTocategoryPage(categoryId);
});

function fetchProductByCategoryAndScrollDown(categoryId,elem,event){
    event.preventDefault();
    $("#productBlockHead").html($("#categoryAnchor_"+categoryId).html());
    if($("#newProductPartialRender").length==0){
        window.location.href =BASEURL+"/home/category/"+categoryId;
        return;
    }
    getProductByCategory(categoryId,elem);
    return false;
}
function redirectTocategoryPage(categoryId){
    window.location.href =BASEURL+"/home/category/"+categoryId;
}
function getProductByCategory(categoryId,elem){
    if(showInfo != undefined){
        showInfo("Loading...")
    }
    var newUrl = BASEURL+"/home/category/"+categoryId;
    $.ajax({
        url: BASEURL+"/home/partial-rendering/category/"+categoryId,
        type: "GET",
        success: function(data){
            history.pushState({}, null, newUrl);
            $("#newProductPartialRender").html(data);

            /*Scrolling down*/
            if(elem != undefined && elem!=null){
                scrollDownWithAnimation(elem);
                hideInfo();
            }

            if(haveNoProduct()){
                $("#loadMoreButtonParent").hide();
            }else{
                $("#loadMoreButtonParent").show();
            }

            var loadMoreObj = getLoadMoreObj();
            loadMoreObj.url= "/home/partial-rendering/load/more/rental-product?categoryId="+categoryId;
            loadMoreObj.offset=1;
            loadMoreObj.limit=8;
            setLoadMoreObj(loadMoreObj);
        }
    });
}
function scrollDownWithAnimation(elem){
    var target = $(elem.hash);
    if (target.length == 0) target = $('a[name="' + elem.hash.substr(1) + '"]');
    if (target.length == 0) target = $('html');
    $('html, body').animate({ scrollTop: target.offset().top }, 700);
}
function selectedCategory(categoryId){
    var categoryName=$("#categoryAnchor_"+categoryId).data("category-name");
    $("#dropdownCategorySelect").attr("data-category-id",categoryId);
    $("#dropdownCategorySelect").html('<i class="fa fa-bars"></i>'+categoryName+'<span class="caret"></span>');
}