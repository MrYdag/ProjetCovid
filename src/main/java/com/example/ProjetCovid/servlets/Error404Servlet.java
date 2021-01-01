package com.example.ProjetCovid.servlets;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "error404Servlet", value = "/error404")
public class Error404Servlet extends HttpServlet {


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request, response );
    }

    public void destroy() {
    }

}
