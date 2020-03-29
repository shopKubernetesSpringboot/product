package com.dgf.shopproduct.repo;

import static junit.framework.TestCase.assertEquals;

import com.dgf.shopproduct.Constants;
import com.dgf.shopproduct.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@SpringBootTest
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
