<jsp:directive.include file="../layouts/error_success_modal.jsp" />
<div class="footer_bottom">
  <div class="container">
    <div class="row">
      <div class="col-md-8 col-sm-12 col-xs-12 col-md-offset-4">
        <div class="bottom_footer_content">
          <div class="copyright">
            <p class="no-margin"> &#169; Copyright 2016 Reneguru24 | All Rights Reserved</p>
          </div>
          <div class="social_link">
            <p class="no-margin"> <span class="social_link_i"><i class="fa fa-twitter"></i></span><span class="social_link_i"><i class="fa fa-facebook"></i></span><span class="social_link_i"><i class="fa fa-youtube"></i></span><span class="social_link_i"><i class="fa fa-google-plus"></i></span><span class="social_link_i"><i class="fa fa-linkedin"></i></span><span class="social_link_i"><i class="fa fa-pinterest"></i></span></p>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<%--Hiden fields for load more--%>
<input id="loadMoreObj" type="hidden" value='{"url":"${loadMoreProductUrl}","limit":0,"offset":1}' />
<script>
  var BASEURL = "${BaseUrl}";
</script>
<script>

  var isLoggedin =${IsLogIn};
  var isUserVerified = "${appUserVerification}";
  if(isUserVerified!=""){
    try{
      isUserVerified = JSON.parse(isUserVerified);
    }catch(ex){
      console.log(ex);
      isUserVerified = false;
    }

  }
  isUserVerified = (typeof isUserVerified == "boolean")?isUserVerified:null;
</script>
<!-- Contact end here -->
<!-- Main container start here -->
<!-- Javascript framework and plugins start here -->

<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"  />" ></script>
<script src="<c:url value="/resources/js/jquery.validate.min.js"  />" ></script>
<script src="<c:url value="/resources/js/modernizr.js"  />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/appear.js"  />" ></script>
<script src="<c:url value="/resources/js/jquery.knob.js"  />" ></script>
<script src="<c:url value="/resources/js/jquery.ccountdown.js"  />" ></script>
<script src="<c:url value="/resources/js/init.js"  />" ></script>
<script src="<c:url value="/resources/js/general.js"  />" ></script>
<script src="<c:url value="/resources/js/jquery.flexslider.js"  />" ></script>
<!-- Theme JavaScript -->
<script src="<c:url value="/resources/js/clean-blog.min.js"  />" ></script>
<script src="<c:url value="/resources/js/owl.carousel.js"  />" ></script>
<script src="<c:url value="/resources/js/jquery.enllax.min.js"/>"></script>
<script src="<c:url value="/resources/js/jquery-ui-1.9.2.custom.min.js"/> " type="text/javascript"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-datepicker.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/cloudzoom.js" />"></script>
<script src="http://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<%--Developer Helpers --%>
<script src="<c:url value="/resources/developer/js/helper/ErrorSuccessModal.js"  />" ></script>
<script src="<c:url value="/resources/developer/js/helper/ErrorMessaging.js" />" ></script>

<%--Developer 3rd party Lib--%>
<%--Doc http://blog.stevenlevithan.com/archives/date-time-format--%>
<script src="<c:url value="/resources/developer/third_party/date.format.js" />" ></script>

<%--Auto Complete --%>
<script src="<c:url value="/resources/auto_complete/jquery.easy-autocomplete.min.js" />" ></script>
<%--<link rel="stylesheet" href="<c:url value="/resources/auto_complete/easy-autocomplete.themes.min.css" />">--%>
<link rel="stylesheet" href="<c:url value="/resources/auto_complete/easy-autocomplete.min.css" />">

<%--Category Navigation Script--%>
<script type="text/javascript" src="<c:url value="/resources/developer/js/nav/categoryNav.js"  />" ></script>

<script>
  function doSearch(event){
    var char = event.which || event.keyCode;
    console.log(char);
    var productStr = $("#searchTxtBox").val();
    console.log(productStr);
    if(char==13 && productStr!=null && productStr != ""){
      console.log("TIME TO GO");
      window.location = BASEURL+"/home?title="+productStr;
    }
  }
  var options = {

    url: function(phrase){
      console.log(phrase);
      var categoryId = $("#dropdownCategorySelect").attr("data-category-id");
      var title = encodeURIComponent(phrase);
      var url = BASEURL+"/api/product/get-product-with-title?limit=8&offset=0&title="+title;
//      if(categoryId!=""){
//        url = BASEURL+"/api/product/get-product-with-category-title?limit=8&offset=0&categoryId="+categoryId+"&title="+title;
//      }
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



  var nowTemp = new Date();
  var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);

  var checkin = $('#dpd1').datepicker({
    onRender: function (date) {
      return date.valueOf() < now.valueOf() ? 'disabled' : '';
    }
  }).on('changeDate', function (ev) {
    if (ev.date.valueOf() > checkout.date.valueOf()) {
      var newDate = new Date(ev.date);
      newDate.setDate(newDate.getDate() + 1);
      checkout.setValue(newDate);
    }
    checkin.hide();
    $('#dpd2')[0].focus();
  }).data('datepicker');
  var checkout = $('#dpd2').datepicker({
    onRender: function (date) {
      return date.valueOf() <= checkin.date.valueOf() ? 'disabled' : '';
    }
  }).on('changeDate', function (ev) {
    checkout.hide();
  }).data('datepicker');
</script>
<script type="text/javascript">
  $('.main_product_slider').carousel();
  $('.owl-carousel').owlCarousel({
    rtl: true,
    loop: true,
    margin: 10,
    nav: true,
    responsive: {
      0: {
        items: 1
      },
      600: {
        items: 3
      },
      1000: {
        items: 5
      }
    }
  });
</script>
<script>
  $(document).ready(function () {
    $('#hot-deals, #main-product').carousel({
      interval: 3000
    });

  });
</script>
<script>

  (function ($) {

    //Plugin activation
    $(window).enllax();

    //            $('#some-id').enllax();

    //            $('selector').enllax({
    //                type: 'background', // 'foreground'
    //                ratio: 0.5,
    //                direction: 'vertical' // 'horizontal'
    //            });

  })(jQuery);
</script>
<script>
  $(window).load(function () {
    // The slider being synced must be initialized first
    $('#carousel').flexslider({
      animation: "slide",
      controlNav: false,
      animationLoop: false,
      slideshow: false,
      itemWidth: 210,
      itemMargin: 5,
      asNavFor: '#slider'
    });

    $('#slider').flexslider({
      animation: "slide",
      controlNav: false,
      animationLoop: false,
      slideshow: false,
      sync: "#carousel"
    });
  });
</script>
<script>
  function signout(){
    $.ajax({
      url: BASEURL+"/api/signout",
      success: function(data){
        window.location.href =BASEURL+"/signin";
      }
    });
  }

  function loadMoreProduct(){
    $("#loadMoreButtonLoader").show();
    var cid=0;
    var title= "";

    var loadMoreObj = getLoadMoreObj();
    loadMoreObj.limit= 8;

//    +"&categoryId="+cid
//    +"&title="+title,
    $.ajax({
      url:  BASEURL+loadMoreObj.url
                    +"&limit="+ loadMoreObj.limit
                    +"&offset="+loadMoreObj.offset,
      type: "GET",
      success: function(data){
        $("#loadMoreButtonLoader").hide();
        //history.pushState({}, null, newUrl);
        if(loadMoreObj.offset==0){
          $("#productListDiv").html(data);
        }else{
          $("#productListDiv").append(data);
        }
        loadMoreObj.offset++;
        setLoadMoreObj(loadMoreObj);


        if(haveNoProduct()){
          $("#loadMoreButtonParent").hide();
        }
        /*Scrolling down*/
//        if(elem != undefined && elem!=null){
//          scrollDownWithAnimation(elem);
//          hideInfo();
//        }
      },
      error:function(e){
        $("#loadMoreButtonLoader").hide();
      }
    });
  }
  function setLoadMoreObj(obj){
    $("#loadMoreObj").val(JSON.stringify(obj))
  }
  function getLoadMoreObj(){
    return JSON.parse($("#loadMoreObj").val());
  }
  function haveNoProduct(){
    var l = $("#productListDiv").find(".developer-no-product-found").length;
    return (l>=1)?true:false;
  }

</script>
<script>
  $(document).ready(function () {
    $('#example1').DataTable({
      "order": [[ 0, "desc" ]]
    });
  });
</script>
<script>
  (function ($) {
    $('.spinner .btn:first-of-type').on('click', function () {
      $('.spinner input').val(parseInt($('.spinner input').val(), 10) + 1);
    });
    $('.spinner .btn:last-of-type').on('click', function () {
      $('.spinner input').val(parseInt($('.spinner input').val(), 10) - 1);
    });
  })(jQuery);
</script>
<script>
  $('#h-slider').slider({
    range: true,
    values: [17, 67]
  });
</script>
<script type="text/javascript">
  window.onload = function () {
    $('.selectpicker').selectpicker();
    $('.rm-mustard').click(function () {
      $('.remove-example').find('[value=Mustard]').remove();
      $('.remove-example').selectpicker('refresh');
    });
    $('.rm-ketchup').click(function () {
      $('.remove-example').find('[value=Ketchup]').remove();
      $('.remove-example').selectpicker('refresh');
    });
    $('.rm-relish').click(function () {
      $('.remove-example').find('[value=Relish]').remove();
      $('.remove-example').selectpicker('refresh');
    });
    $('.ex-disable').click(function () {
      $('.disable-example').prop('disabled', true);
      $('.disable-example').selectpicker('refresh');
    });
    $('.ex-enable').click(function () {
      $('.disable-example').prop('disabled', false);
      $('.disable-example').selectpicker('refresh');
    });

    // scrollYou
//                $('.scrollMe .dropdown-menu').scrollyou();

//                prettyPrint();
  };
</script>