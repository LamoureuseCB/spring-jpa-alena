package org.example.springjpaalena.dto;

import lombok.Builder;
import lombok.Data;
import org.example.springjpaalena.model.Product;

import java.util.List;

@Data
@Builder
public class CategoryFullDto {
    private int id;
    private String name;
    private List<ProductDto> products;

}
