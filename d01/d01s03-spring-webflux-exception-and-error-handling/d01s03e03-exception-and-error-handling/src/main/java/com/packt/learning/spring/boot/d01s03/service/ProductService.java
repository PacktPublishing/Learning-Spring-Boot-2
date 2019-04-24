package com.packt.learning.spring.boot.d01s03.service;

import com.packt.learning.spring.boot.d01s03.exceptions.NotFoundException;
import com.packt.learning.spring.boot.d01s03.repository.ProductRepository;
import com.packt.learning.spring.boot.domain.model.Product;
import com.packt.learning.spring.boot.d01s03.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void create(final ProductDTO productDTO) {
        validateRequest(productDTO);

        productRepository.create(getDTOConverter().apply(productDTO));
    }

    public ProductDTO get(final int id) {
        final Product product = getOrThrow(id);
        return getProductConverter().apply(product);
    }

    public List<ProductDTO> getAll() {
        return productRepository.getAll()
                                .stream()
                                .map(getProductConverter())
                                .collect(Collectors.toList());
    }

    public void update(final int id, final ProductDTO productDTO) {
        validateRequest(productDTO);
        getOrThrow(id);

        productRepository.update(id, getDTOConverter().apply(productDTO));
    }

    public void delete(final int id) {
        getOrThrow(id);
        productRepository.delete(id);
    }

    private Function<ProductDTO, Product> getDTOConverter() {
        return dto -> new Product(dto.getId(), dto.getProductName(), 200d);
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
        return Optional.ofNullable(productRepository.get(id))
                       .orElseThrow(() -> new NotFoundException("There is no product with the ID " + id));
    }
}
