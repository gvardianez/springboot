package ru.alov.springboot.services;

import ru.alov.springboot.entities.Client;
import ru.alov.springboot.entities.Product;

import java.util.Collection;
import java.util.List;

public interface IProductService {
    Product getProduct(Long id);

    List<Product> getAllProducts();

    Product saveOrUpdate(Product product);

    void delete(Long id);

    List<Product> getProductsByCategoryId(Long id);

    List<Client> getClientsByProductId(Long id);

    void changeCost(Long productID, Double deltaCost);

    List<Product> getAllProducts(Integer minPrice, Integer maxPrice);

}
