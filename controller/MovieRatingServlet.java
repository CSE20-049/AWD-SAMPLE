package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.DAO.SubscriberDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "movieRatingServlet", value = "/movieRatingServlet")
public class MovieRatingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double subscriberRate = Double.parseDouble(request.getParameter("@Rate"));
        String movieID = request.getParameter("@MovieID");
        String subscriberEmail = request.getParameter("@EmailAddress");

        try {

            SubscriberDAO.movieRating(subscriberRate,movieID,subscriberEmail);
            String url = "/rate.jsp";
            getServletContext().getRequestDispatcher(url).forward(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
