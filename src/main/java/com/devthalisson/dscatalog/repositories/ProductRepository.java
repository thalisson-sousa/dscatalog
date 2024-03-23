package com.devthalisson.dscatalog.repositories;

import com.devthalisson.dscatalog.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
