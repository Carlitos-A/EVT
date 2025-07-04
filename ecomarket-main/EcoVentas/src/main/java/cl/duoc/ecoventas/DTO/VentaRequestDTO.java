package cl.duoc.ecoventas.DTO;

import lombok.Data;
import java.time.LocalDate;

@Data
public class VentaRequestDTO {
    private String rutUsuario;
    private LocalDate fechaVenta;
    private String tipoUsuario;
    private String tipoPago;
    private Long idSucursal;
}
