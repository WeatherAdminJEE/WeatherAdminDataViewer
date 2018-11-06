package imt.org.web.weatheradmindataviewer.servlet.AlertVisualisation;

import imt.org.web.weatheradmindataviewer.bean.SensorAlertDto;
import imt.org.web.weatheradmindataviewer.crud.CRUDEntityFacade;
import imt.org.web.weatheradmindataviewer.dao.sensoralert.SensorAlertDao;
import imt.org.web.weatheradmindataviewer.transformers.AlertTransformers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

@WebServlet(name = "VisualiseAllAlertServlet",urlPatterns = {"/allAlert"})
public class VisualiseAllAlertServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SensorAlertDao dao = new SensorAlertDao((CRUDEntityFacade)getServletContext().getAttribute("CRUDEntityFacade"));
        Collection<SensorAlertDto> lstAlert = AlertTransformers.entityToDto(dao.findAll());
        request.setAttribute("lstAlert",lstAlert);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/alert/allAlert.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
