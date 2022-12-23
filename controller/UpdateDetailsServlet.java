package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.DAO.AdministratorDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "editDetailsServlet", value = "/editDetailsServlet")
public class UpdateDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String movie =  request.getParameter("movieID");
        String updatedTitle =  request.getParameter("UpdatedTitle");
        String updatedImage = request.getParameter("UpdatedImage");
        String updatedGenre = request.getParameter("UpdatedGenre");
        int updatedYearOfProduction = Integer.parseInt(request.getParameter("UpdatedYearOfProduction"));
        String updatedSynopsis = request.getParameter("UpdatedSynopsis");

        try {
            String url = "/updateMovie.jsp";
            AdministratorDAO.updateMovie(updatedTitle,updatedImage,updatedGenre,updatedYearOfProduction,updatedSynopsis,movie);
            getServletContext().getRequestDispatcher(url).forward(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String movieID = request.getParameter("@MovieID");
        String title = request.getParameter("@TitleAdmin");
        String director = request.getParameter("@DirectorAdmin");
        String actors = request.getParameter("@ActorsAdmin");
        String genre = request.getParameter("@GenreAdmin");
        String yearOfProduction = request.getParameter("@YearOfProductionAdmin");
        String rating = request.getParameter("@RatingAdmin");
        String image = request.getParameter("@ImageAdmin");
        String synopsis = request.getParameter("@SynopsisAdmin");

        HttpSession session2 = request.getSession();
        session2.setAttribute("movieIDAdmin",movieID);
        session2.setAttribute("titleAdmin",title);
        session2.setAttribute("directorAdmin",director);
        session2.setAttribute("actorsAdmin",actors);
        session2.setAttribute("genreAdmin",genre);
        session2.setAttribute("yearOfProductionAdmin",yearOfProduction);
        session2.setAttribute("ratingAdmin",rating);
        session2.setAttribute("imageAdmin",image);
        session2.setAttribute("synopsisAdmin",synopsis);

        String url = "/updateMovieDetails.jsp";

        getServletContext().getRequestDispatcher(url).forward(request,response);

    }
}
