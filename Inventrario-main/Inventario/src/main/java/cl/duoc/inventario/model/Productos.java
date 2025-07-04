package cl.duoc.inventario.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "PRODUCTOS")
@AllArgsConstructor
@NoArgsConstructor
public class Productos {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idProducto")
        private Long idProducto;

        @Column(name = "NOMBRE", nullable = false)
        private String nombre;

        @Column(name = "DESCRIPCION", nullable = false)
        private String descripcion;

        @Column(name = "CATEGORIA",nullable = false)
        private String categoria;

        @Column(name = "MARCA",nullable = false)
        private String marca;

        @Column(name = "PRECIO_VENTA", nullable = false)
        private Integer precioVenta;

        @Column(name = "STOCK_ACTUAL")
        private Integer stockActual;

        @Column(name = "UNIDAD_MEDIDA")
        private String unidadMedida;
    }

