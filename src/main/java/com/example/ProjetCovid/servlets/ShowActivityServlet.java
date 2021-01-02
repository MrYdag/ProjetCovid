package com.example.ProjetCovid.servlets;

import com.example.ProjetCovid.beans.Activity;
import com.example.ProjetCovid.beans.Database;
import com.example.ProjetCovid.beans.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "showActivityServlet", value = "/showActivity")
public class ShowActivityServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");
        Database database = new Database();
        Activity activity = database.getActivityByID(request.getParameter("activity"));
        activity.setLieu(database.getLieu(activity.getIdLieu()));

        List<String> participant;
        List<User> participantList = new ArrayList<>();
        participant = database.participants(activity);
        for(String idUser : participant){
            User user1 = database.getUserbyID(idUser);
            participantList.add(user1);
        }
        request.setAttribute("listParticipants",participantList);
        request.setAttribute("activity",activity);
        request.setAttribute("isParticipating",database.isParticipating(user,activity));
        getServletContext().getRequestDispatcher("/WEB-INF/showActivity.jsp").forward( request, response );
    }
}