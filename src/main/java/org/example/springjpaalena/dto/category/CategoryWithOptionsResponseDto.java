package org.example.springjpaalena.dto.category;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.springjpaalena.dto.option.OptionResponseDto;

import java.util.List;
@Getter
@Setter
@Builder
public class CategoryWithOptionsResponseDto {
    private int id;
    private String name;
    private List<OptionResponseDto> options;
}
