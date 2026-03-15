package fi.metropolia.juhanaha.database_solutions_project.controller;

import fi.metropolia.juhanaha.database_solutions_project.CategoryRepository;
import fi.metropolia.juhanaha.database_solutions_project.dto.CategoryDto;
import fi.metropolia.juhanaha.database_solutions_project.entity.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import fi.metropolia.juhanaha.database_solutions_project.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/")
    public List<CategoryDto> getCategory() {
        return categoryRepository.findAll().stream().map(CategoryService::toDTO).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable final int id) {
        return categoryRepository.findById(id)
                .map(CategoryService::toDTO)
                .map(category -> ResponseEntity.ok(category))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        categoryRepository.save(category);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable final int id) {
        categoryRepository.deleteById(id);
    }
}
