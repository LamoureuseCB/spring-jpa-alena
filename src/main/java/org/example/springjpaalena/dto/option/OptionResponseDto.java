package org.example.springjpaalena.dto.option;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OptionResponseDto {
    private int id;
    private String name;
}
