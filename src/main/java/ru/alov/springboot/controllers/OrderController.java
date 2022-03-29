package ru.alov.springboot.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;
import ru.alov.springboot.beans.Cart;
import ru.alov.springboot.converters.OrderConverter;
import ru.alov.springboot.dto.OrderDto;
import ru.alov.springboot.exceptions.ResourceNotFoundException;
import ru.alov.springboot.services.impl.OrderService;

import java.security.Principal;

@AllArgsConstructor
@RestController
@Data
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final Cart cart;
    private final OrderConverter orderConverter;

    @GetMapping("/create")
    public OrderDto createNewOrder(Principal principal) {
        if (cart.getItems().size() == 0) throw new ResourceNotFoundException("Cart is clear");
        return orderConverter.entityToDto(orderService.createNewOrder(principal, cart), principal.getName());
    }

}
