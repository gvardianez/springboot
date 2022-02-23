package ru.alov.springboot.services;


import ru.alov.springboot.entities.Order;

public interface IOrderService {

    Order getOrder(Long id);

}
