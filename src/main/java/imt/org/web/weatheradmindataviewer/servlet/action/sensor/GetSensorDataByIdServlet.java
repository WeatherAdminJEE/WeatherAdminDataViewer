package imt.org.web.weatheradmindataviewer.servlet.action.sensor;

import com.google.gson.Gson;
import imt.org.web.weatheradmindataviewer.bean.SensorDataDto;
import imt.org.web.weatheradmindataviewer.crud.CRUDEntityFacade;
import imt.org.web.weatheradmindataviewer.dao.sensordata.SensorDataDao;
import imt.org.web.weatheradmindataviewer.transformers.SensorDataTransformer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

@WebServlet(name = "GetSensorDataByIdServlet", urlPatterns = "/getSensorDataById")
public class GetSensorDataByIdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int sensorId;
        try {
            String idStr = request.getParameter("sensorId");
            sensorId = Integer.parseInt(idStr);

            String beginDateStr = request.getParameter("beginDate");
            String endDateStr = request.getParameter("endDate");
            Timestamp beginDate;
            Timestamp endDate = null;
            //S'il y a une datetime range indiquée
            if(
                beginDateStr != null &&
                !beginDateStr.isEmpty() &&
                endDateStr != null &&
                !endDateStr.isEmpty()
            ){
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                Date date = dateFormat.parse(beginDateStr);
                long time = date.getTime();
                beginDate = new Timestamp(time);

                date = dateFormat.parse(endDateStr);
                time = date.getTime();
                endDate = new Timestamp(time);
            }

            //Sinon on prend par défaut les deux dernieres heures
            else {
                LocalDateTime dateTime = LocalDateTime.now().minusHours(2);
                beginDate = Timestamp.valueOf(dateTime);
            }

            //Récupère la liste de données du sensor selon le date range voulu
            SensorDataDao sensorDataDao = new SensorDataDao((CRUDEntityFacade)getServletContext().getAttribute("CRUDEntityFacade"));
            Collection<SensorDataDto> lstSensorData = SensorDataTransformer.entityToDto(sensorDataDao.findDataBySensorDateRange(sensorId, beginDate, endDate));

            String jsonResult = new Gson().toJson(lstSensorData);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonResult);
        }
        catch (NumberFormatException ex){
            System.out.println("Bad parameter format exception");
        }
        catch (ParseException e) {
            System.out.println("Bad datetime format parameter exception");
        }
    }
}
