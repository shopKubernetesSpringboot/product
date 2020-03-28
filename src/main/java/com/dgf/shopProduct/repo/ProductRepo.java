package com.dgf.shopProduct.repo;

import com.dgf.shopProduct.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ProductRepo extends ReactiveMongoRepository<Product, String> {

    Flux<Product> findByNameIgnoreCaseContaining(String name);
}
