package DAO;

import models.Category;

import java.util.List;

/**
 * Created by voronov on 28.07.2015.
 */
public interface CategoryDAO {
    List<Category> find(String categoryName);
    List<Category> all();
    Category add(String categoryName);
    void delete(Category category);
    void update(Category category);
}
