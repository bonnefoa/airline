package airline.listener;

import airline.guiceBindings.Servlet;
import com.google.inject.Guice;
import com.google.inject.Injector;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Feb 7, 2009
 * Time: 11:07:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class GuiceServletContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        Injector injector = Guice.createInjector(new Servlet());
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute(Injector.class.getName(), injector);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        servletContext.removeAttribute(Injector.class.getName());
    }
}
/*
pour référence, histoire d'expliquer ça dans le rapport :
http://www.factorypattern.com/guice-servlets/
 */
