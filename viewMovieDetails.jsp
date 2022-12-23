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
            background-color: #18171f;
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
        .movie-details-content label{
            color: #0099cc;
        }
    </style>
</head>

<body>

    <section class="movie-details">
        <div class="main">
            <%
                HttpSession session1 = request.getSession();
                if (session1 != null){
                    String title = (String) session1.getAttribute("title");
                    String director = (String) session1.getAttribute("director");
                    String actors = (String) session1.getAttribute("actors");
                    String genre = (String) session1.getAttribute("genre");
                    String yearOfProduction = (String) session1.getAttribute("yearOfProduction");
                    String rating = (String) session1.getAttribute("rating");
                    String image = (String) session1.getAttribute("image");
                    String synopsis = (String) session1.getAttribute("synopsis");
            %>
            <img src="<%= image%>" alt="" width="350px" height="auto">
            <div class="movie-details-content">
                <h1>Abo<span>ut</span></h1>
                <h4>Title: <label><%= title%></label></h4>
                <h4>Director: <label><%= director%></label></h4>
                <h4>Actors: <label><%= actors%></label></h4>
                <h4>Genre: <label><%= genre%></label></h4>
                <h4>YearOfProduction: <label><%= yearOfProduction%></label></h4>
                <h4>Rating: <label><%= rating%></label></h4>
                <h2>Syno<span>psis</span></h2>
                <p><%= synopsis%></p>

            </div>
            <%}%>
        </div>
    </section>

</body>
</html>
