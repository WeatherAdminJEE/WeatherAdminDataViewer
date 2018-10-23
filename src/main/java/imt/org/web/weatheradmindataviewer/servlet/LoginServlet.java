package imt.org.web.weatheradmindataviewer.servlet;

import utils.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Page demandée avant d'être redirigé vers la page de connexion
        String requestedURI = request.getParameter("requestedURI");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //Le formulaire est invalide
        /*
        if(username == null || username.isEmpty() || password == null || password.isEmpty()){
            request.setAttribute("errorMessage", "Tous les champs sont obligatoires");
            request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
        }
        */

        //Les identifiants sont incorrects
        boolean authorized = UserManager.IsUserAuthorized(username, password);
        if(!authorized){
            request.setAttribute("errorMessage", "Identifiants incorrects");
            request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
        }

        //Tous est correct
        //On créé la session
        request.getSession(true);

        //redirection vers la page demandée ou la page d'accueil
        //On ne redirige pas vers logout juste apres un login...
        if(requestedURI != null && !requestedURI.isEmpty() && !requestedURI.contains("logout"))
            response.sendRedirect(requestedURI);
        else
            response.sendRedirect("home");
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Page demandée avant d'être redirigé vers la page de connexion
        String requestedURI = request.getParameter("requesteduri");

        if(requestedURI != null)
            request.setAttribute("requestedURI", requestedURI);

        request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
    }
}