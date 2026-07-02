package com.example.PruebaTecnica.repository;

import com.example.PruebaTecnica.model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDetalleVentaRepository extends JpaRepository<DetalleVenta,Long> {
}
