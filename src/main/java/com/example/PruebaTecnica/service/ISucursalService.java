package com.example.PruebaTecnica.service;

import com.example.PruebaTecnica.model.Sucursal;

import java.util.List;

public interface ISucursalService {
    public Sucursal getSucursalById(Long id);
    public List<Sucursal> getSucursales();
    public void deleteSucursalById(Long id);
    public void saveSucursal(Sucursal sucursal);
    public void editSucursal(Sucursal sucursal);
}
