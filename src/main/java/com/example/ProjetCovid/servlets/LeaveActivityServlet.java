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

@WebServlet(name = "leaveActivityServlet", value = "/leaveActivity")
public class LeaveActivityServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        Database database = new Database();
        User user = (User) session.getAttribute("current_user");
        Activity activity = database.getActivityByID(request.getParameter("activity"));
        activity.setLieu(database.getLieu(activity.getIdLieu()));
        request.setAttribute("activity",activity);
        database.removeParticipation(user,activity);

        //Participants
        List<String> participant;
        List<User> participantList = new ArrayList<>();
        participant = database.participants(activity);
        for(String idUser : participant){
            User user1 = database.getUserbyID(idUser);
            participantList.add(user1);
        }
        request.setAttribute("listParticipants",participantList);

        request.setAttribute("isParticipating",false);
        getServletContext().getRequestDispatcher("/WEB-INF/showActivity.jsp").forward( request, response );
    }
}
