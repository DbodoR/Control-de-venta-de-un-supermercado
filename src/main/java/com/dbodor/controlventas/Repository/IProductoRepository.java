package com.dbodor.controlventas.Repository;

import com.dbodor.controlventas.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoRepository extends JpaRepository<Producto, Long> {

}
