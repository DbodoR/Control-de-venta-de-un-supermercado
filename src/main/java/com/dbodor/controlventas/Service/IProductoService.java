package com.dbodor.controlventas.Service;

import com.dbodor.controlventas.DTO.ProductoDTO;

import java.util.List;

public interface IProductoService {

    List<ProductoDTO> getProductos();
    ProductoDTO createProducto(ProductoDTO productoDto);
    ProductoDTO updateProducto(Long id, ProductoDTO productoDto);
    void deleteProducto(Long id);
}
