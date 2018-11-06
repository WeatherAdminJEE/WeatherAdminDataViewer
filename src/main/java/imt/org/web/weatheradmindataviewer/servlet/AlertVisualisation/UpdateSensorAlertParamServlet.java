package imt.org.web.weatheradmindataviewer.servlet.AlertVisualisation;

import imt.org.web.weatheradmindataviewer.crud.CRUDEntityFacade;
import imt.org.web.weatheradmindataviewer.dao.sensoralert.SensorAlertParamDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;

@WebServlet(name = "UpdateSensorAlertParamServlet", urlPatterns = "/updateSensorAlertParam")
public class UpdateSensorAlertParamServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SensorAlertParamDao sensorAlertParamDao = new SensorAlertParamDao((CRUDEntityFacade)getServletContext().getAttribute("CRUDEntityFacade"));
        int idSensor = Integer.parseInt(request.getParameter("idSensorAlertParam"));
        double alertValue = Double.valueOf(request.getParameter("alertValue"));
        Timestamp alertRange = Timestamp.valueOf(request.getParameter("alertRange"));
        sensorAlertParamDao.updateSensorAlertParam(idSensor, alertValue, alertRange);
        response.sendRedirect("sensors");
    }
}
