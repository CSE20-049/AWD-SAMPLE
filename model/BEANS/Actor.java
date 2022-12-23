package model.BEANS;

public class Actor {

    private String firstName;
    private String surname;
    private String dateOfBirth;

    public Actor(){

    }
    public Actor(String firstName,String lastname,String dateOfBirth){
        this.firstName = firstName;
        this.surname = lastname;
        this.dateOfBirth = dateOfBirth;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return ""+getFirstName()+" "+getSurname()+"";
    }

}
