package com.example.ProjetCovid.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    public void doUpdate(String query) {
        try {
            connection = connection();
            System.out.println(connection);
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void inscrireUser(User user){
        String requete = "Insert into coviddb.users (login,firstname,lastname,password, dateCreation ,dateNaissance, admin)" +
                "values ('" + user.getLogin() + "', '" + user.getFirstName() + "', '" + user.getLastName() + "', '" + user.getPassword() + "', " + user.getDateCreation() + "," + user.getDateNaissance() + ",'0')";
        doUpdate(requete);
    }

}
