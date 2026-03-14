package fi.metropolia.juhanaha.database_solutions_project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import fi.metropolia.juhanaha.database_solutions_project.listener.ProductListener;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(ProductListener.class)
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private double price;
    @Column(name = "stock_quantity")
    private int stockQuantity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "product_supplier",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "supplier_id")
    )
    // @JsonBackReference
    private List<Supplier> suppliers = new ArrayList<Supplier>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String productName) {
        this.name = productName;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

}
