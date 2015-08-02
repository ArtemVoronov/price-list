package DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by voronov on 28.07.2015.
 */
public class H2DAOFactory extends DAOFactory {

    Logger log = LoggerFactory.getLogger(H2DAOFactory.class);

    public ProductDAO getProductDAO() {
        return new H2ProductDAO();
    }
    public CategoryDAO getCategoryDAO() {
        return new H2CategoryDAO();
    }
}
