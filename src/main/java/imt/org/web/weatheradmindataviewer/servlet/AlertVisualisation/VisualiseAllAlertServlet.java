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
import java.util.List;

@WebServlet(name = "VisualiseAllAlertServlet",urlPatterns = {"/sensorAlerts"})
public class VisualiseAllAlertServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Dans le cas ou on veut afficher directement un sensor en particulier
        String idStr = request.getParameter("sensorId");
        if (idStr != null && !idStr.isEmpty()) {
            request.setAttribute("sensorSelected", idStr);
        }

        //Récupere la liste des sensors
        SensorDao sensorDao = new SensorDao((CRUDEntityFacade) getServletContext().getAttribute("CRUDEntityFacade"));
        Collection<SensorBean> lst = SensorTransformers.entityToBean(sensorDao.findAll());
        request.setAttribute("sensors", lst);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/alert/allAlert.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
