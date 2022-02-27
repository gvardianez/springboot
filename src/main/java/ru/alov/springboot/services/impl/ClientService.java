package ru.alov.springboot.services.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import ru.alov.springboot.entities.Client;
import ru.alov.springboot.entities.Order;
import ru.alov.springboot.entities.Product;
import ru.alov.springboot.repositories.ClientRepository;
import ru.alov.springboot.services.IClientService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService implements IClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client getClient(Long id) {
            return clientRepository.getById(id);
    }

    @Override
    public List<Order> getClientOrders(Long id) {
            return getClient(id).getOrderList();
    }

    @Override
    public List<Product> getClientProducts(Long id) {
        return getClientOrders(id)
                .stream()
                .flatMap(order -> order.getProductList().stream())
                .distinct()
                .collect(Collectors.toList());

    }

}
