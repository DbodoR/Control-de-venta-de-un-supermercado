package com.dbodor.controlventas.Service;

import com.dbodor.controlventas.DTO.VentaDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService implements IVentaService{
    @Override
    public List<VentaDTO> GetVenta() {
        return List.of();
    }

    @Override
    public VentaDTO createVenta(VentaDTO ventaDto) {
        return null;
    }

    @Override
    public VentaDTO updateVenta(Long id, VentaDTO ventaDto) {
        return null;
    }

    @Override
    public void deleteVenta(Long id) {

    }
}
