package com.dbodor.controlventas.Controller;

import com.dbodor.controlventas.DTO.SucursalDTO;
import com.dbodor.controlventas.Service.ISucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api/sucursales")
public class SucursalController {

    @Autowired
    private ISucursalService sucursalService;

    @GetMapping
    public ResponseEntity<List<SucursalDTO>> getSucursales() {
        return ResponseEntity.ok(sucursalService.getSucursales());
    }

    @PostMapping
    public ResponseEntity<SucursalDTO> createSucursales(@RequestBody SucursalDTO sDto) {
        SucursalDTO sucursalCreada = sucursalService.createSucursal(sDto);
        return ResponseEntity.created(URI.create("/api/sucursales/" + sucursalCreada.getId())).body(sucursalCreada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SucursalDTO> updateSucursales(@PathVariable Long id, @RequestBody SucursalDTO sDto) {
        return ResponseEntity.ok(sucursalService.updateSucursal(id,sDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSucursales(@PathVariable Long id) {
        sucursalService.deleteSucursal(id);
        return ResponseEntity.noContent().build();
    }

}
