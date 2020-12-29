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
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "signinservlet", value = "/connexion")
public class SignInServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();

        String email = request.getParameter("user_mail");
        String password = request.getParameter("user_password");

        String resultat;
        Map<String, String> erreurs = new HashMap<String, String>();

        PrintWriter out = response.getWriter();

        User user = new User();

        try {
            validationEmail(email);
        } catch (Exception e) {
            erreurs.put("email",e.getMessage());
        }
        user.setLogin(email);
        try{
            validationMotsDePasse(password);
        } catch (Exception e) {
            erreurs.put("password", e.getMessage() );
        }
        user.setPassword(password);


        /* Initialisation du résultat global de la validation. */
        if ( erreurs.isEmpty() ) {
            Database dataBase = new Database();
            user = dataBase.connecterUser(email,password);
            if(user.getId()!=null) {
                session.setAttribute("current_user", user);
                resultat = "Succès de connexion.";
            } else {
                resultat = "Échec de connexion: login ou mot de passe incorrect";
            }
        } else {
            resultat = "Échec de connexion: Probleme de structure du login ou du mot de passe";
        }


        out.println("<html><body>");
        out.println("<h1>" + resultat +"</h1>");
        out.println("</body></html>");

        request.setAttribute("user", user);
        request.setAttribute( "erreurs", erreurs );
        request.setAttribute( "resultat", resultat );

        getServletContext().getRequestDispatcher("/index.jsp").forward( request, response );
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher( "/WEB-INF/signIn.jsp" ).forward( request, response );

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
     * @throws Exception
     */
    private void validationMotsDePasse( String motDePasse) throws Exception{
        if (motDePasse != null && motDePasse.trim().length() != 0) {
            if (motDePasse.trim().length() < 3) {
                throw new Exception("Les mots de passe doivent contenir au moins 3 caractères.");
            }
        } else {
            throw new Exception("Merci de saisir et confirmer votre mot de passe.");
        }
    }
}

