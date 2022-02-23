package ru.alov.springboot.services.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.alov.springboot.entities.Order;
import ru.alov.springboot.repositories.OrderRepository;
import ru.alov.springboot.services.IOrderService;

public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order getOrder(Long id) {
            return orderRepository.getById(id);
    }


}
