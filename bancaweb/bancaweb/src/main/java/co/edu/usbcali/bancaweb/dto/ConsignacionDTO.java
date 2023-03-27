package co.edu.usbcali.bancaweb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@Builder
@ToString
public class ConsignacionDTO {
    private Integer codigo;
    private String cuentaNumero;
    private Integer usuarioCedula;
    private BigDecimal valor;
    private Timestamp fecha;
    private String descripcion;
}

