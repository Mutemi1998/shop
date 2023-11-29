package com.commerce.shop.repository;

import com.commerce.shop.dao.Category;
import com.commerce.shop.dao.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(transactionManager = "chainedTransactionManager")
public interface CategoryRepository  extends CrudRepository<Category, Long>  {
    @Override
    @Transactional
    Category save(Category category);
    Category findById(long id);
}
