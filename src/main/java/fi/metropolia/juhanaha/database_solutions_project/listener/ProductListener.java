package fi.metropolia.juhanaha.database_solutions_project.listener;

import fi.metropolia.juhanaha.database_solutions_project.entity.Product;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class ProductListener {
    @PrePersist
    @PreUpdate
    public void validatePrice(Product product) {
        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        // Cap price to 100 million, which is still a ridiculously high price
        // but anything beyond it would be an obvious mistake
        if (product.getPrice() > Math.pow(10, 8)) {
            throw new IllegalArgumentException("Price cannot be greater than 100 million");
        }
    }
}
