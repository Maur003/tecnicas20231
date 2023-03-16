package co.edu.usbcali.bancaweb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tipos_documentos")
public class TipoDocumento {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "tdoc_codigo", length = 10, nullable = false)
    private Integer codigo;

    @Column(name = "tdoc_nombre", length = 50, nullable = false)
    private String nombre;
}
