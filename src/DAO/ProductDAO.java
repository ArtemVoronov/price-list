package DAO;

import models.Category;
import models.Product;

import java.util.List;

/**
 * Created by voronov on 26.07.2015.
 */
public interface ProductDAO {
    List<Product> find(String categoryName, String productName, Double priceMin, Double priceMax);
    List<Product> all();
    Product add(String productName, Double price, Category category);
    void delete(Product product);
    void update(Product product);
}
