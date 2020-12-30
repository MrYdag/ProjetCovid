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

        request.setAttribute("profil",profil);
        request.setAttribute("friend",false);
        getServletContext().getRequestDispatcher("/WEB-INF/profil.jsp").forward( request, response );
    }
}