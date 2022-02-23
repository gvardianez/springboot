package ru.alov.springboot.services;

import ru.alov.springboot.entities.Client;
import ru.alov.springboot.entities.Order;
import ru.alov.springboot.entities.Product;

import java.util.List;

public interface IClientService {

    Client getClient(Long id);

    List<Order> getClientOrders(Long id);

    List<Product> getClientProducts(Long id);

}
