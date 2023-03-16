package co.edu.usbcali.bancaweb.service;

import co.edu.usbcali.bancaweb.dto.TipoDocumentoDTO;
import co.edu.usbcali.bancaweb.mapper.TipoDocumentoMapper;
import co.edu.usbcali.bancaweb.model.TipoDocumento;
import co.edu.usbcali.bancaweb.repository.TipoDocumentoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public TipoDocumentoDTO guardarNuevoTipoDocumento(TipoDocumentoDTO tipoDocumentoDTO) throws Exception {
        //Validar DTO
        if (tipoDocumentoDTO == null) {
            throw new Exception("El tipo de documento no puede ser nulo");
        }
        if (tipoDocumentoDTO.getCodigo() == null ||
                tipoDocumentoDTO.getCodigo().equals(0)) {
            throw new Exception("Código no puede ser nulo o 0");
        }

        if(tipoDocumentoRepository.findById(tipoDocumentoDTO.getCodigo()).isPresent()) {
            throw new Exception("El tipo de documento con código "
                    +tipoDocumentoDTO.getCodigo()+" ya existe");
        }

        if (tipoDocumentoDTO.getNombre() == null ||
                tipoDocumentoDTO.getNombre().trim().isEmpty()) {
            throw new Exception("Nombre no puede ser nulo o vacío");
        }
        //Convertir DTO a Modelo
        TipoDocumento tipoDocumento = TipoDocumentoMapper.dtoToModel(tipoDocumentoDTO);
        return TipoDocumentoMapper.modelToDto(tipoDocumentoRepository.save(tipoDocumento));
    }
}
