package co.edu.usbcali.tiendaapp.repository;

import co.edu.usbcali.tiendaapp.domain.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {
}
