package cl.duoc.sucursal.controller;

import cl.duoc.sucursal.model.Sucursal;
import cl.duoc.sucursal.service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/s1/sucursal")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    public ResponseEntity<?> listarSucursal() {
        List<Sucursal> sucursal = sucursalService.buscarSucursales();
        if (sucursal.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no se encuentra esa sucursal");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(sucursal);
        }
    }

    @GetMapping("/{idSucursal}")
    public ResponseEntity<?> buscarSucursalPorId(@PathVariable Long idSucursal) {
        try{
            Sucursal sucursal = sucursalService.buscarSucursal(idSucursal);
            return ResponseEntity.status(HttpStatus.OK).body(sucursal);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no se encuentra esa sucursal");
        }

    }
    @PostMapping
    public ResponseEntity<?> guardarSucursal(@RequestBody Sucursal sucursalGuardar) {
        try{
            Sucursal sucursalRegistrar = sucursalService.guardarSucursal(sucursalGuardar);
            return ResponseEntity.ok(sucursalRegistrar);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("no se encuentra esa sucursal");
        }
    }

    @DeleteMapping("/{idSucursal}")
    public ResponseEntity<?> eliminarSucursal(@PathVariable Long idSucursal) {
        try{
            Sucursal sucursalBuscada = sucursalService.buscarSucursal(idSucursal);
            sucursalService.eliminarSucursal(idSucursal);
            return ResponseEntity.ok(sucursalBuscada);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no se encuentra esa sucursal");
        }
    }

    @PutMapping("/{idSucursal}")
    public ResponseEntity<?> actualizarSucursal(@PathVariable Long idSucursal, @RequestBody Sucursal sucursalActualizar) {
        try{
            Sucursal sucursalActualizada = sucursalService.buscarSucursal(idSucursal);
            sucursalActualizada.setNombre(sucursalActualizar.getNombre());
            sucursalActualizada.setDireccion( sucursalActualizar.getDireccion());
            sucursalActualizada.setTelefono(sucursalActualizar.getTelefono());
            sucursalActualizada.setEmail(sucursalActualizar.getEmail());
            sucursalActualizada.setCiudad(sucursalActualizar.getCiudad());
            sucursalActualizada.setEncargado(sucursalActualizar.getEncargado());
            sucursalService.guardarSucursal(sucursalActualizada);
            return ResponseEntity.ok(sucursalActualizada);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no se encuentra esa sucursal");
        }

    }

}
