package com.dbodor.controlventas.Repository;

import com.dbodor.controlventas.Model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVentaRepository extends JpaRepository<Venta, Long> {

}
