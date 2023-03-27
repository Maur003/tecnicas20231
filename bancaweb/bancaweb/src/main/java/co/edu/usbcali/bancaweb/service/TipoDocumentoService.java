package co.edu.usbcali.bancaweb.service;

import co.edu.usbcali.bancaweb.dto.TipoDocumentoDTO;

import java.util.List;

public interface TipoDocumentoService {
    List<TipoDocumentoDTO> buscarTodos();

    TipoDocumentoDTO buscarTipoDocumentoPorCodigo(Integer codigo) throws Exception;
}
