package ru.alov.springboot.converters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.alov.springboot.entities.OrderItem;
import ru.alov.springboot.dto.OrderItemDto;

@Component
@AllArgsConstructor
public class OrderItemConverter {

    private final ProductConverter productConverter;

    public OrderItemDto entityToDto(OrderItem orderItem) {
        return new OrderItemDto(productConverter.entityToDto(orderItem.getProduct()),orderItem.getQuantity(),orderItem.getCost());
    }

}
