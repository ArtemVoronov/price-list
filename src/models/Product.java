package models;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;

/**
 * Created by voronov on 28.07.2015.
 */
@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "idProduct")
    private int idProduct;

    @Basic
    @NotNull
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "price")
    private Double price;

//    @ManyToOne(cascade=CascadeType.ALL)
    @ManyToOne(cascade={ CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "Category_idCategory", referencedColumnName = "idCategory", nullable = false)
    private Category categoryByCategoryIdCategory;

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (idProduct != product.idProduct) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (price != null ? !price.equals(product.price) : product.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idProduct;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    public Category getCategoryByCategoryIdCategory() {
        return categoryByCategoryIdCategory;
    }

    public void setCategoryByCategoryIdCategory(Category categoryByCategoryIdCategory) {
        this.categoryByCategoryIdCategory = categoryByCategoryIdCategory;
    }
}
