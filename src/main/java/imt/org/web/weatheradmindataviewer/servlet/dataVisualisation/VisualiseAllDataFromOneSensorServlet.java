package imt.org.web.weatheradmindataviewer.servlet.dataVisualisation;

import imt.org.web.weatheradmindataviewer.bean.SensorBean;
import imt.org.web.weatheradmindataviewer.bean.SensorDataDto;
import imt.org.web.weatheradmindataviewer.dao.sensor.SensorDao;
import imt.org.web.weatheradmindataviewer.dao.sensordata.SensorDataDao;
import imt.org.web.commonmodel.entities.SensorDataEntity;
import imt.org.web.commonmodel.entities.SensorEntity;
import imt.org.web.weatheradmindataviewer.crud.CRUDEntityFacade;
import imt.org.web.weatheradmindataviewer.transformers.SensorDataTransformer;
import imt.org.web.weatheradmindataviewer.transformers.SensorTransformers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "VisualiseAllDataFromOneSensorServlet", urlPatterns = {"/VisualiseAllDataFromOneSensor"})
public class VisualiseAllDataFromOneSensorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idSensor = Integer.parseInt(request.getParameter("idSensor"));
        //@TODO remplacer par logger
        System.out.println("idSensor : "+idSensor);

        SensorDao sensorDao = new SensorDao((CRUDEntityFacade)getServletContext().getAttribute("CRUDEntityFacade"));
        SensorDataDao sensorDataDao = new SensorDataDao((CRUDEntityFacade)getServletContext().getAttribute("CRUDEntityFacade"));

        SensorBean sensor = SensorTransformers.entityToBean(sensorDao.findById(idSensor));
        if(sensor != null) {
            Collection<SensorDataDto> lstSensorData = SensorDataTransformer.entityToDto(sensorDataDao.findAllDataBySensor(idSensor));
            request.setAttribute("sensor", sensor);
            request.setAttribute("lstSensorData", lstSensorData);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/dataVisualisation/sensorData/viewAllDataForOneSensor.jsp");
            dispatcher.forward(request, response);
        }
    }
}
