package com.commerce.shop.dao;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;

    private Double total;
    public Cart(){}
    public Cart(Long id,List<CartItem> cartItems){
        this.cartItems = cartItems;
        this.id = id;
    }
    public List<CartItem> getCartItems() {
        return cartItems;
    }
    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
    public Cart(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public Double getTotal(){
        if (this.cartItems == null || this.cartItems.isEmpty()) {
            return 0.0;
        }
        return this.cartItems.stream()
                .mapToDouble(CartItem::getTotal)
                .sum();
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public void setId(Long id) {
        this.id = id;
    }
}