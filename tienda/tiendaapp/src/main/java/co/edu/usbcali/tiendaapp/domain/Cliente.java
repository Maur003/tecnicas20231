package co.edu.usbcali.tiendaapp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String nombres;

    @Column(nullable = false, length = 50)
    private String apellidos;

    @Column(nullable = false, length = 50)
    private String documento;

    @Column(nullable = false, length = 1)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "tido_id", referencedColumnName = "id", nullable = false)
    private TipoDocumento tipoDocumento;
}
