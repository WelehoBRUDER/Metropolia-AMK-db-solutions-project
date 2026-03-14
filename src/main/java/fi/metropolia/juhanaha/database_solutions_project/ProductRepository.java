package fi.metropolia.juhanaha.database_solutions_project;

import fi.metropolia.juhanaha.database_solutions_project.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.category.id = :cat")
    List<Product> findByCategory(@Param("cat") int cat);

    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.price = p.price * :factor")
    int updatePriceOfAllProducts(@Param("factor") double factor);

    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.price = p.price * :factor WHERE p.category.id = :cat")
    int updatePriceOfCategory(@Param("factor") double factor, @Param("cat") int cat);
}