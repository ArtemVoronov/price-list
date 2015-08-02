package DAO;

import models.Category;
import models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.listeners.EMFListener;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by voronov on 26.07.2015.
 */

public class H2ProductDAO implements ProductDAO {

    Logger log = LoggerFactory.getLogger(H2ProductDAO.class);

    @Override
    public List<Product> find(String categoryName, String productName, Double priceMin, Double priceMax) {
        log.info("finding product by category: {}, name: {}, priceMin: {}, priceMax: {}", categoryName, productName, priceMin, priceMax);
        List<Product> result = null;

        EntityManager em =  EMFListener.createEntityManager();
        CriteriaBuilder cb = EMFListener.getCriteriaBuilder();

        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> rootProduct = query.from(Product.class);

        Expression<String> pathProductName = rootProduct.get("name");
        Path<Category> pathCategory = rootProduct.get("categoryByCategoryIdCategory");
        Expression<String> pathCategoryName = pathCategory.get("name");
        Expression<Double> pathPrice = rootProduct.get("price");

        Predicate condition1 = cb.like(cb.lower(pathProductName), productName.toLowerCase() + "%");
        Predicate condition2 = cb.like(cb.lower(pathCategoryName), categoryName.toLowerCase() + "%");
        Predicate condition3 = null;

        if (priceMax > 0) {
            condition3 = cb.between(pathPrice, priceMin, priceMax);
        } else {
            condition3 = cb.ge(pathPrice, priceMin);
        }

        Predicate commonCondition = cb.and(condition1,condition2, condition3);

        query.where(commonCondition);

        TypedQuery<Product> q = em.createQuery(query);
        result = q.getResultList();

        return result;
    }

    @Override
    public List<Product> all() {
        log.info("getting all products");
        List<Product> result = null;
        EntityManager em = EMFListener.createEntityManager();
        CriteriaBuilder cb = EMFListener.getCriteriaBuilder();

        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> rootEntry = cq.from(Product.class);
        CriteriaQuery<Product> all = cq.select(rootEntry);
        TypedQuery<Product> allQuery = em.createQuery(all);

        result = allQuery.getResultList();

        return result;
    }

    @Override
    public Product add(String productName, Double price, Category category) {
        log.info("adding product with category: {}, name: {}, price: {}", category.getName(), productName, productName, price);
        EntityManager em = EMFListener.createEntityManager();
        Product product = null;

        try {
            product = new Product();
            product.setName(productName);
            product.setPrice(price);
            product.setCategoryByCategoryIdCategory(category);
            em.getTransaction().begin();
            em.merge(product);
            em.getTransaction().commit();
            log.info("created product with id: {}, name: {}", category.getIdCategory(), category.getName());
        }
        finally {
            // Close the database connection:
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }
        return product;
    }

    @Override
    public void delete(Product product) {
        //TODO
    }

    @Override
    public void update(Product product) {
        //TODO
    }
}
