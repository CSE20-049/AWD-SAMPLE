package model.DAO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TesterClass {

    static DataSource dataSource = null;

    public static void main(String[] args) throws NamingException, SQLException, ClassNotFoundException {
        Context initialContext = new InitialContext();
        DataSource dataSource = (DataSource) initialContext.lookup("java:/comp/env/jdbc/redfoxdatabase");

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("Select * from user");
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){

            System.out.println(resultSet.getString(1));

        }
    }

}
