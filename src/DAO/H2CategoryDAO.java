package DAO;

import models.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.listeners.EMFListener;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by voronov on 28.07.2015.
 */
public class H2CategoryDAO implements CategoryDAO {

    Logger log = LoggerFactory.getLogger(H2CategoryDAO.class);

    @Override
    public List<Category> find(String categoryName) {
        log.info("finding category by name: {}", categoryName);
        List<Category> result = null;

        EntityManager em =  EMFListener.createEntityManager();
        CriteriaBuilder cb = EMFListener.getCriteriaBuilder();

        CriteriaQuery<Category> query = cb.createQuery(Category.class);
        Root<Category> rootCategory = query.from(Category.class);

        Expression<String> pathCategoryName = rootCategory.get("name");

        Predicate condition = cb.like(cb.lower(pathCategoryName), categoryName.toLowerCase() + "%");
        query.where(condition);

        TypedQuery<Category> q = em.createQuery(query);
        result = q.getResultList();

        return result;
    }

    @Override
    public List<Category> all() {
        log.info("getting all categories");
        List<Category> result = null;
        EntityManager em = EMFListener.createEntityManager();
        CriteriaBuilder cb = EMFListener.getCriteriaBuilder();

        CriteriaQuery<Category> cq = cb.createQuery(Category.class);
        Root<Category> rootEntry = cq.from(Category.class);
        CriteriaQuery<Category> all = cq.select(rootEntry);
        TypedQuery<Category> allQuery = em.createQuery(all);

        result = allQuery.getResultList();

        return result;
    }

    @Override
    public Category add(String categoryName) {
        log.info("adding category with name: {}", categoryName);
        EntityManager em = EMFListener.createEntityManager();
        Category category = null;

        try {
            category = new Category();
            category.setName(categoryName);
            em.getTransaction().begin();
            em.persist(category);
            em.getTransaction().commit();
            log.info("created category with id: {}, name: {}", category.getIdCategory(), category.getName());
        } finally {
            // Close the database connection:
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }

        return category;
    }

    @Override
    public void delete(Category category) {
        //TODO
    }

    @Override
    public void update(Category category) {
        //TODO
    }
}
