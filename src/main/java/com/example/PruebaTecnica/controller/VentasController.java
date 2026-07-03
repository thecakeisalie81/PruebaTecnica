package com.example.PruebaTecnica.controller;

import com.example.PruebaTecnica.dto.DetalleVentaDTO;
import com.example.PruebaTecnica.dto.VentaDTO;
import com.example.PruebaTecnica.model.DetalleVenta;
import com.example.PruebaTecnica.model.Sucursal;
import com.example.PruebaTecnica.model.Venta;
import com.example.PruebaTecnica.service.IProductoService;
import com.example.PruebaTecnica.service.ISucursalService;
import com.example.PruebaTecnica.service.IVentaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/api/ventas")
@RestController
public class VentasController {

    private final IVentaService ventaService;
    private final ISucursalService sucursalService;
    private final IProductoService productoService;

    public VentasController(IVentaService ventaService, ISucursalService sucursalService, IProductoService productoService) {
        this.ventaService = ventaService;
        this.sucursalService = sucursalService;
        this.productoService = productoService;
    }


    @PostMapping
    public ResponseEntity<Map<String, Object>> saveVenta(@RequestBody VentaDTO venta) {

        Sucursal sucursal = sucursalService.getSucursalById(venta.getSucursalId());

        Venta nuevaVenta = new Venta();
        List<DetalleVenta> detalles = new ArrayList<>();

        nuevaVenta.setEstado(venta.getEstadoVenta());
        nuevaVenta.setFecha(venta.getFecha());

        for (DetalleVentaDTO dto : venta.getDetalles()) {
            DetalleVenta detalle = new DetalleVenta();
            detalle.setPrecioUnitario(dto.getPrecioUnitario());
            detalle.setCantidad(dto.getCantidad());
            detalle.setProducto(productoService.findProductoByNombre(dto.getNombreProducto()));
            detalle.setVenta(nuevaVenta);
            detalles.add(detalle);
        }

        nuevaVenta.setDetalleVentas(detalles);

        sucursal.addVenta(nuevaVenta);

        ventaService.saveVenta(nuevaVenta);

        Map<String, Object> map = new HashMap<>();
        map.put("sucursalId", sucursal.getId());
        map.put("Detalle",  nuevaVenta.getDetalleVentas());

        return ResponseEntity.ok(map);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVenta(@PathVariable Long id) {
        try {
            ventaService.deleteVenta(id);
            return ResponseEntity.ok("Se elimino la venta con exito");
        }catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body("Error al eliminar el venta");
        }
    }

}
