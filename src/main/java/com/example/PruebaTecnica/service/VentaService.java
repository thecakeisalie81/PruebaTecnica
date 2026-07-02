package com.example.PruebaTecnica.service;

import com.example.PruebaTecnica.model.Venta;
import com.example.PruebaTecnica.repository.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private IVentaRepository ventaRepository;


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
        if (ventaRepository.existsById(id)) {
            ventaRepository.deleteById(id);
        }else {
            throw new RuntimeException("No se encontro el venta con el id: " + id);
        }
    }
}
