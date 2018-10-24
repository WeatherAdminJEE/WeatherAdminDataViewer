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

        //On sauvegarde l'uri demandée pour rediriger en cas de connexion nécessaire
        String requestedURI = req.getRequestURI();
        this.context.log("Requested ressource::"+requestedURI);

        HttpSession session = req.getSession(false);

        //On laisse passer les requetes css, js, img...
        if(requestedURI.indexOf("/css") > 0)
            chain.doFilter(request, response);
        else if(requestedURI.indexOf("/js") > 0)
            chain.doFilter(request, response);
        else if(requestedURI.indexOf("/fonts") > 0)
            chain.doFilter(request, response);
        else if (requestedURI.indexOf("/images") > 0)
            chain.doFilter(request, response);
        else {

            //Si l'utilisateur n'est pas connecté :
            if (session == null) {
                //Il ne peut pas accéder à une autre servlet que "login"
                if (!requestedURI.contains("login")) {
                    this.context.log("Unauthorized access");
                    res.sendRedirect("login?requesteduri=" + requestedURI);
                } else {
                    chain.doFilter(request, response);
                }
            }

            //Si l'utilisateur est connecté :
            else {
                //Il ne peut pas accéder à la servlet "login"
                if (requestedURI.contains("login")) {
                    res.sendRedirect("home");
                }
                //Il peut accéder à toutes les autres
                else {
                    chain.doFilter(request, response);
                }
            }
        }
    }
}
