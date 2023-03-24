package co.edu.usbcali.bancaweb.dto;

import co.edu.usbcali.bancaweb.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
@ToString
public class CuentaDTO {
    private String numero;
    private Integer clienteId;
    private BigDecimal saldo;
    private String activa;
    private String clave;
}
