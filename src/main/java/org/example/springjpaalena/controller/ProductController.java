package org.example.springjpaalena.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.springjpaalena.model.Category;
import org.example.springjpaalena.model.Product;
import org.example.springjpaalena.repository.CategoryRepository;
import org.example.springjpaalena.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/products")
@RestController
@RequiredArgsConstructor

public class ProductController {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

//    @GetMapping
//
//    public List<Product> findAll() {
//        return productRepository.findAll();
//    }
//
//    @PostMapping("/{categoryId}")
//    public Product create(@RequestBody Product product,
//                          @PathVariable int categoryId) {
//        Category category = categoryRepository.findById(categoryId).orElseThrow();
//        product.setCategory(category);
//        return productRepository.save(product);
//    }


    @GetMapping("/{from}/{to}")
    public List<Product> findAllByPriceBetween(@PathVariable int from, @PathVariable int to) {
        return productRepository.findAllByPriceBetween(from, to);
    }

    @GetMapping("/{name}")
    public List<Product> findAllByNameContainingIgnoreCaseOrderByPriceDesc(@PathVariable String name) {
       return productRepository.findAllByNameContainingIgnoreCaseOrderByPriceDesc(name);

    }
}

