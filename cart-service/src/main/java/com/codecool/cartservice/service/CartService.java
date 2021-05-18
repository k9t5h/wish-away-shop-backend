package com.codecool.cartservice.service;

import com.codecool.cartservice.model.Cart;
import com.codecool.cartservice.model.ProductDTO;
import com.codecool.cartservice.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private CartRepository cartRepository;
    private ProductServiceCaller productServiceCaller;

    @Autowired
    public CartService(CartRepository cartRepository, ProductServiceCaller productServiceCaller) {
        this.cartRepository = cartRepository;
        this.productServiceCaller = productServiceCaller;
    }

    public Cart getCart() {
        return cartRepository.findAll().get(0);
    }

    public Cart addProductToCart(long productId) {
        Cart cart = getCart();
        List<Long> productIds = cart.getProductIds();
        if(!productIds.contains(productId)){
            productIds.add(productId);
            cartRepository.save(cart);
        }
        return cart;
    }

    public void removeProductFromCart(long productId) {
        Cart cart = getCart();
        List<Long> productIds = cart.getProductIds();
        productIds.remove(productId);
        cartRepository.save(cart);
    }

    public void emptyCart() {
        Cart cart = getCart();
        cart.setProductIds(new ArrayList<>());
        cartRepository.save(cart);
    }

    public Cart getCartWithProducts() {
        Cart cart = getCart();
        List<ProductDTO> products = productServiceCaller.getProducts(cart.getProductIds());
        cart.setProducts(products);
        return cart;
    }
}
