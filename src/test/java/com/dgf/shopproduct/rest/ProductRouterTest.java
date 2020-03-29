package com.dgf.shopproduct.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

public class ProductRouterTest {

    private WebTestClient testClient;

    @BeforeEach
    public void setUp() {
        RouterFunction<?> route = route(POST("/product/create").and(accept(APPLICATION_JSON)).and(contentType(APPLICATION_JSON)),
                request -> ServerResponse.ok().bodyValue("It works!"));
        this.testClient = WebTestClient.bindToRouterFunction(route).build();
    }

    @Test
    public void add() {
        this.testClient.post().uri("/product/create")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("It works!");
    }

}
