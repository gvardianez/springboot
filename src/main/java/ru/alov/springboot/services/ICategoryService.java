package ru.alov.springboot.services;

import org.springframework.stereotype.Component;
import ru.alov.springboot.entities.Category;
import ru.alov.springboot.entities.Product;

import java.util.List;


public interface ICategoryService {

    List<Category> getAllCategories();

    Category getCategoryById(Long id);

    List<Product> getProductsByCategory(Long id);

    Category getCategoryByType(Product.CategoryTypes categoryType);

}
