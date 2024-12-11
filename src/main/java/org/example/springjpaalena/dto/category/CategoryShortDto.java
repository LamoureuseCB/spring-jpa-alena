package org.example.springjpaalena.dto.category;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryShortDto {
    private int id;
    private String name;

}
