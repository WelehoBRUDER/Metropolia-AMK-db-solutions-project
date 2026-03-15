package fi.metropolia.juhanaha.database_solutions_project.controller;

import fi.metropolia.juhanaha.database_solutions_project.ProductRepository;
import fi.metropolia.juhanaha.database_solutions_project.ProductSearchCustom;
import fi.metropolia.juhanaha.database_solutions_project.dto.PriceBulkDto;
import fi.metropolia.juhanaha.database_solutions_project.dto.ProductDto;
import fi.metropolia.juhanaha.database_solutions_project.dto.ProductSearchFilter;
import fi.metropolia.juhanaha.database_solutions_project.entity.Product;
import fi.metropolia.juhanaha.database_solutions_project.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductSearchCustom productSearchCustom;
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository, ProductSearchCustom productSearchCustom) {
        this.productRepository = productRepository;
        this.productSearchCustom = productSearchCustom;
    }

    @GetMapping("/")
    public List<ProductDto> getProduct() {
        return productRepository.findAll()
                .stream()
                .map(ProductService::toDTO)
                .toList();
    }

    @GetMapping("/category/{id}")
    public List<ProductDto> findByCategory(@PathVariable int id) {
        return productRepository.findByCategory(id)
                .stream()
                .map(ProductService::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable final int id) {
        return productRepository.findById(id)
                .map(ProductService::toDTO)
                .map(product -> ResponseEntity.ok(product))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<ProductDto> getProductByCriteriaSearch(@RequestBody ProductSearchFilter request) {
        return productSearchCustom.searchProducts(request.getSupplierId(), request.getMaxPrice())
                .stream()
                .map(ProductService::toDTO)
                .toList();
    }

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        productRepository.save(product);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setDescription(product.getDescription());
                    existingProduct.setPrice(product.getPrice());
                    existingProduct.setStockQuantity(product.getStockQuantity());
                    existingProduct.setCategory(product.getCategory());
                    existingProduct.setSuppliers(product.getSuppliers());
                    productRepository.save(existingProduct);
                    return ResponseEntity.ok(existingProduct);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/price-bulk")
    public ResponseEntity<String> bulkUpdatePrices(@RequestBody PriceBulkDto request) {

        if (request.getFactor() <= 0) {
            throw new IllegalArgumentException("Factor must be positive");
        }

        int updatedRows;
        if (request.getCat() == null || request.getCat() == 0) {

            updatedRows = productRepository.updatePriceOfAllProducts(
                    request.getFactor()
            );

        } else {
            updatedRows = productRepository.updatePriceOfCategory(request.getFactor(), request.getCat());
        }
        return ResponseEntity.ok(updatedRows + " products updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
