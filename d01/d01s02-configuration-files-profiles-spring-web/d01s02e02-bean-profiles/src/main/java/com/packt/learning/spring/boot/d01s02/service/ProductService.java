package com.packt.learning.spring.boot.d01s02.service;

import com.packt.learning.spring.boot.d01s02.RunProfiles;
import com.packt.learning.spring.boot.d01s02.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Optional;

/**
 * A simple {@link ProductService}, which uses the {@link ProductRepository} as a manually wired collaborator
 *
 * @author bogdan.solga
 */
public class ProductService {

    @Autowired
    private Environment environment;

    private final ProductRepository productRepository;

    @Autowired(required = false)
    private Optional<FileSavingService> fileSavingService;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void displayProfiles() {
        System.out.println("Running with the '" + Arrays.asList(environment.getActiveProfiles()) + "' profiles...");
        System.out.println("Default profiles: '" + Arrays.asList(environment.getDefaultProfiles()) + "'");
    }

    public void displayProducts() {
        if (environment.acceptsProfiles(Profiles.of(RunProfiles.PROD))) {
            System.out.println("Do something only when prod is enabled...");
        }

        productRepository.displayProducts();

        fileSavingService.ifPresent(service -> service.saveFile("The important file"));
    }
}
