package org.example.springjpaalena.repository;

import org.example.springjpaalena.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findAllByPriceBetween(int from, int to);
    List<Product> findAllByNameContainingIgnoreCaseOrderByPriceDesc(String name);
}
