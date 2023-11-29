package com.commerce.shop.repository;

import com.commerce.shop.dao.Category;
import com.commerce.shop.dao.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Repository
@Transactional(transactionManager = "chainedTransactionManager")
public interface ProductRepository extends CrudRepository<Product, Long> {
    @Override
    @Transactional
    Product save(Product product);
    Product findById(long id);
    List<Product> findByCategory(Category category);
    List<Product> findByAvailable(boolean available);
}
