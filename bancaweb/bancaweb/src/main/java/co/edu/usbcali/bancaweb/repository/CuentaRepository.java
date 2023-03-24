package co.edu.usbcali.bancaweb.repository;

import co.edu.usbcali.bancaweb.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, String> {
}
