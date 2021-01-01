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
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "removeFriendServlet", value = "/removeFriend")
public class RemoveFriendServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //todo Envoyer une notification plus tard

        System.out.println("Tu n'es plus mon ami !");

        response.setContentType("text/html");
        HttpSession session = request.getSession();
        Database database = new Database();
        User user = (User) session.getAttribute("current_user");
        User profil = database.getUser(request.getParameter("profil"));

        database.removeFriend(user,profil);


        //Date de la création de la notification
        SimpleDateFormat formater = null;
        Date aujourdhui = new Date();
        //formater = new SimpleDateFormat("dd-MM-yy hh:mm:ss");
        formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        //Notif pour celui qui fait la demande
        database.createNotification(user, "Vous avez retiré " + profil.getLogin() + " de votre liste d amis", formater.format(aujourdhui) , "0");

        //Notif pour celui qui recoit la demande
        database.createNotification(profil, user.getLogin() + " vous a retiré de sa liste d amis", formater.format(aujourdhui) , "0");

        request.setAttribute("profil",profil);
        request.setAttribute("friend",false);
        getServletContext().getRequestDispatcher("/WEB-INF/profil.jsp").forward( request, response );
    }
}