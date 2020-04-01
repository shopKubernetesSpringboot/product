package com.dgf.shopproduct.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.ReactiveMapSessionRepository;
import org.springframework.web.server.session.HeaderWebSessionIdResolver;
import org.springframework.web.server.session.WebSessionIdResolver;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class SessionConfig {

    @Bean
    public ReactiveMapSessionRepository reactiveSessionRepository() {
        return new ReactiveMapSessionRepository(new ConcurrentHashMap<>());
    }


    /**
     * Use de session coming in the request cookies named "SESSION"
     * (Solved session issue on client (when /product/list was called cart session was reinitialized))
     * @return resolver
     */
    @Bean
    public WebSessionIdResolver headerWebSessionIdResolver() {
        HeaderWebSessionIdResolver resolver = new HeaderWebSessionIdResolver();
        resolver.setHeaderName("SESSION");
        return resolver;
    }
}
