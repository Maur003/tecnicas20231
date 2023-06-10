package co.edu.usbcali.tiendaapp.service.impl;

import co.edu.usbcali.tiendaapp.domain.TipoDocumento;
import co.edu.usbcali.tiendaapp.dto.TipoDocumentoDTO;
import co.edu.usbcali.tiendaapp.exceptions.TipoDocumentoException;
import co.edu.usbcali.tiendaapp.mapper.TipoDocumentoMapper;
import co.edu.usbcali.tiendaapp.repository.TipoDocumentoRepository;
import co.edu.usbcali.tiendaapp.service.TipoDocumentoService;
import co.edu.usbcali.tiendaapp.util.ValidationsUtil;
import co.edu.usbcali.tiendaapp.util.messages.TipoDocumentoServiceMessages;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoDocumentoServiceImpl implements TipoDocumentoService {

    private final TipoDocumentoRepository tipoDocumentoRepository;

    public TipoDocumentoServiceImpl(TipoDocumentoRepository tipoDocumentoRepository) {
        this.tipoDocumentoRepository = tipoDocumentoRepository;
    }

    @Override
    public List<TipoDocumentoDTO> obtenerTodos() {
        return TipoDocumentoMapper.domainToDtoList(tipoDocumentoRepository.findAll());
    }

    @Override
    public TipoDocumentoDTO buscarPorId(Integer id) throws Exception {
        ValidationsUtil.integerIsNullOrLessZero(id, TipoDocumentoServiceMessages.ID_VALIDO_MESSAGE);
        return tipoDocumentoRepository.findById(id).map(TipoDocumentoMapper::domainToDto).orElseThrow(
                () -> new TipoDocumentoException(
                        String.format(TipoDocumentoServiceMessages.TIPO_DOCUMENTO_NO_ENCONTRADO_POR_ID, id)));
    }

    @Override
    public TipoDocumento buscarTipoDocumentoPorId(Integer id) throws Exception{
        ValidationsUtil.integerIsNullOrLessZero(id, TipoDocumentoServiceMessages.ID_VALIDO_MESSAGE);
        return tipoDocumentoRepository.findById(id).orElseThrow(
                () -> new TipoDocumentoException(
                        String.format(TipoDocumentoServiceMessages.TIPO_DOCUMENTO_NO_ENCONTRADO_POR_ID, id)));
    }
}
