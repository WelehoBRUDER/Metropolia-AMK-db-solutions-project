package fi.metropolia.juhanaha.database_solutions_project.controller;

import fi.metropolia.juhanaha.database_solutions_project.CustomerRepository;
import fi.metropolia.juhanaha.database_solutions_project.dto.CustomerDto;
import fi.metropolia.juhanaha.database_solutions_project.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/")
    public List<CustomerDto> getCategory() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerService::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable final int id) {
        return customerRepository.findById(id)
                .map(CustomerService::toDTO)
                .map(cust -> ResponseEntity.ok(cust))
                .orElse(ResponseEntity.notFound().build());
    }
}