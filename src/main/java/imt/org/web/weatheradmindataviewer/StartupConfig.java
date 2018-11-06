package imt.org.web.weatheradmindataviewer;

import imt.org.web.weatheradmindataviewer.crud.CRUDEntityFacade;
import imt.org.web.weatheradmindataviewer.crud.facade.IEntityFacade;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class StartupConfig implements ServletContextListener {

    /**
     * Init CRUDEntityFacade at server startup
     * @param servletContextEvent servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        IEntityFacade crudEntityFacade = new CRUDEntityFacade();
        servletContextEvent.getServletContext().setAttribute("CRUDEntityFacade", crudEntityFacade);
    }

    /**
     * Unused
     * @param servletContextEvent servletContextEvent
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        throw new UnsupportedOperationException();
    }
}
