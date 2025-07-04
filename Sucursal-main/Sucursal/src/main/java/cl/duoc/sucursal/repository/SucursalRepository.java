package cl.duoc.sucursal.repository;

import cl.duoc.sucursal.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
}
