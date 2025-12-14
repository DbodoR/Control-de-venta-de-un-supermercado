package com.dbodor.controlventas.Service;

import com.dbodor.controlventas.DTO.SucursalDTO;
import com.dbodor.controlventas.Mapper.Mapper;
import com.dbodor.controlventas.Model.Sucursal;
import com.dbodor.controlventas.Repository.ISucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalService implements ISucursalService{

    @Autowired
    private ISucursalRepository sucursalRepository;

    @Override
    public List<SucursalDTO> getSucursales() {
        return sucursalRepository.findAll()
                .stream()
                .map(Mapper::toDTO)
                .toList();
    }

    @Override
    public SucursalDTO createSucursal(SucursalDTO sucursalDto) {
        Sucursal suc = Sucursal.builder()
                .nombre(sucursalDto.getNombre())
                .direccion(sucursalDto.getDireccion())
                .build();

        return Mapper.toDTO(sucursalRepository.save(suc));
    }

    @Override
    public SucursalDTO updateSucursal(Long id, SucursalDTO sucursalDto) {
        Sucursal suc = sucursalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));

        suc.setNombre(sucursalDto.getNombre());
        suc.setDireccion(sucursalDto.getDireccion());

        return Mapper.toDTO(sucursalRepository.save(suc));
    }

    @Override
    public void deleteSucursal(Long id) {
        if (!sucursalRepository.existsById(id)) {
            throw new RuntimeException("Sucursal no encontrada");
        }
        sucursalRepository.deleteById(id);
    }
}
