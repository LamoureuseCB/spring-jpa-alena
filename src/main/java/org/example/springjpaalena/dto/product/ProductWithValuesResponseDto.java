package org.example.springjpaalena.dto.product;

import lombok.*;
import org.example.springjpaalena.dto.value.ValueResponseDto;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductWithValuesResponseDto {
    private int id;
    private String name;
    private double price;
    private String categoryName;
    private List<ValueResponseDto> values;
}
