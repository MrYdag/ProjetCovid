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

@WebServlet(name = "askFriendServlet", value = "/askFriend")
public class AskFriendServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //todo Pour l'instant cette methode est juste là pour le test

        response.setContentType("text/html");
        HttpSession session = request.getSession();
        Database database = new Database();
        User user = (User) session.getAttribute("current_user");
        User profil = database.getUser(request.getParameter("profil"));

        database.addFriend(user,profil);

        request.setAttribute("profil",profil);
        request.setAttribute("friend",true);
        getServletContext().getRequestDispatcher("/WEB-INF/profil.jsp").forward( request, response );
    }
}
