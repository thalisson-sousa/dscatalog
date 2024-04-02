package com.devthalisson.dscatalog.repositories;

import com.devthalisson.dscatalog.entities.Product;
import com.devthalisson.dscatalog.services.exceptions.ResourceNotFoundException;
import com.devthalisson.dscatalog.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Instant;
import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    private long expectecId;
    private long countTotalProducts;

    @BeforeEach
    void setUp() throws Exception {
        expectecId = 1L;
        countTotalProducts = 25L;
    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExist() {

        productRepository.deleteById(expectecId);
        Optional<Product> result = productRepository.findById(expectecId);
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void saveShouldPersistWithAutoIncrementWhenIdIsNull() {
        Product product = Factory.createProduct();
        product.setId(null);
        product = productRepository.save(product);

        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals(countTotalProducts + 1, product.getId());
    }

    @Test
    public void findByIdShouldReturnOptionalNotEmptyWhentIdExist() {
        Optional<Product> result = productRepository.findById(expectecId);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    public void findByIdShouldReturnOptionalEmptyWhentIdDoesNotExist() {
        Optional<Product> result = productRepository.findById(countTotalProducts + 1);
        Assertions.assertFalse(result.isPresent());
    }
}
