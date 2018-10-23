package imt.org.web.weatheradmindataviewer.servlet;

import imt.org.web.commonmodel.entities.SensorEntity;
import imt.org.web.weatheradmindataviewer.crud.CRUDEntityFacade;
import imt.org.web.weatheradmindataviewer.dao.sensor.SensorDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@WebServlet(name = "IndexServlet", urlPatterns = "/index")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SensorDao sensorDao = new SensorDao((CRUDEntityFacade)getServletContext().getAttribute("CRUDEntityFacade"));
        Collection<SensorEntity> lstSensor = sensorDao.findAll();
        Collection<Float> lstSensorCoordinate = parseCoordinate(lstSensor);
        request.setAttribute("lstSensorCoordinate",lstSensorCoordinate);

        request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
    }

    private Collection<Float> parseCoordinate(Collection<SensorEntity> lstSensor){

        List<Float> lstCoordinate = new ArrayList<>(lstSensor.size()*2);
        for(SensorEntity sensor : lstSensor){
            String[] tmpTab = sensor.getGpsCoordinates().split(",");
            lstCoordinate.add(Float.parseFloat(tmpTab[0]));
            lstCoordinate.add(Float.parseFloat(tmpTab[1]));
        }

        System.out.println(lstCoordinate);
        return lstCoordinate;
    }
}
