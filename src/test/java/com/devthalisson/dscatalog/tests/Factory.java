package com.devthalisson.dscatalog.tests;

import com.devthalisson.dscatalog.dto.ProductDTO;
import com.devthalisson.dscatalog.entities.Category;
import com.devthalisson.dscatalog.entities.Product;

import java.time.Instant;

public class Factory {

    public static Product createProduct() {

        Product product = new Product(1L, "TV", "uma tv", 100.0, "http://image.com", Instant.parse("2020-10-20T03:00:00Z"));
        product.getCategories().add(new Category(2L, "Electronics"));
        return product;
    }

    public static ProductDTO createProductDTO() {
        Product product = createProduct();
        return new ProductDTO(product, product.getCategories());
    }
}
