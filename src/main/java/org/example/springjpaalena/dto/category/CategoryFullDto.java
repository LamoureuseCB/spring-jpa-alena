package org.example.springjpaalena.dto.category;

import lombok.Builder;
import lombok.Data;
import org.example.springjpaalena.dto.product.ProductDto;


import java.util.List;

@Data
@Builder
public class CategoryFullDto {
    private int id;
    private String name;
    private List<ProductDto> products;
}
