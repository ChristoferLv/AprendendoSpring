package com.projeto1.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutorDTO {

    private Long id;

    @NotNull
    @Size(max = 200)
    private String name;

    @NotNull
    private Integer age;
}
