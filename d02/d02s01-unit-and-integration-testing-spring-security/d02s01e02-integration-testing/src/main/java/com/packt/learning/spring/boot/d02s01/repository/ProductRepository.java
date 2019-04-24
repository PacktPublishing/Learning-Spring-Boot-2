package com.packt.learning.spring.boot.d02s01.repository;

import com.packt.learning.spring.boot.jpa.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
