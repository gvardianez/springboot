package ru.alov.springboot.repositories;

import org.hibernate.SessionFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.alov.springboot.entities.Category;
import ru.alov.springboot.entities.Product;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoryByCategoryType(Product.CategoryTypes categoryType);

}

