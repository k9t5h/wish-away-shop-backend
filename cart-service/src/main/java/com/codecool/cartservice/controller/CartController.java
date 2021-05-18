package com.codecool.cartservice.controller;

import com.codecool.cartservice.model.Cart;
import com.codecool.cartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/cart")
public class CartController {

    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping(path = {"", "/"})
    public Cart getCart() {
        return cartService.getCartWithProducts();
    }

    @PutMapping(path = "/add/{id}")
    public Cart addProductToCart(@PathVariable(name = "id") long productId) {
        return cartService.addProductToCart(productId);
    }

    @DeleteMapping(path = "/remove/{id}")
    public void removeProductFromCart(@PathVariable(name = "id") long productId) {
        cartService.removeProductFromCart(productId);
    }

    @PutMapping(path = "/empty")
    public void emptyCart() {
        cartService.emptyCart();
    }

}
