package co.edu.usbcali.aerolinea.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class TipoAsientoDTO {
    private Integer tiasId;
    private String descripcion;
    private String estado;
}
