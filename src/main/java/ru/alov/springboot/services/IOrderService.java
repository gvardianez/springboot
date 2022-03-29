package ru.alov.springboot.services;


import ru.alov.springboot.beans.Cart;
import ru.alov.springboot.entities.Order;

import java.security.Principal;

public interface IOrderService {

    Order createNewOrder(Principal principal, Cart cart);

    Order getOrder(Long id);

}
