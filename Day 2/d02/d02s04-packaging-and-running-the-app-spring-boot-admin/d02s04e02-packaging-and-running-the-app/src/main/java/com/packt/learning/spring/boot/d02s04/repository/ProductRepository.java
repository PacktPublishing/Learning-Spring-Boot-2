package com.packt.learning.spring.boot.d02s04.repository;

import com.packt.learning.spring.boot.jpa.model.Product;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Repository
public class ProductRepository {

    private static final Random RANDOM = new Random(100);

    // an in-memory list of products
    private List<Product> products = new ArrayList<>(10);

    @PostConstruct
    public void init() {
        IntStream.range(0, 10).forEach(entry -> {
            products.add(buildRandomProduct());
        });
    }

    public Product get(int id) {
        return id < products.size() ? products.get(id) : buildRandomProduct();
    }

    public List<Product> getAll() {
        return products;
    }

    public void create(final Product product) {
        products.add(product);
    }

    public void update(final int id, final Product product) {
        final Product currentProduct = products.get(id < products.size() ? id : 0);
        currentProduct.setName(product.getName());
    }

    public void delete(final int id) {
        products.remove(id < products.size() ? id : 0);
    }

    private Product buildRandomProduct() {
        return new Product("The product with the ID " + RANDOM.nextInt(100));
    }
}
