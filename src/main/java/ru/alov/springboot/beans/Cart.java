package ru.alov.springboot.beans;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.alov.springboot.cart_utils.OrderItem;
import ru.alov.springboot.entities.Product;
import ru.alov.springboot.exceptions.ResourceNotFoundException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Data
public class Cart {

    private List<OrderItem> items;
    private Double price;

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }

    public void clear() {
        price = 0.0;
        items.clear();
    }

    public void add(Product product) {
        Optional<OrderItem> optionalOrderItem = items.stream().filter(orderItem -> orderItem.getProduct().getId().equals(product.getId())).findAny();
        if (optionalOrderItem.isPresent()) {
            optionalOrderItem.get().increment();
        } else items.add(new OrderItem(product));
        recalculate();
    }

    public void decrement(Product product) {
        Optional<OrderItem> optionalOrderItem = items.stream().filter(orderItem -> orderItem.getProduct().getId().equals(product.getId())).findAny();
        if (optionalOrderItem.isPresent()) {
            optionalOrderItem.get().decrement();
        } else throw new ResourceNotFoundException("Product not found, id = " + product.getId());
        recalculate();
    }

    public void removeByProductId(Long productId) {
        Optional<OrderItem> optionalOrderItem = items.stream()
                .filter(orderItem -> orderItem.getProduct().getId().equals(productId))
                .findFirst();
        if (optionalOrderItem.isPresent()) {
            OrderItem orderItem = optionalOrderItem.get();
            items.remove(orderItem);
        } else throw new ResourceNotFoundException("Product not found, id = " + productId);
    }

    public void recalculate() {
        price = 0.0;
        items.forEach(orderItem -> price += orderItem.getCost());
    }

}
