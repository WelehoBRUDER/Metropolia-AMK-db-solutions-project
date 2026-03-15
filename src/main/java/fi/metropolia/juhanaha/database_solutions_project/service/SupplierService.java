package fi.metropolia.juhanaha.database_solutions_project.service;

import fi.metropolia.juhanaha.database_solutions_project.dto.CustomerDto;
import fi.metropolia.juhanaha.database_solutions_project.dto.CustomerProfileSimpleDto;
import fi.metropolia.juhanaha.database_solutions_project.dto.SupplierDto;
import fi.metropolia.juhanaha.database_solutions_project.dto.SupplierSimpleDto;
import fi.metropolia.juhanaha.database_solutions_project.entity.Supplier;
import fi.metropolia.juhanaha.database_solutions_project.entity.CustomerProfile;

public class SupplierService {
    public static SupplierDto toDTO(Supplier supplier) {
        if (supplier == null) return null;

        SupplierDto dto = new SupplierDto();
        dto.setId(supplier.getId());
        dto.setName(supplier.getName());
        dto.setContactName(supplier.getContactName());
        dto.setPhone(supplier.getPhone());
        dto.setEmail(supplier.getEmail());
        dto.setProducts(supplier.getProducts().stream().map(ProductService::toSimpleDTO).toList());
        return dto;
    }

    public static SupplierSimpleDto toSimpleDTO(Supplier supplier) {
        if (supplier == null) return null;

        SupplierSimpleDto dto = new SupplierSimpleDto();
        dto.setId(supplier.getId());
        dto.setName(supplier.getName());
        dto.setContactName(supplier.getContactName());

        return dto;
    }
}
