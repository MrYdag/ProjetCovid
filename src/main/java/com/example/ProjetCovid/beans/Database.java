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

    public Connection connection() throws SQLException {
        //
        /* Connexion à la base de données */
        String url = "jdbc:mysql://localhost:3306";
        String utilisateur = "covid";
        String motDePasse = "covid";
        Connection connexion = null;
        connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Pilotes jdbc ne démarre pas");
            System.exit(-1);
        }

        try {
            //connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
            connection = DriverManager.getConnection( url, utilisateur, motDePasse );
            /* Ici, nous placerons nos requêtes vers la BDD */
            /* ... */
        } catch ( SQLException e ) {
            /* Gérer les éventuelles erreurs ici */
            System.out.println("erreur");
        } finally {
            //if ( connexion != null )
            if (connection != null)
                try {
                    /* Fermeture de la connexion */
                    //connexion.close();
                    connection.close();
                } catch ( SQLException ignore ) {
                    /* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
                }
        }
        //return connexion;
        return connection;
    }
    public void doUpdate(String query) {
        try {
            connection = connection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void inscrireUser(User user){
        String requete = "Insert into coviddb.users (login,firstname,lastname,password, dateCreation ,dateNaissance, admin)" +
                "values ('" + user.getLogin() + "', '" + user.getFirstName() + "', '" + user.getLastName() + "', '" + user.getPassword() + "', '" + user.getDateCreation() + "','" + user.getDateNaissance() + "','false')";
        doUpdate(requete);
    }

}
