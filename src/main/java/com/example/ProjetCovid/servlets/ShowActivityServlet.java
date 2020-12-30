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
        Database database = new Database();
        List<String> activities = new ArrayList<>();
        List<Activity> activityList = new ArrayList<>();
        activities = database.activities();
        for(String idActivity : activities){
            Activity activity = database.getActivityByID(idActivity);
            activity.setLieu(database.getLieu(activity.getIdLieu()));
            activityList.add(activity);
        }
        request.setAttribute("listActivities",activityList);
        getServletContext().getRequestDispatcher("/WEB-INF/showActivity.jsp").forward( request, response );
    }
}