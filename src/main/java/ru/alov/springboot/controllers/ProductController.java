package ru.alov.springboot.controllers;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.alov.springboot.converters.ProductConverter;
import ru.alov.springboot.dto.ProductDto;
import ru.alov.springboot.entities.Product;
import ru.alov.springboot.services.ICategoryService;
import ru.alov.springboot.services.IClientService;
import ru.alov.springboot.services.IProductService;


@RestController
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
    public Page<ProductDto> index(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "name_part", required = false) String namePart) {
        return productService.findByFilter(minPrice, maxPrice, namePart, page)
                .map(productConverter::entityToDto);
    }

    @GetMapping("/{id}")
    public ProductDto showProduct(@PathVariable("id") Long id) {
        return productConverter.entityToDto(productService.getProduct(id));
    }

    @PostMapping()
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        productDto.setId(null);
        Product product = productService.saveOrUpdate(productConverter.dtoToEntity(productDto));
        return productConverter.entityToDto(product);
    }

    @GetMapping("/{id}/edit")
    public ProductDto editProduct(@PathVariable("id") Long id) {
        return productConverter.entityToDto(productService.getProduct(id));
    }

    @PutMapping("/{id}")
    public ProductDto editProduct(@RequestBody ProductDto productDto) {
        Product product = productService.saveOrUpdate(productConverter.dtoToEntity(productDto));
        return productConverter.entityToDto(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.delete(id);
    }

    @PatchMapping("/change_cost")
    public void changeScore(@RequestParam Long productId, @RequestParam Double deltaCost) {
        productService.changeCost(productId, deltaCost);
    }

}
