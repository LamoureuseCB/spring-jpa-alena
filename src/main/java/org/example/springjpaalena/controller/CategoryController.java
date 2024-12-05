package org.example.springjpaalena.controller;

import lombok.RequiredArgsConstructor;
import org.example.springjpaalena.dto.CategoryFullDto;
import org.example.springjpaalena.dto.CategoryMapper;
import org.example.springjpaalena.dto.CategoryShortDto;
import org.example.springjpaalena.repository.CategoryRepository;
import org.example.springjpaalena.model.Category;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public List<CategoryShortDto> findAll(){

        return categoryMapper.toDto(categoryRepository.findAll());
    }
    @GetMapping("/{id}")
    public CategoryFullDto findById(@PathVariable int id){
        Category category = categoryRepository.findById(id).orElseThrow();
        return categoryMapper.toDtoFull(category);
    }
    @PostMapping
    public Category create(@RequestBody Category category){
        return  categoryRepository.save(category);
    }
}
