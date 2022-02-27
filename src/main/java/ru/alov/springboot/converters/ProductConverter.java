package ru.alov.springboot.converters;

import org.springframework.stereotype.Component;
import ru.alov.springboot.dto.ProductDto;
import ru.alov.springboot.entities.Product;

@Component
public class ProductConverter {

    public Product dtoToEntity(ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getName(), productDto.getCost(), productDto.getCategoryType());
    }

    public ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getCost(), product.getCategoryType());
    }

}
