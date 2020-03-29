package com.dgf.shopproduct.repo;

import com.dgf.shopproduct.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ProductRepo extends ReactiveMongoRepository<Product, String> {

    Flux<Product> findByNameIgnoreCaseContaining(String name);
}
