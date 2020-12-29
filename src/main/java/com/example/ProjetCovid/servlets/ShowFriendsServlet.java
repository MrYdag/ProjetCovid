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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "showFriendsServlet", value = "/showFriends")
public class ShowFriendsServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        Database database = new Database();
        List<String> maybeFriends = new ArrayList<>();
        List<User> friends = new ArrayList<>();
        maybeFriends = database.friends((User) session.getAttribute("current_user"));
        String idCurrentUser = ((User) session.getAttribute("current_user")).getId();
        for(String idUser : maybeFriends){
            if(!idUser.equals(idCurrentUser)){
                friends.add(database.getUserbyID(idUser));
            }
        }
        request.setAttribute("listFriends",friends);
        getServletContext().getRequestDispatcher("/WEB-INF/showFriends.jsp").forward( request, response );
    }
}