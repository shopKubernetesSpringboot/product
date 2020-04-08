package com.dgf.shopproduct;

import com.dgf.shopproduct.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.session.config.annotation.web.server.EnableSpringWebSession;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableSpringWebSession
@EnableWebFlux
@EnableWebFluxSecurity()
@EnableReactiveMongoRepositories
@Slf4j
public class ShopProductApp implements ApplicationContextAware {

    public static void main(String[] args) {
        SpringApplication.run(ShopProductApp.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        ctx.getBean(ProductService.class).initData();
    }

}
