package org.example.springjpaalena.dto.value;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValueResponseDto {
    private int id;
    private String name;
    private String optionName;
}
