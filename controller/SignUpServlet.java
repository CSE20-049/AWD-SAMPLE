package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.BEANS.Subscriber;
import model.DAO.LoginDAO;
import model.DAO.SubscriberDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "signUpServlet", value = "/signUpServlet")
public class SignUpServlet extends HttpServlet {

    String url = "/home.jsp";

    public void init() throws ServletException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        String emailAddress = request.getParameter("@Email") ;
        String firstName = request.getParameter("@FirstName");
        String surname = request.getParameter("@Surname");
        int contact = Integer.parseInt(request.getParameter("@Contact"));
        String password = request.getParameter("@Password");
        String firstPreferredMovie = request.getParameter("@Action");
        String secondPreferredMovie = request.getParameter("@Adventure");
        String thirdPreferredMovie = request.getParameter("@Comedy");
        String fourthPreferredMovie = request.getParameter("@Drama");
        String fifthPreferredMovie = request.getParameter("@Horror");

        try {
            SubscriberDAO.signUpSubscriber(emailAddress,firstName,surname,contact,password,firstPreferredMovie,
                    secondPreferredMovie,thirdPreferredMovie,fourthPreferredMovie,fifthPreferredMovie);

            Subscriber subscriber = LoginDAO.subscriberDetails("@User"+contact,password);
            if (subscriber != null) {

                httpSession.setAttribute("subscriber", subscriber);
                getServletContext().getRequestDispatcher(url).forward(request, response);

            }
            

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
