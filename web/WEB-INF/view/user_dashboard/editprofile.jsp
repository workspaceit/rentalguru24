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
                            <label for="fallback">Upload photo</label>
                            <div id="fallback" class="fallback pos-relative" >
                                Choose a profile picture
                                <span class="inner-load" hidden id="editProfilePicUploadLoader"></span>
                            </div>
                            <p class="help-block error-form" id="errorMsg_profileImageToken"></p>
                        </div>
                    </div>
                    <form class="form-signup clearfix form-edit col-md-9" onsubmit="return postEditProfile();">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="firstName">First name</label>
                                <input type="text" class="form-control" placeholder="ex.John" id="firstName" name="firstName" value="${appCredential.getUserInf().getFirstName()}">
                                <p class="help-block error-form" id="errorMsg_firstName"></p>
                            </div>
                            <div class="form-group">
                                <label for="lastName">Last name</label>
                                <input type="text" class="form-control" placeholder="ex.Wick" id="lastName" name="lastName" value="${appCredential.getUserInf().getLastName()}">
                                <p class="help-block error-form" id="errorMsg_lastName"></p>
                            </div>
                            <div class="form-group">
                                <label for="email">Email</label>
                                <input type="email" class="form-control" placeholder="ex.email@email.com" id="email" name="email" value="${appCredential.getEmail()}">
                                <p class="help-block error-form" id="errorMsg_email"></p>
                            </div>
                            <div class="form-group">
                                <label for="oldPassword">Old Password</label>
                                <input type="password" class="form-control" placeholder="ex.password" id="oldPassword" name="oldPassword">
                                <p class="help-block error-form" id="errorMsg_oldPassword"></p>
                            </div>
                            <div class="form-group">
                                <label for="newPassword">New Password</label>
                                <input type="password" class="form-control" placeholder="ex.password" id="newPassword" name="newPassword">
                                <p class="help-block error-form" id="errorMsg_newPassword"></p>
                            </div>
                            <%--<div class="form-group">--%>
                                <%--<label>Identity Type</label>--%>
                                <%--<select class="selectpicker">--%>
                                    <%--<option value="0">Please select a identity type</option>--%>
                                    <%--<d:forEach var="identity" items="${identityTypes}">--%>
                                    <%--<option value="${identity.id}">${identity.name}</option>--%>
                                    <%--</d:forEach>--%>
                                <%--</select>--%>
                            <%--</div>--%>
                        </div>
                        <div class="col-md-12 text-right">
                            <button class="btn-cstm-sign  pos-relative" style="margin-right:0px;" id="editProfileButton" >
                                Edit Profile
                                <span class="inner-load" hidden id="editProfileLoder"></span>
                            </button>
                            <br>
                            <div class="alert alert-success text-center" role="alert" hidden >Profile Edit Successfully done</div>
                        </div>
                        <input value="" id="profileImageToken" name="profileImageToken" type="hidden" >
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
//                $('.scrollMe .dropdown-menu').scrollyou();

//                prettyPrint();
            };
        </script>
        <script>
            Dropzone.autoDiscover = false;
            $(function() {
                var profilePictureFile = $("div#fallback").dropzone(
                        {
                            url: BASEURL+"/fileupload/upload/auth/user/profile-image",
                            paramName: "profileImage",
                            maxFilesize: 1,
                            uploadprogress:function(file, progress){
                                $('#editProfileButton').attr("disabled","disabled");
                                $('#editProfileLoder').show();
                                $('#editProfilePicUploadLoader').show();
                            },
                            success:function(file, response){
                                if(response.responseStat.status == true) {
                                    $('#editProfilePicUploadLoader').hide();
                                    $('#editProfileButton').removeAttr("disabled","disabled");
                                    $('#editProfileLoder').hide();
                                    $('#profileImageToken').val(response.responseData);
                                } else{
                                    BindErrorsWithHtml('errorMsg_', response.requestErrors);
                                }
                            },
                            previewsContainer: ".profile-pic-review",
                            error:function(file, errorMessage, xhr){
                                $('#editProfilePicUploadLoader').hide();
                                $('#editProfileButton').removeAttr("disabled","disabled");
                                $('#editProfileLoder').hide();
                            }
                        }
                );
            });
        </script>
        <script>
            function postEditProfile(){
                $('#editProfileLoder').show();
                var firstName = $('#firstName').val();
                var lastName = $('#lastName').val();
                var email = $('#email').val();
                var oldPassword = $('#oldPassword').val();
                var newPassword = $('#newPassword').val();
                var profileImageToken = $('#profileImageToken').val();
//                console.log(firstName, lastName, email, oldPassword, newPassword, profileImageToken);
                $.ajax({
                    url: BASEURL+"/api/auth/profile/edit",
                    type: "POST",
                    data: {
                        firstName: firstName,
                        lastName: lastName,
                        email: email,
                        oldPassword: oldPassword,
                        newPassword: newPassword,
                        profileImageToken: profileImageToken,
                    },
                    success: function(data){
                        console.log(data);
                        if(data.responseStat.status == false){
                            BindErrorsWithHtml("errorMsg_", data.responseStat.requestErrors);
                        }else{
                            $('.alert-success').show().delay(5000).fadeOut(500,function(){
                            });
                        }
                        $('#editProfileLoder').hide();
                    },
                    error: function(data){
                        console.log('fail');
                        $('#editProfileLoder').hide();
                    }
                });
                return false;
            }
        </script>

    </body>
</html>


