package org.example.springjpaalena.controller;


import lombok.RequiredArgsConstructor;
import org.example.springjpaalena.dto.product.ProductCreateDto;
import org.example.springjpaalena.dto.product.ProductWithValuesResponseDto;
import org.example.springjpaalena.error.InvalidPageException;
import org.example.springjpaalena.error.InvalidSizeException;
import org.example.springjpaalena.model.Product;
import org.example.springjpaalena.repository.CategoryRepository;
import org.example.springjpaalena.repository.ProductRepository;
import org.example.springjpaalena.service.ProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequestMapping("/products")
@RestController
@RequiredArgsConstructor

public class ProductController {
    private final ProductRepository productRepository;
    private final ProductService productService;

    @GetMapping

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @PostMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductWithValuesResponseDto createProduct(@PathVariable int categoryId, @RequestBody ProductCreateDto productCreateDto) {
        return productService.create(categoryId, productCreateDto);

    }
    @GetMapping("/{from}/{to}")
    public List<Product> findAllByPriceBetween(@PathVariable int from, @PathVariable int to) {
        return productRepository.findAllByPriceBetween(from, to);
    }
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

    //    @GetMapping("/{name}")
//    public List<Product> findAllByNameContainingIgnoreCaseOrderByPriceDesc(@PathVariable String name) {
//       return productRepository.findAllByNameContainingIgnoreCaseOrderByPriceDesc(name);
//
//    }

}

