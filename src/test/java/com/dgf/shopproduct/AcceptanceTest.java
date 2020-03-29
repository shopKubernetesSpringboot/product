package com.dgf.shopproduct;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Collections;
import java.util.function.Consumer;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AcceptanceTest {

    @Autowired
    ApplicationContext context;

    private ObjectMapper mapper = new ObjectMapper();
    private WebTestClient rest;

    @BeforeAll
    public void setup() {
        rest = WebTestClient.bindToApplicationContext(context).build();
    }

    @Test
    public void noCredentials() {
        this.rest.get()
                .uri("/product/list")
                .exchange()
                .expectStatus().isUnauthorized();
    }

    @Test
    public void validCredentials() {
        this.rest.get()
                .uri("/product/list")
                .headers(userCredentials())
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void invalidCredentials() {
        this.rest.get()
                .uri("/product/list")
                .headers(invalidUserCredentials())
                .exchange()
                .expectStatus().isUnauthorized();
    }

    @Test
    public void list() throws JsonProcessingException {
        rest.mutateWith(csrf())
                .get()
                .uri("/product/list")
                .headers(userCredentials())
                .headers(contentType())
                .headers(accept())
                .exchange()
                .expectStatus().isOk()
                .expectBody().json(mapper.writeValueAsString(Constants.PRODUCTS.get()));
    }

    private Consumer<HttpHeaders> contentType() {
        return httpHeaders -> httpHeaders.setContentType(APPLICATION_JSON);
    }

    private Consumer<HttpHeaders> accept() {
        return httpHeaders -> httpHeaders.setAccept(Collections.singletonList(APPLICATION_JSON));
    }

    private Consumer<HttpHeaders> userCredentials() {
        return httpHeaders -> httpHeaders.setBasicAuth("user", "user");
    }

    private Consumer<HttpHeaders> invalidUserCredentials() {
        return httpHeaders -> httpHeaders.setBasicAuth("user", "INVALID");
    }

}
