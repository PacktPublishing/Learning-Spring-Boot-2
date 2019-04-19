package com.packt.learning.spring.boot.d02s02.repository;


import com.packt.learning.spring.boot.domain.bootstrap.ProductsSetup;
import com.packt.learning.spring.boot.domain.model.Product;

import java.util.List;

/**
 * A simple product repository
 *
 * @author bogdan.solga
 */
public class ProductRepository {

    private final String databaseType;

    private List<Product> products = ProductsSetup.getRandomProducts();

    public ProductRepository(final String databaseType) {
        this.databaseType = databaseType;
    }

    public void displayProducts() {
        System.out.println("Displaying all the products from the '" + databaseType + "' database...");
        products.forEach(product -> System.out.println("\t" + product));
    }
}
