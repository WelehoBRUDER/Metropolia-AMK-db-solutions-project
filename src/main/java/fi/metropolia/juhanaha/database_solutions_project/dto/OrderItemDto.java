package fi.metropolia.juhanaha.database_solutions_project.dto;

import fi.metropolia.juhanaha.database_solutions_project.entity.Order;
import fi.metropolia.juhanaha.database_solutions_project.entity.OrderItemId;
import fi.metropolia.juhanaha.database_solutions_project.entity.Product;
import jakarta.persistence.*;

public class OrderItemDto {
    private Integer order;
    private ProductSimpleDto product;
    private Integer quantity;
    private double unitPrice;

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public ProductSimpleDto getProduct() {
        return product;
    }

    public void setProduct(ProductSimpleDto product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
