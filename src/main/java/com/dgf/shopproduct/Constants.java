package com.dgf.shopproduct;

import static com.dgf.shopproduct.model.ProductType.NEW;
import static com.dgf.shopproduct.model.ProductType.OLD;
import static com.dgf.shopproduct.model.ProductType.REGULAR;

import com.dgf.shopproduct.model.Product;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class Constants {

    public static final AtomicReference<List<Product>> PRODUCTS = new AtomicReference<>(Arrays.asList(
        new Product(getId(), getName(), NEW, 5),
        new Product(getId(), getName(), REGULAR, 2),
        new Product(getId(), getName(), REGULAR, 2),
        new Product(getId(), getName(), OLD, 1),
        new Product(getId(), getName(), OLD, 1),
        new Product(getId(), getName(), OLD, 1),
        new Product(getId(), getName(), OLD, 1),
        new Product(getId(), getName(), OLD, 1),
        new Product(getId(), getName(), OLD, 1),
        new Product(getId(), getName(" añldskj fañlkdsj ñalkdsj fñlkasdj fñlkasjd fñlkasdj fñlkasjd fñlaksd jfñlaksdj fñlaksdj fñlaksd jfñlaksdj fñlaksdj fñlaksdj fñlaksdjf ñlkasdj fñlaksdj fñlaksdj fñlaksdj fñlkasdjf ñlaksdjf ñlaksdjf ñlaksdj fñlkasdj fñlkasdj fñlaksdj fñlkasdj fñlkasjdf ñlaksdj fñlaksdj fñlaksdj fñlaksd jfñlkasdj fñlaksjd fñlkasjd fñlkajsd f"), OLD, 1),
//        new Product(getId(), getName(" añldskj fañlkdsj ñalkdsj fñlkasdj fñlkasjd fñlkasdj fñlkasjd fñlaksd jfñlaksdj fñlaksdj fñlaksd jfñlaksdj fñlaksdj fñlaksdj fñlaksdjf ñlkasdj fñlaksdj fñlaksdj fñlaksdj fñlkasdjf ñlaksdjf ñlaksdjf ñlaksdj fñlkasdj fñlkasdj fñlaksdj fñlkasdj fñlkasjdf ñlaksdj fñlaksdj fñlaksdj fñlaksd jfñlkasdj fñlaksjd fñlkasjd fñlkajsd f añldskj fañlkdsj ñalkdsj fñlkasdj fñlkasjd fñlkasdj fñlkasjd fñlaksd jfñlaksdj fñlaksdj fñlaksd jfñlaksdj fñlaksdj fñlaksdj fñlaksdjf ñlkasdj fñlaksdj fñlaksdj fñlaksdj fñlkasdjf ñlaksdjf ñlaksdjf ñlaksdj fñlkasdj fñlkasdj fñlaksdj fñlkasdj fñlkasjdf ñlaksdj fñlaksdj fñlaksdj fñlaksd jfñlkasdj fñlaksjd fñlkasjd fñlkajsd f"), OLD, 1),
//        new Product(getId(), getName(" añldskj fañlkdsj ñalkdsj fñlkasdj fñlkasjd fñlkasdj fñlkasjd fñlaksd jfñlaksdj fñlaksdj fñlaksd jfñlaksdj fñlaksdj fñlaksdj fñlaksdjf ñlkasdj fñlaksdj fñlaksdj fñlaksdj fñlkasdjf ñlaksdjf ñlaksdjf ñlaksdj fñlkasdj fñlkasdj fñlaksdj fñlkasdj fñlkasjdf ñlaksdj fñlaksdj fñlaksdj fñlaksd jfñlkasdj fñlaksjd fñlkasjd fñlkajsd f añldskj fañlkdsj ñalkdsj fñlkasdj fñlkasjd fñlkasdj fñlkasjd fñlaksd jfñlaksdj fñlaksdj fñlaksd jfñlaksdj fñlaksdj fñlaksdj fñlaksdjf ñlkasdj fñlaksdj fñlaksdj fñlaksdj fñlkasdjf ñlaksdjf ñlaksdjf ñlaksdj fñlkasdj fñlkasdj fñlaksdj fñlkasdj fñlkasjdf ñlaksdj fñlaksdj fñlaksdj fñlaksd jfñlkasdj fñlaksjd fñlkasjd fñlkajsd f añldskj fañlkdsj ñalkdsj fñlkasdj fñlkasjd fñlkasdj fñlkasjd fñlaksd jfñlaksdj fñlaksdj fñlaksd jfñlaksdj fñlaksdj fñlaksdj fñlaksdjf ñlkasdj fñlaksdj fñlaksdj fñlaksdj fñlkasdjf ñlaksdjf ñlaksdjf ñlaksdj fñlkasdj fñlkasdj fñlaksdj fñlkasdj fñlkasjdf ñlaksdj fñlaksdj fñlaksdj fñlaksd jfñlkasdj fñlaksjd fñlkasjd fñlkajsd f"), OLD, 1),
        new Product(getId(), getName("large text just to try what happens in the rows for example you know... large text just to try what happens in the rows for example you know"), OLD, 1)
    ));
    private static int nameCounter = 1;

    private static String getName(String extraText) {
        return "Product "+(nameCounter++)+" "+extraText;
    }
    private static String getName() {
        return "Product "+(nameCounter++);
    }

    private static String getId() {
        return UUID.randomUUID().toString();
    }

    private Constants() {
    }
}
