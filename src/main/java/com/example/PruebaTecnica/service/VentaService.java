package com.example.PruebaTecnica.service;

import com.example.PruebaTecnica.model.Sucursal;
import com.example.PruebaTecnica.model.Venta;
import com.example.PruebaTecnica.repository.ISucursalRepository;
import com.example.PruebaTecnica.repository.IVentaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class VentaService implements IVentaService {

    private final IVentaRepository ventaRepository;
    private final ISucursalRepository sucursalRepository;

    public VentaService(IVentaRepository ventaRepository, ISucursalRepository sucursalRepository) {
        this.ventaRepository = ventaRepository;
        this.sucursalRepository =sucursalRepository;

    }


    @Override
    public List<Venta> getVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta getVenta(Long id) {
        return ventaRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontro la venta con el id: " + id));
    }

    @Override
    public void editVenta(Venta venta) {
        this.ventaRepository.save(venta);
    }

    @Override
    public void saveVenta(Venta venta) {
        ventaRepository.save(venta);
    }

    @Override
    public void deleteVenta(Long id) {
        Venta venta = getVenta(id);
        if (venta != null) {
            venta.setBorradoLogico(true);
        }else {
            throw new EntityNotFoundException("No se encontro el venta con el id: " + id);
        }
    }

    @Override
    public List<Venta> getVentasSucursalYFecha(Long idSucursal, LocalDate fecha) {
        boolean sucursal = sucursalRepository.existsById(idSucursal);
        if (sucursal) {
            return ventaRepository.findBySucursalAndFecha(idSucursal, fecha);
        }else {
            throw new EntityNotFoundException("No se encontro la sucursal con el id: " + idSucursal);
        }
    }
}
