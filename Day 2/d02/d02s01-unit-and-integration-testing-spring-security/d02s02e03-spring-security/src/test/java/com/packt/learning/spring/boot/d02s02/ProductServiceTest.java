package com.packt.learning.spring.boot.d02s02;

import com.packt.learning.spring.boot.d02s02.dto.ProductDTO;
import com.packt.learning.spring.boot.d02s02.repository.ProductRepository;
import com.packt.learning.spring.boot.d02s02.service.ProductService;
import net.safedata.spring.training.jpa.model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository; // the collaborator (productRepository) is mocked

    @Spy
    @SuppressWarnings("unused")
    private ExecutorService executorService;

    // the tested service; also called 'system under test' // SUT
    @InjectMocks
    private ProductService productService;

    @Test
    @DisplayName("Given there are available products, when retrieving the products then products are retrieved correctly")
    void givenThereAreAvailableProducts_whenRetrievingProducts_thenProductsAreRetrievedCorrectly() {
        // arrange, including mocking behavior setup    --> given
        final List<Product> products = Arrays.asList(
                new Product( "Asus"),
                new Product( "Dell")
        );
        when(productRepository.findAll()).thenReturn(products); // simple mocking example

        // act --> calling the tested service method    --> when
        final List<ProductDTO> allProducts = productService.getAll();

        // assert --> verifying the response of the tested method is correct (by the requirements)  --> then
        assertNotNull(allProducts);
        assertThat(allProducts.size(), is(products.size()));
        assertThat(allProducts.iterator().hasNext(), is(true));
    }

    @Test
    @DisplayName("Given there are no available products, when retrieving the products then no products are retrieved")
    void givenThereAreNoAvailableProducts_whenRetrievingProducts_thenNoProductsAreReturned() {
        when(productRepository.findAll()).thenReturn(new ArrayList<>());

        final List<ProductDTO> allProducts = productService.getAll();

        assertNotNull(allProducts);
        assertThat(allProducts.size(), is(0));
    }

    @Test
    @DisplayName("Given there are available products, when retrieving a product by ID then the product is retrieved")
    void givenThereAreAvailableProducts_whenRetrievingAProductById_thenTheProductIsCorrectlyRetrieved() {
        final int productId = 20;

        final Product product = mock(Product.class);
        final String mockedName = "mocked name";
        when(product.getName()).thenReturn(mockedName);
        when(product.getId()).thenReturn(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        final ProductDTO resulted = productService.get(productId);

        assertNotNull(resulted);
        assertThat(resulted.getProductName(), is(mockedName));
        assertThat(resulted.getId(), not(0));
        assertThat(resulted.getId(), is(productId));
    }

    @Test
    @DisplayName("Given there are no available products, when retrieving a product by ID then an IAE is thrown")
    void givenThereAreNoAvailableProducts_whenRetrievingAProductById_thenAnIllegalArgumentExceptionIsThrown() {
        assertThrows(IllegalArgumentException.class, () -> productService.get(13));
    }

    @Test
    @DisplayName("Given a product is saved, when saving the product then save is called one time and the response is not " +
            "null or empty")
    void givenAProductIsSaved_whenSavingTheProduct_thenSaveIsCalledOneTimesAndTheResponseShouldNotBeEmptyOrNull () {
        final ProductDTO product = mock(ProductDTO.class);

        final String response = productService.save(product);

        // it verifies that the .save method (from the productRepository collaborator) was called exactly 1 times
        verify(productRepository, times(1)).save(any(Product.class));

        assertNotNull(response);
        assertThat("The displayed error message", response.length(), not(0));
    }
}
