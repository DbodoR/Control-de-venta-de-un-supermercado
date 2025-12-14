package com.dbodor.controlventas.Repository;

import com.dbodor.controlventas.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IProductoRepository extends JpaRepository<Producto, Long> {
    Optional<Producto> findByNombre(String nombre);
}
