package com.example.ProjetCovid.servlets;

import com.example.ProjetCovid.beans.Database;
import com.example.ProjetCovid.beans.Notification;
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

@WebServlet(name = "showNotificationsServlet", value = "/showNotifications")
public class ShowNotificationsServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        Database database = new Database();

        List<String> notificationIDs = new ArrayList<>();
        List<Notification> notifications = new ArrayList<>();
        List<Notification> newNotifications = new ArrayList<>();
        notificationIDs = database.notifications((User) session.getAttribute("current_user"));

        for(String idNotif : notificationIDs){
            Notification notif = database.getNotification(idNotif);
            notifications.add(notif);
            if(notif.getIsReading().equals("0")){
                newNotifications.add(notif);
            }

        }
        request.setAttribute("listNotifs",notifications);
        request.setAttribute("listNewNotifs",newNotifications);
        getServletContext().getRequestDispatcher("/WEB-INF/showNotifications.jsp").forward( request, response );
        for(Notification notification : newNotifications){
            database.read(notification);
        }

    }
}
