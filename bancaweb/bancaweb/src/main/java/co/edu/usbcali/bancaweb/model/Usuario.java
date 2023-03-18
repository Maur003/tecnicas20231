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
@Table(name = "usuarios")
public class Usuario {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "usu_cedula", length = 10, nullable = false)
    private Integer cedula;

    @ManyToOne
    @JoinColumn(name = "tusu_codigo", referencedColumnName = "tusu_codigo")
    private TipoUsuario tipoUsuario;

    @Column(name = "usu_nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "usu_login", length = 30, nullable = false)
    private String login;

    @Column(name = "usu_clave", length = 50, nullable = false)
    private String clave;

}
