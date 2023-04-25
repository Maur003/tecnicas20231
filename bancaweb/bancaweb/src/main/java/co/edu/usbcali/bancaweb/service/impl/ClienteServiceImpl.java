package co.edu.usbcali.bancaweb.service.impl;

import co.edu.usbcali.bancaweb.dto.ClienteDTO;
import co.edu.usbcali.bancaweb.mapper.ClienteMapper;
import co.edu.usbcali.bancaweb.model.Cliente;
import co.edu.usbcali.bancaweb.model.TipoDocumento;
import co.edu.usbcali.bancaweb.repository.ClienteRepository;
import co.edu.usbcali.bancaweb.repository.TipoDocumentoRepository;
import co.edu.usbcali.bancaweb.service.ClienteService;
import co.edu.usbcali.bancaweb.utility.ValidationUtility;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {
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
        validarClienteDTO(clienteDTO, true);
        return crearOModificarCliente(clienteDTO);
    }

    @Override
    public ClienteDTO modificarCliente(ClienteDTO clienteDTO) throws Exception {
        validarClienteDTO(clienteDTO, false);
        return crearOModificarCliente(clienteDTO);
    }

    @Override
    public ClienteDTO buscarPorId(Integer id) throws Exception {
        if (id == null || !clienteRepository.existsById(id)) {
            throw new Exception("No se ha encontrado el cliente con Id " + id + ".");
        }
        return ClienteMapper.modelToDTO(clienteRepository.getReferenceById(id));
    }

    private void validarClienteDTO(ClienteDTO clienteDTO, boolean esCreacion) throws Exception {
        //Validar clienteDTO
        ValidationUtility.isNull(clienteDTO, "No han llegado los datos del cliente.");

        //Validar id cliente
        ValidationUtility.integerIsNullOrZero(clienteDTO.getId(), "El id del cliente es obligatorio.");

        //Validar mail
        ValidationUtility.stringMailValidatePattern(clienteDTO.getMail(), "El correo electrónico no es válido.");

        //Validar si es creación o actualización
        if (esCreacion) {
            if(clienteRepository.existsById(clienteDTO.getId())) {
                throw new Exception("El cliente con Id " +
                        clienteDTO.getId() + " ya se encuentra registrado.");
            }
            if (clienteRepository.existsClienteByMail(clienteDTO.getMail())) {
                throw new Exception("El correo electrónico " + clienteDTO.getMail() + " ya está registrado para otro cliente.");
            }
        }
        if (!esCreacion) {
            if (!clienteRepository.existsById(clienteDTO.getId())) {
                throw new Exception("No se ha encontrado el cliente con Id " +
                        clienteDTO.getId() + ".");
            }
            if (clienteRepository.existsClienteByMailAndIdIsNot(clienteDTO.getMail(), clienteDTO.getId())) {
                throw new Exception("El correo electrónico " + clienteDTO.getMail() + " ya está registrado para otro cliente.");
            }
        }

        // Validar si el código del tipo de documento es null, menor o igual a cero
        ValidationUtility.integerIsNullOrLessZero(clienteDTO.getTipoDocumentoCodigo(), "El tipo de documento debe ser un número positivo.");

        // Validar si el tipo de documento consultado no existe
        if (!tipoDocumentoRepository.existsById(clienteDTO.getTipoDocumentoCodigo())) {
            throw new Exception("El tipo de documento " + clienteDTO.getTipoDocumentoCodigo()
                    + " no se encuentra en base de datos");
        }

        // Validar el nombre del cliente
        ValidationUtility.stringIsNullOrBlank(clienteDTO.getNombre(), "El nombre del cliente es obligatorio.");

        // Validar la dirección del cliente
        ValidationUtility.stringIsNullOrBlank(clienteDTO.getDireccion(), "La dirección del cliente es obligatoria.");

        // Validar el teléfono del cliente
        ValidationUtility.stringIsNullOrBlank(clienteDTO.getTelefono(), "El teléfono del cliente es obligatorio.");
    }

    private ClienteDTO crearOModificarCliente(ClienteDTO clienteDTO) {
        // Mapeo el cliente hacia Domain/Modelo/Entity
        Cliente cliente = ClienteMapper.dtoToModel(clienteDTO);

        //Consulto el tipo de documento en la base de datos
        TipoDocumento tipoDocumento =
                tipoDocumentoRepository.getReferenceById(clienteDTO.getTipoDocumentoCodigo());

        //Agrego el tipo de documento desde Base de Datos al cliente
        cliente.setTipoDocumento(tipoDocumento);

        return ClienteMapper.modelToDTO(clienteRepository.save(cliente));
    }
}

