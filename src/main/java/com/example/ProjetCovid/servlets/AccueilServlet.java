package com.example.ProjetCovid.servlets;

import com.example.ProjetCovid.beans.User;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "accueilServlet", value = "/accueil")
public class AccueilServlet extends HttpServlet {


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");

        request.setAttribute("user", user);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request, response );
    }

    public void destroy() {
    }

}