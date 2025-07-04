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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TestSucursalService {
    @Mock
    private SucursalRepository sucursalRepository;

    @InjectMocks
    private SucursalService sucursalService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);


    }
    @Test
    public void buscarSucursal() {
        List<Sucursal> lista = new ArrayList<>();

        Sucursal sucursal1 = new Sucursal();
        Sucursal sucursal2 = new Sucursal();

        sucursal1.setIdSucursal(20L);
        sucursal1.setNombre("Puente alto");
        sucursal1.setDireccion("avenida miguel 482");
        sucursal1.setCiudad("Santiago");
        sucursal1.setTelefono("1234567890");
        sucursal1.setEmail("SucursalPuente@gmail.com");
        sucursal1.setEncargado("Miguel Perez");

        sucursal2.setIdSucursal(15L);
        sucursal2.setNombre("San joaquin");
        sucursal2.setDireccion("Santa rosa 552");
        sucursal2.setCiudad("Santiago");
        sucursal2.setTelefono("422895223");
        sucursal2.setEmail("SucursalSanJoaquin@gmail.com");
        sucursal2.setEncargado("Pedro Fuentes");
        lista.add(sucursal1);
        lista.add(sucursal2);

        when(sucursalRepository.findAll()).thenReturn(lista);

        List<Sucursal>resultadoBusqueda = sucursalService.buscarSucursales();
        assertEquals(2, resultadoBusqueda.size());
        verify(sucursalRepository,times(1)).findAll();
    }


}
