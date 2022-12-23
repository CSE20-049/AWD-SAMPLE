<jsp:useBean id="administrator" scope="session" type="model.BEANS.Administrator"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Home - RedFox</title>
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
        nav ul li a.active {
            color: rgba(16,148,224);
            transition: all 0.2s ease 0s;
            border-bottom: 0.5px solid white;
            border-bottom-width: medium;
        }
        nav ul li a:hover {
            color: #0099cc;
            transition: all 0.2s ease 0s;
            border-bottom: 0.5px solid white;
            border-bottom-width: medium;
        }

        .right-side-nav {
            margin-right: -83px;
        }
        .right-side-nav .Username{
            color: white;
        }
        .right-side-nav .button1 {
            color: white;
            margin-left: 35px;
            padding: 9px 22px;
            background-color: #ff0066;
            border: none;
            border-radius: 60px;
            transition: all 0.1s ease 0s;
            cursor: pointer;
        }
        .right-side-nav .button1:hover {
            background: #990033;
        }

        footer {
            position: absolute;
            min-height: 30px;
            width: 100%;
            padding-top: 0px;
            color: #FFF;
            font-family: Arial;
            border-top: 0.5px solid white;
            border-top-width: thin;
            border-top-style: solid;
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

        section {
            background-color: #101010;
            background-size: cover;
            height: 100vh;
            color: white;
        }
        .container{
            position: relative;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 100px 0;
            flex-wrap: wrap;
        }
        .container .box{
            background: black;
            border: 4px solid;
            border-image:1 linear-gradient(55deg,#03a9f4,#ff0058);
            position: relative;
            width: 320px;
            height: 400px;
            margin: 20px 30px;
            transition: 0.2s;
        }
        .container .box:hover{
            border-image:1 linear-gradient(60deg,#ff0058,#03a9f4);
            transform: scale(1.02)translateY(-30px);
            cursor: pointer;
        }
        .container .box .box-content{
            margin-top: 150px;
            text-align: center;
            align-items: center;
        }
        .container .box .box-content h3{
            margin-top: 6px;
            font-size: 20px;
            letter-spacing: 2px;
            font-family: "Verdana Pro", sans-serif;
            font-weight: bolder;
        }
        .fa-user,.fa-file-movie-o,.fa-refresh{
            padding-top: 5px;
            margin-top: 0;
            margin-bottom: 0;
            font-weight: bold;
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
                <li><a class="active" href = "home.jsp">Home</a></li>
            </ul>
            <div class="right-side-nav">
                <!--requesting from subscriber details from the session object using jsp:useBean action tag and Expression Language-->
                <label class="Username">${administrator.username}</label>
                <button class = "button1" type="button" onclick="location.href='index.jsp'">LOG OUT</button></a>
            </div>
        </nav>

    </div>
</header>

<section>
    <div class="container">
        <div class="box" onclick="location.href='addEmployee.jsp'">
            <span></span>
            <div class="box-content">
                <span class="fa fa-user fa-4x"></span><br>
                <h3>ADD EMPLOYEE</h3>
            </div>
        </div>
        <div class="box" onclick="location.href='addMovie.jsp'">
            <span></span>
            <div class="box-content">
                <span class="fa fa-file-movie-o fa-4x"></span>
                <h3>ADD MOVIE</h3>
            </div>
        </div>
        <div class="box" onclick="location.href='updateMovie.jsp'">
            <span></span>
            <div class="box-content">
                <span class="fa fa-refresh fa-4x"></span>
                <h3>UPDATE MOVIE</h3>
            </div>
        </div>
    </div>


</section>

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
</body>
</html>
