package co.edu.usbcali.aerolinea.mappers;

import co.edu.usbcali.aerolinea.domain.Usuario;
import co.edu.usbcali.aerolinea.dtos.UsuarioDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    public static UsuarioDTO domainToDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .usuaId(usuario.getUsuaId())
                .rolUsuario_rousId( ( usuario.getRolUsuario() != null )
                        ? usuario.getRolUsuario().getRousId() : null )
                .cedula(usuario.getCedula())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .correo(usuario.getCorreo())
                .estado(usuario.getEstado())
                .build();
    }

    public static Usuario dtoToDomain(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .usuaId(usuarioDTO.getUsuaId())
                .cedula(usuarioDTO.getCedula())
                .nombre(usuarioDTO.getNombre())
                .apellido(usuarioDTO.getApellido())

                .correo(usuarioDTO.getCorreo())
                .estado(usuarioDTO.getEstado())
                .build();
    }

    public static List<UsuarioDTO> domainToDtoList(List<Usuario> usuarios) {
        return usuarios.stream().map(u -> domainToDTO(u)).collect(Collectors.toList());
    }

    public static List<Usuario> dtoToDomainList(List<UsuarioDTO> usuariosDTO) {
        return usuariosDTO.stream().map(u -> dtoToDomain(u)).collect(Collectors.toList());
    }



}
