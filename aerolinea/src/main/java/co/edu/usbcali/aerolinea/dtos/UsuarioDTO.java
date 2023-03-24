package co.edu.usbcali.aerolinea.dtos;

import co.edu.usbcali.aerolinea.domain.RolUsuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class UsuarioDTO {
    private Integer usuaId;
    private Integer rolUsuario_rousId;
    private String cedula;
    private String nombre;
    private String apellido;
    private String correo;
    private String estado;
}
