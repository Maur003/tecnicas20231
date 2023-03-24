package co.edu.usbcali.aerolinea.mappers;

import co.edu.usbcali.aerolinea.domain.TipoAsiento;
import co.edu.usbcali.aerolinea.dtos.TipoAsientoDTO;

import java.util.List;
import java.util.stream.Collectors;

public class TipoAsientoMapper {

    public static TipoAsientoDTO domainToDTO(TipoAsiento tipoAsiento) {
        return TipoAsientoDTO.builder()
                .tiasId(tipoAsiento.getTiasId())
                .descripcion(tipoAsiento.getDescripcion())
                .estado(tipoAsiento.getEstado())
                .build();
    }

    public static List<TipoAsientoDTO> domainToDTOList(List<TipoAsiento> tiposAsientos) {
        return tiposAsientos.stream().map(ta -> domainToDTO(ta)).collect(Collectors.toList());
    }

}
