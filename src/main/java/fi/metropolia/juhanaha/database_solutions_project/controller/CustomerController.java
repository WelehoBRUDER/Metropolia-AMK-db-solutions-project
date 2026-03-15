package fi.metropolia.juhanaha.database_solutions_project.controller;

import fi.metropolia.juhanaha.database_solutions_project.CustomerRepository;
import fi.metropolia.juhanaha.database_solutions_project.dto.CustomerDto;
import fi.metropolia.juhanaha.database_solutions_project.dto.CustomerSimpleDto;
import fi.metropolia.juhanaha.database_solutions_project.entity.Customer;
import fi.metropolia.juhanaha.database_solutions_project.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/")
    public List<CustomerSimpleDto> getCustomer() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerService::toSimpleDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable final int id) {
        return customerRepository.findById(id)
                .map(CustomerService::toDTO)
                .map(cust -> ResponseEntity.ok(cust))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        customerRepository.save(customer);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable final int id, @RequestBody Customer customer) {
        return customerRepository.findById(id)
                .map(existingCustomer -> {
                    existingCustomer.setFirstName(customer.getFirstName());
                    existingCustomer.setLastName(customer.getLastName());
                    existingCustomer.setEmail(customer.getEmail());
                    existingCustomer.setPhone(customer.getPhone());
                    customerRepository.save(existingCustomer);
                    return ResponseEntity.ok(existingCustomer);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable final int id) {
        customerRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}