package cl.duoc.inventario.service;

import cl.duoc.inventario.model.Productos;
import cl.duoc.inventario.repository.productoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@Transactional

public class ProductoService {


    private productoRepository productoRepository;

    public ProductoService(productoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Productos> buscarProductos(){
        return productoRepository.findAll();
    }
    public Productos buscarUnProducto(Long idProducto){
        return productoRepository.findById(idProducto).get();
    }

    public Productos guardarProducto(Productos producto){
        return productoRepository.save(producto);
    }
    public void eliminarProducto(Long idProducto){
        productoRepository.deleteById(idProducto);
    }
}
