package com.dgf.shopproduct.repo;


import com.dgf.shopproduct.Constants;
import com.dgf.shopproduct.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest  //embedded mongo tests can't be unit tests
public class ProductRepoTest {

    @Autowired
    private ProductRepo repo;

    @Test
    public void test() {
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
