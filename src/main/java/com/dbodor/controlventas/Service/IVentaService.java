package com.dbodor.controlventas.Service;

import com.dbodor.controlventas.DTO.VentaDTO;

import java.util.List;

public interface IVentaService {

    List<VentaDTO> GetVenta();
    VentaDTO createVenta(VentaDTO ventaDto);
    VentaDTO updateVenta(Long id, VentaDTO ventaDto);
    void deleteVenta(Long id);
}
