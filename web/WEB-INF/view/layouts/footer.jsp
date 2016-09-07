<jsp:directive.include file="../layouts/error_success_modal.jsp" />
<div class="footer_bottom">
  <div class="container">
    <div class="row">
      <div class="col-md-8 col-sm-12 col-xs-12 col-md-offset-4">
        <%--<div class="bottom_footer_content">--%>
          <%--<div class="copyright">--%>
            <%--<p class="no-margin"> &#169; Copyright 2016 Reneguru24 | All Rights Reserved</p>--%>
          <%--</div>--%>
          <%--<div class="social_link">--%>
            <%--<p class="no-margin"> <span class="social_link_i"><i class="fa fa-twitter"></i></span><span class="social_link_i"><i class="fa fa-facebook"></i></span><span class="social_link_i"><i class="fa fa-youtube"></i></span><span class="social_link_i"><i class="fa fa-google-plus"></i></span><span class="social_link_i"><i class="fa fa-linkedin"></i></span><span class="social_link_i"><i class="fa fa-pinterest"></i></span></p>--%>
          <%--</div>--%>
        <%--</div>--%>
      </div>
    </div>
  </div>
</div>
<script>
  var BASEURL = "${BaseUrl}";
</script>
<script>
  var isLoggedin =${IsLogIn};
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
<!-- Javascript framework and plugins end here -->
<script>
  var nowTemp = new Date();
  var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);

  var checkin = $('#dpd1').datepicker({
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
  /*****************  Fetch product by category ***********/
  $("#categoryPageLinkUl").find("li>a").click(function(event){
    var categoryId = $(this).attr("categoryId");
    $(this).bind('click', fetchProductByCategoryAndScrollDown(categoryId,this,event));
  });
  function fetchProductByCategoryAndScrollDown(categoryId,elem,event){
    event.preventDefault();
    if($("#newProductPartialRender").length==0){
      window.location.href =BASEURL+"/home/category/"+categoryId;
      return;
    }
    getProductByCategory(categoryId,elem);
    return false;
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
    var categoryName=$("#selected"+categoryId).data("category-name");
    $("#dropdownCategorySelect").attr("data-category-id",categoryId);
    $("#dropdownCategorySelect").html('<i class="fa fa-bars"></i>'+categoryName+'<span class="caret"></span>');
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