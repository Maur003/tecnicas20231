package co.edu.usbcali.bancaweb.service;

import co.edu.usbcali.bancaweb.dto.ClienteDTO;
import co.edu.usbcali.bancaweb.mapper.ClienteMapper;
import co.edu.usbcali.bancaweb.model.Cliente;
import co.edu.usbcali.bancaweb.model.TipoDocumento;
import co.edu.usbcali.bancaweb.repository.ClienteRepository;
import co.edu.usbcali.bancaweb.repository.TipoDocumentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService{
    private final ClienteRepository clienteRepository;
    private final TipoDocumentoRepository tipoDocumentoRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository, TipoDocumentoRepository tipoDocumentoRepository) {
        this.clienteRepository = clienteRepository;
        this.tipoDocumentoRepository = tipoDocumentoRepository;
    }

    @Override
    public List<ClienteDTO> buscarTodos() {
        return ClienteMapper.modelToDtoList(clienteRepository.findAll());
    }

    @Override
    public ClienteDTO crearNuevoCliente(ClienteDTO clienteDTO) throws Exception {
        if(clienteDTO == null) {
            throw new Exception("El cliente no puede ser nulo");
        }
        //Aquí van el resto de las validaciones

        //Consulto el tipo de documento en la base de datos
        TipoDocumento tipoDocumento =
                tipoDocumentoRepository.getReferenceById(clienteDTO.getTipoDocumentoCodigo());

        // Mapeo el cliente hacia Domain/Modelo/Entity
        Cliente cliente = ClienteMapper.dtoToModel(clienteDTO);

        //Agrego el tipo de documento desde Base de Datos al cliente
        cliente.setTipoDocumento(tipoDocumento);
        return null;
    }
}
