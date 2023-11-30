package com.commerce.shop.controllers;

import com.commerce.shop.dao.Cart;
import com.commerce.shop.dao.CartItem;
import com.commerce.shop.dao.Product;
import com.commerce.shop.exception.CartNotFoundException;
import com.commerce.shop.services.CartItemService;
import com.commerce.shop.services.CartService;
import com.commerce.shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("api/carts")
public class CartController {
    private final CartService cartService;
    private final ProductService productService;
    private final CartItemService cartItemService;
    @Autowired
    public CartController(CartService cartService, CartItemService cartItemService, ProductService productService, ProductService productService1, CartItemService cartItemService1) {
        this.cartService = cartService;
        this.productService = productService1;
        this.cartItemService = cartItemService1;
    }
    @PostMapping()
    @RequestMapping("/create")
    public ResponseEntity<Cart> createCart() {
        Cart cart = cartService.saveCart(new Cart());
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }
    @PostMapping()
    @RequestMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestBody Cart cart) {
        return new ResponseEntity<>(cartService.addToCart(cart), HttpStatus.CREATED);
    }
    @PostMapping()
    @RequestMapping("/remove")
    public ResponseEntity<Cart> removeFromCart(@RequestBody Cart cart) {
        return new ResponseEntity<>(cartService.addToCart(cart), HttpStatus.CREATED);
    }
    @GetMapping()
    @RequestMapping("/{cartId}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long cartId) {
        Cart cart = cartService.getCartById(cartId);
        if (cart != null) {
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping()
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cartService.getAllCarts();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }
    @DeleteMapping()
    @RequestMapping("/delete/{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long cartId) {
        cartService.deleteCart(cartId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
