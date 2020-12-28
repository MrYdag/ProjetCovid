package com.example.ProjetCovid.beans;

import java.sql.*;

public class Database {

    private PreparedStatement statement;
    private Database database;
    private Connection connection;

    public Database(){

    }

    /**
     * Permet de connecter la database
     * @return la database
     */
    public Connection connection() {
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Pilotes jdbc ne démarre pas");
            System.exit(-1);
        }
        try {
            String DBurl = "jdbc:mysql://localhost:3306?useSSL=false&useTimeZone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true&";
            con = DriverManager.getConnection(DBurl, "covid", "covid");
        } catch (SQLException e) {
            System.exit(-1);
        }
        return con;
    }

    /**
     * Modifier/Remplir la Database
     * @param query Requete que l'on va executer
     */
    public void doUpdate(String query) {
        try {
            connection = connection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Questionner la Database
     * @param query Requete que l'on va executer
     */
    public ResultSet doQuery(String query){
        try {
            connection = connection();
            statement = connection.prepareStatement(query);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Permet de creer un nouvel utilisateur
     * @param user
     */
    public void inscrireUser(User user){
        String requete = "Insert into coviddb.users (login,firstname,lastname,password, dateCreation ,dateNaissance, admin, coroned)" +
                "values ('" + user.getLogin() + "', '" + user.getFirstName() + "', '" + user.getLastName() + "', '" + user.getPassword() + "', " +"'"+user.getDateCreation()+"'" + "," + "'"+user.getDateNaissance() +"'"+ ",'0','0')";

        System.out.println("REQUETE = " + requete);
        doUpdate(requete);
    }

    /**
     * Permet de connecter un utilisateur
     * @param login @mail d'un utilisateur
     * @param password mot de passe d'un utilisateur
     * @return
     */
    public User connecterUser(String login, String password){
        String requete = "Select * from coviddb.users where login = '" + login + "' AND password = '"+ password +"'";
        ResultSet resultSet = doQuery(requete);
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        try {
            if(resultSet.next()) {
                    user.setId(resultSet.getString("idUser"));
                    user.setFirstName(resultSet.getString("firstname"));
                    user.setLastName(resultSet.getString("lastname"));
                    user.setDateCreation(resultSet.getString("dateCreation"));
                    user.setDateNaissance(resultSet.getString("dateNaissance"));
                    user.setCoroned(resultSet.getString("coroned"));
                    user.setAdmin(resultSet.getString("admin"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    /**
     * Permet de modifier les informations d'un utilisateur
     * @param id ID d'un utilisateur
     * @param login @mail d'un utilisateur
     * @param firstname prenom d'un utilisateur
     * @param lastname nom d'un utilisateur
     * @param password mot de passe d'un utilisateur
     * @param dateNaissance date de naissance d'un utilisateur
     */
    public void modifierUser(String id,String login,String firstname, String lastname, String password, String dateNaissance){
        String requete = "Update coviddb.users set login =" + "'"+ login + "',"
                +"firstname =" +"'" + firstname + "',"
                +"lastname =" +"'" + lastname + "',"
                +"password =" +"'" + password + "',"
                +"dateNaissance =" +"'" + dateNaissance + "'"
                + "where idUser = " + "'" + id +"'";
        doUpdate(requete);
    }

    /**
     * Permet de declarer un utilisateur positif à la Covid-19
     * @param user
     */
    public void coroned(User user){
        String requete = "Update coviddb.users set coroned = '1' where idUser = " + "'" + user.getId() + "'";
        doUpdate(requete);
    }

    /**
     * Permet de connaitre l'ID d'un utilisateur à partir de son @mail
     * @param login @mail d'un utilisateur
     * @return
     */
    public String getID(String login){
        String requete = "Select idUser from coviddb.users where login = '" + login +"'";
        ResultSet resultSet =  doQuery(requete);
        String id = null;
        try {
            if(resultSet.next()) {
                id = resultSet.getString("idUser");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }


}
