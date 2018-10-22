package imt.org.web.weatheradmindataviewer.servlet;

import imt.org.web.commonmodel.entities.SensorEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TestJpaServlet", urlPatterns = "/testjpa")
public class TestJpaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("WeatherDatabase");

        EntityManager em = emf.createEntityManager();

        SensorEntity sensor = em.find(SensorEntity.class, 1);
        List<SensorEntity> sensors = em.createQuery("SELECT s FROM SensorEntity s").getResultList();


        em.close();
        emf.close();
    }
}
