package com.example.PruebaTecnica.controller;


import com.example.PruebaTecnica.dto.ProductoDTO;
import com.example.PruebaTecnica.model.Producto;
import com.example.PruebaTecnica.service.IProductoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Validated
public class ProductoController {

    private final IProductoService productoService;

    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<Producto> getProductos() {
        return productoService.getProductos();
    }

    @PostMapping
    public ResponseEntity<Producto> saveProducto(@RequestBody ProductoDTO producto) {
        Producto newProducto = new Producto();
        newProducto.setNombre(producto.getNombre());
        newProducto.setCategoria(producto.getCategoria());
        newProducto.setPrecio(producto.getPrecio());
        Producto productoGuardado = productoService.saveProducto(newProducto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@Positive @PathVariable Long id, @Valid @RequestBody ProductoDTO producto) {
        try{
            Producto productoActualizado = productoService.getProductoById(id);
            productoActualizado.setNombre((producto.getNombre() != null) ? producto.getNombre() : productoActualizado.getNombre() );
            productoActualizado.setCategoria((producto.getCategoria() != null) ? producto.getCategoria() : productoActualizado.getCategoria() );
            productoActualizado.setPrecio((producto.getPrecio() != null) ? producto.getPrecio() : productoActualizado.getPrecio() );
            productoService.editProducto(productoActualizado);
            return ResponseEntity.ok(productoActualizado);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@Positive @PathVariable Long id) {
        try{
            productoService.deleteProductoById(id);
            return ResponseEntity.ok().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
