package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.DAO.AdministratorDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "addEmployeeServlet", value = "/addEmployeeServlet")
public class AddEmployeeServlet extends HttpServlet {


    @Override
    public void init() throws ServletException {


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = "Successfully Added";
        String url = "/addEmployee.jsp";
        String emailAddress = request.getParameter("@EmployeeEmail");
        String firstName = request.getParameter("@EmployeeFirstName");
        String surname = request.getParameter("@EmployeeSurname");
        String gender = (request.getParameter("@Gender"));
        int contact = Integer.parseInt(request.getParameter("@EmployeeContact"));
        int omangNumber = Integer.parseInt(request.getParameter("@EmployeeOmangNumber"));
        String username = request.getParameter("@EmployeeUsername");
        String password = request.getParameter("@EmployeePassword");
        String adminEmailAddress = request.getParameter("@AdminEmailAddress");

        try {
                AdministratorDAO.addEmployee(emailAddress,firstName,surname,gender,contact,omangNumber,username,password,
                        adminEmailAddress);
                request.setAttribute(message,"message");
                getServletContext().getRequestDispatcher(url).forward(request,response);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
