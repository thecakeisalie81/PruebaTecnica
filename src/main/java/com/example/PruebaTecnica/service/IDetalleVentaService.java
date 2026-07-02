package com.example.PruebaTecnica.service;

import com.example.PruebaTecnica.model.DetalleVenta;

import java.util.List;

public interface IDetalleVentaService {
    public List<DetalleVenta> getDetalleVentas();
    public DetalleVenta getDetalleVenta(Long id);
    public void deleteDetalleVenta(long id);
    public void saveDetalleVenta(DetalleVenta detalleVenta);
    public void editDetalleVenta(DetalleVenta detalleVenta);
}
