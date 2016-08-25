<!DOCTYPE html>
<html lang="en">
<jsp:directive.include file="../layouts/header.jsp" />
    <body class="ux">
        <!--top Nav Bar-->
        <jsp:directive.include file="../layouts/top-nav.jsp" />
        <!--mid navbar-->
        <jsp:directive.include file="../layouts/mid-nav.jsp" />
        <!--main navbar-->
        <jsp:directive.include file="../layouts/main-nav.jsp" />
        <!--end main Nav-->
        <!--Dashboard-->
        <div class="container user-dash-container">
            <div class="row">
                <jsp:directive.include file="../layouts/userDashboardLeftMemu.jsp" />
                <div class="col-md-9 side-container">
                    <h3>Edit profile</h3>
                    <div class="col-md-3 profile-pic">
                        <div class="profile-pic-review">

                        </div>
                        <div class="form-group" style="margin-bottom:0px;">
                            <label for="terms">Upload photo</label>
                            <div id="fallback" class="fallback pos-relative" >
                                Choose a profile picture
                                <span class="inner-load"></span>
                            </div>
                        </div>
                    </div>
                    <form class="form-signup clearfix form-edit col-md-9">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="firstname">First name</label>
                                <input type="text" class="form-control" placeholder="ex.John">
                            </div>
                            <div class="form-group">
                                <label for="lastname">Last name</label>
                                <input type="text" class="form-control" placeholder="ex.Wick">
                            </div>
                            <div class="form-group">
                                <label for="dateofbirth">Email</label>
                                <input type="email" class="form-control" placeholder="ex.email@email.com">
                            </div>
                            <div class="form-group">
                                <label for="address">Password</label>
                                <input type="password" class="form-control" placeholder="ex.password">
                            </div>
                            <div class="form-group">
                                <label>Identity Type</label>
                                <select class="selectpicker">
                                    <d:forEach var="identity" items="${identityTypes}">
                                    <option>${identity.name}</option>
                                    </d:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-12 text-right">
                            <button class="btn-cstm-sign  pos-relative" style="margin-right:0px;">
                                Edit Profile
                                <span class="inner-load"></span>
                            </button>
                            <br>
                            <div class="alert alert-success text-center" role="alert">Profile Edit Successfully done</div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!--Dashboard-->
        <jsp:directive.include file="../layouts/footer.jsp" />
        <!-- Javascript framework and plugins end here -->
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
        <script type="text/javascript">
//            $(document).ready(function () {
//                $("#successModal").modal('show');
//            });
        </script>
        <script>
            $(document).ready(function () {
                $('#example1').DataTable();
            });
        </script>
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
                $('.scrollMe .dropdown-menu').scrollyou();

                prettyPrint();
            };
        </script>

    </body>
</html>


