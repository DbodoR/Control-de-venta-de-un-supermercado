package com.dbodor.controlventas.Service;

import com.dbodor.controlventas.DTO.SucursalDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalService implements ISucursalService{
    @Override
    public List<SucursalDTO> GetSucursales() {
        return List.of();
    }

    @Override
    public SucursalDTO createSucursal(SucursalDTO sucursalDto) {
        return null;
    }

    @Override
    public SucursalDTO updateSucursal(Long id, SucursalDTO sucursalDto) {
        return null;
    }

    @Override
    public void deleteSucursal(Long id) {

    }
}
