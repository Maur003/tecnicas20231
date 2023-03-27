package co.edu.usbcali.bancaweb.mapper;

import co.edu.usbcali.bancaweb.dto.UsuarioDTO;
import co.edu.usbcali.bancaweb.model.Usuario;

import java.util.List;

public class UsuarioMapper {

    public static UsuarioDTO modelToDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .cedula(usuario.getCedula())
                .tipoUsuarioCodigo((usuario.getTipoUsuario() != null)?
                        usuario.getTipoUsuario().getCodigo() : null)
                .nombre(usuario.getNombre())
                .login(usuario.getLogin())
                .clave(usuario.getClave())
                .build();
    }

    public static Usuario dtoToModel(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .cedula(usuarioDTO.getCedula())
                .nombre(usuarioDTO.getNombre())
                .login(usuarioDTO.getLogin())
                .clave(usuarioDTO.getClave())
                .build();
    }

    public static List<UsuarioDTO> modelToDtoList(List<Usuario> usuarios) {
        return usuarios.stream().map(UsuarioMapper::modelToDTO).toList();
    }

    public static List<Usuario> dtoToModelList(List<UsuarioDTO> usuariosDTO) {
        return usuariosDTO.stream().map(UsuarioMapper::dtoToModel).toList();
    }
}
