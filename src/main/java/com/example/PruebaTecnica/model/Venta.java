package com.example.PruebaTecnica.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;
    private EstadoVenta estado;
    private boolean borradoLogico;

    @ManyToOne
    @JsonBackReference
    private Sucursal sucursal;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL,  orphanRemoval = true)
    @JsonManagedReference
    private List<DetalleVenta> detalleVentas;

    private void cambiarEstado(EstadoVenta estado) {
        this.estado = estado;
    }
}
