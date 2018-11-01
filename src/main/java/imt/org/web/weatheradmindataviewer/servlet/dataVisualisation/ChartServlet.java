package imt.org.web.weatheradmindataviewer.servlet.dataVisualisation;

import imt.org.web.weatheradmindataviewer.bean.SensorBean;
import imt.org.web.weatheradmindataviewer.crud.CRUDEntityFacade;
import imt.org.web.weatheradmindataviewer.dao.sensor.SensorDao;
import imt.org.web.weatheradmindataviewer.transformers.SensorTransformers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "ChartServlet", urlPatterns = {"/historique"})
public class ChartServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String sensorRequested = request.getParameter("sensorId");
//
//        if(sensorRequested == null || sensorRequested.isEmpty()){
//
//        }
        SensorDao sensorDao = new SensorDao((CRUDEntityFacade)getServletContext().getAttribute("CRUDEntityFacade"));
        Collection<SensorBean> lst = SensorTransformers.entityToBean(sensorDao.findAll());
        request.setAttribute("sensors", lst);

        request.getRequestDispatcher("/jsp/dataVisualisation/sensor/chartSensor.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
