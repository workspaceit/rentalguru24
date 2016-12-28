<%--
  Created by IntelliJ IDEA.
  User: mi
  Date: 10/19/16
  Time: 2:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:directive.include file="../layouts/header.jsp" />
<style>
    body{
        font-family: 'Open Sans','verdana',sans-serif;
        text-align: center;
    }
    .checkmark {
        display:inline-block;
        width: 22px;
        height:22px;
        -ms-transform: rotate(45deg); /* IE 9 */
        -webkit-transform: rotate(45deg); /* Chrome, Safari, Opera */
        transform: rotate(45deg);
    }

    .checkmark_circle {
        position: absolute;
        width:22px;
        height:22px;
        background-color: green;
        border-radius:11px;
        left:0;
        top:0;
    }

    .checkmark_stem {
        position: absolute;
        width:3px;
        height:9px;
        background-color:#fff;
        left:11px;
        top:6px;
    }

    .checkmark_kick {
        position: absolute;
        width:3px;
        height:3px;
        background-color:#fff;
        left:8px;
        top:12px;
    }
    .btn-login{
        width: 130px;
        height:40px;
        border: 0px;
        color: #fff;
        background: #319214;
        text-transform: uppercase;
        margin-bottom: 10px;

    }
</style>

<html>
<head>
    <title></title>
</head>
<body>
<div class="container">
    <div class="col-md-5 clearfix" style="margin:40px auto;width: 500px;padding: 15px; float:none ;border:2px solid #eee;border-radius: 10px;">
        <span class="checkmark">
            <div class="checkmark_circle"></div>
            <div class="checkmark_stem"></div>
            <div class="checkmark_kick"></div>
        </span>

        <h3 style="font-weight: lighter;">Congratulation! Your email is confirmed.</h3>
        <button class="btn-login" id="cButton" onclick="location.href='http://rentguru24.com/signin'">Redirecting in 5</button>
        <p><a href="http://rentguru24.com" style="color: #000;text-transform: uppercase;font-size: 14px;text-decoration: none;">&#8592;&nbsp;Back to home</a> </p>
        </div>
        <input type="hidden" value = "4" id ="counterVal" />
    <%--${statusMsg}--%>
</div>
</body>
</html>
<script type="text/javascript">
    function updateTime()
    {
        currentTime = document.getElementById('counterVal').value;
        if(currentTime==0)
        {window.location= "http://www.rentguru24.com/signin";}
        document.getElementById('cButton').innerHTML = "Redirecting in "+currentTime;
        document.getElementById('counterVal').value = currentTime - 1;
    }
    window.setInterval(updateTime, 1000);
</script>

