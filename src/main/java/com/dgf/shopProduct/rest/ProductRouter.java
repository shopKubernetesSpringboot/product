package com.dgf.shopProduct.rest;

import static org.springframework.http.MediaType.TEXT_PLAIN;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import com.dgf.shopProduct.rest.handler.ProductHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ProductRouter {

    @Bean
    public RouterFunction<ServerResponse> createAll(ProductHandler handler) {
        return route(PUT("/product/createAll"), handler::create);
    }
    @Bean
    public RouterFunction<ServerResponse> find(ProductHandler handler) {
        return route(GET("/product/find/{name}"), handler::find);
    }
    @Bean
    public RouterFunction<ServerResponse> list(ProductHandler handler) {
        return route(GET("/product/list"), handler::list);
    }
}
