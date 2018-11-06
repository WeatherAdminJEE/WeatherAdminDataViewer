package imt.org.web.weatheradmindataviewer.servlet.action.sensor;

import com.google.gson.Gson;
import imt.org.web.commonmodel.entities.SensorAlertParamEntity;
import imt.org.web.weatheradmindataviewer.bean.SensorAlertParamDto;
import imt.org.web.weatheradmindataviewer.crud.CRUDEntityFacade;
import imt.org.web.weatheradmindataviewer.dao.sensoralert.SensorAlertParamDao;
import imt.org.web.weatheradmindataviewer.transformers.SensorAlertParamTransormer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetSensorAlertParamBySensorIdServlet", urlPatterns = { "/getSensorAlertParamBySensorId"})
public class GetSensorAlertParamBySensorIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int sensorId;
        try {
            String idStr = request.getParameter("sensorId");
            sensorId = Integer.parseInt(idStr);

            SensorAlertParamDao sensorAlertDao = new SensorAlertParamDao((CRUDEntityFacade)getServletContext().getAttribute("CRUDEntityFacade"));
            SensorAlertParamDto sensorAlertParam = SensorAlertParamTransormer.entityToDto(sensorAlertDao.findById(sensorId));

            String jsonResult = new Gson().toJson(sensorAlertParam);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonResult);
        }
        catch (NumberFormatException ex){
            System.out.println("Bad parameter format exception");
        }
    }
}
