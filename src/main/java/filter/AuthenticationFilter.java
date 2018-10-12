package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

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


        if(session == null && !uri.contains("login")){
            this.context.log("Unauthorized access");
//            res.sendRedirect("/WeatherAdminDataViewer/jsp/login.jsp");
            res.sendRedirect("login");
        }
        else {
            //Continue to requested URI
            chain.doFilter(request, response);
        }

    }
}
