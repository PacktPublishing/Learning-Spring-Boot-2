package com.packt.learning.spring.boot.d02s03.controller;

import com.packt.learning.spring.boot.d02s03.dto.ProductDTO;
import com.packt.learning.spring.boot.d02s03.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductDTO productDTO) {
        final String result = productService.create(productDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable final int id) {
        return productService.get(id);
    }

    @GetMapping
    public Iterable<ProductDTO> getAll() {
        return productService.getAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable final int id, @RequestBody ProductDTO productDTO) {
        productService.update(id, productDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable final int id) {
        productService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /* TODO uncomment this method to see the usage of the Spring Boot Developer Tools
    @GetMapping("/process/{id}")
    public ResponseEntity processProduct(@PathVariable final int id) {
        productService.processProduct(id);
        return ResponseEntity.ok("The product with the ID " + id + " was successfully processed");
    }
    */
}
