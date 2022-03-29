package ru.alov.springboot.controllers;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alov.springboot.beans.Cart;
import ru.alov.springboot.converters.CartConverter;
import ru.alov.springboot.dto.CartDto;
import ru.alov.springboot.services.impl.ProductService;

@AllArgsConstructor
@RestController
@RequestMapping("/cart")
public class CartController {

    private final ProductService productService;
    private final Cart cart;
    private final CartConverter cartConverter;

    @GetMapping("/add/{id}")
    public CartDto addProductToCartById(@PathVariable Long id){
        cart.add(productService.getProduct(id));
        return cartConverter.entityToDto(cart);
    }

    @GetMapping("/decrement/{id}")
    public CartDto decrementProductToCartById(@PathVariable Long id) {
        cart.decrement(id);
        return cartConverter.entityToDto(cart);
    }

    @GetMapping("/remove/{id}")
    public CartDto removeProductFromCartById(@PathVariable Long id) {
        cart.removeByProductId(id);
        return cartConverter.entityToDto(cart);
    }

    @GetMapping
    public CartDto showCart() {
        return cartConverter.entityToDto(cart);
    }
}