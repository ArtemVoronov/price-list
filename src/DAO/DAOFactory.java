package DAO;

/**
 * Created by voronov on 28.07.2015.
 */
public abstract class DAOFactory {
    public static final int H2 = 1;

    public abstract ProductDAO getProductDAO();
    public abstract CategoryDAO getCategoryDAO();

    public static DAOFactory getDAOFactory(
            int whichFactory) {

        switch (whichFactory) {
            case H2:
                return new H2DAOFactory();
            default:
                return null;
        }
    }
}
