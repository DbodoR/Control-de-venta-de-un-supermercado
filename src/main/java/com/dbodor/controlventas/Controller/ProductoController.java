package com.dbodor.controlventas.Controller;

import com.dbodor.controlventas.DTO.ProductoDTO;
import com.dbodor.controlventas.Service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> getProductos(){

        return ResponseEntity.ok(productoService.getProductos());
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> createProducto (@RequestBody ProductoDTO pDto){
        ProductoDTO productoCreado = productoService.createProducto(pDto);

        return ResponseEntity.created(URI.create("/api/productos" + productoCreado.getId())).body(productoCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> updateProducto (@PathVariable Long id, @RequestBody ProductoDTO pDto){

        return ResponseEntity.ok(productoService.updateProducto(id, pDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto (@PathVariable Long id){
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }

}
