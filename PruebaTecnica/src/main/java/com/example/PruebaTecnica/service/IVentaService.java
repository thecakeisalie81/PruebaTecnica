package com.example.PruebaTecnica.service;

import com.example.PruebaTecnica.model.Venta;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IVentaService {
    public List<Venta> getVentas();
    public Venta getVenta(Long id);
    public void editVenta(Venta venta);
    public void saveVenta(Venta venta);
    public void deleteVenta(Long id);
    public List<Venta> getVentasSucursalYFecha(Long idSucursal, LocalDate fecha);
}
