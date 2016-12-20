<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 12/20/16
  Time: 5:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<a class="btn btn-block btn-social btn-facebook" onclick="loginWithFacebook()" >
  <i class="fa fa-facebook"></i> Sign in with Facebook
  <span id="fbLoginProgressImg" class="inner-load signUpGif" style="display:none;"></span>
</a>
<a class="btn btn-block btn-social btn-google-plus" id="loginWithGooGleBtn" >
  <i class="fa fa-google-plus"></i> Sign in with Google
  <span id="googleLoginInProgressImg" class="inner-load signUpGif" style="display:none;"></span>
</a>


<script src="https://apis.google.com/js/api:client.js"></script>

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
