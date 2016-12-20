<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d" %>
<!DOCTYPE html>
<html lang="en">
<jsp:directive.include file="../layouts/header.jsp" />
<body class="ux">
<div class="container-fluid top_nav">
    <div class="row">
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-sm-6 col-xs-12 support-div">
                    <a href="#" class="support-a"><i class="fa fa-envelope" aria-hidden="true">   support@rentguru24.com</i></a>
                    <%--<ul class="top_nav_ul">--%>
                    <%--<li class="dropdown">--%>
                    <%--<a href="#" class="dropdown-toggle top_nav_a" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">English <span class="caret"></span></a>--%>
                    <%--<ul class="dropdown-menu">--%>
                    <%--<li><a href="#">Action</a></li>--%>
                    <%--</ul>--%>
                    <%--</li>--%>
                    <%--<li class="dropdown">--%>
                    <%--<a href="#" class="dropdown-toggle top_nav_a" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Help <span class="caret"></span></a>--%>
                    <%--<ul class="dropdown-menu">--%>
                    <%--<li><a href="#">Action</a></li>--%>
                    <%--</ul>--%>
                    <%--</li>--%>
                    <%--</ul>--%>
                </div>
                <div class="col-md-6 col-sm-6 col-xs-7 ">
                    <ul class="top_nav_ul right top_nav_ul">
                        <li class="dropdown">
                            <a href="${BaseUrl}/signin" class="dropdown-toggle top_nav_a"
                               aria-expanded="false">
                                <i class="fa fa-user"></i>Login
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<!--top Nav Bar-->
<div class="container-fluid mid_nav">
    <div class="row">
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-sm-6 col-xs-6">
                    <ul class="mid_nav_logo_ul">
                        <li class=>
                            <a href="${BaseUrl}/home"><img src="<c:url value="/resources/img/logo.gif" />" ></a>
                        </li>
                    </ul>
                </div>

                <%--<div class="col-md-6 col-sm-6 col-xs-6 ">--%>
                    <%--<label class="right mid_nav_contact"><i class="fa fa-mobile fa-2x contact_icon"></i><span class="conatct_number">2300-3560-222</span></label>--%>
                <%--</div>--%>
            </div>
        </div>
    </div>
</div>


<div class="parallax-window bg1" data-enllax-ratio="0.7">
    <div class="container title-block">
        <h1>Sign up</h1>
        <p>Sign up for the great Deals. Explore More</p>
    </div>
</div>


<div class="container center-bg">
    <form class="form-signup clearfix" onsubmit="return submitSignUpData();" >
        <div class="col-md-8 col-md-offset-2">
            <div class="form-group">
                <label for="firstname">First name</label>
                <input type="text" class="form-control" placeholder="ex.John" id="firstName" name="firstName">
                <p class="help-block error-form" id="errorMsg_firstName" for="firstName" custom-validation="required"></p>
            </div>
            <div class="form-group">
                <label for="lastname">Last name</label>
                <input type="text" class="form-control" placeholder="ex.Wick" id="lastName" name="lastName">
                <p class="help-block error-form" id="errorMsg_lastName" for="lastName" custom-validation="required" ></p>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" placeholder="ex.email@email.com" id="email" name="email" onblur="isEmailExist()">
                <p class="help-block error-form" id="errorMsg_email" for="email" custom-validation="required" ></p>
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" placeholder="ex.password" id="password" name="password" >
                <p class="help-block error-form" id="errorMsg_password" for="password" custom-validation="required" ></p>
            </div>
            <div class="form-group">
                <label for="conPassword">Confirm password</label>
                <input type="password" class="form-control" placeholder="ex.password" id="conPassword" name="conPassword" >
                <p class="help-block error-form" id="errorMsg_confirmPassword" for="conPassword" custom-validation="required" ></p>
            </div>
            <%--<div class="form-group ">--%>
                <%--<label for="identityTypeId">Identity Type</label>--%>
                <%--&lt;%&ndash;<input type="text" class="form-control" placeholder="Identity Type" id="" name="">&ndash;%&gt;--%>
                <%--<select id="identityTypeId" name="identityTypeId" class="selectpicker">--%>
                    <%--<option value="">Please select a identity type</option>--%>
                <%--</select>--%>
                <%--<p class="help-block error-form" id="errorMsg_identityTypeId" for="identityTypeId" custom-validation="required" errorMsg="Please select identity type" ></p>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<label for="fallback">Identity Document</label>--%>
                <%--<div id="fallback"  class="fallback pos-relative" >--%>
                    <%--Drop files here or click to upload.--%>
                    <%--<span class="inner-load fileUploadGif" hidden></span>--%>
                <%--</div>--%>

                <%--&lt;%&ndash;<input type="file" name="documentIdentity">&ndash;%&gt;--%>
                <%--<p class="help-info" id="nameFileAttached"></p>--%>
                <%--<p class="help-block error-form" id="errorMsg_identityDocToken"></p>--%>
                <%--<p class="help-block error-form" id="errorMsg_documentIdentity" for="identityDocToken" custom-validation="required" errorMsg="Please upload identity document" ></p>--%>

            <%--</div>--%>
        </div>
        <div class="col-md-5 social-sign" style="margin: 0px auto; float: none;">
            <jsp:directive.include file="SocialLogin.jsp" />
        </div>
        <div class="col-md-12 text-center">
            <button class="btn-cstm-sign pos-relative" id="signUpButton">
                Sign up
                <span class="inner-load signUpGif" hidden></span>
            </button>
            <p style="margin: 10px 0"><a href="${BaseUrl}/signin">Already have an account?</a></p>
            <div class="alert alert-success text-center" role="alert" hidden>
                Sign up completed Successfully, Redirecting.....
            </div>
        </div>
        <input type="hidden" value="" id="identityDocToken" name="identityDocToken">
    </form>
</div>
<%------------------------------------------------------------------%>
<div class="table table-striped" class="files" id="previews">

    <div id="template" class="file-row">
        <!-- This is used as the file preview template -->
        <div>
            <span class="preview"><img data-dz-thumbnail /></span>
        </div>
        <div>
            <strong class="error text-danger" data-dz-errormessage></strong>
        </div>

    </div>
</div>
<jsp:directive.include file="../layouts/top-footer.jsp" />
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
<script>
    var BASEURL = "${BaseUrl}";
</script>
<!-- Contact end here -->
<!-- Main container start here -->
<!-- Javascript framework and plugins start here -->
<script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />" ></script>
<script src="<c:url value="/resources/js/dropzone.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />" ></script>
<script src="<c:url value="/resources/js/jquery.validate.min.js" />" ></script>
<script src="<c:url value="/resources/js/modernizr.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/appear.js" />" ></script>
<script src="<c:url value="/resources/js/jquery.knob.js" />" ></script>
<script src="<c:url value="/resources/js/jquery.ccountdown.js" />" ></script>
<script src="<c:url value="/resources/js/init.js" />" ></script>
<script src="<c:url value="/resources/js/general.js" />"  ></script>
<!-- Theme JavaScript -->
<script src="<c:url value="/resources/js/clean-blog.min.js" />" ></script>
<script src="<c:url value="/resources/js/owl.carousel.js" />" ></script>
<script src="<c:url value="/resources/js/jquery.enllax.min.js" />" ></script>
<script src="<c:url value="/resources/developer/js/helper/ErrorMessaging.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-datepicker.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap-select.js"/>"></script>
<script src="https://apis.google.com/js/api:client.js"></script>

<!-- Javascript framework and plugins end here -->
<script type="text/javascript">
    console.log("${pageContext.request.localName}");
    // $("div#fallback").dropzone({ url: "/file/post" });

    $('.main_product_slider').carousel();
    $('.owl-carousel').owlCarousel({
        rtl:true,
        loop:true,
        margin:10,
        nav:true,
        responsive:{
            0:{
                items:1
            },
            600:{
                items:3
            },
            1000:{
                items:5
            }
        }
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
    $(document).ready(function(){
        setAliasMessage("identityDocToken","Identity doc token required","Identity document required");
        /*Removed By client*/
        //fetchIdentity();
    });
    /*Removed By client*/
//    function fetchIdentity(){
//        $.ajax({
//            url: BASEURL+'/api/utility/get-identity',
//            type: 'GET',
//            dataType: 'json',
//            success: function(data){
//                $.each(data.responseData, function(index, identity) {
//                    var select = document.getElementById("identityTypeId");
//                    var option = document.createElement("option");
//                    option.value = identity.id;
//                    option.text = identity.name;
//                    select.add(option);
//                });
//                $('#identityTypeId').selectpicker('refresh');
//            }
//        });
//    }
</script>
<script>
    function submitSignUpData(){
        UnBindErrors("errorMsg_");
        if(!requiredValidation("errorMsg_")){
            return false;
        }



        var firstName = $("#firstName").val();
        var lastName = $("#lastName").val();
        var email = $("#email").val();
        var password = $("#password").val();
        var conPassword = $("#conPassword").val();

        if(conPassword !== password){
            $("#errorMsg_confirmPassword").html("Password miss matched");
            $("#errorMsg_confirmPassword").show();
            $("#conPassword").focus();
            return false;
        }

        $('#signUpButton').attr("disabled","disabled");
        $('.signUpGif').show();
       // var identityTypeId = $("#identityTypeId option:selected").val();
     //   var identityDocToken = $("#identityDocToken").val();
//        console.log(firstName, lastName, email, password, identityTypeId, identityDocToken);
        $.ajax({
            url: BASEURL+'/api/signup/user',
            type: 'POST',
            data: {
                firstName:firstName,
                lastName:lastName,
                email:email,
                password:password,
              //  identityTypeId:identityTypeId,
              //  identityDocToken:identityDocToken
            },
            success: function(data){
                $('#signUpButton').removeAttrs("disabled");
                if(data.responseStat.status == false){
                    BindErrorsWithHtml("errorMsg_", data.responseStat.requestErrors);
                }else{
                    $('form').trigger('reset');
                    $('.alert-success').show().delay(5000).fadeOut(500,function(){
                        window.location.href = BASEURL+"/signin";
                    });
                }
                $('.signUpGif').hide().delay(4998).fadeOut();
            },error:function(er){
                $('#signUpButton').removeAttrs("disabled");
                alert("Something went wrong. Try again later");

            }
        });
        return false;
    }
</script>


<script>
    Dropzone.autoDiscover = false;
    var previewNode = document.querySelector("#template");
    previewNode.id = "";
    var previewTemplate = previewNode.parentNode.innerHTML;
    previewNode.parentNode.removeChild(previewNode);
    $(function() {
        var identityDocFile = $("div#fallback").dropzone(
                {
                    url: BASEURL+"/fileupload/upload/document-identity",
                    paramName: "documentIdentity",
                    maxFilesize: 1,
                    previewTemplate: previewTemplate,
                    thumbnailWidth: 200,
                    thumbnailHeight: 200,
                    maxFiles: 1,
//                    previewsContainer: "#previews",
//                    totaluploadprogress: function(progress, uploadProgress, totalBytesSent) {
//
//                    },
                    acceptedFiles: "image/jpeg,image/png,application/pdf",
                    maxfilesexceeded: function(file) {
                        this.removeAllFiles();
                        this.addFile(file);
                    },
                    addedfile:function(file){
                        $('#signUpButton').attr("disabled","disabled");
                        $('.signUpGif').show();
                        $('.fileUploadGif').show();
                        $('#identityDocUploadProgress').show();

                    },
                    uploadprogress:function(file, progress){
                        console.log("progress "+progress);

                    },
                    success:function(file, response){
                        UnBindErrors('errorMsg_');

                        if(response.responseStat.status == true) {
                            $('#nameFileAttached').html(response.responseStat.msg);
                            $('#identityDocToken').val(response.responseData);
                        }
                        else{
                            $('#nameFileAttached').html("");
                            BindErrorsWithHtml('errorMsg_', response.responseStat.requestErrors);
                        }
                        $('.fileUploadGif').hide();
                        $('#signUpButton').removeAttr("disabled","disabled");
                        $('.signUpGif').hide();

                    },
                    error:function(file, errorMessage, xhr){
                        $('.fileUploadGif').hide();
                        $('#signUpButton').removeAttr("disabled","disabled");
                        $('.signUpGif').hide();
                        $('#nameFileAttached').html("");
                        $('#identityDocUploadProgress').show();
                    }
                }
        );

    });
</script>
<script>
    function isEmailExist(){
        var email = $("#email").val();
        $.ajax({
            type: "POST",
            url: BASEURL+"/api/signup/email-check",
            data: {
                email : email
            },
            success : function(data){
                if(data.responseData == true){
                    BindErrorsWithHtml("errorMsg_", data.responseStat.requestErrors);
                }else{
                    document.getElementById("errorMsg_email").style.display = "none";
                }
            }
        });
    }
</script>
<script>


    window.fbAsyncInit = function() {
        FB.init({
            appId      : '48614701501', // Office Live : 591163881067974 , Local : 141550766294824
            cookie     : true,  // enable cookies to allow the server to access
                                // the session
            xfbml      : true,  // parse social plugins on this page
            version    : 'v2.5' // use graph api version 2.5
        });


    };

    // Load the SDK asynchronously
    (function(d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) return;
        js = d.createElement(s); js.id = id;
        js.src = "//connect.facebook.net/en_US/sdk.js";
        fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));

    function loginWithFacebook(){
        FB.login(function(response) {
            if (response.authResponse) {
                loginWithFaceBookAccessToken(response.authResponse.accessToken);
            } else {
                console.log('User cancelled login or did not fully authorize.');
            }
        },{scope: 'email,public_profile'});
    }

    function loginWithFaceBookAccessToken(accessToken){
        $("#fbLoginProgressImg").show();

        $.ajax({
            url: BASEURL+'/api/social-media/facebook/login/by-facebook-access-token',
            type: 'POST',
            data: {
                accessToken:accessToken
            },
            success: function(data){
                $("#fbLoginProgressImg").hide();
                if(data.responseStat.status == true){
                    $("#alertMsg").html("Login success").fadeIn(500).delay(2000).fadeOut(500,function(){
                        window.location.href =BASEURL+"/home";
                    });
                }else{
                    $("#alertMsg").html(data.responseStat.msg).fadeIn(500).delay(3000).fadeOut(500,function(){
                        $("#signBtn").removeAttrs("disabled","disabled");
                    });
                }


            }
        });
    }

    /*Google Login */
    var googleUser = {};
    var loginWithGoogle = function() {
        gapi.load('auth2', function(){
            // Retrieve the singleton for the GoogleAuth library and set up the client.
            auth2 = gapi.auth2.init({
                client_id: '109533534799-85f6m6k04935qsuc6on9ubqe7e8rtndj.apps.googleusercontent.com',
                cookiepolicy: 'single_host_origin',
                // Request scopes in addition to 'profile' and 'email'
                //scope: 'additional_scope'
            });
            attachSignin(document.getElementById('loginWithGooGleBtn'));
        });
    };

    function attachSignin(element) {
        auth2.attachClickHandler(element, {},
                function(googleUser) {
                    try{
                        loginWithGoogleAccessToken(googleUser.Zi.access_token);
                    }catch(ex){
                        console.log(ex);
                    }
                }, function(error) {
                    alert(JSON.stringify(error, undefined, 2));
                });
    }
    function loginWithGoogleAccessToken(accessToken){
        $("#googleLoginInProgressImg").show();

        $.ajax({
            url: BASEURL+'/api/social-media/google/login/by-google-access-token',
            type: 'POST',
            data: {
                accessToken:accessToken
            },
            success: function(data){
                $("#googleLoginInProgressImg").hide();

                if(data.responseStat.status == true){
                    $("#alertMsg").html("Login success").fadeIn(500).delay(2000).fadeOut(500,function(){
                        window.location.href =BASEURL+"/home";
                    });
                }else{
                    $("#alertMsg").html(data.responseStat.msg).fadeIn(500).delay(3000).fadeOut(500,function(){
                        $("#signBtn").removeAttrs("disabled","disabled");
                    });
                }


            }
        });
    }

    loginWithGoogle();
    $(document).ready(function(){
        $("#email").focus();
    });
</script>
</body>
</body>

</body>
</html>
