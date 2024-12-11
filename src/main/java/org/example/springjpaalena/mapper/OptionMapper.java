package org.example.springjpaalena.mapper;

import lombok.RequiredArgsConstructor;
import org.example.springjpaalena.dto.option.OptionResponseDto;
import org.example.springjpaalena.model.Option;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OptionMapper {

    public OptionResponseDto toOptionResponseDto(Option option){
        return OptionResponseDto.builder().id(option.getId()).name(option.getName()).build();
    }
    public List<OptionResponseDto> toOptionResponseDtos(List<Option> options){
        return options.stream().map(this::toOptionResponseDto).toList();
    }
}
