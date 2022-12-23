<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Movie Details</title>

    <style>
        @import url('https://fonts.googleapis.com/css?family=Signika:500');

        * {
            padding: 0;
            margin:  0;
            box-sizing: border-box;
            font-family: "Signika", sans-serif;
            background-color: #101010;
            position: center;
        }
        span {
            color: #0099cc;
        }
        .movie-details{
            width: 100%;
            padding: 50px 0;
        }
        .movie-details-content{
            width: 725px;
        }
        .main{
            width: 1130px;
            max-width: 95%;
            margin: 0 auto;
            display: flex;
            align-items: center;
            justify-content: space-around;
        }
        .movie-details-content h1{
            color: #ff0066;
            font-size: 80px;
            letter-spacing: 2px;
        }
        .movie-details-content h2{
            color: #ff0066;
            margin-top: 11px;
            font-size: 34px;
            letter-spacing: 2px;
        }
        .movie-details-content h4{
            color: #ff0066;
            margin-top: 5px;
            font-size: 18px;
            letter-spacing: 3px;
            line-height: 14px;
            padding-bottom: 8px;
            border-bottom: 1px solid #7c7c7c;
            margin-bottom: 14px;
        }

        .movie-details-content p{
            color: #e0e0e0;
            letter-spacing: 1px;
            line-height: 28px;
            font-size: 18px;
            margin-bottom: 45px;
        }
        .movie-details-content input{
            border: none;
            margin-top: 6px;
            font-size: 20px;
            color: #0099cc;
            width: 100%;
            position: relative;
        }

        input[type="submit"], button{
            border: none;
            margin-top: 5px;
            margin-left: 30px;
            width: 25%;
            height: 36px;
            background: #ff0066;
            font-size: 18px;
            color: #FFFFFF;
            cursor: pointer;
            transition: all 0.1s ease 0s;
            border-radius: 3px;
        }
        input[type="submit"]:hover, button:hover{
            background: #990033;
        }
        .buttons{
            margin-left: 25%;
        }

        select{
            color: #d2d2d2;
            text-align: center;
            text-transform: uppercase;
            letter-spacing: 4px;
            font-weight: bold;
            background: transparent;
            padding: 6px 4px;
            border-radius: 4px;
            margin-top: 6px;

        }
        option{
            color: #d2d2d2;
        }
        .Genre-DropDown{
            width: 50%;
        }
    </style>
</head>

<body>

<section class="movie-details">
    <div class="main">
        <%
            HttpSession session1 = request.getSession();
            if (session1 != null){
                String movieID = (String) session1.getAttribute("movieIDAdmin");
                String title = (String) session1.getAttribute("titleAdmin");
                String genre = (String) session1.getAttribute("genreAdmin");
                String yearOfProduction = (String) session1.getAttribute("yearOfProductionAdmin");
                String image = (String) session1.getAttribute("imageAdmin");
                String synopsis = (String) session1.getAttribute("synopsisAdmin");
        %>
        <img src="<%= image%>" alt="" width="350px" height="auto">
        <form action="editDetailsServlet" method="get">
        <div class="movie-details-content">
            <h1>Abo<span>ut</span></h1>
            <input hidden type="text" name="movieID" value="<%= movieID%>">
            <h4>Title: <input type="text" name="UpdatedTitle" value="<%= title%>"></h4>
            <h4>Image: <input type="text" name="UpdatedImage" value="<%= image%>"></h4>
            <h4>Genre:
            <div class="Genre-DropDown">

                    <select name="UpdatedGenre" class="Genre">
                        <option value="Action">Action</option>
                        <option value="Adventure">Adventure</option>
                        <option value="Comedy">Comedy</option>
                        <option value="Drama">Drama</option>
                        <option value="Horror">Horror</option>
                    </select>

            </div>
            </h4>
            <h4>YearOfProduction: <input type="text" name="UpdatedYearOfProduction" value="<%= yearOfProduction%>"></h4>
            <h4>Synopsis: <input type="text" name="UpdatedSynopsis" value="<%= synopsis%>"></h4>
            <h2>Syno<span>psis</span></h2>
            <p><%= synopsis%></p>
        </div>
            <div class="buttons">
                <input type="submit" value="UPDATE">
                <button type="button" onclick="location.href='updateMovie.jsp'">CLOSE</button>
            </div>
        </form>
        <%}%>
    </div>
</section>

</body>
</html>
