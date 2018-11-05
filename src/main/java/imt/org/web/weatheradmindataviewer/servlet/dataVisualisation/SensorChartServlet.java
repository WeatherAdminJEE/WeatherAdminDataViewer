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

@WebServlet(name = "SensorChartServlet", urlPatterns = "/sensorChart")
public class SensorChartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Dans le cas ou on veut afficher directement un sensor en particulier
        String idStr = request.getParameter("sensorId");

        SensorDao sensorDao = new SensorDao((CRUDEntityFacade)getServletContext().getAttribute("CRUDEntityFacade"));
        Collection<SensorBean> lst = SensorTransformers.entityToBean(sensorDao.findAll());
        request.setAttribute("sensors", lst);

        if(idStr != null && !idStr.isEmpty()){
            request.setAttribute("sensorSelected", idStr);
        }
        request.getRequestDispatcher("jsp/dataVisualisation/sensorChart/viewSensorChart.jsp").forward(request, response);
    }
}
