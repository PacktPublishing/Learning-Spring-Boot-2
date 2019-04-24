package com.packt.learning.spring.boot.d01s03.repository;

import com.packt.learning.spring.boot.domain.model.Product;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    // an in-memory list of products
    private List<Product> products = new ArrayList<>(1);

    @PostConstruct
    public void init() {
        products.add(getDefaultProduct());
    }

    @SuppressWarnings("unused")
    public Product get(int id) {
        return id < products.size() ? products.get(id) : null;
    }

    public List<Product> getAll() {
        return products;
    }

    public void create(final Product product) {
        products.add(product);
    }

    public void update(final int id, final Product product) {
        products.removeIf(it -> it.getId() == id);
        products.add(product);
    }

    public void delete(final int id) {
        products.remove(id < products.size() ? id : 0);
    }

    private Product getDefaultProduct() {
        return new Product(20, "Dell XPS 9360", 200d);
    }
}
