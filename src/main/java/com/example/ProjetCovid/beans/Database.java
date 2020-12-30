package com.example.ProjetCovid.beans;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
     */
    public void inscrireUser(User user){
        String requete = "Insert into coviddb.users (login,firstname,lastname,password, dateCreation ,dateNaissance, admin, coroned)" +
                "values ('" + user.getLogin() + "', '" + user.getFirstName() + "', '" + user.getLastName() + "', '" + user.getPassword() + "', " +"'"+user.getDateCreation()+"'" + "," + "'"+user.getDateNaissance() +"'"+ ",'0','0')";
        doUpdate(requete);
    }

    /**
     * Permet de connecter un utilisateur
     * @param login @mail d'un utilisateur
     * @param password mot de passe d'un utilisateur
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
     * Permet de trouver un User à partir de son @mail
     * @param login @mail
     * @return l'utilisateur en question
     */
    public User getUser(String login){
        String requete = "Select * from coviddb.users where login = '" + login + "'";
        ResultSet resultSet = doQuery(requete);
        User user = new User();
        user.setLogin(login);
        try {
            if(resultSet.next()) {
                user.setId(resultSet.getString("idUser"));
                user.setPassword(resultSet.getString("password"));
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
     * Permet de trouver un User à partir de son ID
     * @param id ID de l'utilisateur
     * @return l'utilisateur en question
     */
    public User getUserbyID(String id){
        String requete = "Select * from coviddb.users where idUser = '" + id + "'";
        ResultSet resultSet = doQuery(requete);
        User user = new User();
        user.setId(id);
        try {
            if(resultSet.next()) {
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
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
     * @param user Utilisateur
     */
    public void coroned(User user){
        String requete = "Update coviddb.users set coroned = '1' where idUser = " + "'" + user.getId() + "'";
        doUpdate(requete);
    }

    /**
     * Permet de connaitre l'ID d'un utilisateur à partir de son @mail
     * @param login @mail d'un utilisateur
     * @return l'ID
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

    /**
     * Permet de devenir ami avec un autre utilisateur.
     * @param user Utilisateur
     * @param ami Utilisateur
     */
    public void addFriend(User user,User ami){
        String requete = "Insert into coviddb.friends (idFriend1,idFriend2) values ('" + user.getId() + "','" + ami.getId() +"')" ;
        doUpdate(requete);
    }

    /**
     * Permet de retirer un ami de sa liste d'ami
     * @param user L'utilisateur
     * @param ami L'ami
     */
    public void removeFriend(User user, User ami) {
        String requete = "Delete from coviddb.friends where (idFriend1 = '" + user.getId() + "' AND idFriend2 = '" + ami.getId() +"') OR (idFriend1 = '" + ami.getId() + "' AND idFriend2 = '" + user.getId() +"')";
        System.out.println(requete);
        doUpdate(requete);
    }

    /**
     * Permet de savoir si deux utilisateurs sont amis.
     * @param user Utilisateur
     * @param ami Utilisateur
     * @return Si les deux utilisateurs sont amis (True/False)
     */
    public Boolean isFriend(User user,User ami) {
        String requete = "Select * from coviddb.friends where (idFriend1 = " + "'" + user.getId() +"'" + "AND idFriend2 = "  + "'" + ami.getId() +"')"+
                "OR" + "(idFriend2 = " + "'" + user.getId() +"'" + "AND idFriend1 = "  + "'" + ami.getId() +"')" ;
        ResultSet resultSet =  doQuery(requete);
        boolean res = false;
        try {
            if(resultSet.next()){
                res = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }

    /**
     * @param user l'utilisateur à qui ont veut connaitre ses amis
     * @return l'ID de touts les utilisateurs qui sont amis avec un utilisateur
     */
    public List<String> friends(User user){
        String requete = "Select * from coviddb.friends where (idFriend1 =" + "'" +user.getId() +"')" + "OR" + "(idFriend2 =" + "'" +user.getId() +"')";
        ResultSet resultSet = doQuery(requete);
        List<String> listeAmis = new ArrayList<>();
        try{
        while (resultSet.next()){
            listeAmis.add(resultSet.getString("idFriend1"));
            listeAmis.add(resultSet.getString("idFriend2"));
        }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listeAmis;
    }

    /**
     * @param activity L'activité que l'on va creer dans la DB
     */
    public void createActivity(Activity activity){
        String requete = "Insert into coviddb.activity (dateDebut,dateFin,idLieu)" +
                " values ('" + activity.getDateDebut() + "', '" + activity.getDateFin() + "', '" + activity.getIdLieu() + "')";
        System.out.println(requete);
        doUpdate(requete);

    }

    /**
     *
     * @param lieu Le lieu que l'on va creer dans la DB
     */
    public void createLieu(Lieu lieu){
        String requete = "Insert into coviddb.lieu (nom,adresse,coordonneesGPS)" +
                "values ('" + lieu.getNom() + "', '" + lieu.getAdresse() + "', '" + lieu.getCoordGPS() + "')";
        doUpdate(requete);
    }

    public String getID(Lieu lieu){
        String requete ="Select * from coviddb.lieu where (nom =" + "'" +lieu.getNom() +"') AND (adresse = " + "'" + lieu.getAdresse() + "')";
        ResultSet resultSet =  doQuery(requete);
        String id = null;
        try {
            if(resultSet.next()){
                id = resultSet.getString("idLieu");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }

    /**
     * @return l'ID de toutes les activités presentent dans la DB
     */
    public List<String> activities(){
        String requete = "Select * from coviddb.activity";
        ResultSet resultSet = doQuery(requete);
        List<String> activities = new ArrayList<>();
        try{
            while (resultSet.next()){
                activities.add(resultSet.getString("idActivity"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return activities;
    }

    public Activity getActivityByID(String id){
        String requete = "Select * from coviddb.activity where idActivity = '" + id + "'";
        ResultSet resultSet = doQuery(requete);
        Activity activity = new Activity();
        activity.setIdActivity(id);
        try {
            if(resultSet.next()) {
                activity.setDateDebut(resultSet.getString("dateDebut"));
                activity.setDateFin(resultSet.getString("dateFin"));
                activity.setIdLieu(resultSet.getString("idLieu"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return activity;
    }

    public Lieu getLieu(String id) {
        String requete = "Select * from coviddb.lieu where idLieu = '" + id + "'";
        ResultSet resultSet = doQuery(requete);
        Lieu lieu = new Lieu();
        lieu.setIdLieu(id);
        try {
            if(resultSet.next()){
                lieu.setNom(resultSet.getString("nom"));
                lieu.setAdresse(resultSet.getString("adresse"));
                lieu.setCoordGPS(resultSet.getString("coordonneesGPS"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lieu;
    }

}
