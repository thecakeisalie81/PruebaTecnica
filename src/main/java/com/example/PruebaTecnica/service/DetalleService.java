package com.example.PruebaTecnica.service;

import com.example.PruebaTecnica.model.DetalleVenta;
import com.example.PruebaTecnica.repository.IDetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleService implements IDetalleVentaService{

    @Autowired
    private IDetalleVentaRepository detalleVentaRepository;

    @Override
    public List<DetalleVenta> getDetalleVentas() {
        return detalleVentaRepository.findAll();
    }

    @Override
    public DetalleVenta getDetalleVenta(Long id) {
        return detalleVentaRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No existe el detalle de venta con el id: "+id));
    }

    @Override
    public void deleteDetalleVenta(long id) {
        if(detalleVentaRepository.existsById(id)){
            detalleVentaRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("No existe el detalle de venta con el id: "+id);
        }
    }

    @Override
    public void saveDetalleVenta(DetalleVenta detalleVenta) {
        detalleVentaRepository.save(detalleVenta);
    }

    @Override
    public void editDetalleVenta(DetalleVenta detalleVenta) {
        this.saveDetalleVenta(detalleVenta);
    }
}
