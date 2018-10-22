package imt.org.web.weatheradmindataviewer.servlet.dataVisualisation;

import imt.org.web.weatheradmindataviewer.dao.sensor.SensorDao;
import imt.org.web.commonmodel.entities.SensorEntity;
import imt.org.web.weatheradmindataviewer.crud.CRUDEntityFacade;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "VisualiseAllSensorsServlet", urlPatterns = {"/sensors"})
public class VisualiseAllSensorsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SensorDao sensorDao = new SensorDao((CRUDEntityFacade)getServletContext().getAttribute("CRUDEntityFacade"));
        Collection<SensorEntity> lst = sensorDao.findAll();
        request.setAttribute("lstSensors",lst);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/dataVisualisation/sensor/sensorsCore.jsp");
        dispatcher.forward(request, response);
    }
}
