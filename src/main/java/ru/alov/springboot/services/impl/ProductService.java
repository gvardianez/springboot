package ru.alov.springboot.services.impl;

import org.springframework.stereotype.Service;
import ru.alov.springboot.entities.Client;
import ru.alov.springboot.entities.Order;
import ru.alov.springboot.entities.Product;
import ru.alov.springboot.exceptions.ResourceNotFoundException;
import ru.alov.springboot.repositories.ProductRepository;
import ru.alov.springboot.services.IProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public Product getProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(""));
        product.setCategoryType(product.getCategory().getCategoryType());
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        productList.forEach(product -> product.setCategoryType(product.getCategory().getCategoryType()));
        return productList;
    }

    @Override
    public List<Product> getAllProducts(Integer minPrice, Integer maxPrice) {
        if (minPrice != null && maxPrice != null) {
            return productRepository.findProductsByCostBetween(minPrice.doubleValue(), maxPrice.doubleValue());
        }
        if (minPrice != null) {
            return productRepository.findProductsByCostGreaterThan(minPrice.doubleValue());
        }
        if (maxPrice != null) {
            return productRepository.findProductsByCostLessThan(maxPrice.doubleValue());
        }
        return getAllProducts();
    }

    @Override
    public List<Product> getProductsByCategoryId(Long id) {
        return productRepository.findProductsByCategory_Id(id);
    }

    @Override
    public Product saveOrUpdate(Product product) {
        product.setCategory(categoryService.getCategoryByType(product.getCategoryType()));
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Client> getClientsByProductId(Long id) {
        return getProduct(id)
                .getOrderList()
                .stream()
                .map(Order::getClient)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public void changeCost(Long productID, Double deltaCost) {
        Product product = getProduct(productID);
        Double newPrice = product.getCost() + deltaCost;
        product.setCost(newPrice);
        saveOrUpdate(product);
    }

}
