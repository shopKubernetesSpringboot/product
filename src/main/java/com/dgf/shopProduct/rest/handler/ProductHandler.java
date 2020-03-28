package com.dgf.shopProduct.rest.handler;

import static com.dgf.shopProduct.Constants.PRODUCTS;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import com.dgf.shopProduct.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ProductHandler {

    @Autowired
    private ProductService service;

    public Mono<ServerResponse> create(ServerRequest request) {
        return service.create(PRODUCTS.get()).collectList().flatMap(resp->
            ok().contentType(APPLICATION_JSON).body(fromValue(resp))
        );
    }

    public Mono<ServerResponse> find(ServerRequest request) {
        return service.findByNameIgnoreCaseContaining(request.pathVariable("name")).collectList().flatMap(resp->
            ok().contentType(APPLICATION_JSON).body(fromValue(resp))
        );
    }

    public Mono<ServerResponse> list(ServerRequest request) {
        return service.findAll().collectList().flatMap(resp->
            ok().contentType(APPLICATION_JSON).body(fromValue(resp))
        );
    }
}