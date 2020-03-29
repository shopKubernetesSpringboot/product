package com.dgf.shopproduct;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = App.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppIntegrationTest {

    @LocalServerPort
    private int port;
    @Autowired
    ApplicationContext context;

    private ObjectMapper mapper = new ObjectMapper();
    private WebTestClient rest;

    @BeforeAll
    public void setup() {
//        rest = WebTestClient.bindToServer()
        rest = WebTestClient.bindToApplicationContext(context)
//                .baseUrl("http://localhost:" + this.port)
//                .filter(logRequest())
                .build();
    }

    @Test
    public void noCredentials() {
        this.rest
                .get()
                .uri("/product/list")
                .exchange()
                .expectStatus().isUnauthorized();
    }

    @Test
    public void validCredentials() {
        this.rest
                .get()
                .uri("/product/list")
                .headers(userCredentials())
                .exchange()
                .expectStatus().isOk();
//                .expectBody().json("{\"message\":\"Hello user!\"}");
    }

    @Test
    public void contextLoads() {
        App.main(new String[]{});
        assertTrue(true);
    }

    @Test
    public void list() throws JsonProcessingException {
        rest.mutateWith(csrf()).get()
                .uri("/product/list")
                .headers(userCredentials())
                .headers(contentType())
                .headers(accept())
                .exchange()
                .expectStatus().isOk()
                .expectBody().json(mapper.writeValueAsString(Constants.PRODUCTS.get()));
    }

    private Consumer<MultiValueMap<String, String>> cookies(List<ResponseCookie> sessionCookies) {
        return c-> sessionCookies.forEach(c2 -> c.addAll(c2.getName(),Collections.singletonList(c2.getValue())));
    }

    private Consumer<HttpHeaders> userCredentials() {
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return httpHeaders -> httpHeaders.setBasicAuth("user", "user");
    }
    private Consumer<HttpHeaders> contentType() {
        return httpHeaders -> httpHeaders.setContentType(APPLICATION_JSON);
    }
    private Consumer<HttpHeaders> accept() {
        return httpHeaders -> httpHeaders.setAccept(Collections.singletonList(APPLICATION_JSON));
    }

    private Consumer<HttpHeaders> invalidCredentials() {
        return httpHeaders -> httpHeaders.setBasicAuth("user", "INVALID");
    }

    private static ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.info("{}(intTest*) {} {}", clientRequest.logPrefix(), clientRequest.method(), clientRequest.url());
            clientRequest.headers().forEach((name, values) -> values.forEach(value -> log.info("{}(intTest*) {}={}", clientRequest.logPrefix(), name, value)));
            return Mono.just(clientRequest);
        });
    }
}
