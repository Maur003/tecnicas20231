package co.edu.usbcali.aerolinea.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "trayecto")
public class Trayecto {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tray_id", nullable = false)
    private Integer trayId;

    @ManyToOne
    @JoinColumn(name = "avio_id", referencedColumnName = "avio_id")
    private Avion avion;

    @ManyToOne
    @JoinColumn(name = "aero_id_origen", referencedColumnName = "aero_id")
    private Aeropuerto aeropuertoOrigen;

    @ManyToOne
    @JoinColumn(name = "aero_id_destino", referencedColumnName = "aero_id")
    private Aeropuerto aeropuertoDestino;

    @ManyToOne
    @JoinColumn(name = "vuel_id", referencedColumnName = "vuel_id")
    private Vuelo vuelo;

    @Column(name = "hora_salida")
    private Date horaSalida;

    @Column(name = "hora_llegada")
    private Date horaLlegada;

    @Column(name = "estado", nullable = false, length = 1)
    private String estado;


}
