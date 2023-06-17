package co.edu.usbcali.tiendaapp.service;

import co.edu.usbcali.tiendaapp.domain.Categoria;
import co.edu.usbcali.tiendaapp.dto.CategoriaDTO;

import java.util.List;

public interface CategoriaService {
    List<CategoriaDTO> obtenerTodos();

    CategoriaDTO buscarPorId(Integer id) throws Exception;

    Categoria buscarCategoriaPorId(Integer id) throws Exception;

    CategoriaDTO guardar(CategoriaDTO categoriaDTO) throws Exception;

    CategoriaDTO actualizar(CategoriaDTO categoriaDTO) throws Exception;

    void eliminar(Integer id) throws Exception;
}
