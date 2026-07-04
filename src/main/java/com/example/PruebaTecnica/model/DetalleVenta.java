package com.example.PruebaTecnica.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer cantidad;
    private BigDecimal precioUnitario;

    private BigDecimal subtotal(){
        return  precioUnitario.multiply(new BigDecimal(cantidad));
    }

    @ManyToOne
    private Producto producto;

    @ManyToOne()
    @JsonBackReference
    private Venta venta;

}
