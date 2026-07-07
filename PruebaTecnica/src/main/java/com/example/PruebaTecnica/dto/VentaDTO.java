package com.example.PruebaTecnica.dto;

import com.example.PruebaTecnica.model.EstadoVenta;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VentaDTO {
    private Long id;
    @NotNull
    private LocalDate fecha;
    @NotNull @Positive
    private Long SucursalId;
    @NotNull
    private EstadoVenta estadoVenta;
    @NotEmpty
    private List<DetalleVentaDTO> detalles;
}
