package com.example.PruebaTecnica.repository;

import com.example.PruebaTecnica.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface IVentaRepository extends JpaRepository<Venta,Long> {
    
    @Query("SELECT v FROM Venta v WHERE v.sucursal.id = :idSucursal AND v.fecha = :fecha")
    List<Venta> findBySucursalAndFecha(Long idSucursal, LocalDate fecha);
}
