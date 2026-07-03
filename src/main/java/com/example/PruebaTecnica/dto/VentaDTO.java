package com.example.PruebaTecnica.dto;

import com.example.PruebaTecnica.model.EstadoVenta;
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
    private LocalDate fecha;
    private Long SucursalId;
    private EstadoVenta estadoVenta;
    private List<DetalleVentaDTO> detalles;
}
