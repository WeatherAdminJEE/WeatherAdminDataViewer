package servlet.dataVisualisation;

import dao.SensorDao;
import dao.SensorDataDao;
import imt.org.web.commonmodel.entities.SensorDataEntity;
import imt.org.web.commonmodel.entities.SensorEntity;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idSensor = Integer.parseInt(request.getParameter("idSensor"));
        //@TODO remplacer par logger
        System.out.println("idSensor : "+idSensor);

        SensorEntity sensor = SensorDao.getInstance().findById(idSensor);
        if(sensor != null) {
            Collection<SensorDataEntity> lstSensorData = SensorDataDao.getInstance().findAllDataBySensor(idSensor);
            request.setAttribute("sensor",sensor);
            request.setAttribute("lstSensorData", lstSensorData);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/dataVisualisation/sensorData/dataBySensor.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
