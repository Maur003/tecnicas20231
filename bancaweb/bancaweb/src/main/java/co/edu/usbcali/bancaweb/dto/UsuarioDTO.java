package co.edu.usbcali.bancaweb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class UsuarioDTO {

    private Integer cedula;

    private Integer tipoUsuarioCodigo;

    private String nombre;

    private String login;

    private String clave;

}
