package com.example.PruebaTecnica.controller;


import com.example.PruebaTecnica.model.Producto;
import com.example.PruebaTecnica.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final IProductoService productoService;

    //Reemplazo mas moderno del autowired
    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<Producto> getProductos() {
        return productoService.getProductos();
    }

    @PostMapping
    public ResponseEntity<Producto> saveProducto(@RequestBody Producto producto) {
        Producto productoGuardado = productoService.saveProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id,  @RequestBody Producto producto) {

        Producto productoActualizado = productoService.getProductoById(id);
        if (productoActualizado == null) {
            return ResponseEntity.notFound().build();
        }else {
            productoActualizado.setNombre((producto.getNombre() != null) ? producto.getNombre() : productoActualizado.getNombre() );
            productoActualizado.setCategoria((producto.getCategoria() != null) ? producto.getCategoria() : productoActualizado.getCategoria() );
            productoActualizado.setPrecio((producto.getPrecio() != null) ? producto.getPrecio() : productoActualizado.getPrecio() );
            productoService.editProducto(productoActualizado);
            return ResponseEntity.ok(productoActualizado);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Producto> deleteProducto(@PathVariable Long id) {
        Producto productoABorrar = productoService.getProductoById(id);
        if (productoABorrar != null) {
            productoService.deleteProductoById(id);
            return ResponseEntity.ok(productoABorrar);
        }else  {
            return ResponseEntity.notFound().build();
        }
    }
}
