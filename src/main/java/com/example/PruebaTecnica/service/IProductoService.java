package com.example.PruebaTecnica.service;

import com.example.PruebaTecnica.model.Producto;

import java.util.List;

public interface IProductoService {
    public Producto getProductoById(Long id);
    public List<Producto> getProductos();
    public void deleteProductoById(Long id);
    public Producto saveProducto(Producto producto);
    public Producto editProducto(Producto producto);
    public Producto findProductoByNombre(String nombre);
}
