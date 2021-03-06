package com.example.ProjetCovid.beans;

//todo dateCreation et dateNaissance doivent etre not null dans la DB
public class User {
    private String id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String dateCreation;
    private String dateNaissance;
    private String coroned;
    private String admin;

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    /**
     * Prend une @mail en param
     * @param login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getCoroned() {
        return coroned;
    }

    public void setCoroned(String coroned) {
        this.coroned = coroned;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateCreation='" + dateCreation + '\'' +
                ", dateNaissance='" + dateNaissance + '\'' +
                ", coroned='" + coroned + '\'' +
                ", admin='" + admin + '\'' +
                '}';
    }
}
