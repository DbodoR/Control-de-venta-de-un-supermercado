package com.dbodor.controlventas.Service;

import com.dbodor.controlventas.DTO.ProductoDTO;
import com.dbodor.controlventas.Exception.NotFoundException;
import com.dbodor.controlventas.Mapper.Mapper;
import com.dbodor.controlventas.Model.Producto;
import com.dbodor.controlventas.Repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private IProductoRepository productoRepository;

    @Override
    public List<ProductoDTO> getProductos() {
        return productoRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public ProductoDTO createProducto(ProductoDTO productoDto) {
        Producto prod = Producto.builder()
                .nombre(productoDto.getNombre())
                .categoria(productoDto.getCategoria())
                .precio(productoDto.getPrecio())
                .cantidad(productoDto.getCantidad())
                .build();

        return Mapper.toDTO(productoRepository.save(prod));
    }

    @Override
    public ProductoDTO updateProducto(Long id, ProductoDTO productoDto) {

        Producto producto = productoRepository.findById(id).orElseThrow(() -> new NotFoundException("Producto no encontrado"));

        producto.setNombre(productoDto.getNombre());
        producto.setCategoria(productoDto.getCategoria());
        producto.setPrecio(productoDto.getPrecio());
        producto.setCantidad(productoDto.getCantidad());

        return Mapper.toDTO(productoRepository.save(producto));
    }

    @Override
    public void deleteProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new NotFoundException("Producto no encontrado para eliminar");
        }
        productoRepository.deleteById(id);
    }
}
