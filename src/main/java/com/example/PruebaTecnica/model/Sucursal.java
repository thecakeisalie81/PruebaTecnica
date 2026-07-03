package com.example.PruebaTecnica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sucursal
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;

    @OneToMany
    private List<Venta> ventas;

    public void  addVenta(Venta venta)
    {
        ventas.add(venta);
        venta.setSucursal(this);
    }
}
