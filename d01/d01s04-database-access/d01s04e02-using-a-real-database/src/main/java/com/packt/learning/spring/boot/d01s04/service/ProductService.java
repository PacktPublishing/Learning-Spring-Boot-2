package com.packt.learning.spring.boot.d01s04.service;

import com.packt.learning.spring.boot.d01s04.dto.ProductDTO;
import com.packt.learning.spring.boot.d01s04.repository.ProductRepository;
import com.packt.learning.spring.boot.jpa.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void create(final ProductDTO productDTO) {
        validateRequest(productDTO);

        productRepository.save(getDTOConverter().apply(productDTO));
    }

    public ProductDTO get(final int id) {
        final Product product = getOrThrow(id);
        return getProductConverter().apply(product);
    }

    public List<ProductDTO> getAll() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                            .map(getProductConverter())
                            .sorted(Comparator.comparing(ProductDTO::getProductName))
                            .collect(Collectors.toList());
    }

    public void update(final int id, final ProductDTO productDTO) {
        validateRequest(productDTO);
        getOrThrow(id);

        productRepository.save(getDTOConverter().apply(productDTO));
    }

    public void delete(final int id) {
        getOrThrow(id);
        productRepository.deleteById(id);
    }

    private Function<ProductDTO, Product> getDTOConverter() {
        return dto -> new Product(dto.getProductName(), 200d);
    }

    private Function<Product, ProductDTO> getProductConverter() {
        return product -> new ProductDTO(product.getId(), product.getName(), product.getPrice());
    }

    private void validateRequest(final ProductDTO productDTO) {
        // throws an IllegalArgumentException
        Assert.notNull(productDTO, "Cannot process a null product");
    }

    private Product getOrThrow(final int id) {
        // a short circuiting operation
        return productRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("There is no product with the ID " + id));
    }
}
