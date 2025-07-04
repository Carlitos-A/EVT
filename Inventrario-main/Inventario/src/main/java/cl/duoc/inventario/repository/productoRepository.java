package cl.duoc.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.duoc.inventario.model.Productos;

public interface productoRepository extends JpaRepository<Productos,Long> {
}
