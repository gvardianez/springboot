package ru.alov.springboot.cart_utils;

import lombok.Data;
import ru.alov.springboot.entities.Product;

@Data
public class OrderItem {

    private final Product product;
    private int quantity;
    private Double cost;

    public OrderItem(Product product) {
        this.product = product;
        this.quantity = 1;
        this.cost = product.getCost();
    }

    public void increment() {
        this.quantity++;
        this.cost = cost + product.getCost();
    }

    public void decrement() {
        this.quantity--;
        this.cost = cost - product.getCost();
    }


}
