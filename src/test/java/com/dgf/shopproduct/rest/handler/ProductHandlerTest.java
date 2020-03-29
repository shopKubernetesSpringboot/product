package com.dgf.shopproduct.rest.handler;

import com.dgf.shopproduct.Constants;
import com.dgf.shopproduct.model.Product;
import com.dgf.shopproduct.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureDataMongo
//@DataMongoTest
public class ProductHandlerTest extends BaseHandlerTest {

    @MockBean
    private ProductService service;
    @Autowired
    private ProductHandler handler;

    private final List<Product> products = Constants.PRODUCTS.get();
    private final String productsBody = super.mapper.writeValueAsString(products);

    public ProductHandlerTest() throws JsonProcessingException {
    }

    @Test
    public void list() {
        MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/product/list"));
        Mockito.when(service.findAll()).thenReturn(Flux.fromIterable(new ArrayList<>()));
        StepVerifier.create(handler.list(getDefaultServerReq(exchange)).flatMap(response -> {
            assertThat(response.statusCode()).isEqualTo(HttpStatus.OK);
            return response.writeTo(exchange, context);
        })).expectComplete().verify();
        assertExchange(exchange,"[]");
    }

}