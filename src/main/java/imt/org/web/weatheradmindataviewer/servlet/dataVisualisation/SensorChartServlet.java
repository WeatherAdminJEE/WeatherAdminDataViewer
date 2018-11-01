package imt.org.web.weatheradmindataviewer.servlet.dataVisualisation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SensorChartServlet", urlPatterns = "/sensorChart")
public class SensorChartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/dataVisualisation/sensorChart/viewSensorChart.jsp").forward(request, response);
    }
}
