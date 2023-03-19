package co.edu.usbcali.bancaweb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Consignacion {
    @Id
    @Column(name = "con_codigo", length = 10, nullable = false)
    private Integer codigo;

    @ManyToOne
    @JoinColumn(name = "cue_numero", referencedColumnName = "cue_numero")
    private Cuenta cuenta;

    @ManyToOne
    @JoinColumn(name = "usu_cedula", referencedColumnName = "usu_cedula")
    private Usuario usuario;

    @Column(name = "con_valor", length = 10, precision = 2, nullable = false)
    private BigDecimal valor;

    @Column(name = "con_fecha", nullable = false)
    private Timestamp fecha;

    @Column(name = "con_descripcion", length = 50, nullable = false)
    private String descripcion;
}
