package com.commerce.shop.services;

import com.commerce.shop.dao.Cart;
import com.commerce.shop.dao.CartItem;
import com.commerce.shop.dao.Product;
import com.commerce.shop.exception.CartNotFoundException;
import com.commerce.shop.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemService cartItemService;
    private final ProductService productService;
    @Autowired
    public CartService(CartRepository cartRepository, CartItemService cartItemService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.cartItemService = cartItemService;
        this.productService = productService;
    }
    public Cart getCartById(Long id) {
        return cartRepository.findById(id).orElse(null);
    }
    public Cart addToCart(Cart cart){
        Cart existingCart = this.getCartById(cart.getId());
        if (existingCart == null) {
            throw new CartNotFoundException("Cart with ID " + cart.getId() + " not found");
        }
        List<CartItem> items = new ArrayList<>();
        for (CartItem item: cart.getCartItems()){
            CartItem existingItem = findCartItemByProduct(existingCart, item.getProduct().getId());
            if (existingItem != null) {
                existingItem.setQuantity(item.getQuantity());
                items.add(existingItem);
                cartItemService.saveCartItem(existingItem);
            }    else    {
                CartItem cartItem = new CartItem();
                cartItem.setCart(existingCart);
                Product product = productService.getProductById(item.getProduct().getId());
                cartItem.setProduct(product);
                cartItem.setQuantity(item.getQuantity());
                items.add(cartItem);
                cartItemService.saveCartItem(cartItem);
            }
        }
        existingCart.setCartItems(items);
        return this.saveCart(existingCart);
    }
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }
    public void deleteCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }
    public List<Cart> getAllCarts() {
        return (List<Cart>) cartRepository.findAll();
    }
    public void addCartItemsToCart(Long cartId, List<CartItem> cartItems) {
        Cart existingCart = cartRepository.findById(cartId).orElse(null);
        if (existingCart != null) {
            for (CartItem cartItemRequest : cartItems) {
                CartItem cartItem = new CartItem();
                cartItem.setProduct(cartItemRequest.getProduct());
                cartItem.setQuantity(cartItemRequest.getQuantity());
                existingCart.setCartItems((List<CartItem>) cartItem);
            }
            cartRepository.save(existingCart);
        }
    }
    private CartItem findCartItemByProduct(Cart cart, Long productId) {
        return cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);
    }
}
