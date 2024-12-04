package org.example.springjpaalena.repository;

import org.example.springjpaalena.model.Product;
import org.example.springjpaalena.model.Value;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ValueRepository extends JpaRepository<Value,Integer > {
    List<Value> findAllByAndProduct(Product product);


}
