package co.edu.usbcali.tiendaapp.request;

import co.edu.usbcali.tiendaapp.util.messages.ProductoServiceMessages;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrearProductoRequest {

    @NotBlank(message = ProductoServiceMessages.REFERENCIA_REQUERIDA)
    private String referencia;

    @NotBlank(message = ProductoServiceMessages.NOMBRE_REQUERIDO)
    private String nombre;
    private String descripcion;

    @NotNull(message = ProductoServiceMessages.PRECIO_UNITARIO)
    @Min(value = 1, message = ProductoServiceMessages.PRECIO_UNITARIO_NUMERO)
    private BigDecimal precioUnitario;

    @NotNull(message = ProductoServiceMessages.UNIDADES_DISPONIBLES_REQUERIDO)
    @Min(value = 0, message = ProductoServiceMessages.UNIDADES_DISPONIBLES_NUMERO)
    private BigDecimal unidadesDisponibles;

    @NotNull(message = ProductoServiceMessages.CATEGORIA_REQUERIDA)
    private Integer categoriaId;
}
