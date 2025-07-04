package cl.duoc.ecoventas.controller;


import cl.duoc.ecoventas.DTO.SucursalDTO;
import cl.duoc.ecoventas.DTO.VentaSucursalesDTO;
import cl.duoc.ecoventas.DTO.VentaRequestDTO;
import cl.duoc.ecoventas.model.Venta;
import cl.duoc.ecoventas.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/ventas")
public class VentaController {

    private final VentaService ventaService;
    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public ResponseEntity<?> listarVentas() {
        List<Venta> ventas = ventaService.buscarTodaVenta();
        if (ventas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO SE ENCUENTRAN DATOS");
        } else {
            return ResponseEntity.ok(ventas);
        }
    }

    @GetMapping("/{idventa}")
    public ResponseEntity<?> buscarVenta(@PathVariable Long idventa) {
        try {
            Venta ventaBuscada = ventaService.buscarUnaVenta(idventa);
            return ResponseEntity.ok(ventaBuscada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO SE ENCUENTRAN VENTAS");
        }
    }

    @GetMapping("/VentaSucursal/{idVenta}")
    public ResponseEntity<?> buscarVentaSucursal(@PathVariable Long idVenta) {
        try {
            Venta ventaBuscada = ventaService.buscarUnaVenta(idVenta);
            SucursalDTO sucursalVenta = ventaService.buscarSucursal(ventaBuscada.getIdSucursal());
            VentaSucursalesDTO ventaSucursales = new VentaSucursalesDTO();
            ventaSucursales.setIdVenta(ventaBuscada.getIdVenta());
            ventaSucursales.setRutUsuario(ventaBuscada.getRutUsuario());
            ventaSucursales.setNombre(sucursalVenta.getNombre());
            ventaSucursales.setDireccion(sucursalVenta.getDireccion());
            return ResponseEntity.ok(ventaSucursales);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        }
    }

    @PostMapping
    public ResponseEntity<?> guardarVenta(@RequestBody VentaRequestDTO ventaDTO) {
        try {
            boolean existeSucursal = ventaService.validarSucursalExiste(ventaDTO.getIdSucursal());

            if (!existeSucursal) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sucursal no válida");
            }

            Venta nuevaVenta = new Venta();
            nuevaVenta.setRutUsuario(ventaDTO.getRutUsuario());
            nuevaVenta.setFechaVenta(ventaDTO.getFechaVenta());
            nuevaVenta.setTipoUsuario(ventaDTO.getTipoUsuario());
            nuevaVenta.setTipoPago(ventaDTO.getTipoPago());
            nuevaVenta.setIdSucursal(ventaDTO.getIdSucursal());

            Venta guardada = ventaService.guardarVenta(nuevaVenta);
            return ResponseEntity.ok(guardada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al guardar venta");
        }
    }

@DeleteMapping("/{idventa}")
    public ResponseEntity<?> eliminarVenta(@PathVariable Long idventa) {
        try{
            Venta ventaBuscada = ventaService.buscarUnaVenta(idventa);
            ventaService.eliminarVenta(idventa);
            return ResponseEntity.status(HttpStatus.OK).body("LA VENTA A SIDO ELIMINADA");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("VENTA NO ENCONTRADA");

        }


    }
    @PutMapping("/{idventa}")
    public ResponseEntity<?> actualizarVenta(@PathVariable Long idventa, @RequestBody Venta ventaActualizar) {
        try{
            Venta ventaActualizada = ventaService.buscarUnaVenta(idventa);
            ventaActualizada.setRutUsuario(ventaActualizar.getRutUsuario());
            ventaActualizada.setFechaVenta(ventaActualizar.getFechaVenta());
            ventaActualizada.setTipoUsuario(ventaActualizar.getTipoUsuario());
            ventaActualizada.setTipoPago(ventaActualizar.getTipoPago());
            ventaService.guardarVenta(ventaActualizada);
            return ResponseEntity.ok(ventaActualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ESA VENTA NO SE ENCUENTRA REGISTRADA");
        }

    }
}


