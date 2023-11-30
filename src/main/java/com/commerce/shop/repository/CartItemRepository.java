package com.commerce.shop.repository;

import com.commerce.shop.dao.CartItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
@Transactional(transactionManager = "chainedTransactionManager")
public interface CartItemRepository extends CrudRepository<CartItem, Long> {
}
