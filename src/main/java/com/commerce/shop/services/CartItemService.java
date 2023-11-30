package com.commerce.shop.services;

import com.commerce.shop.dao.Cart;
import com.commerce.shop.dao.CartItem;
import com.commerce.shop.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    @Autowired
    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }
    public CartItem getCartItemById(Long id) {
        return cartItemRepository.findById(id).orElse(null);
    }
    public void saveCartItem(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }
    public void deleteCart(Long id) {
        cartItemRepository.deleteById(id);
    }
    public List<CartItem> getAllCartItem() {
        return (List<CartItem>) cartItemRepository.findAll();
    }
}
