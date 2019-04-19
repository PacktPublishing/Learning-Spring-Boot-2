package com.packt.learning.spring.boot.d02s04.service;

import com.packt.learning.spring.boot.d02s04.repository.ProductRepository;
import com.packt.learning.spring.boot.jpa.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static com.packt.learning.spring.boot.d02s04.config.CachingConfig.PRODUCTS;

@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(
            readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = {
                    IllegalArgumentException.class,
                    IllegalAccessException.class
            }
    )
    public void create(final Product product) {
        productRepository.save(product);
    }

    @Cacheable(cacheNames = PRODUCTS)
    @Transactional(
            readOnly = true,
            propagation = Propagation.SUPPORTS
    )
    public Product get(final int id) {
        return productRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("Not found"));
    }

    public Iterable<Product> getAll() {
        return productRepository.findAll();
    }

    public Stream<Product> findAllByName() {
        return productRepository.findAllByName("The product");
    }

    public Page<Product> getPage(final int pageNumber, final int resultsNumber, final String sortMode) {
        return productRepository.findAll(PageRequest.of(pageNumber, resultsNumber,
                Sort.Direction.valueOf(sortMode.toUpperCase()), "name"));
    }

    public void update(final int id, final Product product) {
        final Product existingProduct = get(id);

        existingProduct.setName(product.getName());

        productRepository.save(product);
    }

    public void delete(final int id) {
        productRepository.deleteById(id);
    }

    @Scheduled(fixedDelay = 10000)
    public void scheduledTask() {
        LOGGER.info("Running at {}", LocalDateTime.now());
    }
}
