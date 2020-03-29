package com.dgf.shopproduct.rest;

import com.dgf.shopproduct.rest.handler.ProductHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ProductRouter {

    @Bean
    public RouterFunction<ServerResponse> find(ProductHandler handler) {
        return route(GET("/product/find/{name}"), handler::find);
    }
    @Bean
    public RouterFunction<ServerResponse> list(ProductHandler handler) {
        return route(GET("/product/list"), handler::list);
    }
}
