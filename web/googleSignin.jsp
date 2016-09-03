<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 9/2/16
  Time: 2:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id" content="109533534799-85f6m6k04935qsuc6on9ubqe7e8rtndj.apps.googleusercontent.com">
<div class="g-signin2" data-onsuccess="onSignIn"></div>


<script>
  function onSignIn(googleUser) {
    console.log(googleUser);
    var profile = googleUser.getBasicProfile();
    console.log(profile);
    console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
    console.log('Name: ' + profile.getName());
    console.log('Image URL: ' + profile.getImageUrl());
    console.log('Email: ' + profile.getEmail());
  }
</script>