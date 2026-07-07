package com.example.PruebaTecnica.repository;

import com.example.PruebaTecnica.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepository extends JpaRepository<Producto,Long> {
    @Query("SELECT p FROM Producto p WHERE p.nombre = :nombre")
    Producto findByNombre(@Param("nombre") String nombre);
}
