package com.example.PruebaTecnica.repository;

import com.example.PruebaTecnica.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISucursalRepository extends JpaRepository<Sucursal,Long> {
}
