package web.servlets;

import DAO.H2ProductDAO;
import DAO.ProductDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by voronov on 28.07.2015.
 */
@WebServlet(name = "PriceListServlet")
public class PriceListServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(PriceListServlet.class);
    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {
        log.info("PriceListServlet creates productDAO");
        productDAO = new H2ProductDAO();
        super.init();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");
        String product_name = request.getParameter("product_name");
        String strPriceMin = request.getParameter("price_min");
        String strPriceMax = request.getParameter("price_max");
        double priceMin=0.;
        double priceMax=0.;

        try {
            if(!"".equals(strPriceMin)) {
                priceMin = Double.parseDouble(strPriceMin);
            }
        } catch (NumberFormatException ex) {
            log.error("priceMin has wrong format", ex);
            priceMin=0.;
            strPriceMin="";
        }

        try {
            if(!"".equals(strPriceMax)) {
                priceMax = Double.parseDouble(strPriceMax);
            }
        } catch (NumberFormatException ex) {
            log.error("priceMax has wrong format", ex);
            priceMax=0.;
            strPriceMax="";
        }

        List prods = productDAO.find(category, product_name, priceMin, priceMax);
        request.setAttribute("prods", prods);
        request.setAttribute("old_category_name", category);
        request.setAttribute("old_product_name", product_name);
        request.setAttribute("old_price_min", strPriceMin);
        request.setAttribute("old_price_max", strPriceMax);
        request.getRequestDispatcher("pricelist.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List prods = productDAO.all();
        request.setAttribute("prods", prods);
        request.getRequestDispatcher("pricelist.jsp").forward(request, response);
    }
}
