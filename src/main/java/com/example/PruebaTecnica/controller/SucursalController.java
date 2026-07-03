package com.example.PruebaTecnica.controller;

import com.example.PruebaTecnica.model.Sucursal;
import com.example.PruebaTecnica.service.ISucursalService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
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
    public ResponseEntity<Sucursal> saveSucursal(@RequestBody Sucursal sucursal){
        Sucursal nuevaSucursal = sucursalService.saveSucursal(sucursal);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaSucursal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sucursal> updateSucursal(@PathVariable Long id, @RequestBody Sucursal sucursal){
        Sucursal sucursalAActualizar = sucursalService.getSucursalById(id);
        if(sucursalAActualizar == null){
            return ResponseEntity.notFound().build();
        }else {
            sucursalAActualizar.setNombre((sucursal.getNombre() != null) ? sucursal.getNombre() : sucursalAActualizar.getNombre() );
            sucursalAActualizar.setDireccion((sucursal.getDireccion() != null) ? sucursal.getDireccion() : sucursalAActualizar.getDireccion() );
            sucursalAActualizar.setVentas((sucursal.getVentas() != null) ? sucursal.getVentas() : sucursalAActualizar.getVentas() );
            sucursalAActualizar.setTelefono((sucursal.getTelefono() != null) ? sucursal.getTelefono() : sucursalAActualizar.getTelefono() );
            sucursalService.editSucursal(sucursalAActualizar);
            return ResponseEntity.ok(sucursalAActualizar);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSucursal(@PathVariable Long id){
        try{
            sucursalService.deleteSucursalById(id);
            return ResponseEntity.ok("Sucursal eliminada con exito");
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sucursal no encontrado");
        }
    }

}