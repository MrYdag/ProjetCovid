package com.example.ProjetCovid.servlets;

import com.example.ProjetCovid.beans.Activity;
import com.example.ProjetCovid.beans.Database;
import com.example.ProjetCovid.beans.Lieu;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "createactivityservlet", value = "/createActivity")
public class CreateActivityServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String resultat;
        Map<String, String> erreurs = new HashMap<>();

        String nomLieu = request.getParameter("nomLieu");
        String adresseLieu = request.getParameter("adresseLieu");
        String coordLieu = request.getParameter("coordLieu");
        String dateDebut = request.getParameter("activitydatedebut");
        String heureDebut = request.getParameter("activityheuredebut");
        String dateFin = request.getParameter("activitydatefin");
        String heureFin = request.getParameter("activityheurefin");

        Lieu lieu = new Lieu();
        Activity activity = new Activity();

        try {
            validation(nomLieu);
        } catch (Exception e) {
            e.printStackTrace();
        }
        lieu.setNom(nomLieu);

        try {
            validation(adresseLieu);
        } catch (Exception e) {
            erreurs.put("adresseLieu", e.getMessage());
        }
        lieu.setAdresse(adresseLieu);

        try {
            validation(coordLieu);
        } catch (Exception e) {
            erreurs.put("coordLieu", e.getMessage());
        }
        lieu.setCoordGPS(coordLieu);

        try {
            validation(dateDebut);
        } catch (Exception e) {
            erreurs.put("dateDebut", e.getMessage());
        }
        activity.setDateDebut(dateDebut);

        try {
            validation(dateFin);
        } catch (Exception e) {
            erreurs.put("dateFin", e.getMessage());
        }
        activity.setDateFin(dateFin);

        /* Initialisation du résultat global de la validation. */

        if ( erreurs.isEmpty() ) {
            resultat = "Succès lors de la création de l'activité.";
            Database dataBase = new Database();
            dataBase.createLieu(lieu);
            activity.setIdLieu(dataBase.getID(lieu));
            dataBase.createActivity(activity);
        } else {
            resultat = "Échec lors de la création de l'activité: " + erreurs;
        }

        request.setAttribute("activity",activity);
        request.setAttribute("lieu",lieu);
        request.setAttribute("erreurs",erreurs);
        request.setAttribute("resultat",resultat);
        getServletContext().getRequestDispatcher("/WEB-INF/createActivity.jsp").forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/createActivity.jsp").forward(request, response);
    }

    public void validation(String s) throws Exception {
        if(s.isEmpty()){
            throw new Exception("Merci de saisir une valeur");
        }
    }
}