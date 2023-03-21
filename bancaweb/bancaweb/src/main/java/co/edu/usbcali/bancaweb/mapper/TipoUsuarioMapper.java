package co.edu.usbcali.bancaweb.mapper;

import co.edu.usbcali.bancaweb.dto.TipoUsuarioDTO;
import co.edu.usbcali.bancaweb.model.TipoUsuario;

import java.util.List;
import java.util.stream.Collectors;

public class TipoUsuarioMapper {
    public static TipoUsuarioDTO modelToDto(TipoUsuario tipoUsuario) {
        return TipoUsuarioDTO.builder()
                .codigo(tipoUsuario.getCodigo())
                .nombre(tipoUsuario.getNombre())
                .build();
    }

    public static TipoUsuario dtoToModel(TipoUsuarioDTO tipoUsuarioDTO) {
        return TipoUsuario.builder()
                .codigo(tipoUsuarioDTO.getCodigo())
                .nombre(tipoUsuarioDTO.getNombre())
                .build();
    }

    public static List<TipoUsuarioDTO> modelToDtoList(List<TipoUsuario> tiposUsuarios) {
        return tiposUsuarios.stream().map(tu -> modelToDto(tu)).collect(Collectors.toList());
    }

    public static List<TipoUsuario> dtoToModelList(List<TipoUsuarioDTO> tiposUsuariosDTO) {
        return tiposUsuariosDTO.stream().map(tu -> dtoToModel(tu)).collect(Collectors.toList());
    }

}
