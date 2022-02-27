package ru.alov.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alov.springboot.entities.Product;
import ru.alov.springboot.services.*;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoriesController {

    private final ICategoryService categoryService;

    public CategoriesController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "categories/index";
    }

    @GetMapping("/{id}")
    public String showCategory(@PathVariable("id") Long id, Model model) {
        List<Product> products = categoryService.getProductsByCategory(id);
        model.addAttribute("products", products);
        return "categories/showCategory";
    }

}
