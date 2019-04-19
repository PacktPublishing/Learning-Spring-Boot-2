package com.packt.learning.spring.boot.d01s04.repository;

import com.packt.learning.spring.boot.jpa.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
