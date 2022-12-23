package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "movieDetailsServlet",value = "/movieDetailsServlet")
public class MovieDetailsServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String title = request.getParameter("@Title");
        String director = request.getParameter("@Director");
        String actors = request.getParameter("@Actors");
        String genre = request.getParameter("@Genre");
        String yearOfProduction = request.getParameter("@YearOfProduction");
        String rating = request.getParameter("@Rating");
        String image = request.getParameter("@Image");
        String synopsis = request.getParameter("@Synopsis");

        HttpSession session = request.getSession();
        session.setAttribute("title",title);
        session.setAttribute("director",director);
        session.setAttribute("actors",actors);
        session.setAttribute("genre",genre);
        session.setAttribute("yearOfProduction",yearOfProduction);
        session.setAttribute("rating",rating);
        session.setAttribute("image",image);
        session.setAttribute("synopsis",synopsis);

        String url = "/viewMovieDetails.jsp";

        getServletContext().getRequestDispatcher(url).forward(request,response);
    }

}
