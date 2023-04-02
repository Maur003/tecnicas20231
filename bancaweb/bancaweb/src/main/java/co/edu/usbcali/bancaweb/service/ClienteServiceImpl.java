package co.edu.usbcali.bancaweb.service;

import co.edu.usbcali.bancaweb.dto.ClienteDTO;
import co.edu.usbcali.bancaweb.mapper.ClienteMapper;
import co.edu.usbcali.bancaweb.model.Cliente;
import co.edu.usbcali.bancaweb.model.TipoDocumento;
import co.edu.usbcali.bancaweb.repository.ClienteRepository;
import co.edu.usbcali.bancaweb.repository.TipoDocumentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
        if (Objects.isNull(clienteDTO.getTipoDocumentoCodigo())) {
            throw new Exception("El tipo de documento del cliente no puede ser nulo");
        }

        // Validar si el tipo de documento consultado no existe
        if (!tipoDocumentoRepository.existsById(clienteDTO.getTipoDocumentoCodigo())) {
            throw new Exception("El tipo de documento "+clienteDTO.getTipoDocumentoCodigo()
                    +" no se encuentra en base de datos");
        }
        //Consulto el tipo de documento en la base de datos
        TipoDocumento tipoDocumento =
                tipoDocumentoRepository.getReferenceById(clienteDTO.getTipoDocumentoCodigo());


        // Mapeo el cliente hacia Domain/Modelo/Entity
        Cliente cliente = ClienteMapper.dtoToModel(clienteDTO);

        //Agrego el tipo de documento desde Base de Datos al cliente
        cliente.setTipoDocumento(tipoDocumento);

        //Guardar el cliente en base de datos
        cliente = clienteRepository.save(cliente);

        return ClienteMapper.modelToDTO(cliente);
    }
}
