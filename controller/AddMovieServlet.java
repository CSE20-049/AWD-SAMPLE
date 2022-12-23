package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.DAO.AdministratorDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "addMovieServlet", value = "/addMovieServlet")
public class AddMovieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = "Successfully Added";
        String url = "/addEmployee.jsp";
        String title = request.getParameter("@MovieTitle");
        String genre = request.getParameter("@MovieGenre");
        int yearOfProduction = Integer.parseInt(request.getParameter("@YearOfProduction"));
        String image = request.getParameter("@MovieImage");
        String synopsis = request.getParameter("@MovieSynopsis");
        String status = request.getParameter("@MovieStatus");
        String adminEmailAddress = request.getParameter("@AdminEmailAddress");
        String actor1Name = request.getParameter("@Actor-1-Name");
        String actor1Surname = request.getParameter("@Actor-1-Surname");
        String actor1DOB = request.getParameter("@Actor-1-DOB");
        String actor2Name = request.getParameter("@Actor-2-Name");
        String actor2Surname = request.getParameter("@Actor-2-Surname");
        String actor2DOB = request.getParameter("@Actor-2-DOB");
        String actor3Name = request.getParameter("@Actor-3-Name");
        String actor3Surname = request.getParameter("@Actor-3-Surname");
        String actor3DOB = request.getParameter("@Actor-3-DOB");
        String directorName = request.getParameter("@Director-Name");
        String directorSurname = request.getParameter("@Director-Surname");
        String directorDOB = request.getParameter("@Director-DOB");

        try {
            AdministratorDAO.addMovie(title,genre,yearOfProduction,image,synopsis,status,adminEmailAddress,actor1Name,
                    actor1Surname,actor1DOB,actor2Name,actor2Surname,actor2DOB,actor3Name,actor3Surname,actor3DOB,directorName,
                    directorSurname,directorDOB);
            request.setAttribute(message,"message");
            getServletContext().getRequestDispatcher(url).forward(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
