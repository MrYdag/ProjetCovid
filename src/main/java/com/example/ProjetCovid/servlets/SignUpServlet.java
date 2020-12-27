package com.example.ProjetCovid.servlets;

import com.example.ProjetCovid.beans.Database;
import com.example.ProjetCovid.beans.User;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "signupservlet", value = "/inscription")
public class SignUpServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String resultat;
        Map<String, String> erreurs = new HashMap<String, String>();

        PrintWriter out = response.getWriter();
        String email = request.getParameter("user_mail");
        String firstName = request.getParameter("user_firstname");
        String lastName = request.getParameter("user_lastname");
        String password = request.getParameter("user_password");
        String passwordConfirmation = request.getParameter("user_passwordConfirmation");
        String dateBirthday = request.getParameter("user_birthday");

        User user = new User();

        try {
            validationEmail(email);
        } catch (Exception e) {
            erreurs.put("email",e.getMessage());
        }
        user.setLogin(email);

        try{
            validationMotsDePasse(password,passwordConfirmation);
        } catch (Exception e) {
            erreurs.put("password", e.getMessage() );
        }
        user.setPassword(password);

        try {
            validationFirstName(firstName);
        } catch (Exception e) {
            erreurs.put("firstName",e.getMessage());
        }
        user.setFirstName(firstName);

        try {
            validationLastName(lastName);
        } catch (Exception e) {
            erreurs.put("lastName",e.getMessage());
        }
        user.setLastName(lastName);

        try {
            validationDateNaissance(dateBirthday);
        } catch (Exception e) {
            erreurs.put("birthday",e.getMessage());
        }

        /* Initialisation du résultat global de la validation. */
        if ( erreurs.isEmpty() ) {
            resultat = "Succès de l'inscription.";
            Database dataBase = new Database();
            dataBase.inscrireUser(user);
        } else {
            resultat = "Échec de l'inscription.";
        }

        out.println("<html><body>");
        out.println("<h1>" + message + " " + resultat +"</h1>");
        out.println("</body></html>");

        request.setAttribute("user", user);
        request.setAttribute( "erreurs", erreurs );
        request.setAttribute( "resultat", resultat );

        getServletContext().getRequestDispatcher("/signUp.jsp").forward( request, response );
    }

    public void destroy() {
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
     * Verifie que le mot de passe est correctement formé
     * @param motDePasse
     * @param confirmation
     * @throws Exception
     */
    private void validationMotsDePasse( String motDePasse, String confirmation ) throws Exception{
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