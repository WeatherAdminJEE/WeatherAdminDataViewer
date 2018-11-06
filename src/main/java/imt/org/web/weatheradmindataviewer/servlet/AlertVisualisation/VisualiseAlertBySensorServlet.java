package imt.org.web.weatheradmindataviewer.servlet.AlertVisualisation;

import imt.org.web.weatheradmindataviewer.bean.SensorAlertDto;
import imt.org.web.weatheradmindataviewer.bean.SensorBean;
import imt.org.web.weatheradmindataviewer.crud.CRUDEntityFacade;
import imt.org.web.weatheradmindataviewer.dao.sensor.SensorDao;
import imt.org.web.weatheradmindataviewer.dao.sensoralert.SensorAlertDao;
import imt.org.web.weatheradmindataviewer.transformers.AlertTransformers;
import imt.org.web.weatheradmindataviewer.transformers.SensorTransformers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "VisualiseAlertBySensorServlet", urlPatterns = { "/alertForSensor"})
public class VisualiseAlertBySensorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idSensor = Integer.parseInt(request.getParameter("idSensor"));
        System.out.println("AlertBySensor idSensor : "+idSensor);

        SensorDao sensorDao = new SensorDao((CRUDEntityFacade)getServletContext().getAttribute("CRUDEntityFacade"));
        SensorAlertDao alertDao = new SensorAlertDao((CRUDEntityFacade)getServletContext().getAttribute("CRUDEntityFacade"));
        SensorBean sensor = SensorTransformers.entityToBean(sensorDao.findById(idSensor));
        if(sensor != null) {
            Collection<SensorAlertDto> lstAlert = AlertTransformers.entityToDto(((SensorAlertDao) alertDao).findAllAlertByIdSensor(idSensor));
            request.setAttribute("lstAlert",lstAlert);
            request.setAttribute("sensorName",sensor.getName());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/alert/alertBySensor.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
