package co.edu.usbcali.tiendaapp.repository;

import co.edu.usbcali.tiendaapp.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
