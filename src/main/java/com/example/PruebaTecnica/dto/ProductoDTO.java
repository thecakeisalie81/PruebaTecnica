package com.example.PruebaTecnica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {
    private Long id;
    @NotBlank
    private String nombre;
    @NotNull
    private String categoria;
    @NotNull
    @Positive
    private BigDecimal precio;
}
