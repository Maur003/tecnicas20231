package co.edu.usbcali.tiendaapp.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrearClienteResponse {
    private Integer id;
    private String nombres;
    private String apellidos;
    private String documento;
    private String estado;
    private String tipoDocumentoDescripcion;
}
