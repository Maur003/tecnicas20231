package co.edu.usbcali.tiendaapp.repository;

import co.edu.usbcali.tiendaapp.domain.EstadoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoPedidoRepository extends JpaRepository<EstadoPedido, Integer> {
}
