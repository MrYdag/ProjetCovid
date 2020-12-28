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



@WebServlet(name = "positiveServlet", value = "/positive")
public class PositiveServlet extends HttpServlet {


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        Database database = new Database();
        User user = (User) session.getAttribute("current_user");
        database.coroned(user);
        System.out.println("Je suis positif ! ");
        session.setAttribute("current_user",user);
        getServletContext().getRequestDispatcher( "/WEB-INF/positive.jsp" ).forward( request, response );

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        System.out.println("GET Positif");
        getServletContext().getRequestDispatcher( "/WEB-INF/positive.jsp" ).forward( request, response );
    }

    public void destroy() {
    }

}