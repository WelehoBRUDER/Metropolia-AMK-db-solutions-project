package fi.metropolia.juhanaha.database_solutions_project.controller;

import fi.metropolia.juhanaha.database_solutions_project.SupplierRepository;
import fi.metropolia.juhanaha.database_solutions_project.entity.Supplier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    private final SupplierRepository supplierRepository;

    public SupplierController(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @GetMapping("/")
    public List<Supplier> getSupplier() {
        return supplierRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplier(@PathVariable final int id) {
        return supplierRepository.findById(id)
                .map(supplier -> ResponseEntity.ok(supplier))
                .orElse(ResponseEntity.notFound().build());
    }

//    @GetMapping("/{id}/products")
//    public List<Product> getProducts(@PathVariable final int id) {
//        ResponseEntity<Supplier> supplier = getSupplier(id);
//        return supplier.getProducts();
//    }

}
