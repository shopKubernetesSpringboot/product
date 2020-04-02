package com.dgf.shopproduct;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.ReactiveAuthenticationManager;

public class SecurityTestConfig {

    @MockBean
    ReactiveAuthenticationManager reactiveAuthenticationManager;

    @Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager() {
        return this.reactiveAuthenticationManager;
    }

}
