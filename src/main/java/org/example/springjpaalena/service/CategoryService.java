package org.example.springjpaalena.service;

import lombok.RequiredArgsConstructor;
import org.example.springjpaalena.dto.category.CategoryCreateDto;
import org.example.springjpaalena.dto.category.CategoryWithOptionsResponseDto;
import org.example.springjpaalena.mapper.OptionMapper;
import org.example.springjpaalena.dto.option.OptionResponseDto;
import org.example.springjpaalena.error.CategoryNotFoundException;
import org.example.springjpaalena.model.Category;
import org.example.springjpaalena.model.Option;
import org.example.springjpaalena.repository.CategoryRepository;
import org.example.springjpaalena.repository.OptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final OptionRepository optionRepository;
    private final OptionMapper optionMapper;


    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(int id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Категория с ID" + id + " не найдена"));
    }

    public CategoryWithOptionsResponseDto create(CategoryCreateDto categoryCreateDto) {
        Category createdCategory = new Category();
        createdCategory.setName(categoryCreateDto.getName());
        List<Option> options = categoryCreateDto.getOptions();

        if (options != null) {
            for (Option option : options) {
                optionRepository.save(option);
            }
            createdCategory.setOptionList(options);
        }
        categoryRepository.save(createdCategory);
        List<OptionResponseDto> optionResponseDtos = optionMapper.toOptionResponseDtos(options);
        return CategoryWithOptionsResponseDto.builder()
                .id(createdCategory.getId())
                .name(createdCategory.getName())
                .options(optionResponseDtos).build();
    }

}
