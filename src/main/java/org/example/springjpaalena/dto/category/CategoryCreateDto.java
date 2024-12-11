package org.example.springjpaalena.dto.category;

import lombok.*;
import org.example.springjpaalena.model.Option;

import java.util.List;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCreateDto {
    private String name;
    private List<Option> options;
}
