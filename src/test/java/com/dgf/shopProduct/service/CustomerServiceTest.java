package com.dgf.shopProduct.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private ProductService service;

    @Test
    public void test() {
//        StepVerifier
//            .create(service.findByFirstNameAndLastNameIgnoreCase("John","Doe"))
//            .assertNext(loaded -> assertEquals("John",loaded.getFirstName()))
//            .expectComplete()
//            .verify();
    }
}
