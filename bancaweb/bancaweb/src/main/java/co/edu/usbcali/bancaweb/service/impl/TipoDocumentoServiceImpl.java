package co.edu.usbcali.bancaweb.service.impl;

import co.edu.usbcali.bancaweb.dto.TipoDocumentoDTO;
import co.edu.usbcali.bancaweb.mapper.TipoDocumentoMapper;
import co.edu.usbcali.bancaweb.model.TipoDocumento;
import co.edu.usbcali.bancaweb.repository.TipoDocumentoRepository;
import co.edu.usbcali.bancaweb.service.TipoDocumentoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoDocumentoServiceImpl implements TipoDocumentoService {
    private final TipoDocumentoRepository tipoDocumentoRepository;

    public TipoDocumentoServiceImpl(TipoDocumentoRepository tipoDocumentoRepository) {
        this.tipoDocumentoRepository = tipoDocumentoRepository;
    }

    @Override
    public List<TipoDocumentoDTO> buscarTodos() {
        List<TipoDocumento> tiposDocumentos =
                tipoDocumentoRepository.findAll();
        return TipoDocumentoMapper.modelToDtoList(tiposDocumentos);
    }

    @Override
    public TipoDocumentoDTO buscarTipoDocumentoPorCodigo(Integer codigo) throws Exception {
        if (codigo == null || codigo.equals(0)) {
            throw new Exception("El código debe ser numérico mayor a cero");
        }
        Optional<TipoDocumento> tipoDocumentoOptional = tipoDocumentoRepository.findById(codigo);
        if(tipoDocumentoOptional.isPresent()) {
            return TipoDocumentoMapper.modelToDto(tipoDocumentoOptional.get());
        }
        return TipoDocumentoDTO.builder().build();
    }


}
