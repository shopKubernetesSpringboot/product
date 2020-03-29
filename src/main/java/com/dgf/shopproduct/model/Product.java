package com.dgf.shopproduct.model;

import java.util.Optional;
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
    private ProductType rentalType;
    private int availability;

    public Product(String name, ProductType rentalType, int availability) {
        this.name = name;
        this.rentalType=rentalType;
        this.availability=availability;
    }

    public Optional<Product> availabilityDec() {
        if (availability<=0) {
            return Optional.empty();
        }
        availability=availability-1;
        return Optional.of(this);
    }

    public Product availabilityInc() {
        availability=availability+1;
        return this;
    }
}
