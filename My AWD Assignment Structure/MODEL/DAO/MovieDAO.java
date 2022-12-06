package model.DAO;

import model.BEANS.Actor;
import model.BEANS.Director;
import model.BEANS.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class MovieDAO {

    public static ArrayList<Actor> selectActor(String movieID) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/redfoxdatabase","root","@A$ceBw29");
        PreparedStatement getActors = connection.prepareStatement("select * from Actor inner join Movie on Actor.MovieID = Movie.MovieID where Movie.MovieID = ?");
        getActors.setString(1,movieID);

        ResultSet selectedActors = getActors.executeQuery();
        if (selectedActors.next()){

            ArrayList<Actor> actorArrayList = new ArrayList<>();
            do {

                Actor actor = new Actor();
                actor.setFirstName(selectedActors.getString(1));
                actor.setSurname(selectedActors.getString(2));
                actor.setDateOfBirth(selectedActors.getString(3));
                actorArrayList.add(actor);

            }while (selectedActors.next());
            return actorArrayList;
        }

        return null;
    }

    public static ArrayList<Movie> selectMoviesByGenre(String movieGenre) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/redfoxdatabase","root","@A$ceBw29");
        PreparedStatement getMovies = connection.prepareStatement("select * from movie where Genre = ? and Status != 'Upcoming'");
        getMovies.setString(1,movieGenre);
        ResultSet selectedMovies = getMovies.executeQuery();

        if (selectedMovies.next()){
            ArrayList<Movie> movieArrayList = new ArrayList<>();
            do {
                PreparedStatement getDirector = connection.prepareStatement("select * from Director inner join Movie on Director.MovieID = Movie.MovieID where Movie.MovieID = ?");
                getDirector.setString(1,selectedMovies.getString(1));
                ResultSet selectedDirector = getDirector.executeQuery();
                Director director = new Director();
                ArrayList<Actor> actorArrayList = selectActor(selectedMovies.getString(1));
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

    public static ArrayList<Movie> selectMoviesByGenreAndStatus(String status) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/redfoxdatabase","root","@A$ceBw29");
        PreparedStatement getMovies = connection.prepareStatement("select * from movie where Status = ?");
        getMovies.setString(1,status);
        ResultSet selectedMovies = getMovies.executeQuery();

        if (selectedMovies.next()){
            ArrayList<Movie> movieArrayList = new ArrayList<>();
            do {
                PreparedStatement getDirector = connection.prepareStatement("select * from Director inner join Movie on Director.MovieID = Movie.MovieID where Movie.MovieID = ?");
                getDirector.setString(1,selectedMovies.getString(1));
                ResultSet selectedDirector = getDirector.executeQuery();
                Director director = new Director();
                ArrayList<Actor> actorArrayList = selectActor(selectedMovies.getString(1));
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

    //These methods below a responsible for recommending movies to subscribers
    public static Movie selectTopActionMovie() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/redfoxdatabase","root","@A$ceBw29");
        PreparedStatement selectActionMovies = connection.prepareStatement("select * from Movie where Genre = 'Action'");
        ResultSet selectedActionMovies = selectActionMovies.executeQuery();

        if (selectedActionMovies.next()){
            ArrayList<Double> actionMoviesRating = new ArrayList<>();
            do {
                //We are only going to get the acting Movies ratings
                actionMoviesRating.add(selectedActionMovies.getDouble(5));
            }while (selectedActionMovies.next());

            Iterator<Double> loopThroughAllActionMoviesRatings = actionMoviesRating.iterator();

            while (loopThroughAllActionMoviesRatings.hasNext()){
                Double[] results = new Double[actionMoviesRating.size()];
                for (int i = 0;i < actionMoviesRating.size();i++){
                    results[i] = loopThroughAllActionMoviesRatings.next();
                }
                Arrays.sort(results);
                PreparedStatement selectTopMovie = connection.prepareStatement("Select * from Movie where Rating = ? and genre = 'Action'");
                selectTopMovie.setDouble(1,results[actionMoviesRating.size()-1]);
                ResultSet resultSet = selectTopMovie.executeQuery();
                Movie movie = new Movie();
                if (resultSet.next()){

                        PreparedStatement getDirector = connection.prepareStatement("select * from Director inner join Movie on Director.MovieID = Movie.MovieID where Movie.MovieID = ?");
                        getDirector.setString(1,resultSet.getString(1));
                        ResultSet selectedDirector = getDirector.executeQuery();
                        Director director = new Director();
                        ArrayList<Actor> actorArrayList = selectActor(resultSet.getString(1));
                        assert actorArrayList != null;
                        if (selectedDirector.next()){
                            director.setFirstName(selectedDirector.getString(1));
                            director.setSurname(selectedDirector.getString(2));
                            director.setDateOfBirth(selectedDirector.getString(3));
                            for (int a = 0;a < actorArrayList.size();a++){

                                movie.setActors(actorArrayList);

                            }
                            movie.setMovieID(resultSet.getString(1));
                            movie.setTitle(resultSet.getString(2));
                            movie.setDirector(director);
                            movie.setGenre(resultSet.getString(3));
                            movie.setYearOfProduction(resultSet.getInt(4));
                            movie.setRating(resultSet.getDouble(5));
                            movie.setImage(resultSet.getString(6));
                            movie.setSynopsis(resultSet.getString(7));
                            movie.setStatus(resultSet.getString(8));
                        }
                    return movie;
                }

            }
        }
       return null;
    }
    public static Movie selectTopAdventureMovie() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/redfoxdatabase","root","@A$ceBw29");
        PreparedStatement selectAdventureMovies = connection.prepareStatement("select * from Movie where Genre = 'Adventure'");
        ResultSet selectedAdventureMovies = selectAdventureMovies.executeQuery();

        if (selectedAdventureMovies.next()){
            ArrayList<Double> adventureMoviesRating = new ArrayList<>();
            do {
                //We are only going to get the acting Movies ratings
                adventureMoviesRating.add(selectedAdventureMovies.getDouble(5));
            }while (selectedAdventureMovies.next());

            Iterator<Double> loopThroughAllAdventureMoviesRatings = adventureMoviesRating.iterator();

            while (loopThroughAllAdventureMoviesRatings.hasNext()){
                Double[] results = new Double[adventureMoviesRating.size()];
                for (int i = 0;i < adventureMoviesRating.size();i++){
                    results[i] = loopThroughAllAdventureMoviesRatings.next();
                }
                Arrays.sort(results);
                PreparedStatement selectTopMovie = connection.prepareStatement("Select * from Movie where Rating = ? and genre = 'Adventure'");
                selectTopMovie.setDouble(1,results[adventureMoviesRating.size()-1]);
                ResultSet resultSet = selectTopMovie.executeQuery();
                Movie movie = new Movie();
                if (resultSet.next()){

                    PreparedStatement getDirector = connection.prepareStatement("select * from Director inner join Movie on Director.MovieID = Movie.MovieID where Movie.MovieID = ?");
                    getDirector.setString(1,resultSet.getString(1));
                    ResultSet selectedDirector = getDirector.executeQuery();
                    Director director = new Director();
                    ArrayList<Actor> actorArrayList = selectActor(resultSet.getString(1));
                    assert actorArrayList != null;
                    if (selectedDirector.next()){
                        director.setFirstName(selectedDirector.getString(1));
                        director.setSurname(selectedDirector.getString(2));
                        director.setDateOfBirth(selectedDirector.getString(3));
                        for (int a = 0;a < actorArrayList.size();a++){

                            movie.setActors(actorArrayList);

                        }
                        movie.setMovieID(resultSet.getString(1));
                        movie.setTitle(resultSet.getString(2));
                        movie.setDirector(director);
                        movie.setGenre(resultSet.getString(3));
                        movie.setYearOfProduction(resultSet.getInt(4));
                        movie.setRating(resultSet.getDouble(5));
                        movie.setImage(resultSet.getString(6));
                        movie.setSynopsis(resultSet.getString(7));
                        movie.setStatus(resultSet.getString(8));
                    }
                    return movie;
                }

            }
        }
        return null;
    }
    public static Movie selectTopComedyMovie() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/redfoxdatabase","root","@A$ceBw29");
        PreparedStatement selectComedyMovies = connection.prepareStatement("select * from Movie where Genre = 'Comedy'");
        ResultSet selectedComedyMovies = selectComedyMovies.executeQuery();

        if (selectedComedyMovies.next()){
            ArrayList<Double> comedyMoviesRating = new ArrayList<>();
            do {
                //We are only going to get the acting Movies ratings
                comedyMoviesRating.add(selectedComedyMovies.getDouble(5));
            }while (selectedComedyMovies.next());

            Iterator<Double> loopThroughAllComedyMoviesRatings = comedyMoviesRating.iterator();

            while (loopThroughAllComedyMoviesRatings.hasNext()){
                Double[] results = new Double[comedyMoviesRating.size()];
                for (int i = 0;i < comedyMoviesRating.size();i++){
                    results[i] = loopThroughAllComedyMoviesRatings.next();
                }
                Arrays.sort(results);
                PreparedStatement selectTopMovie = connection.prepareStatement("Select * from Movie where Rating = ? and genre = 'Comedy'");
                selectTopMovie.setDouble(1,results[comedyMoviesRating.size()-1]);
                ResultSet resultSet = selectTopMovie.executeQuery();
                Movie movie = new Movie();
                if (resultSet.next()){

                    PreparedStatement getDirector = connection.prepareStatement("select * from Director inner join Movie on Director.MovieID = Movie.MovieID where Movie.MovieID = ?");
                    getDirector.setString(1,resultSet.getString(1));
                    ResultSet selectedDirector = getDirector.executeQuery();
                    Director director = new Director();
                    ArrayList<Actor> actorArrayList = selectActor(resultSet.getString(1));
                    assert actorArrayList != null;
                    if (selectedDirector.next()){
                        director.setFirstName(selectedDirector.getString(1));
                        director.setSurname(selectedDirector.getString(2));
                        director.setDateOfBirth(selectedDirector.getString(3));
                        for (int a = 0;a < actorArrayList.size();a++){

                            movie.setActors(actorArrayList);

                        }
                        movie.setMovieID(resultSet.getString(1));
                        movie.setTitle(resultSet.getString(2));
                        movie.setDirector(director);
                        movie.setGenre(resultSet.getString(3));
                        movie.setYearOfProduction(resultSet.getInt(4));
                        movie.setRating(resultSet.getDouble(5));
                        movie.setImage(resultSet.getString(6));
                        movie.setSynopsis(resultSet.getString(7));
                        movie.setStatus(resultSet.getString(8));

                    }
                    return movie;
                }

            }
        }
        return null;
    }
    public static Movie selectTopDramaMovie() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/redfoxdatabase","root","@A$ceBw29");
        PreparedStatement selectDramaMovies = connection.prepareStatement("select * from Movie where Genre = 'Drama'");
        ResultSet selectedHorrorMovies = selectDramaMovies.executeQuery();

        if (selectedHorrorMovies.next()){
            ArrayList<Double> dramaMoviesRating = new ArrayList<>();
            do {
                //We are only going to get the acting Movies ratings
                dramaMoviesRating.add(selectedHorrorMovies.getDouble(5));
            }while (selectedHorrorMovies.next());

            Iterator<Double> loopThroughAllDramaMoviesRatings = dramaMoviesRating.iterator();

            while (loopThroughAllDramaMoviesRatings.hasNext()){
                Double[] results = new Double[dramaMoviesRating.size()];
                for (int i = 0;i < dramaMoviesRating.size();i++){
                    results[i] = loopThroughAllDramaMoviesRatings.next();
                }
                Arrays.sort(results);
                PreparedStatement selectTopMovie = connection.prepareStatement("Select * from Movie where Rating = ? and genre = 'Drama'");
                selectTopMovie.setDouble(1,results[dramaMoviesRating.size()-1]);
                ResultSet resultSet = selectTopMovie.executeQuery();
                Movie movie = new Movie();
                if (resultSet.next()){

                    PreparedStatement getDirector = connection.prepareStatement("select * from Director inner join Movie on Director.MovieID = Movie.MovieID where Movie.MovieID = ?");
                    getDirector.setString(1,resultSet.getString(1));
                    ResultSet selectedDirector = getDirector.executeQuery();
                    Director director = new Director();
                    ArrayList<Actor> actorArrayList = selectActor(resultSet.getString(1));
                    assert actorArrayList != null;
                    if (selectedDirector.next()){
                        director.setFirstName(selectedDirector.getString(1));
                        director.setSurname(selectedDirector.getString(2));
                        director.setDateOfBirth(selectedDirector.getString(3));
                        for (int a = 0;a < actorArrayList.size();a++){

                            movie.setActors(actorArrayList);

                        }
                        movie.setMovieID(resultSet.getString(1));
                        movie.setTitle(resultSet.getString(2));
                        movie.setDirector(director);
                        movie.setGenre(resultSet.getString(3));
                        movie.setYearOfProduction(resultSet.getInt(4));
                        movie.setRating(resultSet.getDouble(5));
                        movie.setImage(resultSet.getString(6));
                        movie.setSynopsis(resultSet.getString(7));
                        movie.setStatus(resultSet.getString(8));
                    }
                    return movie;
                }

            }
        }
        return null;
    }
    public static Movie selectTopHorrorMovie() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/redfoxdatabase","root","@A$ceBw29");
        PreparedStatement selectHorrorMovies = connection.prepareStatement("select * from Movie where Genre = 'Horror'");
        ResultSet selectedHorrorMovies = selectHorrorMovies.executeQuery();

        if (selectedHorrorMovies.next()){
            ArrayList<Double> horrorMoviesRating = new ArrayList<>();
            do {
                //We are only going to get the acting Movies ratings
                horrorMoviesRating.add(selectedHorrorMovies.getDouble(5));
            }while (selectedHorrorMovies.next());

            Iterator<Double> loopThroughAllHorrorMoviesRatings = horrorMoviesRating.iterator();

            while (loopThroughAllHorrorMoviesRatings.hasNext()){
                Double[] results = new Double[horrorMoviesRating.size()];
                for (int i = 0;i < horrorMoviesRating.size();i++){
                    results[i] = loopThroughAllHorrorMoviesRatings.next();
                }
                Arrays.sort(results);
                PreparedStatement selectTopMovie = connection.prepareStatement("Select * from Movie where Rating = ? and genre = 'Horror'");
                selectTopMovie.setDouble(1,results[horrorMoviesRating.size()-1]);
                ResultSet resultSet = selectTopMovie.executeQuery();
                Movie movie = new Movie();
                if (resultSet.next()){

                    PreparedStatement getDirector = connection.prepareStatement("select * from Director inner join Movie on Director.MovieID = Movie.MovieID where Movie.MovieID = ?");
                    getDirector.setString(1,resultSet.getString(1));
                    ResultSet selectedDirector = getDirector.executeQuery();
                    Director director = new Director();
                    ArrayList<Actor> actorArrayList = selectActor(resultSet.getString(1));
                    assert actorArrayList != null;
                    if (selectedDirector.next()){
                        director.setFirstName(selectedDirector.getString(1));
                        director.setSurname(selectedDirector.getString(2));
                        director.setDateOfBirth(selectedDirector.getString(3));
                        for (int a = 0;a < actorArrayList.size();a++){

                            movie.setActors(actorArrayList);

                        }
                        movie.setMovieID(resultSet.getString(1));
                        movie.setTitle(resultSet.getString(2));
                        movie.setDirector(director);
                        movie.setGenre(resultSet.getString(3));
                        movie.setYearOfProduction(resultSet.getInt(4));
                        movie.setRating(resultSet.getDouble(5));
                        movie.setImage(resultSet.getString(6));
                        movie.setSynopsis(resultSet.getString(7));
                        movie.setStatus(resultSet.getString(8));
                    }
                    return movie;
                }

            }
        }
        return null;
    }

   
    public static void main(String[] args) throws SQLException {

        ArrayList<Movie> movies = selectTopFiveRecommendedMovies("Action","","","","");

        assert movies != null;
        Iterator<Movie> movieInteger = movies.iterator();

        while (movieInteger.hasNext()){
            System.out.println(movieInteger.next());
        }

    }
}
