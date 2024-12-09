package org.example.springjpaalena.controller;


import lombok.RequiredArgsConstructor;
import org.example.springjpaalena.error.InvalidPageException;
import org.example.springjpaalena.error.InvalidSizeException;
import org.example.springjpaalena.model.Product;
import org.example.springjpaalena.repository.CategoryRepository;
import org.example.springjpaalena.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequestMapping("/products")
@RestController
@RequiredArgsConstructor

public class ProductController {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping

    public List<Product> findAll() {
        return productRepository.findAll();
    }
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

    //    @GetMapping("/{name}")
//    public List<Product> findAllByNameContainingIgnoreCaseOrderByPriceDesc(@PathVariable String name) {
//       return productRepository.findAllByNameContainingIgnoreCaseOrderByPriceDesc(name);
//
//    }
    @GetMapping("/{id}")
    public Product findById(@PathVariable int id) {
        return productRepository.findById(id);
    }

    @GetMapping("/page/size")
    public List<Product> findAllByPageAndSize(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        if (page < 0) {
            throw new InvalidPageException("Ошибка! Страница не должна быть меньше нуля");
        }
        if (size <= 0) {
            throw new InvalidSizeException("Ошибка! Размер должен быть положительным");
        }
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable).getContent();

    }

}

