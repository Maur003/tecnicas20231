package co.edu.usbcali.aerolinea.domain;

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
@Table(name = "tipo_asiento")
public class TipoAsiento {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tias_id", nullable = false)
    private Integer tiasId;

    @Column(nullable = false, length = 30)
    private String descripcion;

    @Column(nullable = false, length = 1)
    private String estado;
}
