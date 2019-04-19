package com.packt.learning.spring.boot.d02s02;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
class ProductRESTControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void given_thereAreTabletsInTheDatabase_andANewTabletIsCreated_whenRetrievingTablets_thenTheirNumberIsCorrect()
            throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/product")
                                      .contentType(MediaType.APPLICATION_JSON)
                                      .content(createProduct("Another tablet"));

        mockMvc.perform(builder)
               .andExpect(MockMvcResultMatchers.status()
                                               .isOk());

        // create one more product
        builder = MockMvcRequestBuilders.post("/product")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(createProduct("Another phone"));

        mockMvc.perform(builder)
               .andExpect(MockMvcResultMatchers.status()
                                               .isOk());

        // get all products
        builder = MockMvcRequestBuilders.get("/product")
                                        .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(builder)
               .andExpect(MockMvcResultMatchers.status()
                                               .isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
               .andDo(MockMvcResultHandlers.print());

    }

    private String createProduct(final String productName) {
        return "{ \"productName\": \"" + productName + "\"}";
    }
}
