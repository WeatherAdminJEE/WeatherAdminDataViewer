package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AuthenticationServlet", urlPatterns = "/authenticationservlet")
public class AuthenticationServlet extends HttpServlet {

    private boolean isAuthenticated(String username, String password){
        return false;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("username");
        String password = request.getParameter("password");

        if(isAuthenticated(user, password)){
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

        }
    }



}
