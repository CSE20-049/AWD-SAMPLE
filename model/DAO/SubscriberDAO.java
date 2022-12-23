package model.DAO;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

public class SubscriberDAO {

    private static final DecimalFormat  decimalFormat = new DecimalFormat("0.00");

    public static void signUpSubscriber(String emailAddress,String firstName,String surname,int contact,String password,
                                        String movieGenre1,String movieGenre2,String movieGenre3,String movieGenre4,
                                        String movieGenre5) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/redfoxdatabase","root","@A$ceBw29");
        PreparedStatement enterUserDetails = connection.prepareStatement("insert into User values(?,?)");
            enterUserDetails.setString(1,"@User"+contact);
            enterUserDetails.setString(2,password);
            enterUserDetails.executeUpdate();
            enterUserDetails.close();

        PreparedStatement enterSubscriberDetails = connection.prepareStatement("insert into Subscriber values(?,?,?,?,?,?,?,?,?,?)");
            enterSubscriberDetails.setString(1,emailAddress);
            enterSubscriberDetails.setString(2,firstName);
            enterSubscriberDetails.setString(3,surname);
            enterSubscriberDetails.setInt(4,contact);
            enterSubscriberDetails.setString(5,movieGenre1);
            enterSubscriberDetails.setString(6,movieGenre2);
            enterSubscriberDetails.setString(7,movieGenre3);
            enterSubscriberDetails.setString(8,movieGenre4);
            enterSubscriberDetails.setString(9,movieGenre5);
            enterSubscriberDetails.setString(10,"@User"+contact);
            enterSubscriberDetails.executeUpdate();
            enterSubscriberDetails.close();
    }

    public static void movieRating(double subscriberRating,String movieID,String emailAddress) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/redfoxdatabase","root","@A$ceBw29");
        PreparedStatement getRatingInfo = connection.prepareStatement("select * from Rating where MovieID = ? and EmailAddress = ?");
        getRatingInfo.setString(1,movieID);
        getRatingInfo.setString(2,emailAddress);
        ResultSet ratingInfo = getRatingInfo.executeQuery();

        //Update rating
        if (ratingInfo.next() && ratingInfo.getString(2).equals(movieID) && ratingInfo.getString(3).equals(emailAddress)){
            PreparedStatement updateMovieRating = connection.prepareStatement("update Rating set Rating = ? where MovieID = ? and  EmailAddress = ?");
            updateMovieRating.setDouble(1,subscriberRating);
            updateMovieRating.setString(2,movieID);
            updateMovieRating.setString(3,emailAddress);
            updateMovieRating.executeUpdate();
            updateMovieRating.close();

            PreparedStatement selectedMovieRatings = connection.prepareStatement("select * from Rating where MovieID = ?");
            selectedMovieRatings.setString(1,movieID);
            ResultSet selectedMovieRatingsResults = selectedMovieRatings.executeQuery();
            ArrayList<Double> ratings = new ArrayList<>();
            while (selectedMovieRatingsResults.next()){
                //Gets all the subscribers ratings of a specific movies and adds them into the Arraylist
                ratings.add(selectedMovieRatingsResults.getDouble("Rating"));
            }
            //This iterator will be looping through our collection(arraylist)
            Iterator<Double> doubleIterator = ratings.iterator();
            double sumOfMovieRatings = 0;
            while (doubleIterator.hasNext()){
                //This array will be used to calculate the sum of all the rating from subscribers of a specific movie
                // that are in the iterator
                double additionArray[] = new double[ratings.size()];
                for (int i = 0;i < ratings.size();i++){
                    additionArray[i] = doubleIterator.next();
                    sumOfMovieRatings = sumOfMovieRatings + additionArray[i];
                }

            }
            double averageMovieRate = Double.parseDouble(decimalFormat.format(sumOfMovieRatings/ratings.size()));
            PreparedStatement insertNewRating = connection.prepareStatement("update Movie set Rating = ? where MovieID = ?");
            insertNewRating.setDouble(1,averageMovieRate);
            insertNewRating.setString(2,movieID);
            insertNewRating.executeUpdate();
            insertNewRating.close();
            System.out.println("Rating Updated");

            //Insert rating
        } else {
            PreparedStatement insertMovieRating = connection.prepareStatement("insert into Rating values(?,?,?)");
            insertMovieRating.setDouble(1,subscriberRating);
            insertMovieRating.setString(2,movieID);
            insertMovieRating.setString(3,emailAddress);
            insertMovieRating.executeUpdate();
            insertMovieRating.close();

            PreparedStatement selectedMovieRatings = connection.prepareStatement("select * from Rating where MovieID = ?");
            selectedMovieRatings.setString(1,movieID);
            ResultSet selectedMovieRatingsResults = selectedMovieRatings.executeQuery();
            ArrayList<Double> ratings = new ArrayList<>();
            while (selectedMovieRatingsResults.next()){
                //Gets all the subscribers ratings of a specific movies and adds them into the Arraylist
                ratings.add(selectedMovieRatingsResults.getDouble("Rating"));
            }
            //This iterator will be looping through our collection(arraylist)
            Iterator<Double> doubleIterator = ratings.iterator();
            double sumOfMovieRatings = 0;
            while (doubleIterator.hasNext()){
                //This array will be used to calculate the sum of all the rating from subscribers of a specific movie
                // that are in the iterator
                double additionArray[] = new double[ratings.size()];
                for (int i = 0;i < ratings.size();i++){
                    additionArray[i] = doubleIterator.next();
                    sumOfMovieRatings = sumOfMovieRatings + additionArray[i];
                }

            }
            double averageMovieRate = Double.parseDouble(decimalFormat.format(sumOfMovieRatings/ratings.size()));
            PreparedStatement insertNewRating = connection.prepareStatement("update Movie set Rating = ? where MovieID = ?");
            insertNewRating.setDouble(1,averageMovieRate);
            insertNewRating.setString(2,movieID);
            insertNewRating.executeUpdate();
            insertNewRating.close();
            System.out.println("Rating Inserted");

        }

    }

}
