package co.edu.usbcali.tiendaapp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "pedidos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@ManyToOne
    @JoinColumn(name = "cli_id")
	private Cliente cliente;
	
	@OneToOne
    @JoinColumn(name = "espe_id")
    private EstadoPedido estadoPedido;
	
	@Column(nullable=false)
	private Instant fecha;
	
	@Column(nullable=false)
	private BigDecimal total;
}
