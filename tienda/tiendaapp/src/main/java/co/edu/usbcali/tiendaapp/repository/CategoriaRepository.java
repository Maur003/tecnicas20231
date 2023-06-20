package co.edu.usbcali.tiendaapp.repository;

import co.edu.usbcali.tiendaapp.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    boolean existsByNombreIgnoreCase(String nombre);

    boolean existsByNombreIgnoreCaseAndIdNot(String nombre, Integer id);

    List<Categoria> findByNombreLikeIgnoreCase(String nombre);
    
    List<Categoria> findAllByOrderByNombreAsc();

}
