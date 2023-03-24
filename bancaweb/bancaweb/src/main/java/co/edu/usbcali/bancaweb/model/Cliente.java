package co.edu.usbcali.bancaweb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "clientes")
public class Cliente {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cli_id", length = 10, nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tdoc_codigo", referencedColumnName = "tdoc_codigo")
    private TipoDocumento tipoDocumento;

    @Column(name = "cli_nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "cli_direccion", length = 50, nullable = false)
    private String direccion;

    @Column(name = "cli_telefono", length = 50, nullable = false)
    private String telefono;

    @Column(name = "cli_mail", length = 50)
    private String mail;

}
