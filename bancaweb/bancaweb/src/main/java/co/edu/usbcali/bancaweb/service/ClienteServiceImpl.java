package co.edu.usbcali.bancaweb.service;

import co.edu.usbcali.bancaweb.dto.ClienteDTO;
import co.edu.usbcali.bancaweb.mapper.ClienteMapper;
import co.edu.usbcali.bancaweb.model.Cliente;
import co.edu.usbcali.bancaweb.model.TipoDocumento;
import co.edu.usbcali.bancaweb.repository.ClienteRepository;
import co.edu.usbcali.bancaweb.repository.TipoDocumentoRepository;
import co.edu.usbcali.bancaweb.utility.ConstantesUtility;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.PatternMatchUtils;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

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
        if (clienteDTO == null) throw new Exception("No han llegado los datos del cliente.");

        if (clienteDTO.getId() == null) throw new Exception("El id del cliente es obligatorio.");

        if (StringUtils.isBlank(clienteDTO.getMail()) ||
                !Pattern.matches(ConstantesUtility.PATTERN_MAIL_REGEX, clienteDTO.getMail())) {
            throw new Exception("El correo electrónico no es válido.");
        }

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

        if (clienteDTO.getTipoDocumentoCodigo() == null || clienteDTO.getTipoDocumentoCodigo() <= 0) {
            throw new Exception("El tipo de documento debe ser un número positivo.");
        }

        // Validar si el tipo de documento consultado no existe
        if (!tipoDocumentoRepository.existsById(clienteDTO.getTipoDocumentoCodigo())) {
            throw new Exception("El tipo de documento " + clienteDTO.getTipoDocumentoCodigo()
                    + " no se encuentra en base de datos");
        }

        if (StringUtils.isBlank(clienteDTO.getNombre())) throw new Exception("El nombre del cliente es obligatorio.");


        if (StringUtils.isBlank(clienteDTO.getDireccion()))
            throw new Exception("La dirección del cliente es obligatoria.");


        if (StringUtils.isBlank(clienteDTO.getTelefono()))
            throw new Exception("El teléfono del cliente es obligatorio.");


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

