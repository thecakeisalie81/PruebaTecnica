package com.example.PruebaTecnica.controller;

import com.example.PruebaTecnica.dto.SucursalDTO;
import com.example.PruebaTecnica.model.Sucursal;
import com.example.PruebaTecnica.service.ISucursalService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
@Validated
public class SucursalController {

    private final ISucursalService sucursalService;

    public SucursalController(ISucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }


    @GetMapping
    public List<Sucursal> listaSucursales(){
        return sucursalService.getSucursales();
    }

    @PostMapping
    public ResponseEntity<Sucursal> saveSucursal(@Valid @RequestBody SucursalDTO sucursal){
        Sucursal nuevaSucursal = new Sucursal();
        nuevaSucursal.setNombre(sucursal.getNombre());
        nuevaSucursal.setDireccion(sucursal.getDireccion());
        nuevaSucursal.setTelefono(sucursal.getTelefono());
        sucursalService.saveSucursal(nuevaSucursal);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaSucursal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sucursal> updateSucursal(@PathVariable Long id, @RequestBody SucursalDTO sucursal){
        try {
            Sucursal sucursalAActualizar = sucursalService.getSucursalById(id);
            sucursalAActualizar.setNombre((sucursal.getNombre() != null) ? sucursal.getNombre() : sucursalAActualizar.getNombre() );
            sucursalAActualizar.setDireccion((sucursal.getDireccion() != null) ? sucursal.getDireccion() : sucursalAActualizar.getDireccion() );
            sucursalAActualizar.setTelefono((sucursal.getTelefono() != null) ? sucursal.getTelefono() : sucursalAActualizar.getTelefono() );
            sucursalService.editSucursal(sucursalAActualizar);
            return ResponseEntity.ok(sucursalAActualizar);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSucursal(@PathVariable Long id){
        try{
            sucursalService.deleteSucursalById(id);
            return ResponseEntity.ok("Sucursal eliminada con exito");
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}