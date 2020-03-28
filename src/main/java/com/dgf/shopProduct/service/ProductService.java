package com.dgf.shopProduct.service;

import com.dgf.shopProduct.Constants;
import com.dgf.shopProduct.model.Product;
import com.dgf.shopProduct.repo.ProductRepo;
import java.util.List;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.util.Logger;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public Flux<Product> findByNameIgnoreCaseContaining(String title) {
        log.info("finding titles like: {}", title);
        return repo.findByNameIgnoreCaseContaining(title);
    }

    public Flux<Product> create(List<Product> items) {
        return repo.deleteAll().thenMany(
            repo.saveAll(items)
        );
    }

    Flux<Product> increaseStock(List<String> items) {
        log.info("increase stock {} ", items);
        Flux<Product> updatedItems = repo.findAllById(items)
            .map(Product::availabilityInc);
        log.info("increase stock save updatedItems={} ", updatedItems);
        return repo.saveAll(updatedItems);
    }

    Flux<Product> decreaseStock(@NotNull List<String> items) {
        log.info("decrease stock {} ", items);
        Flux<Product> availableItems = repo.findAllById(items).map(Product::availabilityDec)
            .filter(Optional::isPresent)
            .map(Optional::get);
        log.info("decrease stock save availableItems={} ", availableItems);
        return repo.saveAll(availableItems);
    }

    public Flux<Product> findAll() {
        return repo.findAll();
    }

    public void initData() {
        log.info("Creating products");
        repo.saveAll(Constants.PRODUCTS.get()).subscribe();
//        .subscribe(product -> log.debug("Created product={}",product));
    }
}
