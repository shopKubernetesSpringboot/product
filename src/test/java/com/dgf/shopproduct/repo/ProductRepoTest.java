package com.dgf.shopproduct.repo;


import com.dgf.shopproduct.Constants;
import com.dgf.shopproduct.SecurityTestConfig;
import com.dgf.shopproduct.TestConfig;
import com.dgf.shopproduct.model.Product;
import com.dgf.shopproduct.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest()
@Import({TestConfig.class, SecurityTestConfig.class})
public class ProductRepoTest {

    @MockBean
    ProductService service;
    @Autowired
    private ProductRepo repo;

    @Test
    public void test() {
        repo.saveAll(Constants.PRODUCTS.get()).subscribe();
        Product entity = Constants.PRODUCTS.get().get(0);
        StepVerifier
            .create(repo.findByNameIgnoreCaseContaining(entity.getName()))
            .assertNext(loaded -> {
                assertEquals(entity.getId(),loaded.getId());
                assertEquals(entity.getName(),loaded.getName());
                assertEquals(entity.getAvailability(),loaded.getAvailability());
            })
            .expectComplete()
            .verify();
    }
}
