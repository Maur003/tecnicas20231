package co.edu.usbcali.tiendaapp.service;

import co.edu.usbcali.tiendaapp.domain.TipoDocumento;
import co.edu.usbcali.tiendaapp.dto.TipoDocumentoDTO;

import java.util.List;

public interface TipoDocumentoService {
    List<TipoDocumentoDTO> obtenerTodos();
    TipoDocumentoDTO buscarPorId(Integer id) throws Exception;
    TipoDocumento buscarTipoDocumentoPorId(Integer id) throws Exception;
}
