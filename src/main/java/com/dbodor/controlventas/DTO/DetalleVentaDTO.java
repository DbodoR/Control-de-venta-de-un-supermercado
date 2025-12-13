package com.dbodor.controlventas.DTO;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class DetalleVentaDTO {
    private Long id;
    private String nombreProducto;
    private Integer cantProducto;
    private Double precio;
    private Double subtotal;
}
