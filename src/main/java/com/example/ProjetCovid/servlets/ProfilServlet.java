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

@WebServlet(name = "profilServlet", value = "/profil")
public class ProfilServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        Database database = new Database();
        User profil = database.getUser(request.getParameter("profil"));
        request.setAttribute("profil",profil);
        if(session.getAttribute("current_user")!=null) {
            request.setAttribute("friend", database.isFriend((User) (session.getAttribute("current_user")), profil));
        }
        getServletContext().getRequestDispatcher("/WEB-INF/profil.jsp").forward( request, response );
    }
}
