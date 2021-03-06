package com.example.ProjetCovid.servlets;

import com.example.ProjetCovid.beans.Database;
import com.example.ProjetCovid.beans.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



@WebServlet(name = "informationservlet", value = "/information")
public class InformationServlet extends HttpServlet {

    private User user;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();


        String resultat;
        Map<String, String> erreurs = new HashMap<>();

        PrintWriter out = response.getWriter();
        String newEmail = request.getParameter("user_mail");
        String email = user.getLogin();
        String firstName = request.getParameter("user_firstname");
        String lastName = request.getParameter("user_lastname");
        String password = request.getParameter("user_password");
        String newPassword = request.getParameter("new_user_password");
        String newpasswordConfirmation = request.getParameter("new_user_passwordConfirmation");
        String dateBirthday = request.getParameter("user_birthday");

        //Test la pertinence de l'adresse mail
        try {
            validationEmail(newEmail);
        } catch (Exception e) {
            erreurs.put("new email",e.getMessage());
        }

        //Test la pertinence de l'adresse mail
        try {
            validationEmail(email);
        } catch (Exception e) {
            erreurs.put("email",e.getMessage());
        }

        //Test la pertinence du mot de passe, et verifie que le mot de passe et identique au mot de passe de confirmation
        try{
            validationNewMotsDePasse(newPassword,newpasswordConfirmation);
        } catch (Exception e) {
            erreurs.put("new password", e.getMessage() );
        }

        try {
            validationMotDePasse(password);
        } catch (Exception e) {
            erreurs.put("password", e.getMessage());
        }


        //Test la pertinence du prenom
        try {
            validationFirstName(firstName);
        } catch (Exception e) {
            erreurs.put("firstName",e.getMessage());
        }

        //Test la pertinence du nom
        try {
            validationLastName(lastName);
        } catch (Exception e) {
            erreurs.put("lastName",e.getMessage());
        }

        //Test la pertinence de la date de naissance
        try {
            validationDateNaissance(dateBirthday);
        } catch (Exception e) {
            erreurs.put("birthday",e.getMessage());
        }

        if ( erreurs.isEmpty() ) {
            resultat = "Succès de modification.";
            Database dataBase = new Database();
            dataBase.modifierUser(dataBase.getID(email), newEmail, firstName, lastName, newPassword, dateBirthday);

            session = request.getSession();
            user = (User) session.getAttribute("current_user");
            user.setLogin(newEmail);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassword(newPassword);
            user.setDateNaissance(dateBirthday);

            //Date de la création de la notification
            SimpleDateFormat formater;
            Date aujourdhui = new Date();
            formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            dataBase.createNotification(user,"Vos informations personnelles ont été mises à jour", formater.format(aujourdhui), "0");
        } else {
            resultat = "Échec de modification: " + erreurs;
        }

        request.setAttribute( "erreurs", erreurs );
        request.setAttribute( "resultat", resultat );

        getServletContext().getRequestDispatcher("/WEB-INF/changeInfo.jsp").forward( request, response );
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("current_user");
        getServletContext().getRequestDispatcher("/WEB-INF/changeInfo.jsp").forward( request, response );
    }

    /**
     * Verifie que l'adresse mail est correctement formée
     */
    private void validationEmail( String email ) throws Exception {
        if ( email != null && email.trim().length() != 0 ) {
            if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
                throw new Exception( "Merci de saisir une adresse mail valide." );
            }
        } else {
            throw new Exception( "Merci de saisir une adresse mail." );
        }
    }

    /**
     * Verifie que le nouveau mot de passe est correctement formé
     * @param motDePasse mot de passe de l'utilisateur
     * @param confirmation mot de passe de confirmation de l'utilisateur
     * @throws Exception indique l'erreur
     */
    private void validationNewMotsDePasse( String motDePasse, String confirmation ) throws Exception{
        if (motDePasse != null && motDePasse.trim().length() != 0 && confirmation != null && confirmation.trim().length() != 0) {
            if (!motDePasse.equals(confirmation)) {
                throw new Exception("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
            } else if (motDePasse.trim().length() < 3) {
                throw new Exception("Les mots de passe doivent contenir au moins 3 caractères.");
            }
        } else {
            throw new Exception("Merci de saisir et confirmer votre mot de passe.");
        }
    }

    /**
     * Verifie que l'ancien mot de passe est correct
     * @param motDePasse mot de passe que rentre l'utilisateur
     * @throws Exception indique l'erreur
     */
    private void validationMotDePasse(String motDePasse) throws Exception {
        if(motDePasse != null){
            if(!motDePasse.equals(user.getPassword())){
                throw new Exception("Mot de passe incorrect");
            }

        } else {
            throw new Exception("Merci de saisir votre mot de passe");
        }
    }

    /**
     * Verifie d'un prenom d'utilisateur est correctement formé
     */
    private void validationFirstName( String prenom ) throws Exception {
        if ( prenom != null && prenom.trim().length() < 3 ) {
            throw new Exception( "Le prenom doit contenir au moins 3 caractères." );
        }
    }

    /**
     * Verifie d'un prenom d'utilisateur est correctement formé
     */
    private void validationLastName( String nom ) throws Exception {
        if ( nom != null && nom.trim().length() < 3 ) {
            throw new Exception( "Le nom doit contenir au moins 3 caractères." );
        }
    }

    private void validationDateNaissance(String date) throws Exception {
        if(date == null) {
            throw  new Exception("Vous devez bien avoir une date de naissance, non ?");
        }
    }
}
