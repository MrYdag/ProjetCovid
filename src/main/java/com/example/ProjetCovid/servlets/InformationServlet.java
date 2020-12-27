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



@WebServlet(name = "informationservlet", value = "/information")
public class InformationServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();


        String resultat;
        Map<String, String> erreurs = new HashMap<String, String>();

        PrintWriter out = response.getWriter();
        String email = request.getParameter("user_mail");
        String firstName = request.getParameter("user_firstname");
        String lastName = request.getParameter("user_lastname");
        String password = request.getParameter("user_password");
        String passwordConfirmation = request.getParameter("user_passwordConfirmation");
        String dateBirthday = request.getParameter("user_birthday");

//todo Faire les verifications des nouvelles infos
        //todo Messages d'erreurs

        if ( erreurs.isEmpty() ) {
            resultat = "Succès de modification.";
            Database dataBase = new Database();
            dataBase.modifierUser(dataBase.getID(email), email, firstName, lastName, password, dateBirthday);

            session = request.getSession();
            User user = (User) session.getAttribute("current_user");
            user.setLogin(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassword(password);
            user.setDateNaissance(dateBirthday);
        } else {
            resultat = "Échec de modification.";
        }



        getServletContext().getRequestDispatcher("/WEB-INF/changeInfo.jsp").forward( request, response );
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        getServletContext().getRequestDispatcher("/WEB-INF/changeInfo.jsp").forward( request, response );
    }

    //todo Fonctions de verification des champs
}
