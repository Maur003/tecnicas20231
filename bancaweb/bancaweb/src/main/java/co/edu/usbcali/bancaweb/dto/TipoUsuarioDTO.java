package co.edu.usbcali.bancaweb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class TipoUsuarioDTO {
    private Integer codigo;
    private String nombre;
}
