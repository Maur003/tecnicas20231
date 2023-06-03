package co.edu.usbcali.tiendaapp.repository;

import co.edu.usbcali.tiendaapp.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
