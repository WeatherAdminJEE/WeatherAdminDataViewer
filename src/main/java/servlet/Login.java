package servlet;

import utils.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Login", urlPatterns = "/login")
public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //Le formulaire est invalide
        if(username == null || username.isEmpty() || password == null || password.isEmpty()){
            request.setAttribute("errorMessage", "Tous les champs sont obligatoires");
            request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
        }

        //Les identifiants sont incorrects
        boolean authorized = UserManager.IsUserAuthorized(username, password);
        if(!authorized){
            request.setAttribute("errorMessage", "Identifiants incorrects");
            request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
        }

        //Tous est correct
        request.getRequestDispatcher("home").forward(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
    }
}
