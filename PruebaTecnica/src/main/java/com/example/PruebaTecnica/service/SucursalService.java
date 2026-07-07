package com.example.PruebaTecnica.service;

import com.example.PruebaTecnica.model.Sucursal;
import com.example.PruebaTecnica.repository.ISucursalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalService implements ISucursalService {

    @Autowired
    private ISucursalRepository sucursalRepository;

    @Override
    public Sucursal getSucursalById(Long id) {
        return sucursalRepository.findById(id).orElseThrow(()-> new RuntimeException("Sucursal no encontrado"));
    }

    @Override
    public List<Sucursal> getSucursales() {
        return sucursalRepository.findAll();
    }

    @Override
    public void deleteSucursalById(Long id) {
        if(sucursalRepository.existsById(id)){
            sucursalRepository.deleteById(id);
        }
        else{
            throw new EntityNotFoundException("Sucursal no encontrado");
        }
    }

    @Override
    public Sucursal saveSucursal(Sucursal sucursal) {
        sucursalRepository.save(sucursal);
        return sucursal;
    }

    @Override
    public void editSucursal(Sucursal sucursal) {
        this.saveSucursal(sucursal);
    }
}
