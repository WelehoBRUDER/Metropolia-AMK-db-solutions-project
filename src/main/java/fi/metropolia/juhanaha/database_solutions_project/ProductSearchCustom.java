package fi.metropolia.juhanaha.database_solutions_project;

import fi.metropolia.juhanaha.database_solutions_project.entity.Product;
import fi.metropolia.juhanaha.database_solutions_project.entity.Supplier;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductSearchCustom {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> searchProducts(
            Integer supplierId,
            BigDecimal maxPrice) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> product = cq.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();

        Join<Product, Supplier> supplierJoin = product.join("suppliers");

        // Supplier filter
        if (supplierId != null) {
            predicates.add(
                    cb.equal(supplierJoin.get("id"), supplierId)
            );
        }

        // Price filter
        if (maxPrice != null) {
            predicates.add(
                    cb.lessThan(product.get("price"), maxPrice)
            );
        }

        // Combine all predicates with AND
        cq.where(cb.and(predicates.toArray(new Predicate[0])));
        TypedQuery<Product> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}
