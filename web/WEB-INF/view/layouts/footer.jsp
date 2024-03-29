<jsp:directive.include file="../layouts/error_success_modal.jsp" />
<div class="footer_bottom">
  <div class="container">
    <div class="row">
      <div class="col-md-12 col-sm-12 col-xs-12 ">
        <div class="bottom_footer_content">
          <div class="copyright">
            <p class="no-margin"> &#169; Copyright 2016 Reneguru24 | All Rights Reserved</p>
          </div>
          <div class="social_link" >
            <p class="no-margin"> <span class="social_link_i"><i class="fa fa-paypal" aria-hidden="true"></i><i class="fa fa-cc-amex" aria-hidden="true"></i><i class="fa fa-cc-visa" aria-hidden="true"></i><i class="fa fa-cc-mastercard" aria-hidden="true"></i></p>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<a href="#" class="scrollToTop"><i class="fa fa-angle-up fa-2x"></i></a>
<%--Hiden fields for load more--%>
<input id="loadMoreObj" type="hidden" value='{"url":"${loadMoreProductUrl}","limit":0,"offset":1}' />
<script>
  var BASEURL = "${BaseUrl}";
</script>
<script>

  var isLoggedin =${isLogin};
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
<script src="<c:url value="/resources/js/easyzoom.js" />"></script>
<script src="http://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<%--Developer Helpers --%>
<script src="<c:url value="/resources/developer/js/helper/ErrorSuccessModal.js"  />" ></script>
<script src="<c:url value="/resources/developer/js/helper/ErrorMessaging.js" />" ></script>
<script src="<c:url value="/resources/developer/js/helper/UrlHelper.js" />" ></script>
<%--Developer 3rd party Lib--%>
<%--Doc http://blog.stevenlevithan.com/archives/date-time-format--%>
<script src="<c:url value="/resources/developer/third_party/date.format.js" />" ></script>

<%--Auto Complete --%>
<script src="<c:url value="/resources/auto_complete/jquery.easy-autocomplete.min.js" />" ></script>
<%--<link rel="stylesheet" href="<c:url value="/resources/auto_complete/easy-autocomplete.themes.min.css" />">--%>
<link rel="stylesheet" href="<c:url value="/resources/auto_complete/easy-autocomplete.min.css" />">

<%--Category Navigation Script--%>
<script type="text/javascript" src="<c:url value="/resources/developer/js/nav/categoryNav.js"  />" ></script>
<script type="text/javascript" src="<c:url value="/resources/developer/js/search/searchRentalProduct.js"  />" ></script>

<%-- Utility (Fetch etc) --%>
<script type="text/javascript" src="<c:url value="/resources/developer/js/utility/fetch-dependent-data.js"  />" ></script>

<script>




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
    $("#loadMoreButton").attr("disabled","disabled");
    $("#loadMoreButtonLoader").show();
    var cid=0;
    var title= "";

    var loadMoreObj = getLoadMoreObj();
    loadMoreObj.limit= 12;

//    +"&categoryId="+cid
//    +"&title="+title,
    $.ajax({
      url:  BASEURL+loadMoreObj.url
      +"&limit="+ loadMoreObj.limit
      +"&offset="+loadMoreObj.offset,
      type: "GET",
      success: function(data){
        $("#loadMoreButtonLoader").hide();
        $("#loadMoreButton").removeAttrs("disabled");
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
        $("#loadMoreButton").removeAttrs("disabled");
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
<script>
  $(".chzn-select").chosen({
    create_option: true,
    persistent_create_option: true,
    create_option_text: 'add',
  });
</script>
<script>
  /**
   * https://jqueryui.com/slider/
  * */
  $( function() {
    var distance = 0;
    try{
      var distanceStr =  getUrlParamsVal("radius");
      distance =  (distanceStr!="")?parseInt( distanceStr ):0;
      distance = (isNaN(distance))?0:distance;
      distance = (distance>1200)?1200:distance;
    }catch(ex){
      console.log(ex.message)
      distance = 0;
    }

    $( "#slider-range" ).slider({
      range: "min",
      min: 0,
      max: 1200,
      value:distance,
      create: function( event, ui ) {
        console.log( $( "#radiusDistance" ));
        $( "#radiusDistance" ).val(distance);
      },
      slide: function( event, ui ) {

        $( "#radiusDistance" ).val(ui.value);

      },
      stop: function( event, ui ) {
        $(this).slider( "disable" );
        searchByRange(ui.value);
      }
    });

  } );



</script>
<script type="text/javascript">
  $(document).ready(function(e){
    $('.search-panel .dropdown-menu').find('a').click(function(e) {
      e.preventDefault();
      var param = $(this).attr("href").replace("#","");
      $("#categorySelectedInSearch").val($(this).attr("categoryId"));

      var concept = $(this).text();
      $('.search-panel span#search_concept').text(concept);
      $('.input-group #search_param').val(param);

    });
    $('.search-panel .dropdown-menu').find('a').each(function() {
      var selected = $(this).attr("selected");
      if(selected!== undefined){
        $("#categorySelectedInSearch").val($(this).attr("categoryId"));
        var concept = $(this).text();
        $('.search-panel span#search_concept').text(concept);
        return;
      }


    });
    if($('.search-panel span#search_concept').text()==""){
      $('.search-panel span#search_concept').text("Filter by")
    }

  });
</script>

<script>
  $(document).ready(function(){

    //Check to see if the window is top if not then display button
    $(window).scroll(function(){
      if ($(this).scrollTop() > 100) {
        $('.scrollToTop').fadeIn();
      } else {
        $('.scrollToTop').fadeOut();
      }
    });

    //Click event to scroll to top
    $('.scrollToTop').click(function(){
      $('html, body').animate({scrollTop : 0},800);
      return false;
    });
  });
</script>