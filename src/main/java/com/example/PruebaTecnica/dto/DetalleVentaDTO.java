package com.example.PruebaTecnica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVentaDTO {
    @NotNull @Positive
    private Long productoId;
    @NotBlank
    private String nombreProducto;
    @NotNull
    private Integer cantidad;
    @NotNull
    private BigDecimal precioUnitario;
    @NotNull
    private BigDecimal precioTotal;

}
