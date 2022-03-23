package ru.alov.springboot.converters;

import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.alov.springboot.beans.Cart;
import ru.alov.springboot.dto.CartDto;
import ru.alov.springboot.dto.OrderItemDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CartConverter {

    private final OrderItemConverter orderItemConverter;

    public CartDto entityToDto(Cart cart) {
        List<OrderItemDto> orderItemDtoList = cart.getItems().stream().map(orderItemConverter::entityToDto).collect(Collectors.toList());
        return new CartDto(orderItemDtoList, cart.getPrice());
    }

}
