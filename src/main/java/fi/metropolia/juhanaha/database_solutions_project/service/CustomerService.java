package fi.metropolia.juhanaha.database_solutions_project.service;

import fi.metropolia.juhanaha.database_solutions_project.dto.CustomerDto;
import fi.metropolia.juhanaha.database_solutions_project.dto.CustomerProfileSimpleDto;
import fi.metropolia.juhanaha.database_solutions_project.dto.CustomerSimpleDto;
import fi.metropolia.juhanaha.database_solutions_project.entity.Customer;
import fi.metropolia.juhanaha.database_solutions_project.entity.CustomerProfile;
import fi.metropolia.juhanaha.database_solutions_project.entity.Order;

import java.util.stream.Collectors;

public class CustomerService {
    public static CustomerDto toDTO(Customer customer) {
        if (customer == null) return null;

        CustomerDto dto = new CustomerDto();
        dto.setId(customer.getId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setPhone(customer.getPhone());
        dto.setEmail(customer.getEmail());
        dto.setOrders(customer.getOrders().stream().map(OrderService::toDTO).collect(Collectors.toList()));

        if (customer.getProfile() != null) {
            dto.setProfile(toDTO(customer.getProfile()));
        }

        return dto;
    }

    public static CustomerSimpleDto toSimpleDTO(Customer customer) {
        if (customer == null) return null;

        CustomerSimpleDto dto = new CustomerSimpleDto();
        dto.setId(customer.getId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setPhone(customer.getPhone());
        dto.setEmail(customer.getEmail());

        return dto;
    }

    public static CustomerProfileSimpleDto toDTO(CustomerProfile profile) {
        if (profile == null) return null;

        CustomerProfileSimpleDto dto = new CustomerProfileSimpleDto();
        dto.setId(profile.getId());
        dto.setUsername(profile.getUsername());
        dto.setEmail(profile.getEmail());

        return dto;
    }
}
