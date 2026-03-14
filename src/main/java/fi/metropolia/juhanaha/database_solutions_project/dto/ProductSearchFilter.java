package fi.metropolia.juhanaha.database_solutions_project.dto;

import java.math.BigDecimal;

public class ProductSearchFilter {
    private Integer supplierId;
    private double maxPrice;

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public BigDecimal getMaxPrice() {
        return BigDecimal.valueOf(maxPrice);
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }
}
