package co.edu.usbcali.tiendaapp.mapper;

import co.edu.usbcali.tiendaapp.domain.TipoDocumento;
import co.edu.usbcali.tiendaapp.dto.TipoDocumentoDTO;

import java.util.List;

public class TipoDocumentoMapper {

    public static TipoDocumentoDTO domainToDto(TipoDocumento tipoDocumento) {
        return TipoDocumentoDTO.builder()
                .id(tipoDocumento.getId())
                .descripcion(tipoDocumento.getDescripcion())
                .build();
    }

    public static TipoDocumento dtoToDomain(TipoDocumentoDTO tipoDocumentoDTO) {
        return TipoDocumento.builder()
                .id(tipoDocumentoDTO.getId())
                .descripcion(tipoDocumentoDTO.getDescripcion())
                .build();
    }

    public static List<TipoDocumentoDTO> domainToDtoList(List<TipoDocumento> tiposDocumento) {
        return tiposDocumento.stream().map(TipoDocumentoMapper::domainToDto).toList();
    }

    public static List<TipoDocumento> dtoToDomainList(List<TipoDocumentoDTO> tiposDocumentoDtos) {
        return tiposDocumentoDtos.stream().map(TipoDocumentoMapper::dtoToDomain).toList();
    }

}
