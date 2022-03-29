package ru.alov.springboot.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "order_items")
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "cost")
    private Double cost;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

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

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", cost=" + cost +
                ", order=" + order +
                '}';
    }
}
