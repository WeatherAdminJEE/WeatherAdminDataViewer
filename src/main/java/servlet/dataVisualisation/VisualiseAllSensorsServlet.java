package servlet.dataVisualisation;

import dao.SensorDao;
import imt.org.web.commonmodel.entities.SensorEntity;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Collection<SensorEntity> lst = SensorDao.getInstance().findAll();
        request.setAttribute("lstSensors",lst);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/dataVisualisation/sensor/sensorsCore.jsp");
        dispatcher.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
