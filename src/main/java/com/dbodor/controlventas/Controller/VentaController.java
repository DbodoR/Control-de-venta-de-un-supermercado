package com.dbodor.controlventas.Controller;

import com.dbodor.controlventas.DTO.VentaDTO;
import com.dbodor.controlventas.Service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private IVentaService ventaService;

    @GetMapping
    public ResponseEntity<List<VentaDTO>> getVentas() {
        return ResponseEntity.ok(ventaService.getVenta());
    }

    @PostMapping
    public ResponseEntity<VentaDTO> createVentas(@RequestBody VentaDTO dto) {
        VentaDTO created = ventaService.createVenta(dto);
        return ResponseEntity.created(URI.create("/api/ventas/" + created.getId()))
                .body(created);
    }

    @PutMapping("/{id}")
    public VentaDTO updateVentas(@PathVariable Long id, @RequestBody VentaDTO dto) {

        return ventaService.updateVenta(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVentas(@PathVariable Long id) {
        ventaService.deleteVenta(id);
        return ResponseEntity.noContent().build();
    }


}
