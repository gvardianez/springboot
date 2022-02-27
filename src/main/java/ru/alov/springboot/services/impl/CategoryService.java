package ru.alov.springboot.services.impl;

import org.springframework.stereotype.Service;
import ru.alov.springboot.entities.Category;
import ru.alov.springboot.entities.Product;
import ru.alov.springboot.repositories.CategoryRepository;
import ru.alov.springboot.services.ICategoryService;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.getById(id);
    }

    @Override
    public Category getCategoryByType(Product.CategoryTypes categoryType) {
        return categoryRepository.findCategoryByCategoryType(categoryType);
    }

    @Override
    public List<Product> getProductsByCategory(Long id) {
        return getCategoryById(id).getProductList();
    }

}
