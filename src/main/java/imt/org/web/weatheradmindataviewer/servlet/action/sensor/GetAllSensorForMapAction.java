package imt.org.web.weatheradmindataviewer.servlet.action.sensor;

import com.google.gson.Gson;
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

@WebServlet(name = "GetAllSensorForMapAction", urlPatterns = {"/getAllSensorForMapAction"})
public class GetAllSensorForMapAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SensorDao sensorDao = new SensorDao((CRUDEntityFacade)getServletContext().getAttribute("CRUDEntityFacade"));
        Collection<SensorBean> lstSensorBean = SensorTransformers.entityToBean(sensorDao.findAll());

        String json = new Gson().toJson(lstSensorBean);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
