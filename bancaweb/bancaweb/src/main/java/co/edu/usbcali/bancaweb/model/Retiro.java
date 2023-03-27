package co.edu.usbcali.bancaweb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "retiros")
public class Retiro {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ret_codigo", length = 10, nullable = false)
    private Integer codigo;

    @ManyToOne
    @JoinColumn(name = "cue_numero", referencedColumnName = "cue_numero")
    private Cuenta cuenta;

    @ManyToOne
    @JoinColumn(name = "usu_cedula", referencedColumnName = "usu_cedula")
    private Usuario usuario;

    @Column(name = "ret_valor", length = 10, precision = 2, nullable = false)
    private BigDecimal valor;

    @Column(name = "ret_fecha", nullable = false)
    private Timestamp fecha;

    @Column(name = "ret_descripcion", length = 50, nullable = false)
    private String descripcion;
}
