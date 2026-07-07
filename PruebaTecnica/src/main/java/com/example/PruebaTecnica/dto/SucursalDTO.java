package com.example.PruebaTecnica.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class SucursalDTO {
    private Long id;
    @NotBlank
    private String nombre;
    @NotBlank
    private String direccion;
    @NotBlank
    private String telefono;
}
