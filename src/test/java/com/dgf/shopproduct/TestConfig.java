package com.dgf.shopproduct;

import org.springframework.context.annotation.Bean;
import org.springframework.session.ReactiveMapSessionRepository;

import java.util.concurrent.ConcurrentHashMap;

public class TestConfig {

    @Bean
    public ReactiveMapSessionRepository reactiveSessionRepository() {
        return new ReactiveMapSessionRepository(new ConcurrentHashMap<>());
    }

}
