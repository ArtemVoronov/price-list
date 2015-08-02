package web.listeners; /**
 * Created by voronov on 28.07.2015.
 */

import DAO.CategoryDAO;
import DAO.DAOFactory;
import DAO.ProductDAO;
import models.Category;
import org.slf4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import static org.slf4j.LoggerFactory.getLogger;

@WebListener()
public class EMFListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    private static EntityManagerFactory emf;
    Logger log = null;
    // Public constructor is required by servlet spec
    public EMFListener() {
    }

    public static EntityManager createEntityManager() {
        if (emf == null) {
            throw new IllegalStateException("Context is not initialized yet.");
        }

        return emf.createEntityManager();
    }

    public static CriteriaBuilder getCriteriaBuilder() {
        if (emf == null) {
            throw new IllegalStateException("Context is not initialized yet.");
        }

        return emf.getCriteriaBuilder();
    }

    /* some data for demonstration */
    public void initPersistData() {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.H2);
        ProductDAO productDAO = daoFactory.getProductDAO();
        CategoryDAO categoryDAO = daoFactory.getCategoryDAO();

        Category software = categoryDAO.add("софт");
        Category hardware = categoryDAO.add("железо");
        Category printers = categoryDAO.add("принтеры");
        Category consumables = categoryDAO.add("расходники");
        Category phones = categoryDAO.add("смартфоны");
        Category laptops = categoryDAO.add("ноутбуки");

        productDAO.add("Антивирус Касперского", 1600., software);
        productDAO.add("Антивирус Norton", 700., software);
        productDAO.add("Антивирус Avast", 200., software);
        productDAO.add("Windows 7 Ultimate", 11500., software);
        productDAO.add("Windows 7 Pro", 9600., software);
        productDAO.add("Windows 7 HP", 7500., software);
        productDAO.add("Windows 7 HB", 4300., software);
        productDAO.add("Windows XP", 2300., software);
        productDAO.add("MS Office 2013", 7300., software);
        productDAO.add("MS Office 2010", 5300., software);
        productDAO.add("MS Office 2007", 3300., software);
        productDAO.add("MS Office 2003", 1300., software);
        productDAO.add("MS Visual Studio 2010", 7399., software);

        productDAO.add("Kingston 512 ddr3", 1800., hardware);
        productDAO.add("Transcend 512 ddr3", 1200., hardware);
        productDAO.add("Kingston 1024 ddr3", 2800., hardware);
        productDAO.add("Transcend 1024 ddr3", 3200., hardware);
        productDAO.add("Nvidia GTX8", 4800., hardware);
        productDAO.add("Nvidia GTS3", 2200., hardware);
        productDAO.add("Nvidia GTX7", 3800., hardware);
        productDAO.add("Nvidia GTS4", 3200., hardware);

        productDAO.add("принтер Epson", 2100., printers);
        productDAO.add("принтер HP", 2600., printers);
        productDAO.add("принтер Samsung", 4100., printers);
        productDAO.add("принтер Lenovo", 1600., printers);

        productDAO.add("картридж для принтера Epson", 320., consumables);
        productDAO.add("картридж для принтера HP", 460., consumables);
        productDAO.add("картридж для принтера Samsung", 520., consumables);
        productDAO.add("картридж для принтера Lenovo", 760., consumables);

        productDAO.add("HTC Desire S", 3320., phones);
        productDAO.add("HTC Wildfire S", 8460., phones);
        productDAO.add("Lenovo A316i", 2999.99, phones);
        productDAO.add("Nokia Lumia", 6000., phones);
        productDAO.add("Apple iPhone 4", 29000., phones);
        productDAO.add("Apple iPhone 4s", 39000., phones);
        productDAO.add("Apple iPhone 5", 69000., phones);
        productDAO.add("Apple iPhone 6", 89000., phones);

        productDAO.add("Asus N61JV", 39000., laptops);
        productDAO.add("Asus N61JA", 47000., laptops);
        productDAO.add("Asus N53", 53000., laptops);
        productDAO.add("Asus N47JK", 25000., laptops);
        productDAO.add("Acer 357", 21000., laptops);
        productDAO.add("Asus 358", 24000., laptops);
        productDAO.add("Asus 766", 56000., laptops);
        productDAO.add("Asus 689n", 110000., laptops);
        productDAO.add("Lenovo Ideapad", 16000., laptops);
        productDAO.add("HP 255", 11000., laptops);
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */

        System.setProperty("org.slf4j.simpleLogger.showDateTime","true");
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel","info");
        System.setProperty("org.slf4j.simpleLogger.dateTimeFormat", "yyyy-MM-dd::HH-mm-ss-SSS");

        log = getLogger(this.getClass().getName());
        log.info("The application started");

        emf = Persistence.createEntityManagerFactory("PriceListPU");
        initPersistData();
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
        log.info("The application stopped");
        emf.close();
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
      /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
      /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
