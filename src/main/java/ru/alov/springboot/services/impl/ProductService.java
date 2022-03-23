package ru.alov.springboot.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.alov.springboot.converters.ProductConverter;
import ru.alov.springboot.entities.Client;
import ru.alov.springboot.entities.Order;
import ru.alov.springboot.entities.Product;
import ru.alov.springboot.exceptions.ResourceNotFoundException;
import ru.alov.springboot.repositories.ProductRepository;
import ru.alov.springboot.repositories.specifications.ProductsSpecifications;
import ru.alov.springboot.services.IProductService;

import javax.transaction.Transactional;
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
    public Page<Product> findByFilter(Integer minScore, Integer maxScore, String partName, Integer page) {
        Specification<Product> spec = Specification.where(null);
        if (minScore != null) {
            spec = spec.and(ProductsSpecifications.costGreaterOrEqualsThan(minScore));
        }
        if (maxScore != null) {
            spec = spec.and(ProductsSpecifications.costLessThanOrEqualsThan(maxScore));
        }
        if (partName != null) {
            spec = spec.and(ProductsSpecifications.nameLike(partName));
        }
        Page<Product> productPage =  productRepository.findAll(spec, PageRequest.of(page - 1, 10));
        productPage.forEach(product -> product.setCategoryType(product.getCategory().getCategoryType()));
        return productPage;
    }

    @Override
    public Product getProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
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
        if (minPrice == null) minPrice = 0;
        if (maxPrice == null) maxPrice = Integer.MAX_VALUE;
        return productRepository.findProductsByCostBetween(minPrice.doubleValue(), maxPrice.doubleValue());
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
    @Transactional
    public void changeCost(Long productID, Double deltaCost) {
        Product product = getProduct(productID);
        Double newPrice = product.getCost() + deltaCost;
        product.setCost(newPrice);
    }

}
