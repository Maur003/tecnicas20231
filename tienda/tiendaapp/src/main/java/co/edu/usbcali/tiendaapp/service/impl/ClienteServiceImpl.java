package co.edu.usbcali.tiendaapp.service.impl;

import co.edu.usbcali.tiendaapp.domain.Cliente;
import co.edu.usbcali.tiendaapp.domain.TipoDocumento;
import co.edu.usbcali.tiendaapp.dto.ClienteDTO;
import co.edu.usbcali.tiendaapp.exceptions.ClienteException;
import co.edu.usbcali.tiendaapp.mapper.ClienteMapper;
import co.edu.usbcali.tiendaapp.repository.ClienteRepository;
import co.edu.usbcali.tiendaapp.request.CrearClienteRequest;
import co.edu.usbcali.tiendaapp.response.CrearClienteResponse;
import co.edu.usbcali.tiendaapp.service.ClienteService;
import co.edu.usbcali.tiendaapp.service.TipoDocumentoService;
import co.edu.usbcali.tiendaapp.util.ValidationsUtil;
import co.edu.usbcali.tiendaapp.util.messages.ClienteServiceMessages;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;
    private final TipoDocumentoService tipoDocumentoService;

    public ClienteServiceImpl(
            ClienteRepository clienteRepository,
            TipoDocumentoService tipoDocumentoService) {
        this.clienteRepository = clienteRepository;
        this.tipoDocumentoService = tipoDocumentoService;
    }

    @Override
    public List<ClienteDTO> obtenerTodos() {
        return ClienteMapper.domainToDtoList(clienteRepository.findAll());

        /*
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteDTO> clienteDTOS = ClienteMapper.domainToDtoList(clientes);
        return clienteDTOS;
        */
    }

    @Override
    public ClienteDTO buscarPorId(Integer id) throws Exception{
        ValidationsUtil.integerIsNullOrLessZero(id, ClienteServiceMessages.ID_VALIDO_MSG);

        return clienteRepository.findById(id).map(ClienteMapper::domainToDto).orElseThrow(
                () -> new ClienteException(String.format(ClienteServiceMessages.CLIENTE_NO_ENCONTRADO_POR_ID, id)));

        /*
        Cliente cliente = clienteRepository.getReferenceById(id);
        if(cliente == null){
            throw new Exception("No se ha encontrado el cliente con Id "+id);
        }

        ClienteDTO clienteDTO = ClienteMapper.domainToDto(cliente);

        return clienteDTO;
        * */
    }

    @Override
    public ClienteDTO guardar(ClienteDTO clienteDTO) throws Exception {
        validarCliente(clienteDTO, true);
        Cliente cliente = ClienteMapper.dtoToDomain(clienteDTO);
        TipoDocumento tipoDocumento = tipoDocumentoService.buscarTipoDocumentoPorId(
                clienteDTO.getTipoDocumentoId());
        cliente.setTipoDocumento(tipoDocumento);
        /*
        * ClienteDTO clienteDTOReturn;
        cliente = clienteRepository.save(cliente);
        clienteDTOReturn = ClienteMapper.domainToDto(cliente);
        return clienteDTOReturn;
        * */

        return ClienteMapper.domainToDto(clienteRepository.save(cliente));
    }

    @Override
    public ClienteDTO actualizar(ClienteDTO clienteDTO) throws Exception {
        validarCliente(clienteDTO, false);
        Cliente cliente = ClienteMapper.dtoToDomain(clienteDTO);
        TipoDocumento tipoDocumento = tipoDocumentoService.buscarTipoDocumentoPorId(
                clienteDTO.getTipoDocumentoId());
        cliente.setTipoDocumento(tipoDocumento);

        return ClienteMapper.domainToDto(clienteRepository.save(cliente));
    }

    @Override
    public CrearClienteResponse crearCliente(CrearClienteRequest crearClienteRequest) throws Exception {
        Cliente cliente = ClienteMapper.crearRequestToDomain(crearClienteRequest);
        TipoDocumento tipoDocumento = tipoDocumentoService.buscarTipoDocumentoPorId(
                crearClienteRequest.getTipoDocumentoId());

        boolean existePorTipoYDocumento = clienteRepository.existsByTipoDocumentoIdAndDocumento(
                crearClienteRequest.getTipoDocumentoId(), crearClienteRequest.getDocumento());

        if(existePorTipoYDocumento) throw new Exception(
                String.format(ClienteServiceMessages.EXISTE_POR_TIPO_DOCUMENTO_Y_DOCUMENTO,
                        tipoDocumento.getDescripcion(), crearClienteRequest.getDocumento())
        );

        cliente.setTipoDocumento(tipoDocumento);

        return ClienteMapper.crearDomainToResponse(cliente);
    }

    private void validarCliente(ClienteDTO clienteDTO, boolean esGuardado) throws Exception{
        if(!esGuardado) {
            ValidationsUtil.isNull(clienteDTO.getId(), ClienteServiceMessages.ID_REQUERIDO);
        }
        ValidationsUtil.isNull(clienteDTO, ClienteServiceMessages.CLIENTE_NULO);
        ValidationsUtil.stringIsNullOrBlank(clienteDTO.getNombres(), ClienteServiceMessages.NOMBRES_REQUERIDOS);
        ValidationsUtil.stringIsNullOrBlank(clienteDTO.getApellidos(), ClienteServiceMessages.APELLIDOS_REQUERIDOS);
        ValidationsUtil.stringIsNullOrBlank(clienteDTO.getDocumento(), ClienteServiceMessages.DOCUMENTO_REQUERIDO);
        ValidationsUtil.stringIsNullOrBlank(clienteDTO.getEstado(), ClienteServiceMessages.ESTADO_REQUERIDO);
        ValidationsUtil.lenghtString(clienteDTO.getEstado(), 1, ClienteServiceMessages.ESTADO_LENGHT);
        ValidationsUtil.integerIsNullOrLessZero(clienteDTO.getTipoDocumentoId(), ClienteServiceMessages.TIPO_DOCUMENTO_ID_REQUERIDO);
    }

}
