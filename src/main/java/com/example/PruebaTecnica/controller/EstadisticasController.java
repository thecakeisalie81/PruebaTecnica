package com.example.PruebaTecnica.controller;

import com.example.PruebaTecnica.model.DetalleVenta;
import com.example.PruebaTecnica.model.Producto;
import com.example.PruebaTecnica.service.IDetalleVentaService;
import com.example.PruebaTecnica.service.IProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
public class EstadisticasController {

    private final IProductoService productoService;
    private final IDetalleVentaService detalleVentaService;

    public EstadisticasController(IProductoService productoService, IDetalleVentaService detalleVentaService) {
        this.productoService = productoService;
        this.detalleVentaService = detalleVentaService;
    }

    @GetMapping("/api/estadisticas/producto-mas-vendido")
    public ResponseEntity<Producto> getProductos() {
        List<DetalleVenta> detalleVenta = detalleVentaService.getDetalleVentas();

        Map<Long, Integer> cantidadVendidos = detalleVenta.stream().parallel().
                collect(Collectors.groupingBy(v -> v.getProducto().getId(),
                        Collectors.summingInt(DetalleVenta::getCantidad)));

        Map.Entry<Long, Integer> masVendido = cantidadVendidos.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue)).get();

        return ResponseEntity.ok(productoService.getProductoById(masVendido.getKey()));
    }
}
