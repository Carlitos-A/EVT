package cl.duoc.sucursal.service;

import cl.duoc.sucursal.model.Sucursal;
import cl.duoc.sucursal.repository.SucursalRepository;
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

public class SucursalApplicationTests {
    @Mock
    private SucursalRepository sucursalRepository;

    @InjectMocks
    private SucursalService sucursalService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);


    }
    @Test
    public void pruebaBuscarsucursales() {
        List<Sucursal> lista = new ArrayList<>();

        Sucursal sucursal1 = new Sucursal();
        Sucursal sucursal2 = new Sucursal();

        sucursal1.setIdSucursal(20L);
        sucursal1.setNombre("Puente alto");
        sucursal1.setDireccion("avenida miguel 482");
        sucursal1.setCiudad("Santiago");
        sucursal1.setTelefono("1234567890");
        sucursal1.setEmail("SucursalPuente@gmail.com");

        sucursal2.setIdSucursal(15L);
        sucursal2.setNombre("San joaquin");
        sucursal2.setDireccion("Santa rosa 552");
        sucursal2.setCiudad("Santiago");
        sucursal2.setTelefono("422895223");
        sucursal2.setEmail("SucursalSanJoaquin@gmail.com");
        lista.add(sucursal1);
        lista.add(sucursal2);

        when(sucursalRepository.findAll()).thenReturn(lista);

        List<Sucursal>resultadoBusqueda = sucursalService.buscarSucursales();
        assertEquals(2, resultadoBusqueda.size());
        verify(sucursalRepository,times(1)).findAll();
    }
    @Test
    public void pruebaBuscarSucursal() {
        Sucursal sucursal1 = new Sucursal();
        sucursal1.setIdSucursal(20L);
        sucursal1.setNombre("Puente alto");
        sucursal1.setDireccion("avenida miguel 482");
        sucursal1.setCiudad("Santiago");
        sucursal1.setTelefono("1234567890");
        sucursal1.setEmail("SucursalPuente@gmail.com");


        when(sucursalRepository.findById(20L)).thenReturn(Optional.of(sucursal1));

        Sucursal sucursalBuscada = sucursalService.buscarSucursal(20L);
        assertEquals(20L, sucursalBuscada.getIdSucursal());
        verify(sucursalRepository,times(1)).findById(20L);

    }

    @Test
    public void pruebaGuardarSucursal() {
         Sucursal sucursal1 = new Sucursal();
        sucursal1.setIdSucursal(30L);
        sucursal1.setNombre("Providencia");
        sucursal1.setDireccion("Manutara 392");
        sucursal1.setCiudad("Santiago");
        sucursal1.setTelefono("5968554615");
        sucursal1.setEmail("SucursalProvidencia@gmail.com");

        when(sucursalRepository.save(sucursal1)).thenReturn(sucursal1);
        Sucursal sucursalGuardada = sucursalService.guardarSucursal(sucursal1);
        assertEquals(30L, sucursalGuardada.getIdSucursal());
        verify(sucursalRepository,times(1)).save(sucursal1);

    }

    @Test
    public void pruebaEliminarSucursal() {
        String idSucursal = "20";
        doNothing().when(sucursalRepository).deleteById(20L);
        sucursalService.eliminarSucursal(20L);

        verify(sucursalRepository,times(1)).deleteById(20L);

    }

    @Test
    public void pruebaEditarUsuario(){

        Sucursal sucursal1 = new Sucursal();
        sucursal1.setIdSucursal(20L);
        sucursal1.setNombre("Puente alto");
        sucursal1.setDireccion("avenida miguel 482");

        Sucursal sucursalEditada = new Sucursal();
        sucursalEditada.setIdSucursal(11L);
        sucursalEditada.setNombre("cal y canto");
        sucursalEditada.setDireccion("mapocho 332");

        when(sucursalRepository.save(any(Sucursal.class))).thenReturn(sucursalEditada);
        when(sucursalRepository.existsById(11L)).thenReturn(true);
        Sucursal resultado = sucursalService.guardarSucursal(sucursalEditada);

        assertNotNull(resultado);
        assertEquals(11L, resultado.getIdSucursal());
        assertEquals("cal y canto", resultado.getNombre());
        assertEquals("mapocho 332", resultado.getDireccion());

        verify(sucursalRepository, times(1)).save(sucursalEditada);
    }
    }


