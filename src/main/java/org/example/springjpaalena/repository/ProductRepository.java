package org.example.springjpaalena.repository;

import org.example.springjpaalena.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findAllByPriceBetween(double price, double price2);
    List<Product> findAllByNameContainingIgnoreCaseOrderByPriceDesc(String name);
    Product findById(int productId);
    @Query
    ("select p from Product p join p.valueList v where v.name = :valueName")
    List<Product> findAllByValueListAndName(String valueName);



}
