package co.edu.usbcali.bancaweb.repository;

import co.edu.usbcali.bancaweb.model.Retiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetiroRepository extends JpaRepository<Retiro, Integer> {
}
