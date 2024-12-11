package org.example.springjpaalena.mapper;

import org.example.springjpaalena.dto.value.ValueCreateDto;
import org.example.springjpaalena.dto.value.ValueResponseDto;
import org.example.springjpaalena.model.Option;
import org.example.springjpaalena.model.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class ValueMapper {

    public ValueCreateDto toValueCreateDto(Value value) {
        return ValueCreateDto.builder()
                .name(value.getName())
                .optionId(value.getOption().getId())
                .build();
    }

    public List<ValueCreateDto> toValueCreateDtos(List<Value> values) {
        return values.stream()
                .map(this::toValueCreateDto)
                .collect(Collectors.toList());
    }

    public ValueResponseDto toValueResponseDto(Value value, Option option) {

        return ValueResponseDto.builder()
                .id(value.getId())
                .name(value.getName())
                .optionName(option.getName())
                .build();
    }

    public List<ValueResponseDto> toValueResponseDtos(List<Value> values, List<Option> options) {
        List<ValueResponseDto> valueResponseList = new ArrayList<>();
        for (Value value : values) {
            for (Option option : options) {
                if (option.getId() == value.getOption().getId()) {
                    valueResponseList.add(toValueResponseDto(value, option));
                }
            }

        }
        return valueResponseList;
    }
}
