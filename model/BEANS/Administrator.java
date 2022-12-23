package model.BEANS;

import java.io.Serializable;

public class Administrator extends User implements Serializable {

    private String firstName;
    private String surname;
    private String gender;
    private String emailAddress;
    private int contact;
    private int omangNumber;
    private String username;

    public Administrator(){

    }
    public Administrator(String userName,String password,String firstName,String surname){
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public int getOmangNumber() {
        return omangNumber;
    }

    public void setOmangNumber(int omangNumber) {
        this.omangNumber = omangNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
