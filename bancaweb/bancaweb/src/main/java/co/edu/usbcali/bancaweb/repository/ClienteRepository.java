package co.edu.usbcali.bancaweb.repository;

import co.edu.usbcali.bancaweb.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    //Método para validar la existencia de un cliente por mail
    boolean existsClienteByMail(String mail);
    boolean existsClienteByMailAndIdIsNot(String mail, Integer id);
}
