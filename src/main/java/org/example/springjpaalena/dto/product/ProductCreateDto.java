package org.example.springjpaalena.dto.product;

import lombok.*;
import org.example.springjpaalena.dto.value.ValueCreateDto;

import java.util.List;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ProductCreateDto {
    private String name;
    private double price;
    private List<ValueCreateDto> values;
}
