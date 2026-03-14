package fi.metropolia.juhanaha.database_solutions_project;

import fi.metropolia.juhanaha.database_solutions_project.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}