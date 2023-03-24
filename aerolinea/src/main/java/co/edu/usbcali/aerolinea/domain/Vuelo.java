package co.edu.usbcali.aerolinea.domain;

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
@Table(name = "vuelo")
public class Vuelo {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vuel_id", nullable = false)
    private Integer vuelId;

    @ManyToOne
    @JoinColumn(name = "aero_id_origen", referencedColumnName = "aero_id")
    private Aeropuerto aeropuertoOrigen;

    @ManyToOne
    @JoinColumn(name = "aero_id_destino", referencedColumnName = "aero_id")
    private Aeropuerto aeropuertoDestino;

    @Column(name = "precio", length = 10, precision = 2)
    private BigDecimal precio;

    @Column(name = "hora_salida")
    private Timestamp horaSalida;

    @Column(name = "hora_llegada")
    private Timestamp horaLlegada;

    @Column(name = "precio_asiento_vip", length = 10, precision = 2)
    private BigDecimal precioAsientoVip;

    @Column(name = "precio_asiento_normal", length = 10, precision = 2)
    private BigDecimal precioAsientoNormal;

    @Column(name = "precio_asiento_basico", length = 10, precision = 2)
    private BigDecimal precioAsientoBasico;

    @Column(name = "estado", nullable = false, length = 1)
    private String estado;
}
