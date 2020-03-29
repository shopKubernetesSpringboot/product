package com.dgf.shopproduct.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id()
    private String id;

    private String name;
    private ProductType type;
    private int availability;

//    public Optional<Product> availabilityDec() {
//        if (availability<=0) {
//            return Optional.empty();
//        }
//        availability=availability-1;
//        return Optional.of(this);
//    }
//
//    public Product availabilityInc() {
//        availability=availability+1;
//        return this;
//    }
}
