package com.dgf.shopproduct;

import static com.dgf.shopproduct.model.ProductType.NEW;
import static com.dgf.shopproduct.model.ProductType.OLD;
import static com.dgf.shopproduct.model.ProductType.REGULAR;

import com.dgf.shopproduct.model.Product;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Constants {

    public static final AtomicReference<List<Product>> PRODUCTS = new AtomicReference<>(Arrays.asList(
        new Product("5d89f274c22a011f98f6dc90", "Product 1", NEW, 5),
        new Product("5d89f274c22a011f98f6dc91", "Product 2", REGULAR, 2),
        new Product("5d89f274c22a011f98f6dc92", "Product 3", REGULAR, 2),
        new Product("5d89f274c22a011f98f6dc94", "Product 4", OLD, 1),
        new Product("5d89f274c22a011f98f6dc95", "Product 5", OLD, 1),
        new Product("5d89f274c22a011f98f6dc96", "Product 6", OLD, 1),
        new Product("5d89f274c22a011f98f6dc97", "Product 7", OLD, 1),
        new Product("5d89f274c22a011f98f6dc98", "Product 8", OLD, 1),
        new Product("5d89f274c22a011f98f6dc99", "Product 9", OLD, 1),
        new Product("5d89f274c22a011f98f6dd00", "Product10 large text just to try what happens in the rows for example you know... large text just to try what happens in the rows for example you know", OLD, 1)
    ));

    private Constants() {
    }
}
