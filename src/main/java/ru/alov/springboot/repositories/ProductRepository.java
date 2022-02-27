package ru.alov.springboot.repositories;

import org.hibernate.SessionFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alov.springboot.entities.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByCategory_Id(Long id);

    List<Product> findProductsByCostBetween(Double minCost, Double maxCost);

    List<Product> findProductsByCostLessThan(Double maxCost);

    List<Product> findProductsByCostGreaterThan(Double minCost);
}
