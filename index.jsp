<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>RedFox</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        @import url('https://fonts.googleapis.com/css?family=Signika:500');

        * {
            padding: 0;
            margin:  0;
            box-sizing: border-box;
            font-family: "Signika", sans-serif;
        }

        header {
            background-color: #18171f;
        }
        nav {
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding-top: 7px;
            padding-bottom: 7px;
            padding-left: 10%;
            padding-right: 10%;
        }
        .logo {
            color: #ff0066;
            cursor: pointer;
        }
        span {
            color: #0099cc;
        }
        nav ul li {
            list-style-type: none;
            display: inline-block;
            padding: 10px 20px;
        }
        nav ul li a {
            color: white;
            text-decoration: none;
            font-weight: bold;
        }
        nav ul li a:hover {
            color: #0099cc;
            transition: all 0.2s ease 0s;
            border-bottom: 0.5px solid white;
            border-bottom-width: medium;
            border-bottom-style: solid;
        }
        .button1 {
            color: white;
            margin-left: 35px;
            padding: 9px 22px;
            background-color: #ff0066;
            border: none;
            border-radius: 60px;
            transition: all 0.1s ease 0s;
            cursor: pointer;
        }
        .button1:hover {
            background: #990033;
        }

        .banner-area {
            background-image: url(images/wallpaper/Webwallpaper5.jpg);
            background-position: absolute;
            background-size: cover;
            height: 100vh;

            animation-name: animate;
            animation-duration: 70s;
        }

        @Keyframes animate{
            50%{
                background-image: url(images/wallpaper/Webwallpaper2.jpg);
            }
        }

        .overlay {
            background-position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0,0,0,0.79);
        }
        .content {
            font-size: 20px;
            position: absolute;
            top: 60%;
            left: 50%;
            transform: translate(-50%,-50%);
            color: #FFF;
        }

        .content h1, p {
            text-align: center;
        }

        .button2 {
            margin-top: 25px;
            margin-left: 43%;
            color: white;
            padding: 11px 25px;
            background-color: #ff0066;
            border: none;
            border-radius: 60px;
            transition: all 0.1s ease 0s;
            cursor: pointer;
        }

        .button2:hover{
            background: #990033;
        }

        .form-popup{
            display: none;
            position: absolute;
            top: 58%;
            left: 50%;
            transform: translate(-50%,-50%);
            width: 400px;
            background: #18171f;
            border-radius: 10px;
        }
        .form-popup h1{
            text-align: center;
            margin-top: 10px;
            color: #ff0066;
            font-size: 30px;
            margin-bottom: -24px;
        }
        .form-popup h3{
            text-align: center;
            padding: 2px 0 15px 0;
            margin-top: -10px;
            margin-bottom: 20px;
            border-bottom: 1px solid #C0C0C0FF;
            color: #ff0066;
        }
        .form-popup form{
            padding: 0 40px;
            box-sizing: border-box;
        }
        form .text-field{
            position: relative;
            border-bottom: 2px solid #0c658b;
            margin: 20px 0;
        }
        .text-field input{
            width: 100%;
            padding: 0 5px;
            height: 40px;
            border: none;
            background: none;
            outline: none;
            color: #f8f8f8;
            font-size: 17px;
        }
        .text-field span::before{
            content: '';
            position: absolute;
            top: 40px;
            left: 0;
            width: 100%;
            height: 2px;
            background: #0c658b;
        }
        .text-field input:focus ~ label, .text-field input:valid ~ label{
            top: -10px;
            color: #0890cc;
        }

        .check-boxes{
            color: #adadad;
            text-align: center;
        }
        .check-boxes input{
            margin: 0 0 0 0;
        }
        .check-boxes label{
            margin: 0 15px 0 3px;
        }

        input[type="submit"]{
            border: none;
            margin-top: 10px;
            margin-left: 89px;
            width: 40%;
            height: 39px;
            background: #ff0066;
            font-size: 18px;
            color: #FFFFFF;
            cursor: pointer;
            transition: all 0.1s ease 0s;
        }
        input[type="submit"]:hover{
            background: #990033;
        }

        .login_link{
            margin: 11px 0;
            text-align: center;
            font-size: 15px;
            color: #666666;
        }
        .login_link a{
            color: #0099cc;
            text-decoration: none;
        }
        .login_link a:hover{
            color: #ff0066;
        }

        .closeButton{
            background: none;
            border: none;
            padding: 5px 0;
            margin-top: -20px;
            margin-left: 42px;
            font-size: 30px;
            color: #adadad;
            cursor: pointer;
        }
        .closeButton:hover{
            color: #990033;
        }

        .form-popup2{
            position: absolute;
            top: 58%;
            left: 50%;
            transform: translate(-50%,-50%);
            width: 400px;
            background: #18171f;
            border-radius: 10px;
        }

        .message-error{
            color: #ff0066;
            margin-bottom: -15px;
        }
        .form-popup2 h1{
            text-align: center;
            margin-top: 10px;
            color: #ff0066;
            font-size: 30px;
            margin-bottom: -24px;
        }
        .form-popup2 form{
            padding: 0 40px;
            box-sizing: border-box;
        }
        .closeButton2{
            background: none;
            border: none;
            padding: 5px 0;
            margin-top: -20px;
            margin-left: 42px;
            font-size: 30px;
            color: #adadad;
            cursor: pointer;
        }
        .closeButton2:hover{
            color: #990033;
        }

        footer {
            position: absolute;
            min-height: 30px;
            width: 100%;
            padding-top: 0px;
            color: #FFF;
            font-family: Arial, serif;
            border-top: 0.5px solid white;
            border-top-width: thin;
            background-color: #18171f;
        }

        .footer-content {
            display: flex;
            align-items: center;
            justify-content: center;
            flex-direction: column;
            text-align: center;
        }

        .socials {
            list-style: none;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 25px 0px 12px 7px;
            margin-bottom: 3px;
            margin-top: 10px;
        }

        .socials li {
            margin: 0 15px;
        }

        .socials a {
            text-decoration: none;
            color: #FFF;
            font-size: 35px;
            letter-spacing: 15px;
        }

        .socials a i {
            transition: all 0.1s ease 0s;
        }

        .socials a:hover  {
            color: #0099cc;
        }

        .footer-bottom {
            color: #666666;
            width:100%;
            padding: 4px 0;
            text-align: center;
            padding-bottom: 15px;
            padding-top: 5px;
        }

        .footer-bottom p {
            font-size: 14px;
            word-spacing: 2px;
            text-transform: capitalize;
            margin-left: 1px;
            padding-bottom: 1px;
            margin-bottom: -7px;
        }
    </style>
</head>

<body>
<header>
    <!-- Navigation Bar contruction -->
    <div class="navigation">
        <nav>
            <h1 class = "logo">R e d<span> F o x</span></h1>
            <ul class = "navigation-links">
                <li><a onclick="openLoginForm()" href = "#">Home</a></li>
                <li><a onclick="openLoginForm()" href = "#">Categories</a></li>
                <li><a onclick="openLoginForm()" href = "#">Rate</a></li>
            </ul>
            <button class = "button1" type="button" onclick="openLoginForm()">LOG IN</button></a>
        </nav>
    </div>
</header>

<section>
    <!-- Page content -->
    <div class = "banner-area">
        <div class = "overlay">
            <div class="form-popup" id="signUpForm">
                <h1>SIGN <span>UP</span></h1> <button class = "closeButton" onclick="closeSignUpForm()" type="button">x</button></a>
                <form action="signUpServlet" method="post">
                    <div class="text-field">
                        <input type="text" required name="@FirstName" value="" placeholder="First Name">
                    </div>
                    <div class="text-field">
                        <input type="text" required name="@Surname" value="" placeholder="Surname">
                    </div>
                    <div class="text-field">
                        <input type="email" required name="@Email" value="" placeholder="Email Address">
                    </div>
                    <div class="text-field">
                        <input type="tel" required name="@Contact" value="" placeholder="Contact">
                    </div>
                    <div class="text-field">
                        <input type="password" required name="@Password" value="" placeholder="Password">
                    </div>
                    <div class="check-boxes">
                        <h3>PREFERRED <span>MOVIES</span></h3>
                        <input type="checkbox" name="@Action" value="Action">
                        <label>Action</label>
                        <input type="checkbox" name="@Adventure" value="Adventure">
                        <label>Adventure</label>
                        <input type="checkbox" name="@Comedy" value="Comedy">
                        <label>Comedy</label>
                    </div><br>
                    <div class="check-boxes">
                        <input type="checkbox" name="@Drama" value="Drama">
                        <label>Drama</label>
                        <input type="checkbox" name="@Horror" value="Horror">
                        <label>Horror</label>
                    </div>
                    <input type="submit" value="SIGN UP">
                    <div class="login_link">
                        Already a subscriber? <a onclick="openLoginFormLink()" href="#">Login</a>
                    </div>
                </form>
            </div>
            <!-- loginForm -->
            <div class="form-popup2" id="loginForm">
                <h1>LOGIN <span>UP</span></h1> <button class = "closeButton2" onclick="closeLoginForm()" type="button">x</button></a>
                <!-- Login Error messages -->
                <%
                    String message = (String) request.getAttribute("message");
                    if (message != null){
                %>
                <p class="message-error" style="text-align: center;" > <%= message%></p>
                <%}%>
                <form action="loginServlet" method="post">
                    <div class="text-field">
                        <input type="text" required name="@LoginUsername" value="" placeholder="Username">
                    </div>
                    <div class="text-field">
                        <input type="password" required name="@LoginPassword" value="" placeholder="password">
                    </div>
                    <input type="submit" class="loginButton" value="LOG IN">
                    <div class="login_link">
                        Not a subscriber? <a onclick="openSignUpFormLink()" href="#">Sign Up</a>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- javascript for signUp and login forms-->
    <script>
        function openSignUpForm(){
            document.getElementById("signUpForm").style.display = "block"
            document.getElementById("pageContent").style.display = "none"
        }
        function closeSignUpForm(){
            document.getElementById("signUpForm").style.display = "none"
            document.getElementById("pageContent").style.display = "block"
        }
        function openLoginForm(){
            document.getElementById("loginForm").style.display = "block"
            document.getElementById("pageContent").style.display = "none"
        }
        function closeLoginForm(){
            document.getElementById("loginForm").style.display = "none"
            document.getElementById("pageContent").style.display = "block"
        }
        function openLoginFormLink(){
            document.getElementById("loginForm").style.display = "block"
            document.getElementById("signUpForm").style.display = "none"
        }
        function openSignUpFormLink(){
            document.getElementById("signUpForm").style.display = "block"
            document.getElementById("loginForm").style.display = "none"
        }
    </script>
</section>
</body>

<footer>
    <div class = "footer-content">
        <ul class = "socials">
            <li><a href = ""><i class = "fa fa-facebook"></i></a></li>
            <li><a href = ""><i class = "fa fa-twitter"></i></a></li>
            <li><a href = ""><i class = "fa fa-instagram"></i></a></li>
        </ul>
    </div>
    <div class = "footer-bottom">
    <p>&copy; RedFox. All rights reserved</p>
    </div>
</footer>
</html>