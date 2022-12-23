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

    public static ArrayList<Movie> selectTopFiveRecommendedMovies(String genre1,String genre2,String genre3,String genre4,
                                                                  String genre5) throws SQLException {

        String allGenres = genre1+","+genre2+","+genre3+","+genre4+","+genre5;
        ArrayList<Movie> movieArrayList = new ArrayList<>();
        Movie action = new Movie();
        Movie adventure = new Movie();
        Movie comedy = new Movie();
        Movie drama = new Movie();
        Movie horror = new Movie();

        switch (allGenres){
            //==============================================For Action================================================\\
            //Single\\
            case "Action,,,,":
                action = selectTopActionMovie();
                movieArrayList.add(action);
                return movieArrayList;

            //Double\\
            case "Action,Adventure,,,":
                action = selectTopActionMovie();
                adventure = selectTopAdventureMovie();
                movieArrayList.add(action);
                movieArrayList.add(adventure);
                return movieArrayList;

            case "Action,,Comedy,,":
                action = selectTopActionMovie();
                comedy = selectTopComedyMovie();
                movieArrayList.add(action);
                movieArrayList.add(comedy);
                return movieArrayList;

            case "Action,,,Drama,":
                action = selectTopActionMovie();
                drama = selectTopDramaMovie();
                movieArrayList.add(action);
                movieArrayList.add(drama);
                return movieArrayList;

            case "Action,,,,Horror":
                action = selectTopActionMovie();
                horror = selectTopHorrorMovie();
                movieArrayList.add(action);
                movieArrayList.add(horror);
                return movieArrayList;

            //Triple\\
            case "Action,Adventure,Comedy,,":
                action = selectTopActionMovie();
                adventure = selectTopAdventureMovie();
                comedy = selectTopComedyMovie();
                movieArrayList.add(action);
                movieArrayList.add(adventure);
                movieArrayList.add(comedy);
                return movieArrayList;

            case "Action,Adventure,,Drama,":
                action = selectTopActionMovie();
                adventure = selectTopAdventureMovie();
                drama = selectTopDramaMovie();
                movieArrayList.add(action);
                movieArrayList.add(adventure);
                movieArrayList.add(drama);
                return movieArrayList;

            case "Action,Adventure,,,Horror":
                action = selectTopActionMovie();
                adventure = selectTopAdventureMovie();
                horror = selectTopHorrorMovie();
                movieArrayList.add(action);
                movieArrayList.add(adventure);
                movieArrayList.add(horror);
                return movieArrayList;

            case "Action,,Comedy,Drama,":
                action = selectTopActionMovie();
                comedy = selectTopComedyMovie();
                drama = selectTopDramaMovie();
                movieArrayList.add(action);
                movieArrayList.add(comedy);
                movieArrayList.add(drama);
                return movieArrayList;

            case "Action,,Comedy,,Horror":
                action = selectTopActionMovie();
                comedy = selectTopComedyMovie();
                horror = selectTopHorrorMovie();
                movieArrayList.add(action);
                movieArrayList.add(comedy);
                movieArrayList.add(horror);
                return movieArrayList;

            case "Action,,,Drama,Horror":
                action = selectTopActionMovie();
                drama = selectTopDramaMovie();
                horror = selectTopHorrorMovie();
                movieArrayList.add(action);
                movieArrayList.add(drama);
                movieArrayList.add(horror);
                return movieArrayList;

            //Quad\\
            case "Action,Adventure,,Drama,Horror":
                action = selectTopActionMovie();
                adventure = selectTopAdventureMovie();
                drama = selectTopDramaMovie();
                horror = selectTopHorrorMovie();
                movieArrayList.add(action);
                movieArrayList.add(adventure);
                movieArrayList.add(drama);
                movieArrayList.add(horror);
                return movieArrayList;

            case "Action,Adventure,Comedy,,Horror":
                action = selectTopActionMovie();
                adventure = selectTopAdventureMovie();
                comedy = selectTopComedyMovie();
                horror = selectTopHorrorMovie();
                movieArrayList.add(action);
                movieArrayList.add(adventure);
                movieArrayList.add(comedy);
                movieArrayList.add(horror);
                return movieArrayList;

            case "Action,,Comedy,Drama,Horror":
                action = selectTopActionMovie();
                comedy = selectTopComedyMovie();
                drama = selectTopDramaMovie();
                horror = selectTopHorrorMovie();
                movieArrayList.add(action);
                movieArrayList.add(comedy);
                movieArrayList.add(drama);
                movieArrayList.add(horror);
                return movieArrayList;

            case "Action,Adventure,Comedy,Drama,":
                action = selectTopActionMovie();
                adventure = selectTopAdventureMovie();
                comedy = selectTopComedyMovie();
                drama = selectTopDramaMovie();
                movieArrayList.add(action);
                movieArrayList.add(adventure);
                movieArrayList.add(comedy);
                movieArrayList.add(drama);
                return movieArrayList;

            //Five\\
            case "Action,Adventure,Comedy,Drama,Horror":
                action = selectTopActionMovie();
                adventure = selectTopAdventureMovie();
                comedy = selectTopComedyMovie();
                drama = selectTopDramaMovie();
                horror = selectTopHorrorMovie();
                movieArrayList.add(action);
                movieArrayList.add(adventure);
                movieArrayList.add(comedy);
                movieArrayList.add(drama);
                movieArrayList.add(horror);
                return movieArrayList;
            //========================================================================================================\\

            //==============================================For Adventure=============================================\\
            //Single\\
            case ",Adventure,,,":
                adventure = selectTopAdventureMovie();
                movieArrayList.add(adventure);
                return movieArrayList;

            //Double\\
            case ",Adventure,Comedy,,":
                adventure = selectTopAdventureMovie();
                comedy = selectTopComedyMovie();
                movieArrayList.add(adventure);
                movieArrayList.add(comedy);
                return movieArrayList;

            case ",Adventure,,Drama,":
                adventure = selectTopAdventureMovie();
                drama = selectTopDramaMovie();
                movieArrayList.add(adventure);
                movieArrayList.add(drama);
                return movieArrayList;

            case ",Adventure,,,Horror":
                adventure = selectTopAdventureMovie();
                horror = selectTopHorrorMovie();
                movieArrayList.add(adventure);
                movieArrayList.add(horror);
                return movieArrayList;

            //Triple\\
            case ",Adventure,Comedy,Drama,":
                adventure = selectTopAdventureMovie();
                comedy = selectTopComedyMovie();
                drama = selectTopDramaMovie();
                movieArrayList.add(adventure);
                movieArrayList.add(comedy);
                movieArrayList.add(drama);
                return movieArrayList;

            case ",Adventure,Comedy,,Horror":
                adventure = selectTopAdventureMovie();
                comedy = selectTopComedyMovie();
                horror = selectTopHorrorMovie();
                movieArrayList.add(adventure);
                movieArrayList.add(comedy);
                movieArrayList.add(horror);
                return movieArrayList;

            case ",Adventure,,Drama,Horror":
                adventure = selectTopAdventureMovie();
                drama = selectTopDramaMovie();
                horror = selectTopHorrorMovie();
                movieArrayList.add(adventure);
                movieArrayList.add(drama);
                movieArrayList.add(horror);
                return movieArrayList;

            //Quad\\
            case ",Adventure,Comedy,Drama,Horror":
                adventure = selectTopAdventureMovie();
                comedy = selectTopComedyMovie();
                drama = selectTopDramaMovie();
                horror = selectTopHorrorMovie();
                movieArrayList.add(adventure);
                movieArrayList.add(comedy);
                movieArrayList.add(drama);
                movieArrayList.add(horror);
                return movieArrayList;
            //========================================================================================================\\

            //==============================================For Comedy================================================\\
            //Single\\
            case ",,Comedy,,":
                comedy = selectTopComedyMovie();
                movieArrayList.add(comedy);
                return movieArrayList;

            //Double\\
            case ",,Comedy,Drama,":
                comedy = selectTopComedyMovie();
                drama =selectTopDramaMovie();
                movieArrayList.add(comedy);
                movieArrayList.add(drama);
                return movieArrayList;

            case ",,Comedy,,Horror":
                comedy = selectTopComedyMovie();
                horror = selectTopHorrorMovie();
                movieArrayList.add(comedy);
                movieArrayList.add(horror);
                return movieArrayList;

            //Triple\\
            case ",,Comedy,Drama,Horror":
                comedy = selectTopComedyMovie();
                drama = selectTopDramaMovie();
                horror = selectTopHorrorMovie();
                movieArrayList.add(comedy);
                movieArrayList.add(drama);
                movieArrayList.add(horror);
                return movieArrayList;

            //========================================================================================================\\

            //==============================================For Drama=================================================\\
            //Single\\
            case ",,,Drama,":
                drama = selectTopDramaMovie();
                movieArrayList.add(drama);
                return movieArrayList;

            //Double\\
            case ",,,Drama,Horror":
                drama = selectTopDramaMovie();
                horror = selectTopHorrorMovie();
                movieArrayList.add(drama);
                movieArrayList.add(horror);
                return movieArrayList;

            //========================================================================================================\\

            //==============================================For Horror=================================================\\
            //Single\\
            case ",,,,Horror":
                horror = selectTopHorrorMovie();
                movieArrayList.add(horror);
                return movieArrayList;

            //========================================================================================================\\
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
