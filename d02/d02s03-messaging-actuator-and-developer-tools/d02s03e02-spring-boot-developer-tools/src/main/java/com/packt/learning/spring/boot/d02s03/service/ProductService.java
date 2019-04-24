package com.packt.learning.spring.boot.d02s03.service;

import com.packt.learning.spring.boot.d02s03.dto.ProductDTO;
import com.packt.learning.spring.boot.d02s03.repository.ProductRepository;
import com.packt.learning.spring.boot.jpa.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional(
            readOnly = false,
            propagation = Propagation.REQUIRED
    )
    public void initializeDefaultProducts() {
        productRepository.save(new Product("Tablet", 200d));
        productRepository.save(new Product("Phone", 300d));
    }

    @Transactional(
            readOnly = false,
            propagation = Propagation.REQUIRED
    )
    public String create(final ProductDTO productDTO) {
        productRepository.save(new Product(productDTO.getProductName()));
        return "OK";
    }

    @Transactional(
            readOnly = true,
            propagation = Propagation.SUPPORTS
    )
    public ProductDTO get(final int id) {
        final Product product = getByIdOrThrow(id);
        return getProductConverter().apply(product);
    }

    private Product getByIdOrThrow(int id) {
        return productRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("There is no product with the id " + id));
    }

    @Transactional(
            readOnly = true,
            propagation = Propagation.SUPPORTS
    )
    public List<ProductDTO> getAll() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                            .map(getProductConverter())
                            .sorted(Comparator.comparing(ProductDTO::getProductName))
                            .collect(Collectors.toList());
    }

    public void update(final int id, final ProductDTO productDTO) {
        final Product product = getByIdOrThrow(id);
        productRepository.save(convertProductForUpdate().apply(productDTO, product));
    }

    private Function<Product, ProductDTO> getProductConverter() {
        return product -> new ProductDTO(product.getId(), product.getName(), product.getPrice());
    }

    private BiFunction<ProductDTO, Product, Product> convertProductForUpdate() {
        return (dto, existingProduct) -> {
            existingProduct.setName(dto.getProductName());
            return existingProduct;
        };
    }

    public void delete(final int id) {
        productRepository.deleteById(id);
    }

    /* TODO uncomment this method to see the usage of the Spring Boot Developer Tools
    public void processProduct(final int productId) {
        final Product product = getByIdOrThrow(productId);
        LOGGER.info("The processed product is {}", product);

        // performing long running processing...
    }
    */
}
