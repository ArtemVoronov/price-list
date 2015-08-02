package models;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by voronov on 28.07.2015.
 */
@Entity
@Table(name="category")
public class Category {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "idCategory")
    private int idCategory;

    @Basic
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "categoryByCategoryIdCategory")
    private Collection<Product> productsByIdCategory;

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (idCategory != category.idCategory) return false;
        if (name != null ? !name.equals(category.name) : category.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCategory;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public Collection<Product> getProductsByIdCategory() {
        return productsByIdCategory;
    }

    public void setProductsByIdCategory(Collection<Product> productsByIdCategory) {
        this.productsByIdCategory = productsByIdCategory;
    }
}
