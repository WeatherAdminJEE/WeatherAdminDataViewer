package imt.org.web.weatheradmindataviewer.servlet.action.sensor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetSensorAlertByIdServlet", urlPatterns = { "/getSensorAlertById"})
public class GetSensorAlertByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int sensorId;
        try {
            String idStr = request.getParameter("sensorId");
            sensorId = Integer.parseInt(idStr);

        }
        catch (NumberFormatException ex){
            System.out.println("Bad parameter format exception");
        }
    }
}
