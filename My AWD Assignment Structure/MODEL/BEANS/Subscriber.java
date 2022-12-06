package model.BEANS;

import java.io.Serializable;

public class Subscriber extends User implements Serializable {

    private String firstName;
    private String surname;
    private String emailAddress;
    private int contact;
    private String firstPreferredMovieGenre;
    private String secondPreferredMovieGenre;
    private String thirdPreferredMovieGenre;
    private String fourthPreferredMovieGenre;
    private String fifthPreferredMovieGenre;

    public Subscriber(){

    }
    public Subscriber(String userName, String password, String firstName, String surname){
        super(userName, password);
        this.firstName = firstName;
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public String getFirstPreferredMovieGenre() {
        return firstPreferredMovieGenre;
    }

    public void setFirstPreferredMovieGenre(String firstPreferredMovieGenre) {
        this.firstPreferredMovieGenre = firstPreferredMovieGenre;
    }

    public String getSecondPreferredMovieGenre() {
        return secondPreferredMovieGenre;
    }

    public void setSecondPreferredMovieGenre(String secondPreferredMovieGenre) {
        this.secondPreferredMovieGenre = secondPreferredMovieGenre;
    }

    public String getThirdPreferredMovieGenre() {
        return thirdPreferredMovieGenre;
    }

    public void setThirdPreferredMovieGenre(String thirdPreferredMovieGenre) {
        this.thirdPreferredMovieGenre = thirdPreferredMovieGenre;
    }

    public String getFourthPreferredMovieGenre() {
        return fourthPreferredMovieGenre;
    }

    public void setFourthPreferredMovieGenre(String fourthPreferredMovieGenre) {
        this.fourthPreferredMovieGenre = fourthPreferredMovieGenre;
    }

    public String getFifthPreferredMovieGenre() {
        return fifthPreferredMovieGenre;
    }

    public void setFifthPreferredMovieGenre(String fifthPreferredMovieGenre) {
        this.fifthPreferredMovieGenre = fifthPreferredMovieGenre;
    }


}
