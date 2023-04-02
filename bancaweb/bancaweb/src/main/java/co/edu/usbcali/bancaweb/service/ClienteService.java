package co.edu.usbcali.bancaweb.service;

import co.edu.usbcali.bancaweb.dto.ClienteDTO;

import java.util.List;

public interface ClienteService {
    List<ClienteDTO> buscarTodos() ;

    ClienteDTO crearNuevoCliente(ClienteDTO clienteDTO) throws Exception;

    ClienteDTO modificarCliente(ClienteDTO clienteDTO) throws Exception;

    ClienteDTO buscarPorId(Integer id) throws Exception;

}
