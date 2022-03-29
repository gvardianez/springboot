package ru.alov.springboot.services.impl;

import lombok.Data;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.alov.springboot.beans.Cart;
import ru.alov.springboot.entities.Order;
import ru.alov.springboot.entities.User;
import ru.alov.springboot.repositories.OrderRepository;
import ru.alov.springboot.services.IOrderService;

import java.security.Principal;

@Service
@Data
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;

    @Override
    public Order createNewOrder(Principal principal, Cart cart) {
        String username = principal.getName();
        User user = userService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return orderRepository.save(new Order(user, cart));
    }

    @Override
    public Order getOrder(Long id) {
        return orderRepository.getById(id);
    }

}
