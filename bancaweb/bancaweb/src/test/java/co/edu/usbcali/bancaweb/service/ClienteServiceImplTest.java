package co.edu.usbcali.bancaweb.service;

import co.edu.usbcali.bancaweb.dto.ClienteDTO;
import co.edu.usbcali.bancaweb.model.Cliente;
import co.edu.usbcali.bancaweb.model.TipoDocumento;
import co.edu.usbcali.bancaweb.repository.ClienteRepository;
import co.edu.usbcali.bancaweb.repository.TipoDocumentoRepository;
import co.edu.usbcali.bancaweb.service.impl.ClienteServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class ClienteServiceImplTest {

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Mock
    private TipoDocumentoRepository tipoDocumentoRepository;

    @Mock
    ClienteRepository clienteRepository;


    @Test
    void buscarPorId() throws Exception {
        TipoDocumento tipoDocumento = TipoDocumento.builder().codigo(1).nombre("Cédula").build();
        Cliente cliente = Cliente.builder()
                .id(1)
                .tipoDocumento(tipoDocumento)
                .nombre("Prueba")
                .direccion("Calle Carrera")
                .telefono("55555")
                .mail("elmail@mail.com")
                .build();

        tipoDocumentoRepository.save(tipoDocumento);
        clienteRepository.save(cliente);

        // Mock de la respuesta del repositorio
        given(clienteRepository.existsById(1)).willReturn(true);
        given(clienteRepository.getReferenceById(cliente.getId())).willReturn(cliente);

        // Llamado al método a probar
        ClienteDTO clienteDTO = clienteService.buscarPorId(1);

        // Verificación del resultado esperado
        assertEquals(clienteDTO.getId(), 1);
    }


    @Test
    void buscarTodos() {
        TipoDocumento tipoDocumento = TipoDocumento.builder().codigo(1).nombre("Cédula").build();

        List<Cliente> clientesRetorno = Arrays.asList(Cliente.builder()
                .id(1)
                .tipoDocumento(tipoDocumento)
                .nombre("Prueba")
                .direccion("Calle Carrera")
                .telefono("55555")
                .mail("elmail@mail.com")
                .build(),
                Cliente.builder()
                        .id(2)
                        .tipoDocumento(tipoDocumento)
                        .nombre("Prueba2")
                        .direccion("Calle Carrera")
                        .telefono("55555")
                        .mail("elmail@mail.com")
                        .build());

        // Mock de la respuesta del repositorio
        given(clienteRepository.findAll()).willReturn(clientesRetorno);

        List<ClienteDTO> clientes = clienteService.buscarTodos();

        assertEquals(2, clientes.size());
        assertEquals("Prueba2", clientes.get(1).getNombre());

    }

    @Test
    void crearNuevoCliente() throws Exception {
        TipoDocumento tipoDocumento = TipoDocumento.builder().codigo(1).nombre("Cédula").build();
        ClienteDTO clienteDTO = ClienteDTO.builder()
                .id(1)
                .tipoDocumentoCodigo(1)
                .nombre("Prueba")
                .direccion("Calle Carrera")
                .telefono("55555")
                .mail("elmail@mail.com")
                .build();

       Cliente cliente = Cliente.builder()
               .id(1)
               .tipoDocumento(tipoDocumento)
               .nombre("Prueba")
               .direccion("Calle Carrera")
               .telefono("55555")
               .mail("elmail@mail.com")
               .build();

        given(tipoDocumentoRepository.existsById(tipoDocumento.getCodigo())).willReturn(true);
        given(tipoDocumentoRepository.getReferenceById(tipoDocumento.getCodigo())).willReturn(tipoDocumento);
        given(clienteRepository.save(cliente)).willReturn(cliente);
        ClienteDTO clienteGuardado = clienteService.crearNuevoCliente(clienteDTO);

        assertEquals(clienteDTO.getId(), clienteGuardado.getId());
        assertEquals(clienteDTO.getMail(), clienteGuardado.getMail());

    }



}
