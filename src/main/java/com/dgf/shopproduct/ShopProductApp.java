package com.dgf.shopproduct;

import com.dgf.shopproduct.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
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
public class ShopProductApp{
//public class ShopProductApp implements ApplicationListener<ApplicationStartedEvent> {
//public class ShopProductApp implements ApplicationListener<ApplicationReadyEvent> {
//public class ShopProductApp implements ApplicationContextAware {

    public static void main(String[] args) {
        SpringApplication.run(ShopProductApp.class);
//        ConfigurableApplicationContext run = SpringApplication.run(ShopProductApp.class);
//        run.addApplicationListener((ApplicationListener<ApplicationReadyEvent>) event -> {
//            event.getApplicationContext().getBean(ProductService.class).initData();
//        });
    }

//    @Override
//    public void onApplicationEvent(ApplicationReadyEvent event) {
//        event.getApplicationContext().getBean(ProductService.class).initData();
//    }

//    @Override
//    public void setApplicationContext(ApplicationContext ctx) {
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                try {
//                    log.info("waiting to context to be ready...");
//                    sleep(7000);
//                    ctx.getBean(ProductService.class).initData();
//                } catch (InterruptedException e) {
//                    log.error("", e);
//                }
//            }
//        }.start();
//    }

//    @Override
//    public void onApplicationEvent(ApplicationStartedEvent event) {
//        event.getApplicationContext().getBean(ProductService.class).initData();
//    }
}
