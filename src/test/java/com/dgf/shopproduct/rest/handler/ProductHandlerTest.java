package com.dgf.shopproduct.rest.handler;

import com.dgf.shopproduct.TestConfig;
import com.dgf.shopproduct.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.web.reactive.function.server.RouterFunctions;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

/**
 * https://github.com/spring-projects/spring-framework/blob/master/spring-webflux/src/test/java/org/springframework/web/reactive/function/server/DefaultServerRequestTests.java
 */
@AutoConfigureDataMongo
@Import(TestConfig.class)
public class ProductHandlerTest extends BaseHandlerTest {

    @MockBean
    private ProductService service;
    @Autowired
    private ProductHandler handler;

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

    @Test
    public void find() {
        MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/product/find"));
        Map<String, String> pathVariables = Collections.singletonMap("name", "aa");
        exchange.getAttributes().put(RouterFunctions.URI_TEMPLATE_VARIABLES_ATTRIBUTE, pathVariables);
        Mockito.when(service.findByNameIgnoreCaseContaining(any())).thenReturn(Flux.fromIterable(new ArrayList<>()));
        StepVerifier.create(handler.find(getDefaultServerReq(exchange)).flatMap(response -> {
            assertThat(response.statusCode()).isEqualTo(HttpStatus.OK);
            return response.writeTo(exchange, context);
        })).expectComplete().verify();
        assertExchange(exchange,"[]");
    }

}