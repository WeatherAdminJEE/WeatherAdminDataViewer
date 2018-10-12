package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Home", urlPatterns = { "/home", "/accueil"})
/**
@ServletSecurity(
        @HttpConstraint(rolesAllowed = "admin")
)
**/
public class Home extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head> <title>Accueil</title> </head> ");
        out.println(" ");
        out.println("<body>");


        out.println("<h1>" + "ACCUEIL" + "</h1>");
        out.println("</body>");
        out.println("</html>");

    }
}
