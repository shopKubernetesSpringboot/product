package com.dgf.shopproduct.rest.handler;

import com.dgf.shopproduct.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@RestController
@RequiredArgsConstructor
public class ProductHandler {

    private final ProductService service;

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