package co.edu.usbcali.bancaweb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class ClienteDTO {
    private Integer id;
    private Integer tipoDocumentoCodigo;
    private String tipoDocumentoNombre;
    private String nombre;
    private String direccion;
    private String telefono;
    private String mail;
}

