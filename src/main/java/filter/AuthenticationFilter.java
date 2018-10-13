package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

/***
 * Filtre déclanché avant chaque appel à un servlet.
 * Permet de vérifier que l'utilisateur est bien connecté
 * S'il ne l'ai pas : redirection vers page de connexion
 */
@WebFilter(filterName = "AuthenticationFilter")
public class AuthenticationFilter implements Filter {
    private ServletContext context;

    public void init(FilterConfig config) throws ServletException {
        this.context = config.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    public void destroy() {
        System.out.println("destroy");
    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;

        String uri = req.getRequestURI();
        this.context.log("Requested ressource::"+uri);

        HttpSession session = req.getSession(false);

        //Si l'utilisateur n'est pas connecté :
        if(session == null){
            //Il ne peut pas accéder à une autre servlet que "login"
            if(!uri.contains("login")){
                this.context.log("Unauthorized access");
                res.sendRedirect("login");
            }
            else{
                chain.doFilter(request, response);
            }
        }
        //Si l'utilisateur est connecté :
        else{
            //Il ne peut pas accéder à la servlet "login"
            if(uri.contains("login")){
                res.sendRedirect("home");
            }
            //Il peut accéder à toutes les autres
            else{
                chain.doFilter(request, response);
            }
        }



    }
}
