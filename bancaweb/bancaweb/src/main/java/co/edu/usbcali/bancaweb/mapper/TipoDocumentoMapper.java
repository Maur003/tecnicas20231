package co.edu.usbcali.bancaweb.mapper;

import co.edu.usbcali.bancaweb.dto.TipoDocumentoDTO;
import co.edu.usbcali.bancaweb.model.TipoDocumento;

import java.util.List;
import java.util.stream.Collectors;

public class TipoDocumentoMapper {
    public static TipoDocumentoDTO modelToDto(TipoDocumento tipoDocumento) {
        return TipoDocumentoDTO.builder()
                .codigo(tipoDocumento.getCodigo())
                .nombre(tipoDocumento.getNombre())
                .build();
    }

    public static TipoDocumento dtoToModel(TipoDocumentoDTO tipoDocumentoDTO) {
        return TipoDocumento.builder()
                .codigo(tipoDocumentoDTO.getCodigo())
                .nombre(tipoDocumentoDTO.getNombre())
                .build();
    }

    public static List<TipoDocumentoDTO> modelToDtoList(List<TipoDocumento> tiposDocumentos) {
        return tiposDocumentos.stream().map(td -> modelToDto(td)).collect(Collectors.toList());
    }

    public static List<TipoDocumento> dtoToModelList(List<TipoDocumentoDTO> tiposDocumentosDTO) {
        return tiposDocumentosDTO.stream().map(td -> dtoToModel(td)).collect(Collectors.toList());
    }

}
