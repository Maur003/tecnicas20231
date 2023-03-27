package co.edu.usbcali.bancaweb.repository;

import co.edu.usbcali.bancaweb.model.Consignacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsignacionRepository extends JpaRepository<Consignacion, Integer> {
}
