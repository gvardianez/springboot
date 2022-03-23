package ru.alov.springboot.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.alov.springboot.entities.Product;

public class ProductsSpecifications {
    public static Specification<Product> costGreaterOrEqualsThan(Integer cost) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), cost);
    }

    public static Specification<Product> costLessThanOrEqualsThan(Integer cost) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("cost"), cost);
    }

    public static Specification<Product> nameLike(String namePart) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), String.format("%%%s%%", namePart));
    }
}
