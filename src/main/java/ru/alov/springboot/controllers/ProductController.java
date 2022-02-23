package ru.alov.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.alov.springboot.converters.ProductConverter;
import ru.alov.springboot.dto.ProductDto;
import ru.alov.springboot.entities.Product;
import ru.alov.springboot.services.ICategoryService;
import ru.alov.springboot.services.IClientService;
import ru.alov.springboot.services.IProductService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final IProductService productService;
    private final ICategoryService categoryService;
    private final IClientService clientService;
    private final ProductConverter productConverter;

    public ProductController(IProductService productService, ICategoryService categoryService, IClientService clientService, ProductConverter productConverter) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.clientService = clientService;
        this.productConverter = productConverter;
    }

    @GetMapping
    @ResponseBody
    public List<ProductDto> index( @RequestParam(name = "min_price", required = false) Integer minPrice,
                                   @RequestParam(name = "max_price", required = false) Integer maxPrice) {
        return productService.getAllProducts(minPrice, maxPrice)
                .stream()
                .map(productConverter::entityToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public String showProduct(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProduct(id);
        model.addAttribute("product", product);
        return "product/showProduct";
    }

    @GetMapping("/new")
    public String newProduct(@ModelAttribute("product") Product product) {
        return "product/new";
    }

    @PostMapping()
    public String createProduct(@ModelAttribute("product") Product product
    ) {
        productService.saveOrUpdate(product);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String editProduct(Model model, @PathVariable("id") Long id) {
        model.addAttribute(productService.getProduct(id));
        return "product/edit";
    }

    @PatchMapping("/{id}")
    public String editProduct(@ModelAttribute("product") Product product) {
        productService.saveOrUpdate(product);
        return "redirect:/products";
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.delete(id);
    }

    @GetMapping("/change_cost")
    @ResponseBody
    public void changeScore(@RequestParam Long productId, @RequestParam Double deltaCost) {
        productService.changeCost(productId, deltaCost);
    }

}
