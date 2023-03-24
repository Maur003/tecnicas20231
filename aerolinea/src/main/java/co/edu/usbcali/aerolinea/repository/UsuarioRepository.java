package co.edu.usbcali.aerolinea.repository;

import co.edu.usbcali.aerolinea.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
