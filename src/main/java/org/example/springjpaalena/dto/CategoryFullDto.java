package org.example.springjpaalena.dto;

import lombok.Builder;
import lombok.Data;


import java.util.List;

@Data
@Builder
public class CategoryFullDto {
    private int id;
    private String name;
    private List<ProductDto> products;

}
