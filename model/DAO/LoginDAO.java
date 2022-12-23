package model.DAO;

import model.BEANS.Administrator;
import model.BEANS.Employee;
import model.BEANS.Subscriber;

import java.sql.*;

public class LoginDAO {

    public static Administrator administratorDetails(String username,String password) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/redfoxdatabase","root","@A$ceBw29");
        PreparedStatement administratorLoginCredentials = connection.prepareStatement("select * from user inner join administrator on user.username = administrator.username where user.username = ? and password = ?");
        administratorLoginCredentials.setString(1,username);
        administratorLoginCredentials.setString(2,password);

        ResultSet verifyAdministratorCredentials = administratorLoginCredentials.executeQuery();
        if (verifyAdministratorCredentials.next()){

            Administrator administrator = new Administrator();
            administrator.setEmailAddress(verifyAdministratorCredentials.getString(3));
            administrator.setFirstName(verifyAdministratorCredentials.getString(4));
            administrator.setSurname(verifyAdministratorCredentials.getString(5));
            administrator.setGender(verifyAdministratorCredentials.getString(6));
            administrator.setContact(verifyAdministratorCredentials.getInt(7));
            administrator.setOmangNumber(verifyAdministratorCredentials.getInt(8));
            administrator.setUsername(verifyAdministratorCredentials.getString(9));

            return administrator;

        }

        return null;

    }

    public static Employee employeeDetails(String username,String password) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/redfoxdatabase","root","@A$ceBw29");
        PreparedStatement employeeLoginCredentials = connection.prepareStatement("select * from user inner join administrator on user.username = administrator.username where user.username = ? and password = ?");
        employeeLoginCredentials.setString(1,username);
        employeeLoginCredentials.setString(2,password);

        ResultSet verifyEmployeeCredentials = employeeLoginCredentials.executeQuery();
        if (verifyEmployeeCredentials.next()){

            Employee employee = new Employee();
            employee.setEmailAddress(verifyEmployeeCredentials.getString(3));
            employee.setFirstName(verifyEmployeeCredentials.getString(4));
            employee.setSurname(verifyEmployeeCredentials.getString(5));
            employee.setGender(verifyEmployeeCredentials.getString(6));
            employee.setContact(verifyEmployeeCredentials.getInt(7));
            employee.setOmangNumber(verifyEmployeeCredentials.getInt(8));
            employee.setUsername(verifyEmployeeCredentials.getString(9));

            return employee;

        }

        return null;
    }

    public static Subscriber subscriberDetails(String username,String password) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/redfoxdatabase","root","@A$ceBw29");
        PreparedStatement subscriberLoginCredentials = connection.prepareStatement("select * from user inner join subscriber on user.username = subscriber.username where user.username = ? and password = ?");
            subscriberLoginCredentials.setString(1,username);
            subscriberLoginCredentials.setString(2,password);

        ResultSet verifySubscriberCredentials = subscriberLoginCredentials.executeQuery();
        if (verifySubscriberCredentials.next()){

            Subscriber subscriber = new Subscriber();
            subscriber.setUserName(verifySubscriberCredentials.getString(1));
            subscriber.setPassword(verifySubscriberCredentials.getString(2));
            subscriber.setFirstName(verifySubscriberCredentials.getString(4));
            subscriber.setSurname(verifySubscriberCredentials.getString(5));
            subscriber.setEmailAddress(verifySubscriberCredentials.getString(3));
            subscriber.setContact(verifySubscriberCredentials.getInt(6));
            subscriber.setFirstPreferredMovieGenre(verifySubscriberCredentials.getString(7));
            subscriber.setSecondPreferredMovieGenre(verifySubscriberCredentials.getString(8));
            subscriber.setThirdPreferredMovieGenre(verifySubscriberCredentials.getString(9));
            subscriber.setFourthPreferredMovieGenre(verifySubscriberCredentials.getString(10));
            subscriber.setFifthPreferredMovieGenre(verifySubscriberCredentials.getString(11));

            return subscriber;

        }

        return null;

    }

    public static void main(String[] args) throws SQLException {

    }

}
