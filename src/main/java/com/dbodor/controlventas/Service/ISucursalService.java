package com.dbodor.controlventas.Service;

import com.dbodor.controlventas.DTO.SucursalDTO;
import com.dbodor.controlventas.Model.Sucursal;

import java.util.List;

public interface ISucursalService {

    List<SucursalDTO> getSucursales();
    SucursalDTO createSucursal(SucursalDTO sucursalDto);
    SucursalDTO updateSucursal(Long id, SucursalDTO sucursalDto);
    void deleteSucursal(Long id);
}
