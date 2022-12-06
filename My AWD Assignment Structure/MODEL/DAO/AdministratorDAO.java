package model.DAO;

import model.BEANS.Actor;
import model.BEANS.Director;
import model.BEANS.Movie;

import java.sql.*;
import java.util.ArrayList;

public class AdministratorDAO {

    public static ArrayList<Movie> selectAllMovies() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/redfoxdatabase","root","@A$ceBw29");
        PreparedStatement getMovies = connection.prepareStatement("select * from movie");
        ResultSet selectedMovies = getMovies.executeQuery();

        if (selectedMovies.next()){
            ArrayList<Movie> movieArrayList = new ArrayList<>();
            do {
                PreparedStatement getDirector = connection.prepareStatement("select * from Director inner join Movie on Director.MovieID = Movie.MovieID where Movie.MovieID = ?");
                getDirector.setString(1,selectedMovies.getString(1));
                ResultSet selectedDirector = getDirector.executeQuery();
                Director director = new Director();
                ArrayList<Actor> actorArrayList = MovieDAO.selectActor(selectedMovies.getString(1));
                assert actorArrayList != null;
                Movie movie = new Movie();
                if (selectedDirector.next()){
                    director.setFirstName(selectedDirector.getString(1));
                    director.setSurname(selectedDirector.getString(2));
                    director.setDateOfBirth(selectedDirector.getString(3));
                    for (int a = 0;a < actorArrayList.size();a++){

                        movie.setActors(actorArrayList);

                    }
                    movie.setMovieID(selectedMovies.getString(1));
                    movie.setTitle(selectedMovies.getString(2));
                    movie.setDirector(director);
                    movie.setGenre(selectedMovies.getString(3));
                    movie.setYearOfProduction(selectedMovies.getInt(4));
                    movie.setRating(selectedMovies.getDouble(5));
                    movie.setImage(selectedMovies.getString(6));
                    movie.setSynopsis(selectedMovies.getString(7));
                    movieArrayList.add(movie);
                }

            }while (selectedMovies.next());

            return  movieArrayList;
        }
        return null;
    }

    public static void updateMovie(String title,String image,String genre,int yearOfProduction,String synopsis,String movieID) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/redfoxdatabase","root","@A$ceBw29");
        PreparedStatement updatedMovieDetails = connection.prepareStatement("update Movie set Title = ?,Image = ?,Genre = ?,YearOfProduction = ?,Synopsis = ? where MovieID = ?");
        updatedMovieDetails.setString(1,title);
        updatedMovieDetails.setString(2,image);
        updatedMovieDetails.setString(3,genre);
        updatedMovieDetails.setInt(4,yearOfProduction);
        updatedMovieDetails.setString(5,synopsis);
        updatedMovieDetails.setString(6,movieID);
        updatedMovieDetails.executeUpdate();
        updatedMovieDetails.close();
        
    }

    public static void addEmployee(String emailAddress,String firstName,String surname,String gender,int contact,
                                   int omangNumber,String username,String password,String adminEmailAddress) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/redfoxdatabase","root","@A$ceBw29");

        PreparedStatement addUser = connection.prepareStatement("insert into User values(?,?)");
        addUser.setString(1,"@Emp!oyee"+username);
        addUser.setString(2,password);
        addUser.executeUpdate();
        addUser.close();

        PreparedStatement addEmployee = connection.prepareStatement("insert into Employee values(?,?,?,?,?,?,?,?)");
        addEmployee.setString(1,emailAddress);
        addEmployee.setString(2,firstName);
        addEmployee.setString(3,surname);
        addEmployee.setString(4,gender);
        addEmployee.setInt(5,contact);
        addEmployee.setInt(6,omangNumber);
        addEmployee.setString(7,"@Emp!oyee"+username);
        addEmployee.setString(8,adminEmailAddress);
        addEmployee.executeUpdate();
        addEmployee.close();

    }

    public static int movieIDGenerator(String genre) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/redfoxdatabase","root","@A$ceBw29");
        PreparedStatement preparedStatement = connection.prepareStatement("select * from movie where genre = ?");
        preparedStatement.setString(1,genre);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            ArrayList<String> stringArrayList = new ArrayList<>();
            int i = 0;
            do {
                stringArrayList.add(resultSet.getString(1));
                i = stringArrayList.size() + 1;
            }while (resultSet.next());

            return i;

        }

        return 0;
    }

    public static void addMovie(String title,String genre,int yearOfProduction, String image, String synopsis,
                                String status,String adminEmailAddress,String actor1Name,String actor1Surname, String actor1DOB, String actor2Name,
                                String actor2Surname,String actor2DOB,String actor3Name, String actor3Surname,
                                String actor3DOB,String directorName,String directorSurname, String directorDOB) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/redfoxdatabase","root","@A$ceBw29");

        String movieID = genre+""+movieIDGenerator(genre);

        PreparedStatement addMovie = connection.prepareStatement("insert into Movie values(?,?,?,?,0,?,?,?,?)");
        addMovie.setString(1,movieID);
        addMovie.setString(2,title);
        addMovie.setString(3,genre);
        addMovie.setInt(4,yearOfProduction);
        addMovie.setString(5,image);
        addMovie.setString(6,synopsis);
        addMovie.setString(7,status);
        addMovie.setString(8,adminEmailAddress);
        addMovie.executeUpdate();
        addMovie.close();

        PreparedStatement addDirector = connection.prepareStatement("insert into Director values(?,?,?,?)");
        addDirector.setString(1,directorName);
        addDirector.setString(2,directorSurname);
        addDirector.setString(3,directorDOB);
        addDirector.setString(4,movieID);
        addDirector.executeUpdate();
        addDirector.close();

        PreparedStatement addActor1 = connection.prepareStatement("insert into Actor values(?,?,?,?)");
        addActor1.setString(1,actor1Name);
        addActor1.setString(2,actor1Surname);
        addActor1.setString(3,actor1DOB);
        addActor1.setString(4,movieID);
        addActor1.executeUpdate();
        addActor1.close();
        PreparedStatement addActor2 = connection.prepareStatement("insert into Actor values(?,?,?,?)");
        addActor2.setString(1,actor2Name);
        addActor2.setString(2,actor2Surname);
        addActor2.setString(3,actor2DOB);
        addActor2.setString(4,movieID);
        addActor2.executeUpdate();
        addActor2.close();
        PreparedStatement addActor3 = connection.prepareStatement("insert into Actor values(?,?,?,?)");
        addActor3.setString(1,actor3Name);
        addActor3.setString(2,actor3Surname);
        addActor3.setString(3,actor3DOB);
        addActor3.setString(4,movieID);
        addActor3.executeUpdate();
        addActor3.close();
    }



}
