package fi.metropolia.juhanaha.database_solutions_project;

import fi.metropolia.juhanaha.database_solutions_project.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
