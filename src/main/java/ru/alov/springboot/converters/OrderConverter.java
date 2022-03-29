package ru.alov.springboot.converters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.alov.springboot.beans.Cart;
import ru.alov.springboot.dto.CartDto;
import ru.alov.springboot.dto.OrderDto;
import ru.alov.springboot.dto.OrderItemDto;
import ru.alov.springboot.entities.Order;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class OrderConverter {

    private final OrderItemConverter orderItemConverter;

    public OrderDto entityToDto(Order order, String name) {
        List<OrderItemDto> orderItemDtoList = order.getOrderItems().stream().map(orderItemConverter::entityToDto).collect(Collectors.toList());
        return new OrderDto(name, orderItemDtoList, order.getCost());
    }

}
