package fi.metropolia.juhanaha.database_solutions_project.dto;

import fi.metropolia.juhanaha.database_solutions_project.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class SupplierDto {
    private int id;
    private String name;
    private String contactName;
    private String phone;
    private String email;
    private List<ProductSimpleDto> products = new ArrayList<ProductSimpleDto>();

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

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ProductSimpleDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductSimpleDto> products) {
        this.products = products;
    }
}
