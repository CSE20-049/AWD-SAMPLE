<%@ page import="model.BEANS.Subscriber" %>
<%@ page import="model.BEANS.Movie" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.DAO.MovieDAO" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="subscriber" scope="session" type="model.BEANS.Subscriber"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Rate - RedFox</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <style>
        @import url('https://fonts.googleapis.com/css?family=Signika:500');

        * {
            padding: 0;
            margin:  0;
            box-sizing: border-box;
            font-family: "Signika", sans-serif;
        }

        /*Navigation Style*/
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
        /*Navigation Style*/

        /*Footer Style*/
        footer {
            position: absolute;
            min-height: 30px;
            width: 100%;
            padding-top: 0;
            color: #FFF;
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
            margin: 25px 0 12px 7px;
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
        /*Footer Style*/

        section {
            background-color: #101010;
            background-size: cover;
            height: auto;
            color: white;
            padding-bottom: 20px;
        }
        section h2{
            width: 90%;
            margin: auto;
            padding-top: 45px;
            padding-bottom: 5px;
            letter-spacing: 3px;
            color: #ff0066;
            border-bottom: 2px double #0099cc;
            border-bottom-width: medium;
        }
        /*Images*/
        img{
            width: 255px;
            height: 330px;
            /*box-shadow: 3px 3px 5px #0099cc;*/
            cursor: pointer;
        }
        .movie-gallery{
            padding-top: 20px;
            width: 90%;
            margin: auto;
            display: grid;
            grid-template-columns: repeat(4,1fr);
            grid-gap: 20px;
        }
        .Movie-Image{
            position: relative;
            width: 255px;
            height:auto;
        }
        .Movie-Image:hover{
            cursor: pointer;
        }
        .Movie-Image-Content{
            position: absolute;
            top: 0;
            left: 0;
            width: 255px;
            height: 330px;
            background: rgba(0,0,0,0.6);
            color: white;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            opacity: 0;
            transition: opacity 0.35s;
        }
        .Movie-Image-Content:hover{
            opacity: 1;
        }
        .Movie-Title{
            margin-top: 110px;
            font-size: 20px;
            text-align: center;
        }
        .Year{
            font-size: 18px;
            font-weight: initial;
            margin-top: 10px;
        }
        .fa-star{
            color: orange;
            padding-top: 40px;
            margin-top: 0;
            margin-bottom: 0;
            font-weight: bold;
        }
        .Movie-Rating-Radio{
            color: #0099cc;
            font-size: 16px;
            position: relative;
            text-align: center;
            margin: 0 5px 0 3px;
            left: 14%;
            font-weight: bold;
        }
        .Rating-Submit-Button{
            position: relative;
            left: 37%;
            color: white;
            border: none;
            width: 25%;
            font-size: 14px;
            border-radius: 2px;
            padding: 4px 4px;
            text-transform: uppercase;
            background-color: #0c658b;
            margin-top: 8px;
        }
        .Rating-Submit-Button:hover{
            cursor: pointer;
            background-color: #990033;
        }
        /*Images*/

    </style>
</head>

<body>
<header>
    <!-- Navigation Bar contruction -->
    <div class="navigation">
        <nav>
            <h1 class = "logo">R e d<span> F o x</span></h1>
            <ul class = "navigation-links">
                <li><a href = "home.jsp">Home</a></li>
                <li><a href = "categories.jsp">Categories</a></li>
                <li><a class="active" href = "rate.jsp">Rate</a></li>
            </ul>
            <div class="right-side-nav">
                <!--requesting from subscriber details from the session object using JSP Scripting elements-->
                <label class = "Username"><%= subscriber.getUserName()%></label>
                <input class = "button1" type="submit" value="LOG OUT" onclick="location.href='index.jsp'">
            </div>
        </nav>
    </div>
</header>

<section>
    <h2>Act<span>ion</span></h2>
    <div class="movie-gallery">
        <%! ArrayList<Movie> movies = new ArrayList<>();%>
        <%
            try {
                movies = MovieDAO.selectMoviesByGenre("Action");
                assert movies != null;
                for (int i = 0; i < movies.size();i++){
        %>
        <div class="Movie-Image">
            <form action="movieRatingServlet" method="post">
                <img src="<%= movies.get(i).getImage()%>" alt="">
                <div class="Movie-Image-Content">
                    <div class="Movie-Title">
                        <h3><%= movies.get(i).getTitle().toUpperCase()%></h3>
                        <p class = "Year"><%= movies.get(i).getYearOfProduction()%></p>
                        <span class="fa fa-star fa-1x"> <%= movies.get(i).getRating()%></span><br>
                    </div>
                </div>
                <label class="Movie-Rating-Radio">1 <input type="radio" name="@Rate" value="1"></label>
                <label class="Movie-Rating-Radio">2 <input type="radio" name="@Rate" value="2"></label>
                <label class="Movie-Rating-Radio">3 <input type="radio" name="@Rate" value="3"></label>
                <label class="Movie-Rating-Radio">4 <input type="radio" name="@Rate" value="4"></label>
                <label class="Movie-Rating-Radio">5 <input type="radio" name="@Rate" value="5"></label>
                <label><input hidden type="text" name="@MovieID" value="<%= movies.get(i).getMovieID()%>"></label>
                <label><input hidden type="text" name="@EmailAddress" value="<%= subscriber.getEmailAddress()%>"></label>
                <br><input class="Rating-Submit-Button" type="submit" value="RATE">
            </form>
        </div>
        <%}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }%>
    </div>

    <!--Adventure Movies-->
    <h2>Adven<span>ture</span></h2>
    <div class="movie-gallery">
        <%
            try {
                movies = MovieDAO.selectMoviesByGenre("Adventure");
                assert movies != null;
                for (int i = 0; i < movies.size();i++){
        %>
        <div class="Movie-Image">
            <form action="movieRatingServlet" method="post">
                <img src="<%= movies.get(i).getImage()%>" alt="">
                <div class="Movie-Image-Content">
                    <div class="Movie-Title">
                        <h3><%= movies.get(i).getTitle().toUpperCase()%></h3>
                        <p class = "Year"><%= movies.get(i).getYearOfProduction()%></p>
                        <span class="fa fa-star fa-1x"> <%= movies.get(i).getRating()%></span><br>
                    </div>
                </div>
                <label class="Movie-Rating-Radio">1 <input type="radio" name="@Rate" value="1"></label>
                <label class="Movie-Rating-Radio">2 <input type="radio" name="@Rate" value="2"></label>
                <label class="Movie-Rating-Radio">3 <input type="radio" name="@Rate" value="3"></label>
                <label class="Movie-Rating-Radio">4 <input type="radio" name="@Rate" value="4"></label>
                <label class="Movie-Rating-Radio">5 <input type="radio" name="@Rate" value="5"></label>
                <label><input hidden type="text" name="@MovieID" value="<%= movies.get(i).getMovieID()%>"></label>
                <label><input hidden type="text" name="@EmailAddress" value="<%= subscriber.getEmailAddress()%>"></label>
                <br><input class="Rating-Submit-Button" type="submit" value="RATE">
            </form>
        </div>
        <%}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }%>
    </div>

    <!--Horror Movies-->
    <h2>Hor<span>ror</span></h2>
    <div class="movie-gallery">
        <%
            try {
                movies = MovieDAO.selectMoviesByGenre("Horror");
                assert movies != null;
                for (int i = 0; i < movies.size();i++){
        %>
        <div class="Movie-Image">
            <form action="movieRatingServlet" method="post">
                <img src="<%= movies.get(i).getImage()%>" alt="">
                <div class="Movie-Image-Content">
                    <div class="Movie-Title">
                        <h3><%= movies.get(i).getTitle().toUpperCase()%></h3>
                        <p class = "Year"><%= movies.get(i).getYearOfProduction()%></p>
                        <span class="fa fa-star fa-1x"> <%= movies.get(i).getRating()%></span><br>
                    </div>
                </div>
                <label class="Movie-Rating-Radio">1 <input type="radio" name="@Rate" value="1"></label>
                <label class="Movie-Rating-Radio">2 <input type="radio" name="@Rate" value="2"></label>
                <label class="Movie-Rating-Radio">3 <input type="radio" name="@Rate" value="3"></label>
                <label class="Movie-Rating-Radio">4 <input type="radio" name="@Rate" value="4"></label>
                <label class="Movie-Rating-Radio">5 <input type="radio" name="@Rate" value="5"></label>
                <label><input hidden type="text" name="@MovieID" value="<%= movies.get(i).getMovieID()%>"></label>
                <label><input hidden type="text" name="@EmailAddress" value="<%= subscriber.getEmailAddress()%>"></label>
                <br><input class="Rating-Submit-Button" type="submit" value="RATE">
            </form>
        </div>
        <%}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }%>
    </div>

    <!--Drama Movies-->
    <h2>Dra<span>ma</span></h2>
    <div class="movie-gallery">
        <%
            try {
                movies = MovieDAO.selectMoviesByGenre("Drama");
                assert movies != null;
                for (int i = 0; i < movies.size();i++){
        %>
        <div class="Movie-Image">
            <form action="movieRatingServlet" method="post">
                <img src="<%= movies.get(i).getImage()%>" alt="">
                <div class="Movie-Image-Content">
                    <div class="Movie-Title">
                        <h3><%= movies.get(i).getTitle().toUpperCase()%></h3>
                        <p class = "Year"><%= movies.get(i).getYearOfProduction()%></p>
                        <span class="fa fa-star fa-1x"> <%= movies.get(i).getRating()%></span><br>
                    </div>
                </div>
                <label class="Movie-Rating-Radio">1 <input type="radio" name="@Rate" value="1"></label>
                <label class="Movie-Rating-Radio">2 <input type="radio" name="@Rate" value="2"></label>
                <label class="Movie-Rating-Radio">3 <input type="radio" name="@Rate" value="3"></label>
                <label class="Movie-Rating-Radio">4 <input type="radio" name="@Rate" value="4"></label>
                <label class="Movie-Rating-Radio">5 <input type="radio" name="@Rate" value="5"></label>
                <label><input hidden type="text" name="@MovieID" value="<%= movies.get(i).getMovieID()%>"></label>
                <label><input hidden type="text" name="@EmailAddress" value="<%= subscriber.getEmailAddress()%>"></label>
                <br><input class="Rating-Submit-Button" type="submit" value="RATE">
            </form>
        </div>
        <%}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }%>
    </div>

    <!--Comedy Movies-->
    <h2>Com<span>edy</span></h2>
    <div class="movie-gallery">
        <%
            try {
                movies = MovieDAO.selectMoviesByGenre("Comedy");
                assert movies != null;
                for (int i = 0; i < movies.size();i++){
        %>
        <div class="Movie-Image">
            <form action="movieRatingServlet" method="post">
                <img src="<%= movies.get(i).getImage()%>" alt="">
                <div class="Movie-Image-Content">
                    <div class="Movie-Title">
                        <h3><%= movies.get(i).getTitle().toUpperCase()%></h3>
                        <p class = "Year"><%= movies.get(i).getYearOfProduction()%></p>
                        <span class="fa fa-star fa-1x"> <%= movies.get(i).getRating()%></span><br>
                    </div>
                </div>
                <label class="Movie-Rating-Radio">1 <input type="radio" name="@Rate" value="1"></label>
                <label class="Movie-Rating-Radio">2 <input type="radio" name="@Rate" value="2"></label>
                <label class="Movie-Rating-Radio">3 <input type="radio" name="@Rate" value="3"></label>
                <label class="Movie-Rating-Radio">4 <input type="radio" name="@Rate" value="4"></label>
                <label class="Movie-Rating-Radio">5 <input type="radio" name="@Rate" value="5"></label>
                <label><input hidden type="text" name="@MovieID" value="<%= movies.get(i).getMovieID()%>"></label>
                <label><input hidden type="text" name="@EmailAddress" value="<%= subscriber.getEmailAddress()%>"></label>
                <br><input class="Rating-Submit-Button" type="submit" value="RATE">
            </form>
        </div>
        <%}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }%>
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
