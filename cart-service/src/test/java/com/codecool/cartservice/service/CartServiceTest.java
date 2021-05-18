package com.codecool.cartservice.service;

import com.codecool.cartservice.model.Cart;
import com.codecool.cartservice.model.ProductDTO;
import com.codecool.cartservice.repository.CartRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
class CartServiceTest {

    private static CartService cartService;
    private static CartRepository mockCartRepository;
    private static ProductServiceCaller mockProductServiceCaller;

    @BeforeAll
    public static void init(){

        mockCartRepository = mock(CartRepository.class);
        mockProductServiceCaller = mock(ProductServiceCaller.class);
        cartService = new CartService(mockCartRepository, mockProductServiceCaller);
    }

    @Test
    public void getCartWithProducts_cartWithOneProductId_returnsCartWithOneProduct(){
        Cart cart = Cart.builder().productIds(Collections.singletonList(1L)).build();
        ProductDTO testProduct = ProductDTO.builder().id(1L).description("test product").build();
        when(mockCartRepository.findAll()).thenReturn(List.of(cart));
        when(mockProductServiceCaller.getProducts(cart.getProductIds())).thenReturn(List.of(testProduct));
        Cart cartResult = cartService.getCartWithProducts();
        assertEquals(cart, cartResult);
        assertEquals(testProduct, cartResult.getProducts().get(0));
    }

    @Test
    public void getCartWithProducts_cartWithNoProductId_returnsCartWithNoProducts(){
        Cart cart = Cart.builder().productIds(new ArrayList<>()).build();
        when(mockCartRepository.findAll()).thenReturn(List.of(cart));
        when(mockProductServiceCaller.getProducts(cart.getProductIds())).thenReturn(List.of());
        Cart cartResult = cartService.getCartWithProducts();
        assertEquals(cart, cartResult);
        assertEquals(0, cartResult.getProducts().size());
    }

    @Test
    public void getCartWithProducts_cartWithMultiple_returnsCartWithProducts(){
        ProductDTO testProduct1 = ProductDTO.builder().id(1L).description("test product 1").build();
        ProductDTO testProduct2 = ProductDTO.builder().id(2L).description("test product 2").build();
        ProductDTO testProduct3 = ProductDTO.builder().id(3L).description("test product 3").build();
        Cart cart = Cart.builder().productIds(List.of(testProduct1.getId(), testProduct2.getId(), testProduct3.getId())).build();
        when(mockCartRepository.findAll()).thenReturn(List.of(cart));
        when(mockProductServiceCaller.getProducts(cart.getProductIds())).thenReturn(List.of(testProduct1, testProduct2, testProduct3));
        Cart cartResult = cartService.getCartWithProducts();
        assertEquals(cart, cartResult);
        assertEquals(3, cartResult.getProducts().size());
    }

    @Test
    public void addProductToCart_addOneToEmptyCart_returnsCartWithProductId(){
        Cart cart = Cart.builder().productIds(new ArrayList<>()).build();
        Cart cartResult = cartService.addProductToCart(1L);
        assertEquals(1L, cartResult.getProductIds().get(0));
    }

    @Test
    public void addProductToCart_addIdTwice_returnCartWithOneElement(){
        Cart cart = Cart.builder().productIds(new ArrayList<>()).build();
        when(mockCartRepository.findAll()).thenReturn(List.of(cart));
        List<Long> ids = List.of(1L, 2L);
        cartService.addProductToCart(ids.get(0));
        cartService.addProductToCart(ids.get(1));
        Cart cartResult = cartService.addProductToCart(ids.get(0));
        assertEquals(ids, cartResult.getProductIds());


    }

}