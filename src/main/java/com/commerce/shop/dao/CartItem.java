package com.commerce.shop.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "cartItem")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonBackReference // Prevents infinite recursion in JSON serialization
    private Cart cart;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private Double total;
    public CartItem(){
    }
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Product getProduct() {
        return product;
    }
    public Double getTotal(){
        return this.product.getPrice() * this.getQuantity();
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public void setCart(Cart cart) {
        this.cart = cart;
    }
    public Cart getCart() {
        return this.cart;
    }
}
