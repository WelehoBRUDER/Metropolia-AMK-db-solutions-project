package fi.metropolia.juhanaha.database_solutions_project.service;


import fi.metropolia.juhanaha.database_solutions_project.dto.CategoryDto;
import fi.metropolia.juhanaha.database_solutions_project.entity.Category;

public class CategoryService {
    public static CategoryDto toDTO(Category category) {
        if (category == null) return null;

        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        dto.setProducts(category.getProducts().stream().map(ProductService::toDTO).toList());
        return dto;
    }
}
