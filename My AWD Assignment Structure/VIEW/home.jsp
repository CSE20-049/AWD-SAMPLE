<%@ page import="java.util.ArrayList" %>
<%@ page import="model.BEANS.Movie" %>
<%@ page import="model.DAO.MovieDAO" %>
<%@ page import="java.sql.SQLException" %>
<jsp:useBean id="subscriber" scope="session" type="model.BEANS.Subscriber"/>
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

        /*Section*/
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
        .fa-star{
            color: orange;
            margin-bottom: 0;
            margin-top: 9px;
            align-items: center;
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
            z-index: 1;
            transform: scale(1.08);
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

        .Movie-Button{
            color: white;
            border: none;
            margin-top: 10px;
            margin-bottom: 3px;
            position: relative;
            left: 25%;
            width: 50%;
            font-size: 16px;
            border-radius: 7px;
            padding: 7px 10px;
            text-transform: uppercase;
            background-color: #0c658b;
        }
        .Movie-Button:hover{
            background-color: #990033;
            cursor: pointer;
        }
        /*Images*/
        /*Section*/
    </style>
</head>

<body>

<section>
    <!--Recommended Movies-->
    <h2>Recomm<span>ended</span></h2>
    <div class="movie-gallery">
        <%! ArrayList<Movie> movies = new ArrayList<>();%>
        <%
            try {
                movies = MovieDAO.selectTopFiveRecommendedMovies(subscriber.getFirstPreferredMovieGenre(),
                        subscriber.getSecondPreferredMovieGenre(),subscriber.getThirdPreferredMovieGenre(),
                        subscriber.getFourthPreferredMovieGenre(),subscriber.getFifthPreferredMovieGenre());
                assert movies != null;
                for (int i = 0; i < movies.size();i++){
        %>
        <div class="Movie-Image">
            <form action="movieDetailsServlet" method="post">
                <img src="<%= movies.get(i).getImage()%>" alt="">
                <div class="Movie-Image-Content">
                    <div class="Movie-Title">
                        <h3><%= movies.get(i).getTitle().toUpperCase()%></h3>
                        <p class = "Year"><%= movies.get(i).getYearOfProduction()%></p>
                        <span class="fa fa-star fa-1x"> <br><%= movies.get(i).getRating()%>/5.0</span><br>
                    </div>
                </div>
                <label>
                    <input hidden type="text" value="<%= movies.get(i).getTitle()%>" name="@Title">
                </label>
                <label>
                    <input hidden type="text" value="<%= movies.get(i).getDirector()%>" name="@Director">
                </label>
                <label>
                    <input hidden type="text" value="<%= movies.get(i).getActors()%>" name="@Actors">
                </label>
                <label>
                    <input hidden type="text" value="<%= movies.get(i).getGenre()%>" name="@Genre">
                </label>
                <label>
                    <input hidden type="text" value="<%= movies.get(i).getYearOfProduction()%>" name="@YearOfProduction">
                </label>
                <label>
                    <input hidden type="text" value="<%= movies.get(i).getRating()%>" name="@Rating">
                </label>
                <label>
                    <input hidden type="text" value="<%= movies.get(i).getImage()%>" name="@Image">
                </label>
                <label>
                    <input hidden type="text" value="<%= movies.get(i).getSynopsis()%>" name="@Synopsis">
                </label>
                <input class="Movie-Button" type="submit" value="More">
            </form>
        </div>
        <%}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }%>
    </div>

    <!--Brand New Movies-->
    <h2>Brand<span> New</span></h2>
    <div class="movie-gallery">
        <%
            try {
                movies = MovieDAO.selectMoviesByGenreAndStatus("New");
                assert movies != null;
                for (int i = 0; i < movies.size();i++){
        %>
        <div class="Movie-Image">
            <form action="movieDetailsServlet" method="post">
                <img src="<%= movies.get(i).getImage()%>" alt="">
                <div class="Movie-Image-Content">
                    <div class="Movie-Title">
                        <h3><%= movies.get(i).getTitle().toUpperCase()%></h3>
                        <p class = "Year"><%= movies.get(i).getYearOfProduction()%></p>
                        <span class="fa fa-star fa-1x"> <br><%= movies.get(i).getRating()%>/5.0</span><br>
                    </div>
                </div>
                <label>
                    <input hidden type="text" value="<%= movies.get(i).getTitle()%>" name="@Title">
                </label>
                <label>
                    <input hidden type="text" value="<%= movies.get(i).getDirector()%>" name="@Director">
                </label>
                <label>
                    <input hidden type="text" value="<%= movies.get(i).getActors()%>" name="@Actors">
                </label>
                <label>
                    <input hidden type="text" value="<%= movies.get(i).getGenre()%>" name="@Genre">
                </label>
                <label>
                    <input hidden type="text" value="<%= movies.get(i).getYearOfProduction()%>" name="@YearOfProduction">
                </label>
                <label>
                    <input hidden type="text" value="<%= movies.get(i).getRating()%>" name="@Rating">
                </label>
                <label>
                    <input hidden type="text" value="<%= movies.get(i).getImage()%>" name="@Image">
                </label>
                <label>
                    <input hidden type="text" value="<%= movies.get(i).getSynopsis()%>" name="@Synopsis">
                </label>
                <input class="Movie-Button" type="submit" value="More">
            </form>
        </div>
        <%}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }%>
    </div>

    <!--Upcoming Movies-->
    <h2>Upco<span>ming</span></h2>
    <div class="movie-gallery">
        <%
            try {
                movies = MovieDAO.selectMoviesByGenreAndStatus("Upcoming");
                assert movies != null;
                for (int i = 0; i < movies.size();i++){
        %>
        <div class="Movie-Image">
            <form action="movieDetailsServlet" method="post">
                <img src="<%= movies.get(i).getImage()%>" alt="">
                <div class="Movie-Image-Content">
                    <div class="Movie-Title">
                        <h3><%= movies.get(i).getTitle().toUpperCase()%></h3>
                        <p class = "Year"><%= movies.get(i).getYearOfProduction()%></p>
                        <span class="fa fa-star fa-1x"> <br><%= movies.get(i).getRating()%>/5.0</span><br>
                    </div>
                </div>
                <label>
                    <input hidden type="text" value="<%= movies.get(i).getTitle()%>" name="@Title">
                </label>
                <label>
                    <input hidden type="text" value="<%= movies.get(i).getDirector()%>" name="@Director">
                </label>
                <label>
                    <input hidden type="text" value="<%= movies.get(i).getActors()%>" name="@Actors">
                </label>
                <label>
                    <input hidden type="text" value="<%= movies.get(i).getGenre()%>" name="@Genre">
                </label>
                <label>
                    <input hidden type="text" value="<%= movies.get(i).getYearOfProduction()%>" name="@YearOfProduction">
                </label>
                <label>
                    <input hidden type="text" value="<%= movies.get(i).getRating()%>" name="@Rating">
                </label>
                <label>
                    <input hidden type="text" value="<%= movies.get(i).getImage()%>" name="@Image">
                </label>
                <label>
                    <input hidden type="text" value="<%= movies.get(i).getSynopsis()%>" name="@Synopsis">
                </label>
                <input class="Movie-Button" type="submit" value="More">
            </form>
        </div>
        <%}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }%>
    </div>
</section>

</body>
</html>
