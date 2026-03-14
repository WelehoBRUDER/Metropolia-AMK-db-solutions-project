package fi.metropolia.juhanaha.database_solutions_project;

import fi.metropolia.juhanaha.database_solutions_project.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}