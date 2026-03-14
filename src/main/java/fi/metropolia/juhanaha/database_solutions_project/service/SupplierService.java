package fi.metropolia.juhanaha.database_solutions_project.service;

import fi.metropolia.juhanaha.database_solutions_project.dto.CustomerDto;
import fi.metropolia.juhanaha.database_solutions_project.dto.CustomerProfileSimpleDto;
import fi.metropolia.juhanaha.database_solutions_project.dto.SupplierSimpleDto;
import fi.metropolia.juhanaha.database_solutions_project.entity.Supplier;
import fi.metropolia.juhanaha.database_solutions_project.entity.CustomerProfile;

public class SupplierService {
//    public static SupplierDto toDTO(Supplier supplier) {
//        if (supplier == null) return null;
//
//        CustomerDto dto = new CustomerDto();
//        dto.setId(supplier.getId());
//        dto.setFirstName(supplier.getFirstName());
//        dto.setLastName(supplier.getLastName());
//        dto.setPhone(supplier.getPhone());
//        dto.setEmail(supplier.getEmail());
//
//        if (supplier.getProfile() != null) {
//            dto.setProfile(toDTO(supplier.getProfile()));
//        }
//
//        return dto;
//    }

    public static SupplierSimpleDto toDTO(Supplier supplier) {
        if (supplier == null) return null;

        SupplierSimpleDto dto = new SupplierSimpleDto();
        dto.setId(supplier.getId());
        dto.setName(supplier.getName());
        dto.setContactName(supplier.getContactName());

        return dto;
    }
}
