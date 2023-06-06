package co.edu.usbcali.tiendaapp.repository;

import co.edu.usbcali.tiendaapp.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
