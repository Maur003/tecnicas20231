package co.edu.usbcali.bancaweb.mapper;

import co.edu.usbcali.bancaweb.dto.ClienteDTO;
import co.edu.usbcali.bancaweb.model.Cliente;

import java.util.List;

public class ClienteMapper {

    public static ClienteDTO modelToDTO(Cliente cliente) {
        return ClienteDTO.builder()
                .id(cliente.getId())
                .tipoDocumentoCodigo((cliente.getTipoDocumento() != null) ?
                        cliente.getTipoDocumento().getCodigo() : null)
                .tipoDocumentoNombre((cliente.getTipoDocumento() != null) ?
                        cliente.getTipoDocumento().getNombre() : null)
                .nombre(cliente.getNombre())
                .direccion(cliente.getDireccion())
                .telefono(cliente.getTelefono())
                .mail(cliente.getMail())
                .build();
    }

    public static Cliente dtoToModel(ClienteDTO clienteDTO) {
        return Cliente.builder()
                .id(clienteDTO.getId())
                .nombre(clienteDTO.getNombre())
                .direccion(clienteDTO.getDireccion())
                .telefono(clienteDTO.getTelefono())
                .mail(clienteDTO.getMail())
                .build();
    }

    public static List<ClienteDTO> modelToDtoList(List<Cliente> clientes) {
        return clientes.stream().map(ClienteMapper::modelToDTO).toList();
    }

    public static List<Cliente> dtoToModelList(List<ClienteDTO> clientesDTO) {
        return clientesDTO.stream().map(ClienteMapper::dtoToModel).toList();
    }
}
