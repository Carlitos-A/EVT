package cl.duoc.inventario.controller;


import cl.duoc.inventario.model.Productos;
import cl.duoc.inventario.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/p1/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<?> ListarProductos(){
        List<Productos> productos = productoService.buscarProductos();
        if(productos.isEmpty()){
            return ResponseEntity.noContent().build();

        }else{
            return ResponseEntity.ok(productos);
        }
    }

    @GetMapping("/{idProducto}")
    public ResponseEntity<?> BuscarProducto(@PathVariable Long idProducto){
        try{
            Productos productoBuscado = productoService.buscarUnProducto(idProducto);
            return ResponseEntity.ok(productoBuscado);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el producto");
        }
    }

    @PostMapping
    public ResponseEntity<?> GuardarProducto(@RequestBody Productos productoGuardar){
        try{
            Productos productoRegistrar = productoService.guardarProducto(productoGuardar);
            return ResponseEntity.ok(productoRegistrar);

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al guardar el producto");
        }
    }

    @DeleteMapping("/{idProducto}")
    public ResponseEntity<?> EliminarProducto(@PathVariable Long idProducto){
        try{
            Productos productoBuscado = productoService.buscarUnProducto(idProducto);
            productoService.eliminarProducto(idProducto);
            return ResponseEntity.status(HttpStatus.OK).body("la venta ha sido eliminada");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PORDUCTO NO ENCONTRADO");
        }
    }

    @PutMapping("/{idProducto}")
    public ResponseEntity<?> ActualizarProducto(@PathVariable Long idProducto, @RequestBody Productos productoActualizar){
        try{
            Productos productoActualizado = productoService.buscarUnProducto(idProducto);
            productoActualizado.setNombre(productoActualizar.getNombre());
            productoActualizado.setDescripcion(productoActualizar.getDescripcion());
            productoActualizado.setCategoria(productoActualizar.getCategoria());
            productoActualizado.setMarca(productoActualizar.getMarca());
            productoActualizado.setPrecioVenta(productoActualizar.getPrecioVenta());
            productoActualizado.setStockActual(productoActualizar.getStockActual());
            productoActualizado.setUnidadMedida(productoActualizar.getUnidadMedida());
            productoService.guardarProducto(productoActualizado);
            return ResponseEntity.ok(productoActualizado);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al actualizar el producto");
        }
    }





}
