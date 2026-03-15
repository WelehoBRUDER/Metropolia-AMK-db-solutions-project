package fi.metropolia.juhanaha.database_solutions_project.dto;

import java.util.ArrayList;
import java.util.List;

public class CategoryDto {
    private int id;
    private String name;
    private String description;
    private List<ProductDto> products = new ArrayList<ProductDto>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }
}
