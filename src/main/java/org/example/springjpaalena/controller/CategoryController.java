package org.example.springjpaalena.controller;

import lombok.RequiredArgsConstructor;
import org.example.springjpaalena.dto.category.CategoryCreateDto;
import org.example.springjpaalena.dto.category.CategoryFullDto;
import org.example.springjpaalena.dto.category.CategoryWithOptionsResponseDto;
import org.example.springjpaalena.mapper.CategoryMapper;
import org.example.springjpaalena.dto.category.CategoryShortDto;
import org.example.springjpaalena.model.Category;
import org.example.springjpaalena.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public List<CategoryShortDto> findAll() {
        return categoryMapper.toDto(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public CategoryFullDto findById(@PathVariable int id) {
        Category category = categoryService.findById(id);
        return categoryMapper.toDtoFull(category);
    }

    @PostMapping
    public CategoryWithOptionsResponseDto create(@RequestBody CategoryCreateDto categoryCreateDto) {
        return categoryService.create(categoryCreateDto);
    }
}
