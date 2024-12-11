package org.example.springjpaalena.dto.value;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValueCreateDto {
    private int optionId;
    private String name;
}
