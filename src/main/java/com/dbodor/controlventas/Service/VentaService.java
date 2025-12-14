package com.dbodor.controlventas.Service;

import com.dbodor.controlventas.DTO.DetalleVentaDTO;
import com.dbodor.controlventas.DTO.VentaDTO;
import com.dbodor.controlventas.Exception.NotFoundException;
import com.dbodor.controlventas.Mapper.Mapper;
import com.dbodor.controlventas.Model.DetalleVenta;
import com.dbodor.controlventas.Model.Producto;
import com.dbodor.controlventas.Model.Sucursal;
import com.dbodor.controlventas.Model.Venta;
import com.dbodor.controlventas.Repository.IProductoRepository;
import com.dbodor.controlventas.Repository.ISucursalRepository;
import com.dbodor.controlventas.Repository.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService implements IVentaService{

    @Autowired
    private IVentaRepository ventaRepository;

    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    private ISucursalRepository sucursalRepository;

    @Override
    public List<VentaDTO> getVenta() {
        List<Venta> ventas = ventaRepository.findAll();
        List<VentaDTO> ventasDto = new ArrayList<>();
        VentaDTO vDto;
        for (Venta venta : ventas){
            vDto = Mapper.toDTO(venta);
            ventasDto.add(vDto);
        }

        return ventasDto;

    }

    @Override
    public VentaDTO createVenta(VentaDTO ventaDto) {

        //validaciones previas
        if (ventaDto == null) throw new RuntimeException("Venta es nula");
        if (ventaDto.getIdSucursal() == null) throw new RuntimeException("Debe indicar una sucursal");
        if (ventaDto.getDetalle() == null || ventaDto.getDetalle().isEmpty())
            throw new RuntimeException("La venta debe incluir al menos un producto");

        //Buscar sucursal asociada a la venta
        Sucursal sucursal = sucursalRepository.findById(ventaDto.getIdSucursal()).orElseThrow(null);
        if (sucursal == null) throw new NotFoundException("Sucursal no encontrada");

        //crear venta
        Venta venta = new Venta();
        venta.setFecha(ventaDto.getFecha());
        venta.setEstado(ventaDto.getEstado());
        venta.setSucursal(sucursal);
        venta.setTotal(ventaDto.getTotal());

        //lista de detalles
        List<DetalleVenta> detalles = new ArrayList<>();

        for (DetalleVentaDTO detDto : ventaDto.getDetalle()){
            Producto producto = productoRepository.findByNombre(detDto.getNombreProducto()).orElse(null);
            if (producto == null){
                throw new RuntimeException("Producto no encontrado: " + detDto.getNombreProducto());
            }
            DetalleVenta detallev = new DetalleVenta();
            detallev.setProducto(producto);
            detallev.setPrecio(detDto.getPrecio());
            detallev.setCantProducto(detDto.getCantProducto());
            detallev.setVenta(venta);

            detalles.add(detallev);
        }

        //guardamos el detalle creado
        venta.setDetalle(detalles);

        //guardamos en base de datos
        ventaRepository.save(venta);

        return Mapper.toDTO(venta);

    }

    @Override
    public VentaDTO updateVenta(Long id, VentaDTO ventaDto) {
        Venta venta = ventaRepository.findById(id).orElse(null);
        if (venta == null) throw new NotFoundException("Venta no encontrada");

        if (ventaDto.getFecha() != null)
            venta.setFecha(ventaDto.getFecha());

        if (ventaDto.getEstado() != null)
            venta.setEstado(ventaDto.getEstado());

        if (ventaDto.getIdSucursal() != null){
            Sucursal sucursal = sucursalRepository.findById(ventaDto.getIdSucursal()).orElse(null);
            if (sucursal == null) throw new NotFoundException("Sucursal no encontrada");

            venta.setSucursal(sucursal);
        }
        ventaRepository.save(venta);

        return Mapper.toDTO(venta);

    }

    @Override
    public void deleteVenta(Long id) {
        Venta venta = ventaRepository.findById(id).orElse(null);
        if (venta == null) throw new NotFoundException("Venta no encontrada");
        ventaRepository.delete(venta);
    }
}
