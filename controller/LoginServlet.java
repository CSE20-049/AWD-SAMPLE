package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.BEANS.Administrator;
import model.BEANS.Employee;
import model.BEANS.Subscriber;
import model.DAO.LoginDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "loginServlet",value = "/loginServlet")
public class LoginServlet extends HttpServlet {

    public void init() throws ServletException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = "Wrong credentials";
        String url = "/home.jsp";
        String index = "/index.jsp";
        String subscriberHome = "/home.jsp";
        String administratorHome = "/adminHome.jsp";
        String employeeHome = "/employeeHome.jsp";

        HttpSession httpSession = request.getSession();

        String username = request.getParameter("@LoginUsername");
        String password = request.getParameter("@LoginPassword");


        if (username.contains("@User")){
            try {
                Subscriber subscriber = LoginDAO.subscriberDetails(username,password);
                if (subscriber == null) {

                    request.setAttribute("message", message);
                    getServletContext().getRequestDispatcher(index).forward(request, response);

                }else {

                    httpSession.setAttribute("subscriber", subscriber);
                    getServletContext().getRequestDispatcher(subscriberHome).forward(request, response);

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } else if (username.contains("@RedFox")) {
            try {
                Administrator administrator = LoginDAO.administratorDetails(username,password);
                if (administrator == null) {

                    request.setAttribute("message", message);
                    getServletContext().getRequestDispatcher(index).forward(request, response);

                }else {

                    httpSession.setAttribute("administrator", administrator);
                    getServletContext().getRequestDispatcher(administratorHome).forward(request, response);

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (username.contains("@Emp!oyee")) {
            try {
                Employee employee = LoginDAO.employeeDetails(username,password);
                if (employee == null) {

                    request.setAttribute("message", message);
                    getServletContext().getRequestDispatcher(index).forward(request, response);

                }else {

                    httpSession.setAttribute("employee", employee);
                    getServletContext().getRequestDispatcher(employeeHome).forward(request, response);

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            request.setAttribute("message",message);
            getServletContext().getRequestDispatcher(index).forward(request,response);
        }

    }
}
