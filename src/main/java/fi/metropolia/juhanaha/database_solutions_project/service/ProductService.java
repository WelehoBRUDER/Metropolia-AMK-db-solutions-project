package fi.metropolia.juhanaha.database_solutions_project.service;

import fi.metropolia.juhanaha.database_solutions_project.dto.ProductDto;
import fi.metropolia.juhanaha.database_solutions_project.entity.Product;
import fi.metropolia.juhanaha.database_solutions_project.entity.Supplier;

import java.util.List;
//private List<Integer> suppliers;
public class ProductService {
    public static ProductDto toDTO(Product product) {
        if (product == null) return null;

        List<Integer> supplierIds = product.getSuppliers()
                .stream()
                .map(Supplier::getId)
                .toList();

        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity());
        dto.setCategory(product.getCategory() != null ? product.getCategory().getId() : null);
        dto.setSuppliers(supplierIds);
        return dto;
    }

//    public int updatePrices(Integer categoryId,
//                            Double minPrice,
//                            double factor) {
//
//        if (factor <= 0) {
//            throw new IllegalArgumentException("Factor must be positive");
//        }
//
//        return productRepository.updatePrices(
//                categoryId,
//                minPrice,
//                factor
//        );
//        }
}
