package com.dgf.shopproduct.service;

import com.dgf.shopproduct.Constants;
import com.dgf.shopproduct.model.Product;
import com.dgf.shopproduct.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepo repo;

    public Flux<Product> findByNameIgnoreCaseContaining(String name) {
        log.info("finding names like: {}", name);
        return repo.findByNameIgnoreCaseContaining(name);
    }

    public Flux<Product> create(List<Product> items) {
        return repo.deleteAll().thenMany(
            repo.saveAll(items)
        );
    }

//    Flux<Product> increaseStock(List<String> items) {
//        log.info("increase stock {} ", items);
//        Flux<Product> updatedItems = repo.findAllById(items)
//            .map(Product::availabilityInc);
//        log.info("increase stock save updatedItems={} ", updatedItems);
//        return repo.saveAll(updatedItems);
//    }

//    Flux<Product> decreaseStock(@NotNull List<String> items) {
//        log.info("decrease stock {} ", items);
//        Flux<Product> availableItems = repo.findAllById(items).map(Product::availabilityDec)
//            .filter(Optional::isPresent)
//            .map(Optional::get);
//        log.info("decrease stock save availableItems={} ", availableItems);
//        return repo.saveAll(availableItems);
//    }

    public Flux<Product> findAll() {
        return repo.findAll();
    }

    public void initData() {
        log.info("Creating products");
        repo.saveAll(Constants.PRODUCTS.get()).subscribe();
    }
}
