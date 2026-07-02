package com.example.PruebaTecnica.service;

import com.example.PruebaTecnica.model.Producto;
import com.example.PruebaTecnica.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private IProductoRepository productoRepository;


    @Override
    public Producto getProductoById(Long id) {
        return productoRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe el producto con el id: " + id));
    }

    @Override
    public List<Producto> getProductos() {
        return productoRepository.findAll();
    }

    @Override
    public void deleteProductoById(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
        }else {
            throw new RuntimeException("No existe el producto con el id: " + id);
        }
    }

    @Override
    public void saveProducto(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public void editProducto(Producto producto) {
        this.saveProducto(producto);
    }
}
