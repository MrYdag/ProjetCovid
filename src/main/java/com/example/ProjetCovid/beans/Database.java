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
        try {
            if(resultSet.next()) {
                user.setLogin(login);
                user.setPassword(password);
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
        try {
            if(resultSet.next()) {
                user.setLogin(login);
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
        try {
            if(resultSet.next()) {
                user.setId(id);
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

    public List<String> casContact(User user){
        String requeteAmi = "Select * from coviddb.friends where (idFriend1 =" + "'" +user.getId() +"')" + "OR" + "(idFriend2 =" + "'" +user.getId() +"')";
        ResultSet resultSet = doQuery(requeteAmi);

        //Les amis de l'utilisateur
        List<String> casContacts = new ArrayList<>();
        try{
            while (resultSet.next()){
                if(resultSet.getString("idFriend1") != user.getId()) {
                    casContacts.add(resultSet.getString("idFriend1"));
                }
                if(resultSet.getString("idFriend2") != user.getId()) {
                    casContacts.add(resultSet.getString("idFriend2"));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //Tous les utilisateurs qui participent a des activités où l'utilisateur coroned participe
        List<String> listActivityString = this.getActivities(user);
        for(String s : listActivityString){
            casContacts.addAll(this.participants(this.getActivityByID(s)));
        }

        for (String uz : casContacts) {
            System.out.println(uz);
        }

        return casContacts;
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

    /**
     * Retrouver une activité grace à son ID
     * @param id de l'activité
     * @return l'activité correspondante
     */
    public Activity getActivityByID(String id){
        String requete = "Select * from coviddb.activity where idActivity = '" + id + "'";
        ResultSet resultSet = doQuery(requete);
        Activity activity = new Activity();
        try {
            if(resultSet.next()) {
                activity.setIdActivity(id);
                activity.setDateDebut(resultSet.getString("dateDebut"));
                activity.setDateFin(resultSet.getString("dateFin"));
                activity.setIdLieu(resultSet.getString("idLieu"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return activity;
    }

    /**
     * Permet de trouver un lieu à partir de son ID
     * @param id du lieu
     * @return le lieu correspondant
     */
    public Lieu getLieu(String id) {
        String requete = "Select * from coviddb.lieu where idLieu = '" + id + "'";
        ResultSet resultSet = doQuery(requete);
        Lieu lieu = new Lieu();
        try {
            if(resultSet.next()){
                lieu.setIdLieu(id);
                lieu.setNom(resultSet.getString("nom"));
                lieu.setAdresse(resultSet.getString("adresse"));
                lieu.setCoordGPS(resultSet.getString("coordonneesGPS"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lieu;
    }

    /**
     *
     * @param user
     * @param activity
     */
    public void createParticipation(User user, Activity activity){
        String requete = "Insert into coviddb.participation (idUser,idActivity)" +
                " values ('" + user.getId() + "', '" + activity.getIdActivity() + "')";
        System.out.println(requete);
        doUpdate(requete);
    }

    /**
     *
     * @param user
     * @param activity
     * @return
     */
    public Boolean isParticipating(User user, Activity activity) {
        String requete = "Select * from coviddb.participation where (idUser = " + "'" + user.getId() +"'" + "AND idActivity = " + "'" + activity.getIdActivity() +"')";
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
     *
     * @param user
     * @param activity
     */
    public void removeParticipation(User user, Activity activity) {
        String requete = "Delete from coviddb.participation where (idUser = '" + user.getId() + "' AND idActivity = '" + activity.getIdActivity() +"')";
        doUpdate(requete);
    }


    /**
     *
     * @param activity
     * @return
     */
    public List<String> participants(Activity activity){
        String requete = "Select * from coviddb.participation where idActivity = '" + activity.getIdActivity() + "'";
        ResultSet resultSet = doQuery(requete);
        List<String> participants = new ArrayList<>();
        try{
            while (resultSet.next()){
                participants.add(resultSet.getString("idUser"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return participants;
    }


    /**
     * Permet de connaiter les activités auquelles participes un utilisateur
     * @param user l'utilisateur auquel on veut connaitre ses activités
     * @return une liste d'activité
     */
    public List<String> getActivities(User user){
        String requete = "Select * from coviddb.participation where idUser = '" + user.getId() + "'";
        ResultSet resultSet = doQuery(requete);
        List<String> activities = new ArrayList<>();
        try{
            while (resultSet.next()){
                activities.add(resultSet.getString("idUser"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return activities;
    }



    /**
     *
     * @param user
     * @param about
     * @param date
     * @param isRead
     */
    public void createNotification(User user, String about, String date, String isRead ){
        String requete = "Insert into coviddb.notifications (about,dateSending,idUser,isRead)" +
                " values ('" + about + "', '" + date + "', '" + user.getId() + "', '" + isRead + "')";
        System.out.println(requete);
        doUpdate(requete);
    }

    public List<String> notifications(User user){
        String requete = "Select * from coviddb.notifications where idUser = '" + user.getId() + "'";
        ResultSet resultSet = doQuery(requete);
        List<String> notifications = new ArrayList<>();
        try{
            while (resultSet.next()){
                notifications.add(resultSet.getString("idNotif"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return notifications;
    }

    /**
     *
     * @param id
     * @return
     */
    public Notification getNotification(String id) {
        String requete = "Select * from coviddb.notifications where idNotif = '" + id + "'";
        ResultSet resultSet = doQuery(requete);
        Notification notification = new Notification();
        try {
            if(resultSet.next()){
                notification.setIdNotif(id);
                notification.setAbout(resultSet.getString("about"));
                notification.setDateSending(resultSet.getString("dateSending"));
                notification.setIdUser(resultSet.getString("idUser"));
                notification.setIsReading(resultSet.getString("isRead"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return notification;
    }

    public void read(Notification notification){
        String requete = "Update coviddb.notifications set isRead = '1' where idNotif = " + "'" + notification.getIdNotif() + "'";
        doUpdate(requete);
    }







}
