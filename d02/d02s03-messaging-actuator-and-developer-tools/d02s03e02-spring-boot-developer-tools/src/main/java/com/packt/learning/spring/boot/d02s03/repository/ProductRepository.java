package com.packt.learning.spring.boot.d02s03.repository;

import com.packt.learning.spring.boot.jpa.model.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * A simple Spring Data {@link CrudRepository} for the {@link Product} entity
 *
 * @author bogdan.solga
 */
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
