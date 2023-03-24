package co.edu.usbcali.bancaweb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cuentas")
public class Cuenta {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cue_numero", length = 30, nullable = false)
    private String numero;

    @ManyToOne
    @JoinColumn(name = "cli_id", referencedColumnName = "cli_id")
    private Cliente cliente;

    @Column(name = "cue_saldo", length = 10, precision = 2, nullable = false)
    private BigDecimal saldo;

    @Column(name = "cue_activa", length = 2, nullable = false)
    private String activa;

    @Column(name = "cue_clave", length = 50, nullable = false)
    private String clave;
}
