package com.product.product.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.product.controllers.ProductRestController;
import com.product.product.models.documents.Product;
import com.product.product.models.service.ProductService;
import com.product.product.tests.models.ResponseProduct;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import rx.Single;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(ProductRestController.class)
public class ProductTestController
{

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;


    @Test
    public void create() throws Exception {

        var product = Product.builder()
                .id("1")
                .name("papa")
                .price(1f)
                .quantity(10)
                .build();


        when(productService.create(any(Product.class)))
                .thenReturn(Single.just(product));


        var result = mockMvc.perform(
                            post("/api/")
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .content(objectMapper.writeValueAsString(new Product()))
                                    .accept("application/json"))
                            .andDo(print())
                            .andExpect(status().isOk())
                            .andReturn();

        /*Optional<ResponseProduct> optionalResponseProduct = Optional.of((ResponseProduct) result.getAsyncResult());

        if(optionalResponseProduct.isPresent())
        {
            var responseProduct = optionalResponseProduct.get();
            Assertions.assertThat(responseProduct.getMessage()).isEqualTo("Done");
        }*/



        /*when(productService.create(any(Product.class)))
            .thenReturn(Single.just(product));

        MvcResult mvcResult = mockMvc.perform(post("/api/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new ResponseProduct())))
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message",nullValue()));

        verify(productService, times(1)).create(any(Product.class));*/






    }

    /*@Test
    public void update()
    {
        webTestClient.put()
                .uri("/api/{id}","1")
                .contentType(MediaType.APPLICATION_JSON)
                .body(product,Product.class)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(new ParameterizedTypeReference<ResponseProduct<Product>>(){})
                .value(responseProduct -> {
                    var product1 = responseProduct.getData();
                    Assertions.assertThat(product1.getId()).isEqualTo("1");
                });
    }*/


}
