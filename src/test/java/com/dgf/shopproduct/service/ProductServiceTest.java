package com.dgf.shopproduct.service;

import com.dgf.shopproduct.Constants;
import com.dgf.shopproduct.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.test.StepVerifier;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService service;

    @Test
    public void test() {
        Product entity = Constants.PRODUCTS.get().get(0);
        StepVerifier
            .create(service.findByNameIgnoreCaseContaining(entity.getName()))
            .assertNext(loaded -> assertEquals(entity.getName(),loaded.getName()))
            .expectComplete()
            .verify();
    }
}
