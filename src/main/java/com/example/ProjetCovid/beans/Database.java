package com.example.ProjetCovid.beans;

import java.sql.*;

public class Database {

    private PreparedStatement statement;
    private Database database;
    private Connection connection;

    public Database(){

    }

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
            System.out.println("connexion réussie");
        } catch (SQLException e) {
            System.out.println("Connection à la BDD impossible");
            System.exit(-1);
        }
        return con;
    }

    /**
     * Modifier la Database
     * @param query
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
     * @param query
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
    public void inscrireUser(User user){
        String requete = "Insert into coviddb.users (login,firstname,lastname,password, dateCreation ,dateNaissance, admin)" +
                "values ('" + user.getLogin() + "', '" + user.getFirstName() + "', '" + user.getLastName() + "', '" + user.getPassword() + "', " + user.getDateCreation() + "," + user.getDateNaissance() + ",'0')";
        doUpdate(requete);
    }

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
                    user.setAdmin(resultSet.getString("admin"));
            }
            System.out.println(user.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

}
