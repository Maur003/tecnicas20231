package co.edu.usbcali.tiendaapp.service;

import co.edu.usbcali.tiendaapp.dto.ClienteDTO;

import java.util.List;

public interface ClienteService {
    List<ClienteDTO> obtenerTodos();
    ClienteDTO buscarPorId(Integer id);
    ClienteDTO guardar(ClienteDTO clienteDTO);
    ClienteDTO actualizar(ClienteDTO clienteDTO);

}
