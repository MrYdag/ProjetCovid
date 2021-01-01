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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@WebServlet(name = "positiveServlet", value = "/positive")
public class PositiveServlet extends HttpServlet {


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        Database database = new Database();
        User user = (User) session.getAttribute("current_user");
        database.coroned(user);
        user.setCoroned("1");
        List<User> casContact = new ArrayList<>();
        for(String id : database.casContact(user)){
            if(!id.equals(user.getId())) {
                casContact.add(database.getUserbyID(id));
            }
        }

        //Date de la création de la notification
        SimpleDateFormat formater = null;
        Date aujourdhui = new Date();
        //formater = new SimpleDateFormat("dd-MM-yy hh:mm:ss");
        formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        for (User userContact : casContact){
            database.createNotification(userContact,"ATTENTION: Vous avez été en contact avec quelqu un de positif", formater.format(aujourdhui) ,"0");
        }
        database.createNotification(user,"ATTENTION: Vous vous etes déclaré positif le " + formater.format(aujourdhui) + " à partir de maintenant, isolez-vous", formater.format(aujourdhui) ,"0");

        session.setAttribute("currentUser", user);
        getServletContext().getRequestDispatcher( "/WEB-INF/positive.jsp" ).forward( request, response );

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");
        request.setAttribute("current_user", user);
        getServletContext().getRequestDispatcher( "/WEB-INF/positive.jsp" ).forward( request, response );
    }

    public void destroy() {
    }

}