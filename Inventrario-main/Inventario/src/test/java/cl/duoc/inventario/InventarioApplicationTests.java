package cl.duoc.inventario.service;

import cl.duoc.inventario.model.Productos;
import cl.duoc.inventario.repository.productoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class InventarioApplicationTests {
    @Mock
    private productoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);


    }
    @Test
    public void pruebaBuscarProducto() {
        List<Productos> lista = new ArrayList<>();

        Productos producto1 = new Productos();

        producto1.setIdProducto(10L);
        producto1.setNombre("Fideos");
        producto1.setMarca("Carozzi");
        producto1.setPrecioVenta(5000);

        lista.add(producto1);

        when(productoRepository.findAll()).thenReturn(lista);

        List<Productos>resultadoBusqueda = productoService.buscarProductos();
        assertEquals(1, resultadoBusqueda.size());
        verify(productoRepository,times(1)).findAll();
    }
    @Test
    public void pruebaBuscarUnProducto() {
        Productos producto1 = new Productos();
        producto1.setIdProducto(10L);
        producto1.setNombre("Fideos");
        producto1.setMarca("Carozzi");
        producto1.setPrecioVenta(5000);


        when(productoRepository.findById(10L)).thenReturn(Optional.of(producto1));

        Productos productoBuscado = productoService.buscarUnProducto(10L);
        assertEquals(10L, productoBuscado.getIdProducto());
        verify(productoRepository,times(1)).findById(10L);

    }

    @Test
    public void pruebaGuardarProducto() {
        Productos producto1 = new Productos();
        producto1.setIdProducto(25L);
        producto1.setNombre("Arroz");
        producto1.setMarca("Basmati");
        producto1.setPrecioVenta(6500);

        when(productoRepository.save(producto1)).thenReturn(producto1);
        Productos productoGuardado = productoService.guardarProducto(producto1);
        assertEquals(25L, productoGuardado.getIdProducto());
        verify(productoRepository,times(1)).save(producto1);

    }

    @Test
    public void pruebaEliminarProducto() {
        String idSucursal = "10";
        doNothing().when(productoRepository).deleteById(10L);
        productoService.eliminarProducto(10L);

        verify(productoRepository,times(1)).deleteById(10L);

    }

    @Test
    public void pruebaEditarProducto(){

        Productos producto1 = new Productos();
        producto1.setNombre("Fideos");
        producto1.setMarca("Carozzi");
        producto1.setPrecioVenta(5000);

        Productos productoEditado = new Productos();
        productoEditado.setIdProducto(10L);
        productoEditado.setNombre("Chocapic");
        productoEditado.setPrecioVenta(6200);

        when(productoRepository.save(any(Productos.class))).thenReturn(productoEditado);
        when(productoRepository.existsById(10L)).thenReturn(true);
        Productos resultado = productoService.guardarProducto(productoEditado);

        assertNotNull(resultado);
        assertEquals(10L, resultado.getIdProducto());
        assertEquals("Chocapic", resultado.getNombre());
        assertEquals(6200, resultado.getPrecioVenta());

        verify(productoRepository, times(1)).save(productoEditado);
    }
}
